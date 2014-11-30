import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Before;
import org.junit.Test;

public class ComiteServicoTests {
	public class TabelaResposta {
		Integer t_artigo;
		Integer t_revisor;
		Integer t_nota;
		public TabelaResposta(Integer artigo, Integer revisor, Integer nota) {
			super();
			this.t_artigo = artigo;
			this.t_revisor = revisor;
			this.t_nota = nota;
		}
	}
	
	List<TabelaResposta> tabelaResposta;
	
	
	@Before
	public void setUp() throws Exception {
		tabelaResposta = new ArrayList<>();
	
		tabelaResposta.add(new TabelaResposta(1, 8, 2));
		tabelaResposta.add(new TabelaResposta(1, 10, -99));
		tabelaResposta.add(new TabelaResposta(2, 1, 2));
		tabelaResposta.add(new TabelaResposta(2, 2, 3));
		tabelaResposta.add(new TabelaResposta(3, 4, -1));
		tabelaResposta.add(new TabelaResposta(3, 6, 1));
		tabelaResposta.add(new TabelaResposta(4, 1, 1));
		tabelaResposta.add(new TabelaResposta(4, 3, 0));
		tabelaResposta.add(new TabelaResposta(5, 4, -3));
		tabelaResposta.add(new TabelaResposta(5, 5, -3));
		tabelaResposta.add(new TabelaResposta(6, 3, -1));
		tabelaResposta.add(new TabelaResposta(6, 6, 0));
	}

	
	
	@Test
	public void test() {
		
		Database data = new Database(true);
		ComiteServico comite = new ComiteServico(data);
		
		List<String> conferencias = comite.getConferenciaSiglas();
		
		comite.alocaArtigos("FSE", 2);
		conferencias.remove(comite.getConferencia("FSE"));
		comite.alocaArtigos("SBES", 2);
		conferencias.remove(comite.getConferencia("SBES"));
		for (String string : comite.getLog()) {
			System.out.println(string);	
		}
		
		for (Artigo artigo : comite.getArtigosSobRevisao()) {
			for (Revisao revisao : artigo.getRevisoes()) {
				Boolean taNaLista = false;
				for (TabelaResposta tabela : tabelaResposta) {
					Boolean artigoIgual = tabela.t_artigo == revisao.getIdArtigo();
					Boolean revisorIgual = tabela.t_revisor == revisao.getIdPesquisador();
					if (artigoIgual && revisorIgual) {
						taNaLista = true;
					}
				}
				assertTrue("Artigo de ID:" + revisao.getIdArtigo() + " foi alocado erroneamente para pesquisador de ID:" + revisao.getIdPesquisador(), taNaLista);
			}
			comite.getTodosArtigos();
			
		}
	}
}
