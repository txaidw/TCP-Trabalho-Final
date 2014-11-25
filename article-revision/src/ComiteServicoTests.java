import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class ComiteServicoTests {
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void name() {
		System.out.println("ahahahah");
	}
	
	@Test
	public void test() {
		Database data = new Database(true);
		ComiteServico comite = new ComiteServico(data);
		
		List<String> conferencias = comite.getConferenciaSiglas();
		
		List<String> logss = new ArrayList<String>();
		while (!conferencias.isEmpty()) {
			comite.alocaArtigos(conferencias.get(0), 2);
			conferencias.remove(0);
			
			List<String> temp = new ArrayList<String>();
			temp = comite.getLog();
			Collections.sort(temp);
		 	logss.addAll(temp);
		}

		
		for (String string : logss) {
	 		System.out.println(string);	
		}
	}

}
