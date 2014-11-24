
public class MainAplication {

	public static void main(String[] args) {
		Database database = new Database(true);
		Comite comite = new Comite(database);
		
		comite.alocaArtigos("FSE", 2);
		for (String string : comite.getLog()) {
			System.out.println(string);
		}
	}

}
