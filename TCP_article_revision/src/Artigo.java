public class Artigo implements Comparable<Artigo> {
	private int id;
	private String titulo;
	private Pesquisador autor;
	private Conferencia conferencia;
	private String topicoPesquisa;
	
	public Artigo(int id, String titulo, Pesquisador autor,
			Conferencia conferencia, String topicoPesquisa) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.conferencia = conferencia;
		this.topicoPesquisa = topicoPesquisa;
	}

	// getters and setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Pesquisador getAutor() {
		return autor;
	}
	
	public void setAutor(Pesquisador autor) {
		this.autor = autor;
	}
	public Conferencia getConferencia() {
		return conferencia;
	}
	
	public void setConferencia(Conferencia conferencia) {
		this.conferencia = conferencia;
	}
	
	public String getTopicoPesquisa() {
		return topicoPesquisa;
	}
	
	public void setTopicoPesquisa(String topicoPesquisa) {
		this.topicoPesquisa = topicoPesquisa;
	}

	@Override
	public int compareTo(Artigo artigo) {
		Integer artigo1Id = new Integer(this.id);
		Integer artigo2Id = new Integer(artigo.getId());
		return artigo1Id.compareTo(artigo2Id);
	}
	
}
