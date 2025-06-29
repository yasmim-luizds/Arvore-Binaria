package estruturaMercado;


public class ArvoreBinariaPesquisa {
	private NoArv raiz;
	private int quantNos;

	public ArvoreBinariaPesquisa() {
		this.raiz = null;
	}

	public boolean eVazia() {
		return (this.raiz == null);
	}

	public NoArv getRaiz() {
		return this.raiz;
	}

	public int getQuantNos() {
		return this.quantNos;
	}

	
    public boolean inserirProduto(Produto novo) {
        if (this.raiz == null) {
            this.raiz = new NoArv(novo);
            return true;
        }
        if (pesquisarNomeCodigo(novo.getNome(), novo.getCodBarras()) != null) {
            return false;
        }
        NoArv resultado = inserirProduto(this.raiz, novo);
        return resultado != null;
    }

  
    private NoArv inserirProduto(NoArv atual, Produto novo) {
        int comparacao = novo.getNome().compareToIgnoreCase(atual.getInfo().getNome());

        if (comparacao < 0) {
            if (atual.getEsq() == null) {
                atual.setEsq(new NoArv(novo));
                return atual.getEsq();
            } else {
                return inserirProduto(atual.getEsq(), novo);
            }
        } else if (comparacao > 0) {
            if (atual.getDir() == null) {
                atual.setDir(new NoArv(novo));
                return atual.getDir();
            } else {
                return inserirProduto(atual.getDir(), novo);
            }
        } else {
            return null;
        }
    }

    
    
    public NoArv pesquisarNomeCodigo(String nome, int codBarras) {
        return pesquisarNomeCodigo(this.raiz, nome, codBarras);
    }

    private NoArv pesquisarNomeCodigo(NoArv atual, String nome, int codBarras) {
        if (atual == null) {
            return null;
        }

        if (atual.getInfo().getNome().equalsIgnoreCase(nome) || atual.getInfo().getCodBarras() == codBarras) {
            return atual;
        }

        int comparacao = nome.compareToIgnoreCase(atual.getInfo().getNome());

        if (comparacao < 0) {
            return pesquisarNomeCodigo(atual.getEsq(), nome, codBarras);
        } else {
            return pesquisarNomeCodigo(atual.getDir(), nome, codBarras);
        }
    }
    
  
    public NoArv buscarProdutoPorNome(String nome) {
        return buscarProdutoPorNome(this.raiz, nome);
    }

    private NoArv buscarProdutoPorNome(NoArv atual, String nome) {
        if (atual == null) {
            return null;
        }

        if (atual.getInfo().getNome().equalsIgnoreCase(nome)) {
            return atual;
        }

        int comparacao = nome.compareToIgnoreCase(atual.getInfo().getNome());

        if (comparacao < 0) {
            return buscarProdutoPorNome(atual.getEsq(), nome);
        } else {
            return buscarProdutoPorNome(atual.getDir(), nome);
        }
    }


 
    public boolean removerProduto(String nome) {
        if (buscarProdutoPorNome(nome) != null) {
            this.raiz = removerProduto(this.raiz, nome);
            return true;
        } else {
            return false;
        }
    }

    private NoArv removerProduto(NoArv atual, String nome) {
        if (atual == null) {
            return null;
        }

        int comparacao = nome.compareToIgnoreCase(atual.getInfo().getNome());

        if (comparacao < 0) {
            atual.setEsq(removerProduto(atual.getEsq(), nome));
        } else if (comparacao > 0) {
            atual.setDir(removerProduto(atual.getDir(), nome));
        } else {
            if (atual.getEsq() == null) {
                return atual.getDir();
            } else if (atual.getDir() == null) {
                return atual.getEsq();
            } else {
                NoArv substituto = encontrarMinimo(atual.getDir());
                atual.setInfo(substituto.getInfo());
                atual.setDir(removerProduto(atual.getDir(), substituto.getInfo().getNome()));
            }
        }

        return atual;
    }
    
  
    public boolean atualizarProduto(String nome, double novoPreco, int novoEstoque, String novaCategoria) {
        NoArv produtoNo = buscarProdutoPorNome(this.raiz, nome);

        if (produtoNo == null) {
            return false;
        }

        produtoNo.getInfo().setPreco(novoPreco);
        produtoNo.getInfo().setEstoque(novoEstoque);
        produtoNo.getInfo().setCategoria(novaCategoria);
        return true;
    }
    
	
    private NoArv encontrarMinimo(NoArv atual) {
        while (atual.getEsq() != null) {
            atual = atual.getEsq();
        }
        return atual;
    }

	
	public Produto[] listarPorFaixaDePreco(double min, double max) {
		int total = contarNos(raiz);
		Produto[] resultado = new Produto[total];
		int[] indice = new int[1];
		listarFaixaPreco(raiz, min, max, resultado, indice); 
		Produto[] filtrado = new Produto[indice[0]];
		for (int i = 0; i < indice[0]; i++) {
			filtrado[i] = resultado[i];
		}
		return filtrado;
	}

	private void listarFaixaPreco(NoArv no, double min, double max, Produto[] vetor, int[] indice) {
		if (no != null) {
			if (no.getInfo().getPreco() >= min && no.getInfo().getPreco() <= max) {
				vetor[indice[0]] = no.getInfo();
				indice[0]++;
			}
			listarFaixaPreco(no.getEsq(), min, max, vetor, indice);
			listarFaixaPreco(no.getDir(), min, max, vetor, indice);
		}
	}

	
	private int contarNos(NoArv no) {
		if (no == null) {
			return 0;
		}
		return 1 + contarNos(no.getEsq()) + contarNos(no.getDir());
	}

	
	public Produto produtoMaisBarato() {
		return buscarMaisBarato(raiz, null);
	}

	private Produto buscarMaisBarato(NoArv no, Produto atual) {
		if (no == null) {
			return atual;
		}
		if (atual == null || no.getInfo().getPreco() < atual.getPreco()) {
			atual = no.getInfo();
		}
		atual = buscarMaisBarato(no.getEsq(), atual);
		atual = buscarMaisBarato(no.getDir(), atual);
		return atual;
	}

	
	public Produto produtoMaisCaro() {
		return buscarMaisCaro(raiz, null);
	}

	private Produto buscarMaisCaro(NoArv no, Produto atual) {
		if (no == null) {
			return atual;
		}
		if (atual == null || no.getInfo().getPreco() > atual.getPreco()) {
			atual = no.getInfo();
		}
		atual = buscarMaisCaro(no.getEsq(), atual);
		atual = buscarMaisCaro(no.getDir(), atual);
		return atual;
	}

	
	public int contarPorCategoria(String categoria) {
		return contarCategoria(raiz, categoria);
	}

	private int contarCategoria(NoArv atual, String categoria) {
		if (atual == null) {
			return 0;
		}
		int cont = 0;
		if (atual.getInfo().getCategoria().equalsIgnoreCase(categoria)) {
			cont = 1;
		}
		cont += contarCategoria(atual.getEsq(), categoria);
		cont += contarCategoria(atual.getDir(), categoria);
		return cont;
	}


}
