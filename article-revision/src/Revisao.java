public class Revisao {

	private int idArtigo;
	private int idPesquisador;
	private int nota;
	
	public Revisao(int idArtigo, int idPesquisador) {
		this.idArtigo = idArtigo;
		this.idPesquisador = idPesquisador;
	}

	// getters and setters
	public int getIdArtigo() {
		return idArtigo;
	}
	
	public void setIdArtigo(int idArtigo) {
		this.idArtigo = idArtigo;
	}
	
	public int getIdPesquisador() {
		return idPesquisador;
	}
	
	public void setIdPesquisador(int idPesquisador) {
		this.idPesquisador = idPesquisador;
	}
	
	public int getNota() {
		return nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}
}