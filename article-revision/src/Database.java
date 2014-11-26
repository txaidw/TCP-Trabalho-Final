import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

	private final Map<Integer, Pesquisador> pesquisadores;
	private final Map<String, Conferencia> conferencias;
	private final Map<Integer, Artigo> artigos;
	
	public Database() {
		this(true);
	}

	public Database(boolean initData) {
		pesquisadores = new HashMap<>();
		conferencias = new HashMap<>();
		artigos = new HashMap<>();
		
		if (initData) {
			initData();
		}
	}
	
	private void initData() {
		try {
			
			int id;
			
			// pesquisadores
			id = 0;
			
			Collection<String> topicosInteresse1 = new ArrayList<>();
			topicosInteresse1.add("Software Product Lines");
			topicosInteresse1.add("Software Reuse");
			topicosInteresse1.add("Modularity");
			Pesquisador p1 = new Pesquisador(++id, "Joao", "UFRGS", topicosInteresse1);
			save(p1);
			
			Collection<String> topicosInteresse2 = new ArrayList<>();
			topicosInteresse2.add("Software Architecture");
			topicosInteresse2.add("Modularity");
			topicosInteresse2.add("Software Reuse");
			Pesquisador p2 = new Pesquisador(++id, "Ana", "USP", topicosInteresse2);
			save(p2);
			
			Collection<String> topicosInteresse3 = new ArrayList<>();
			topicosInteresse3.add("Software Product Lines");
			topicosInteresse3.add("Software Testing");
			Pesquisador p3 = new Pesquisador(++id, "Manoel", "UFRGS", topicosInteresse3);
			save(p3);
			
			Collection<String> topicosInteresse4 = new ArrayList<>();
			topicosInteresse4.add("Software Product Lines");
			topicosInteresse4.add("Software Reuse");
			topicosInteresse4.add("Software Architecture");
			topicosInteresse4.add("Aspect-oriented Programming");
			Pesquisador p4 = new Pesquisador(++id, "Joana", "UFRJ", topicosInteresse4);
			save(p4);
			
			Collection<String> topicosInteresse5 = new ArrayList<>();
			topicosInteresse5.add("Software Architecture");
			topicosInteresse5.add("Modularity");
			topicosInteresse5.add("Software Testing");
			Pesquisador p5 = new Pesquisador(++id, "Miguel", "UFRGS", topicosInteresse5);
			save(p5);
			
			Collection<String> topicosInteresse6 = new ArrayList<>();
			topicosInteresse6.add("Software Reuse");
			topicosInteresse6.add("Software Testing");
			topicosInteresse6.add("Aspect-oriented Programming");
			Pesquisador p6 = new Pesquisador(++id, "Beatriz", "UFRJ", topicosInteresse6);
			save(p6);
			
			Collection<String> topicosInteresse7 = new ArrayList<>();
			topicosInteresse7.add("Aspect-oriented Programming");
			topicosInteresse7.add("Modularity");
			topicosInteresse7.add("Software Reuse");
			Pesquisador p7 = new Pesquisador(++id, "Suzana", "UFRGS", topicosInteresse7);
			save(p7);
			
			Collection<String> topicosInteresse8 = new ArrayList<>();
			topicosInteresse8.add("Modularity");
			topicosInteresse8.add("Software Reuse");
			topicosInteresse8.add("Software Quality");
			topicosInteresse8.add("Software Product Lines");
			Pesquisador p8 = new Pesquisador(++id, "Natasha", "UFRJ", topicosInteresse8);
			save(p8);
			
			Collection<String> topicosInteresse9 = new ArrayList<>();
			topicosInteresse9.add("Aspect-oriented Programming");
			topicosInteresse9.add("Software Architecture");
			Pesquisador p9 = new Pesquisador(++id, "Pedro", "USP", topicosInteresse9);
			save(p9);
			
			Collection<String> topicosInteresse10 = new ArrayList<>();
			topicosInteresse10.add("Software Reuse");
			topicosInteresse10.add("Modularity");
			topicosInteresse10.add("Software Testing");
			Pesquisador p10 = new Pesquisador(++id, "Carlos", "USP", topicosInteresse10);
			save(p10);
			
			// conferências
			List<Pesquisador> m1 = new ArrayList<>();
			m1.add(p1); m1.add(p2); m1.add(p3); m1.add(p4); m1.add(p5); m1.add(p6); m1.add(p7);
			Conferencia c1 = new Conferencia("ICSE", m1);
			save(c1);
			
			List<Pesquisador> m2 = new ArrayList<>();
			m2.add(p1); m2.add(p2); m2.add(p3); m2.add(p4); m2.add(p5); m2.add(p6); m2.add(p7);
			Conferencia c2 = new Conferencia("FSE", m2);
			save(c2);
			
			List<Pesquisador> m3 = new ArrayList<>();
			m3.add(p4); m3.add(p5); m3.add(p6); m3.add(p7); m3.add(p8); m3.add(p9); m3.add(p10);
			Conferencia c3 = new Conferencia("SBES", m3);
			save(c3);
			
			// artigos
			id = 0;

			save(new Artigo(++id, "Coupling and Cohesion", p1, c3, "Modularity"));
			save(new Artigo(++id, "Design Patterns", p6, c2, "Software Reuse"));
			save(new Artigo(++id, "AspectJ", p7, c2, "Aspect-oriented Programming"));
			save(new Artigo(++id, "Feature Model", p8, c2, "Software Product Lines"));
			save(new Artigo(++id, "Architecture Recovery", p9, c2, "Software Architecture"));
			save(new Artigo(++id, "Funcional Testing", p10, c2, "Software Testing"));
			save(new Artigo(++id, "COTs", p6, c1, "Software Reuse"));
			save(new Artigo(++id, "Pointcut", p7, c1, "Aspect-oriented Programming"));
			save(new Artigo(++id, "Product Derivation", p8, c1, "Software Product Lines"));
			save(new Artigo(++id, "Architecture Comformance", p9, c1, "Software Architecture"));
			save(new Artigo(++id, "Structural Testing", p10, c1, "Software Testing"));
			
			syncConferencias();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// save
	public void save(Pesquisador pesquisador) {
		this.pesquisadores.put(pesquisador.getId(), pesquisador);
	}

	public void save(Conferencia conferencia) {
		this.conferencias.put(conferencia.getSigla(), conferencia);
	}

	public void save(Artigo artigo) {
		this.artigos.put(artigo.getId(), artigo);
	}
	
	// setters and getters
	public Conferencia getConferencia(String conferencia) {
		return  conferencias.get(conferencia);
	}
	
	public Collection<Conferencia> getAllConferencias() {
		return conferencias.values();
	}
	
	public Pesquisador getPesquisador(int id) {
		return pesquisadores.get(id);
	}

	public Collection<Pesquisador> getAllPesquisadores() {
		return pesquisadores.values();
	}

	public Collection<Artigo> getAllArtigos() {
		return artigos.values();
	}
	
	public Artigo getArtigo(int idArtigo) {
		return artigos.get(idArtigo);
	}
	
	public List<Artigo> getArtigos(String conferencia) {
		Collection<Artigo> artigos = this.getAllArtigos();
		List<Artigo> artigosDaConferencia = new ArrayList<>();
		for (Artigo artigo : artigos) {
			if (artigo.getConferencia().getSigla().equals(conferencia)) {
				artigosDaConferencia.add(artigo);
			}
		}
		return artigosDaConferencia;
	}
	
	private void syncConferencias() {
		Collection<Artigo> artigos = getAllArtigos();
		Collection<Conferencia> conferencias = getAllConferencias();
		
		for (Conferencia conferencia : conferencias)
			for (Artigo artigo : artigos) {
				if (artigo.getConferencia().getSigla().equals(conferencia.getSigla())) {
					conferencia.getArtigos().add(artigo);	
				}
		}
	}
}
