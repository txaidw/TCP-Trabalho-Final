import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comite {

	private final Database database;
	private final List<String> log;
	
	public Comite(Database database) {
		this.database = database;
		this.log = new ArrayList<>();
	}

	public void alocaArtigos(String conferencia, int numRevisores) {
		
		this.addLog("Iniciando alocação...");
		
		List<Pesquisador> membros = this.database.getConferencia(conferencia).getMembros();
	
		Map<Integer, Integer> artigosAlocadosPorPesquisador = new HashMap<Integer, Integer>();
		for (Pesquisador pesquisador : membros) {
			artigosAlocadosPorPesquisador.put(pesquisador.getId(), 0);
		}
	
		for (int i = 0; i < numRevisores; i++) {
			List<Artigo> artigos = this.database.getArtigos(conferencia);
			Collections.sort(artigos);
			
			while (!artigos.isEmpty()) {

				Artigo artigoComMenorId = artigos.get(0);

				List<Pesquisador> membrosSelecionados = this.selecionarMembros(
						membros, artigoComMenorId);
				List<Pesquisador> membrosSelecionadosOrdenado = this
						.ordenaMembrosSelecionados(membrosSelecionados,
								artigosAlocadosPorPesquisador);

				Pesquisador revisor = membrosSelecionadosOrdenado.get(0);

				this.database.save(new Revisao(artigoComMenorId.getId(),
						revisor.getId()));
				
				this.addLog("Artigo id " + artigoComMenorId.getId() + " alocado ao revisor id " + revisor.getId());
				
				Integer alocacoes = artigosAlocadosPorPesquisador.get(revisor.getId());
				artigosAlocadosPorPesquisador.put(revisor.getId(), ++alocacoes);
				
				artigos.remove(0);

			}
		}
		
		this.addLog("Fim da alocação");
	}

	public void atribuiNota(Revisao revisao) {

	}

	public void selecionaArtigos(String conferencia) {

	}

	public void alocacao() {
		
	}

	public void atribuicao() {

	}
	
	public void selecao() {

	}
	
	// métodos privados
	private List<Pesquisador> selecionarMembros(List<Pesquisador> candidatos, Artigo artigo) {
		
		List<Pesquisador> membrosSelecionados = new ArrayList<>();
		
		for (Pesquisador pesquisador : candidatos) {
			if (!(pesquisador.getNome().equals(artigo.getAutor().getNome()) || pesquisador.getAfiliacao().equals(artigo.getAutor().getAfiliacao()) || !pesquisador.getTopicosInteresse().contains(artigo.getTopicoPesquisa()))) {
				membrosSelecionados.add(pesquisador);
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
