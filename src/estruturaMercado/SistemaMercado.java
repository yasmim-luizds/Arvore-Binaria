package estruturaMercado;

import java.util.Scanner;

public class SistemaMercado {
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Seja bem-vindo ao SuperMercado VillaCardo!");

		System.out.println("Insira o login: ");
		String login = teclado.next();
		teclado.nextLine();

		System.out.println("Insira a senha: ");
		String senha = teclado.nextLine();

		if (!login.equals("123456") || !senha.equals("admin")) {
			System.out.println("Acesso negado! Login ou senha incorretos. Tente novamente.");
			System.out.println("Insira o login: ");
			login = teclado.next();
			teclado.nextLine();
			System.out.println("Insira a senha: ");
			senha = teclado.nextLine();
		}

		if (login.equals("123456") && senha.equals("admin")) {
			ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
			int opcao;

			do {
				System.out.println("\n--- Menu ---");
				System.out.println("1 - Cadastrar Produto");
				System.out.println("2 - Consultar Produto");
				System.out.println("3 - Excluir Produto");
				System.out.println("4 - Atualizar Cadastro de Produto");
				System.out.println("5 - Contagem por Categoria");
				System.out.println("6 - Listar Produtos por Faixa de Preço");
				System.out.println("7 - Mostrar Produto mais barato e mais caro");
				System.out.println("8 - Sair");
				System.out.print("Escolha uma opção: ");
				opcao = teclado.nextInt();
				teclado.nextLine();

				switch (opcao) {
				case 1:
				    System.out.print("Nome do produto: ");
				    String nome = teclado.nextLine().trim();

				    System.out.print("Código de Barras (6 dígitos ou mais): ");
				    int cod = teclado.nextInt();
				    if (cod < 100000 ) {
				        System.out.println("Erro: Código de barras inválido. Deve ser um número com 6 dígitos ou mais.");
				        teclado.nextLine();
				        break;
				    }

				    System.out.print("Preço: ");
				    double preco = teclado.nextDouble();
				    if (preco <= 0) {
				        System.out.println("Erro: O preço deve ser maior que zero.");
				        teclado.nextLine();
				        break;
				    }

				    System.out.print("Estoque: ");
				    int estoque = teclado.nextInt();
				    if (estoque < 0) {
				        System.out.println("Erro: O estoque não pode ser negativo.");
				        teclado.nextLine();
				        break;
				    }

				    teclado.nextLine();

				    System.out.println("Opções de Categoria: ");
				    System.out.println("- Hortifruti\n- Açougue\n- Padaria\n- Mercearia\n- Limpeza\n- Frios e bebidas");
				    String categoria = teclado.nextLine().trim();

				    if (
				        !categoria.equalsIgnoreCase("Hortifruti") &&
				        !categoria.equalsIgnoreCase("Açougue") &&
				        !categoria.equalsIgnoreCase("Padaria") &&
				        !categoria.equalsIgnoreCase("Mercearia") &&
				        !categoria.equalsIgnoreCase("Limpeza") &&
				        !categoria.equalsIgnoreCase("Frios e bebidas")
				    ) {
				        System.out.println("Erro: Categoria inválida. Digite novamente:");
				        categoria = teclado.nextLine();
				    }

				    Produto novo = new Produto(nome, cod, preco, estoque, categoria);
				    if (arvore.inserirProduto(novo)) {
				        System.out.println("Produto '" + nome + "' cadastrado com sucesso!");
				    } else {
				        System.out.println("Erro: Produto com nome ou código já existe.");
				    }
				    break;


					case 2:
						System.out.print("Digite o nome do produto: ");
						String nomeConsulta = teclado.nextLine();
						NoArv encontrado = arvore.buscarProdutoPorNome(nomeConsulta);
						if (encontrado != null) {
							Produto produto = encontrado.getInfo();
							System.out.println("Nome: " + produto.getNome());
							System.out.println("Código: " + produto.getCodBarras());
							System.out.println("Preço: " + produto.getPreco());
							System.out.println("Estoque: " + produto.getEstoque());
							System.out.println("Categoria: " + produto.getCategoria());
						} else {
							System.out.println("Produto não encontrado.");
						}
						break;

					case 3:
						System.out.print("Nome do produto a remover: ");
						String nomeRemover = teclado.nextLine();
						if (arvore.removerProduto(nomeRemover)) {
							System.out.println("Produto removido.");
						} else {
							System.out.println("Produto não encontrado.");
						}
						break;

					case 4:
						System.out.print("Nome do produto para atualizar: ");
						String nomeAtualizar = teclado.nextLine();
						System.out.print("Novo preço: ");
						double novoPreco = teclado.nextDouble();
						if (novoPreco <= 0) {
					        System.out.println("Erro: O preço deve ser maior que zero.");
					        teclado.nextLine();
					        break;
					    }
						
						System.out.print("Novo estoque: ");
						int novoEstoque = teclado.nextInt();
						teclado.nextLine();
						 if (novoEstoque < 0) {
						        System.out.println("Erro: O estoque não pode ser negativo.");
						        teclado.nextLine();
						        break;
						    }

						System.out.println("Nova categoria:");
						System.out.println("- Hortifruti\n- Açougue\n- Padaria\n- Mercearia\n- Limpeza\n- Frios e bebidas");
						String novaCategoria = teclado.nextLine();

						while (
							!novaCategoria.equalsIgnoreCase("Hortifruti") &&
							!novaCategoria.equalsIgnoreCase("Açougue") &&
							!novaCategoria.equalsIgnoreCase("Padaria") &&
							!novaCategoria.equalsIgnoreCase("Mercearia") &&
							!novaCategoria.equalsIgnoreCase("Limpeza") &&
							!novaCategoria.equalsIgnoreCase("Frios e bebidas")
						) {
							System.out.println("Erro: Categoria inválida. Digite novamente:");
							novaCategoria = teclado.nextLine();
						}

						if (arvore.atualizarProduto(nomeAtualizar, novoPreco, novoEstoque, novaCategoria)) {
							System.out.println("Produto" + nomeAtualizar + " atualizado.");
						} else {
							System.out.println("Produto não encontrado.");
						}
						break;

					case 5:
						System.out.print("Digite a categoria: ");
						String categ = teclado.nextLine();
						int contagem = arvore.contarPorCategoria(categ);
						if (contagem > 0) {
							System.out.println("Total de produtos na categoria '" + categ + "': " + contagem);
						} else {
							System.out.println("Nenhum produto encontrado nessa categoria.");
						}
						break;

					case 6:
						System.out.print("Preço mínimo: ");
						double min = teclado.nextDouble();
						System.out.print("Preço máximo: ");
						double max = teclado.nextDouble();
						Produto[] faixa = arvore.listarPorFaixaDePreco(min, max);
						if (faixa.length > 0) {
							System.out.println("Produtos na faixa de preço:");
							for (Produto produto : faixa) {
								System.out.println("- " + produto.getNome() + " | R$ " + produto.getPreco());
							}
						} else {
							System.out.println("Nenhum produto encontrado nessa faixa de preço.");
						}
						break;

					case 7:
						Produto maisBarato = arvore.produtoMaisBarato();
						Produto maisCaro = arvore.produtoMaisCaro();
						if (maisBarato != null && maisCaro != null) {
							System.out.println("Produto mais barato: " + maisBarato.getNome() + " - R$ " + maisBarato.getPreco());
							System.out.println("Produto mais caro: " + maisCaro.getNome() + " - R$ " + maisCaro.getPreco());
						} else {
							System.out.println("Nenhum produto cadastrado.");
						}
						break;

					case 8:
						System.out.println("Saindo do sistema...");
						break;

					default:
						System.out.println("Opção inválida.");
				}

			} while (opcao != 8);
		}
	}
}
