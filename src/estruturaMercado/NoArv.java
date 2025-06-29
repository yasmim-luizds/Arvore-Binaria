package estruturaMercado;


public class NoArv {
	private Produto info;
	private NoArv esq, dir;

	public NoArv(Produto produto){
		this.info = produto;
		this.esq = null;
		this.dir = null;
	}
	public NoArv getEsq(){
		return this.esq;
	}
	public NoArv getDir(){
		return this.dir;
	}
	public Produto getInfo(){
		return this.info;
	}
	public void setEsq(NoArv no){
		this.esq = no;
	}
	public void setDir(NoArv no){
		this.dir = no;
	}
	public void setInfo(Produto produto){
		this.info = produto;
	}

}
