/*
 * Created on 6 Jan 2014 21:14:05 
 */
package bank.ui.graphic.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import bank.business.AccountManagementService;
import bank.business.BusinessException;
import bank.business.domain.CurrentAccount;
import bank.business.domain.Transaction;
import bank.business.domain.Transfer;
import bank.ui.TextManager;
import bank.ui.graphic.BankGraphicInterface;
import bank.ui.graphic.GUIUtils;
import bank.util.ButtonColumn;

/**
 * @author ingrid
 * 
 */
public class PendingTransfersAction extends BankAction {

	private static final long serialVersionUID = 5090183202921964451L;

	private JDialog dialog;
	private AccountManagementService accountManagementService;
	private List<Transfer> pendingTransfers;

	public PendingTransfersAction(BankGraphicInterface bankInterface,
			TextManager textManager,
			AccountManagementService accountManagementService) {
		super(bankInterface, textManager);
		this.accountManagementService = accountManagementService;

		super.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		super.putValue(Action.NAME,
				textManager.getText("action.pending.transfers"));
	}

	@Override
	public void execute() {

		// Close Button
		JButton okButton = new JButton(textManager.getText("button.close"));
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		
		// Building Table
		pendingTransfers = accountManagementService.getAllPendingTransfers();
		
		Vector<Vector<String>> dataVector = tableDataVectorFromPendingTransfers(pendingTransfers);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add(textManager.getText("client"));
		columnNames.add(textManager.getText("source.br"));
		columnNames.add(textManager.getText("source.cc"));
		columnNames.add(textManager.getText("value"));
		columnNames.add(textManager.getText("client"));
		columnNames.add(textManager.getText("destination.br"));
		columnNames.add(textManager.getText("destination.cc"));
		columnNames.add(textManager.getText(""));
		columnNames.add(textManager.getText(""));
		
		DefaultTableModel model = new DefaultTableModel(dataVector, columnNames);

		JTable table = new JTable(model);
		
		// Table buttons
		Action authorize = authorizeAction();
		ButtonColumn authorizeButtonColumn = new ButtonColumn(table, authorize, 7);
		authorizeButtonColumn.setMnemonic(KeyEvent.VK_A);
		
		Action cancel = cancelAction();
		ButtonColumn cancelButtonColumn = new ButtonColumn(table, cancel, 8);
		cancelButtonColumn.setMnemonic(KeyEvent.VK_D);
		
		// Statement result
		JPanel transfersPanel = new JPanel();
		transfersPanel
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		transfersPanel.add(scrollPane);
		
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(okButton, BorderLayout.SOUTH);
		pane.add(transfersPanel, BorderLayout.NORTH);

		this.dialog = GUIUtils.INSTANCE.createDialog(bankInterface.getFrame(),"action.pending.transfers", pane);
		this.dialog.setVisible(true);
	}
	
	public void close() {
		dialog.dispose();
		dialog = null;
	}
	
	private Vector<Vector<String>> tableDataVectorFromPendingTransfers(List<Transfer> pendingTransfers)
	{
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		
		for (Transfer transfer: pendingTransfers) {
				
				CurrentAccount sourceAccount		= transfer.getAccount();
				String sourceName					= sourceAccount.getClient().getFirstName()  + " " + sourceAccount.getClient().getLastName();
				String sourceBranch 				= Long.toString(sourceAccount.getId().getBranch().getNumber());
				String sourceAccountId				= Long.toString(sourceAccount.getId().getNumber());
				
				String value						= Double.toString(transfer.getAmount());
				
				CurrentAccount destinationAccount	= transfer.getDestinationAccount();
				String destinationName 				= destinationAccount.getClient().getFirstName() + " " + destinationAccount.getClient().getLastName();
				String destinationBranch			= Long.toString(destinationAccount.getId().getBranch().getNumber());
				String destinationAccountId			= Long.toString(destinationAccount.getId().getNumber());
				
				Vector<String> rowStrings = new Vector<String>();
				
				rowStrings.add(sourceName);
				rowStrings.add(sourceBranch);
				rowStrings.add(sourceAccountId);
				
				rowStrings.add(value);
				
				rowStrings.add(destinationName);
				rowStrings.add(destinationBranch);
				rowStrings.add(destinationAccountId);
				
				rowStrings.add(textManager.getText("authorize"));
				rowStrings.add(textManager.getText("cancel"));
				dataVector.add(rowStrings);
		}
		
		return dataVector;
	}
	
	private Action authorizeAction() {
		Action authorize = new AbstractAction()
		{
			private static final long serialVersionUID = 6889745899595887721L;

			public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        Transaction transaction;
		        if (pendingTransfers != null)
		        {
		        	transaction = pendingTransfers.get(modelRow);

			        try {
						accountManagementService.authorizeTransaction(transaction);
						GUIUtils.INSTANCE.showMessage(bankInterface.getFrame(), textManager.getText("transfer.authorization.sucess"), JOptionPane.INFORMATION_MESSAGE);
						((DefaultTableModel)table.getModel()).removeRow(modelRow);
					} catch (BusinessException e1) {
						e1.printStackTrace();
						GUIUtils.INSTANCE.showMessage(bankInterface.getFrame(), e1.getMessage(), e1.getArgs(),
								JOptionPane.WARNING_MESSAGE);
						log.warn(e1);
					}
		        }
		    }
		};
		
		return authorize;
	}
	
	private Action cancelAction() {
		Action cancel = new AbstractAction()
		{
			private static final long serialVersionUID = 6889745899595887721L;

			public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        Transaction transaction;
		        if (pendingTransfers != null)
		        {
		        	transaction = pendingTransfers.get(modelRow);

			        try {
						accountManagementService.cancelTransaction(transaction);
						GUIUtils.INSTANCE.showMessage(bankInterface.getFrame(), textManager.getText("transfer.cancelation.sucess"), JOptionPane.INFORMATION_MESSAGE);
						((DefaultTableModel)table.getModel()).removeRow(modelRow);
					} catch (BusinessException e1) {
						e1.printStackTrace();
						GUIUtils.INSTANCE.showMessage(bankInterface.getFrame(), e1.getMessage(), e1.getArgs(),
								JOptionPane.WARNING_MESSAGE);
						log.warn(e1);
					}
		        }
		    }
		};
		
		return cancel;
	}
}
