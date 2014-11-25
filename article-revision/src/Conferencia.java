import java.util.List;

public class Conferencia {

	private String sigla;
	private List<Artigo> artigos;
	private List<Pesquisador> membros;
	private boolean sobRevisao;

	public Conferencia(String sigla, List<Pesquisador> membros) {
		super();
		this.sigla = sigla;
		this.membros = membros;
		this.setSobRevisao(false);
	}

	// getters and setters
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public List<Artigo> getArtigos() {
		return artigos;
	}
	
	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}
	
	public List<Pesquisador> getMembros() {
		return membros;
	}
	
	public void setMembros(List<Pesquisador> membros) {
		this.membros = membros;
	}

	public boolean isSobRevisao() {
		return sobRevisao;
	}

	public void setSobRevisao(boolean sobRevisao) {
		this.sobRevisao = sobRevisao;
	}
}
