import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComiteServico {

	private final Database database;
	private final List<String> log;
	
	public ComiteServico(Database database) {
		this.database = database;
		this.log = new ArrayList<>();
	}

	public void alocaArtigos(String siglaConferencia, int numRevisores) {
		
		this.log.clear();
		this.addLog("Iniciando alocaçãoo de " + numRevisores + " revisores para conferência: " + siglaConferencia  + "\n");
		
		Conferencia conferencia = this.database.getConferencia(siglaConferencia);
		List<Pesquisador> membros = conferencia.getMembros();
	
		Map<Integer, Integer> artigosAlocadosPorPesquisador = new HashMap<Integer, Integer>();
		for (Pesquisador pesquisador : membros) {
			artigosAlocadosPorPesquisador.put(pesquisador.getId(), 0);
		}
	
		for (int i = 0; i < numRevisores; i++) {
			List<Artigo> artigos = this.database.getArtigos(siglaConferencia);
			Collections.sort(artigos);
			
			while (!artigos.isEmpty()) {

				Artigo artigoComMenorId = artigos.get(0);

				List<Pesquisador> membrosSelecionados = this.selecionarMembros(
						membros, artigoComMenorId);
				
				List<Pesquisador> membrosSelecionadosOrdenado = this
						.ordenaMembrosSelecionados(membrosSelecionados,
								artigosAlocadosPorPesquisador);

				
				Pesquisador revisor = membrosSelecionadosOrdenado.get(0);
				
				
				
				artigoComMenorId.addRevisao(new Revisao( artigoComMenorId.getId(), revisor.getId()));
				
				this.addLog("Artigo id " + artigoComMenorId.getId() + " alocado ao revisor id " + revisor.getId() + "\n");
				
				Integer alocacoes = artigosAlocadosPorPesquisador.get(revisor.getId());
				artigosAlocadosPorPesquisador.put(revisor.getId(), ++alocacoes);
				
				artigos.remove(0);
			}
		}
		conferencia.setSobRevisao(true);
		this.addLog("Fim da alocação" + "\n" + "\n");
	}


	public void atribuiNota(Revisao revisao) {

	}

	public void selecionaArtigos(String conferencia) {

	}
	
	// getters
	public List<String> getConferenciaSiglas() {
		List<String> siglas = new ArrayList<>();
		for (Conferencia conferencia : database.getAllConferencias())
			siglas.add(conferencia.getSigla());
		return siglas;
	}
	
	public List<String> getConferenciaSiglasNaoAlocadas() {
		List<String> siglas = new ArrayList<>();
		for (Conferencia conferencia : database.getAllConferencias())
			if (!conferencia.isSobRevisao())
				siglas.add(conferencia.getSigla());
		return siglas;
	}
	
	public List<Artigo> getArtigosSobRevisao() {
		Collection<Artigo> artigos = database.getAllArtigos();
		List<Artigo> artigosSobRevisao = new ArrayList<Artigo>();
		for (Artigo artigo : artigos) {
			if (!artigo.getRevisoes().isEmpty()) {
				artigosSobRevisao.add(artigo);
			}
		}
		Collections.sort(artigosSobRevisao, new Comparator<Artigo>() {
			 @Override
	         public int compare(Artigo a1, Artigo a2) {
				 return a1.getTitulo().compareTo(a2.getTitulo());
			 }
		});
		return artigosSobRevisao;
	}
	
	public Pesquisador getPesquisador(int id) {
		return database.getPesquisador(id);
	}
	
	// métodos privados
	private List<Pesquisador> selecionarMembros(List<Pesquisador> candidatos, Artigo artigo) {
		
		List<Pesquisador> membrosSelecionados = new ArrayList<>();
		System.out.println("ID: "+artigo.getId());
		for (Pesquisador pesquisador : candidatos) {
			if (!(pesquisador.getId() == artigo.getAutor().getId()) ) {
				
				System.out.println("PES: "+pesquisador.getAfiliacao());
				System.out.println("AFI: "+artigo.getAutor().getAfiliacao());

				if (!(pesquisador.getAfiliacao().equals(artigo.getAutor().getAfiliacao()))) {
					if (pesquisador.getTopicosInteresse().contains(artigo.getTopicoPesquisa())) {
						
						Boolean ehIgual = false;
						for (Revisao rev : artigo.getRevisoes()) {
							if (pesquisador.getId() == rev.getIdPesquisador()) {
								ehIgual = true;
							}
						}
						if (!ehIgual) {
							membrosSelecionados.add(pesquisador);	
						}
					}
				}
			}
		}
		
		return membrosSelecionados;
	}
	
	private List<Pesquisador> ordenaMembrosSelecionados(List<Pesquisador> membrosSelecionados, final Map<Integer, Integer> artigosAlocadosPorPesquisador) {
		
		Collections.sort(membrosSelecionados, new Comparator<Pesquisador>() {
			 @Override
	         public int compare(Pesquisador p1, Pesquisador p2) {
				 int primeiraComparacao = Integer.compare(artigosAlocadosPorPesquisador.get(p1.getId()), artigosAlocadosPorPesquisador.get(p2.getId())); 
				 if (primeiraComparacao == 0) {
					return Integer.compare(p1.getId(), p2.getId());
				} else {
					return primeiraComparacao;
				}
			 }
		});
		return membrosSelecionados;
	}
	
	private void addLog(String logMessage) {
		this.log.add(logMessage);
	}
	
	public List<String> getLog() {
		return log;
	}
}
