/*
 * Created on 5 Jan 2014 00:51:19 
 */
package bank.business.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bank.business.AccountManagementService;
import bank.business.BusinessException;
import bank.business.domain.Branch;
import bank.business.domain.Client;
import bank.business.domain.CurrentAccount;
import bank.business.domain.Employee;
import bank.business.domain.OperationLocation;
import bank.business.domain.Transaction;
import bank.business.domain.Transfer;
import bank.business.domain.Transaction.State;
import bank.data.Database;
import bank.util.RandomString;

/**
 * @author Ingrid Nunes
 * 
 */
public class AccountManagementServiceImpl implements AccountManagementService {

	private final Database database;
	private RandomString random;

	public AccountManagementServiceImpl(Database database) {
		this.database = database;
		this.random = new RandomString(8);
	}

	@Override
	public CurrentAccount createCurrentAccount(long branch, String name,
			String lastName, int cpf, Date birthday, double balance)
			throws BusinessException {
		OperationLocation operationLocation = database
				.getOperationLocation(branch);
		if (operationLocation == null || !(operationLocation instanceof Branch)) {
			throw new BusinessException("exception.invalid.branch");
		}

		Client client = new Client(name, lastName, cpf, random.nextString(),
				birthday);
		CurrentAccount currentAccount = new CurrentAccount(
				(Branch) operationLocation,
				database.getNextCurrentAccountNumber(), client, balance);

		database.save(currentAccount);

		return currentAccount;
	}

	@Override
	public Employee login(String username, String password)
			throws BusinessException {
		Employee employee = database.getEmployee(username);

		if (employee == null) {
			throw new BusinessException("exception.inexistent.employee");
		}
		if (!employee.getPassword().equals(password)) {
			throw new BusinessException("exception.invalid.password");
		}

		return employee;
	}
	
	public List<Transfer> getAllPendingTransfers() {
		
		Collection<CurrentAccount> allCurrentAccounts = database.getAllCurrentAccounts();
		List<Transfer> selectedTransfers = new LinkedList<>();
		
		for (CurrentAccount currentAccount : allCurrentAccounts) {
			List<Transfer> transfers = currentAccount.getTransfers();
			
			for (Transfer tranfer: transfers) {
				if (tranfer.getState() == State.PENDING)
					selectedTransfers.add(tranfer);
			}
		}
		
		Collections.sort(selectedTransfers, new Comparator<Transfer>() {
			@Override
			public int compare(Transfer o1, Transfer o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		return selectedTransfers;
	}
	
	@Override
	public void authorizeTransaction(Transaction transaction) throws BusinessException {
		transaction.setState(State.FINALIZED);
		if (transaction instanceof Transfer) {
			Transfer transfer = (Transfer)transaction;
			CurrentAccount destinationAccount = transfer.getDestinationAccount();
			destinationAccount.getTransfers().add(transfer);
			destinationAccount.depositAmount(transfer.getAmount());
		}
	}
	
	@Override
	public void cancelTransaction(Transaction transaction)
			throws BusinessException {
		transaction.setState(State.CANCELED);
		if (transaction instanceof Transfer) {
			Transfer transfer = (Transfer)transaction;
			transfer.getAccount().depositAmount(transaction.getAmount());
		}
		
	}


}
