public class Revisao {

	private int idArtigo;
	private int idPesquisador;
	private float nota;
	private boolean avaliado;
	
	public Revisao(int idArtigo, int idPesquisador) {
		this.idArtigo = idArtigo;
		this.idPesquisador = idPesquisador;
		this.avaliado = false;
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
	
	public float getNota() {
		return nota;
	}
	
	public void setNota(float nota) {
		this.nota = nota;
		this.avaliado = true;
	}

	public boolean isAvaliado() {
		return avaliado;
	}

	public void setAvaliado(boolean avaliado) {
		this.avaliado = avaliado;
	}
}