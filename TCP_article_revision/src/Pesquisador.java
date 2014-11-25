import java.util.Collection;
import java.util.List;

public class Pesquisador {

	private int id;
	private String nome;
	private String afiliacao;
	private Collection<String> topicosInteresse;
	
	public Pesquisador(int id, String nome, String afiliacao, Collection<String> topicosInteresse) {
		super();
		this.id = id;
		this.nome = nome;
		this.afiliacao = afiliacao;
		this.topicosInteresse = topicosInteresse;
	}

	//getters and setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAfiliacao() {
		return afiliacao;
	}
	
	public void setAfiliacao(String afiliacao) {
		this.afiliacao = afiliacao;
	}
	
	public Collection<String> getTopicosInteresse() {
		return topicosInteresse;
	}
	
	public void setTopicosInteresse(List<String> topicosInteresse) {
		this.topicosInteresse = topicosInteresse;
	}
}
