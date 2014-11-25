import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.management.StringValueExp;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.GridLayout;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;

import javax.swing.ScrollPaneConstants;


public class AtribuirNotas extends JDialog {

	private static final long serialVersionUID = -3985652409103343749L;
	
	private final JPanel contentPanel = new JPanel();
	private ComiteServico comiteServico;
	private JList<String> artigosList;
	DefaultListModel<String> artigosListModel;
	private JList<String> revisoesList;
	DefaultListModel<String> revisoesListModel;

	/**
	 * Create the dialog.
	 */
	public AtribuirNotas(ComiteServico comiteServico) {
		setModal(true);
		this.comiteServico = comiteServico;
		this.initializeGui();
	}

	private void initializeGui() {
		setBounds(100, 100, 728, 496);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel labelsPanel = new JPanel();
			contentPanel.add(labelsPanel, BorderLayout.NORTH);
			labelsPanel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblArtigos = new JLabel("Artigos:");
				labelsPanel.add(lblArtigos);
			}
			{
				JLabel lblRevises = new JLabel("Revisões:");
				labelsPanel.add(lblRevises);
			}
		}
		{
			JPanel listsPanel = new JPanel();
			contentPanel.add(listsPanel, BorderLayout.CENTER);
			listsPanel.setLayout(new BoxLayout(listsPanel, BoxLayout.X_AXIS));
			{
				artigosListModel = new DefaultListModel<String>();
				artigosListModel.addElement("Artigo #1");
				artigosListModel.addElement("Artigo #2");
				artigosListModel.addElement("Artigo #3");

				artigosList = new JList<String>(artigosListModel);
				ListSelectionModel artigosSelectionModel = artigosList.getSelectionModel();
				artigosSelectionModel.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
						}					
					}
				});
				
				JScrollPane artigosScrlPane = new JScrollPane(artigosList);
				artigosScrlPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				listsPanel.add(artigosScrlPane);
			}
			{
				revisoesListModel = new DefaultListModel<String>();
				
				revisoesList = new JList<String>(revisoesListModel);
				JScrollPane revisoesScrlPane = new JScrollPane(revisoesList);
				revisoesScrlPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				listsPanel.add(revisoesScrlPane);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
