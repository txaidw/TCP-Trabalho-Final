import java.util.List;

public class Database {

	private final Map<int, int> currentAccounts;
	private final Map<String, Employee> employees;
	private final Log log;
	private final Map<Long, OperationLocation> operationLocations;

	public Database() {
		this(true);
	}

	public Database(boolean initData) {
		this.log = LogFactory.getLog(getClass());
		this.operationLocations = new HashMap<>();
		this.employees = new HashMap<>();
		this.currentAccounts = new HashMap<>();
		if (initData) {
			initData();
		}
	}

	
	
	
	private void initData() {
		try {
			// Operation Location
			int olId = 0;
			Branch b1 = new Branch(++olId, "Campus Vale");
			save(b1);
			Branch b2 = new Branch(++olId, "Centro");
			save(b2);
			ATM atm1 = new ATM(++olId);
			save(atm1);
			ATM atm2 = new ATM(++olId);
			save(atm2);
			ATM atm3 = new ATM(++olId);
			save(atm3);

			// Employee
			Employee employee = new Employee("Ingrid", "Nunes", "ingrid",
					"123", new Date());
			save(employee);

			// Current Accounts
			Client client1 = new Client("Ingrid", "Nunes", 1234567890, "123",
					new Date());
			CurrentAccount ca1 = new CurrentAccount(b1, 1l, client1, 300);
			save(ca1);
			Client client2 = new Client("Joao", "Silva", 1234567890, "123",
					new Date());
			CurrentAccount ca2 = new CurrentAccount(b2, 2l, client2, 200);
			save(ca2);
			Client client3 = new Client("Richer", "Rich", 1234567890, "123",
					new Date());
			CurrentAccount ca3 = new CurrentAccount(b2, 3l, client3, 10000);
			save(ca3);

			// Transactions
			Random r = new Random(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			for (int i = 0; i < 8; i++) {
				changeDate(
						ca1.deposit(b1, r.nextInt(10000), r.nextDouble() * 150),
						r, cal);
				changeDate(ca1.withdrawal(atm1, r.nextDouble() * 100), r, cal);
				changeDate(ca1.transfer(atm2, ca2, r.nextDouble() * 100), r,
						cal);

				changeDate(
						ca2.deposit(b2, r.nextInt(10000), r.nextDouble() * 150),
						r, cal);
				changeDate(ca2.withdrawal(atm2, r.nextDouble() * 100), r, cal);
				changeDate(ca2.transfer(atm3, ca1, r.nextDouble() * 100), r,
						cal);

				cal.add(Calendar.MONTH, -1);
			}
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	public List<Conferencia> getAllConferencias() {
		return null;
	}

	public List<Pesquisador> getAllPesquisadores() {
		return null;
	}

	public List<Artigo> getAllArtigos() {
		return null;
	}

	public List<Revisao> getAllRevisoes() {
		return null;
	}

	public List<Artigo> getArtigos(String conferencia) {
		return null;
	}

	public List<Revisao> getRevisoes(int idArtigo) {
		return null;
	}

}
