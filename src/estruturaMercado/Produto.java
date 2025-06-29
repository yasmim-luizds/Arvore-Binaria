package estruturaMercado;

public class Produto {
	private String nome;
	private int codBarras;
	private double preco;
	private int estoque;
	private String categoria;
	
	public Produto(String nome, int codBarras, double preco, int estoque, String categoria) {
		super();
		this.nome = nome;
		this.codBarras = codBarras;
		this.preco = preco;
		this.estoque = estoque;
		this.categoria = categoria;
	}

	public Produto() {
		this.nome = "";
		this.codBarras = 0;
		this.preco = 0.0;
		this.estoque = 0;
		this.categoria = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(int codBarras) {
		this.codBarras = codBarras;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	

}
