import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AlocarArtigos extends JDialog {

	private static final long serialVersionUID = 8242945255849979062L;
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> conferenciaCbBx;
	private JSpinner numRevisoresSpn;
	private JTextArea logTextArea;
	private ComiteServico comiteServico;

	/**
	 * Create the dialog.
	 */
	public AlocarArtigos(ComiteServico comiteServico) {
		this.comiteServico = comiteServico;
		initilizeGui();
	}

	private void initilizeGui() {
		setModal(true);
		setTitle("Alocar artigos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 400);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.LEFT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			JLabel lblLog = new JLabel("Log:");
			lblLog.setVerticalAlignment(SwingConstants.BOTTOM);
			contentPanel.add(lblLog);
		}
		{
			JPanel BtnPane = new JPanel();
			getContentPane().add(BtnPane, BorderLayout.SOUTH);
			BtnPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel LeftBtnPane = new JPanel();
				BtnPane.add(LeftBtnPane, BorderLayout.WEST);
				LeftBtnPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel lblConferncia = new JLabel("Confer\u00EAncia:");
					LeftBtnPane.add(lblConferncia);
				}
				{
					conferenciaCbBx = new JComboBox<String>();
					LeftBtnPane.add(conferenciaCbBx);
					for (String siglaConferencia : comiteServico.getConferenciaSiglasNaoAlocadas())
						conferenciaCbBx.addItem(siglaConferencia);
				}
				{
					JLabel lblNmeroDeRevisores = new JLabel("N\u00FAmero de revisores:");
					LeftBtnPane.add(lblNmeroDeRevisores);
				}
				{
					JButton alocarButton = new JButton("Alocar");
					alocarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (conferenciaCbBx.getItemCount() > 0) {
								comiteServico.alocaArtigos(conferenciaCbBx.getItemAt(0), (Integer)numRevisoresSpn.getValue());
								conferenciaCbBx.removeAllItems();
								for (String siglaConferencia : comiteServico.getConferenciaSiglasNaoAlocadas())
									conferenciaCbBx.addItem(siglaConferencia);
								for (String logLine : comiteServico.getLog()) 
										logTextArea.append(logLine);
							}
							else {
								logTextArea.append("AVISO: Todas conferências já possuem revisores alocados!\n");
							}

						}
					});
					{
						numRevisoresSpn = new JSpinner();
						numRevisoresSpn.setModel(new SpinnerNumberModel(2, 2, 5, 1));
						LeftBtnPane.add(numRevisoresSpn);
					}
					LeftBtnPane.add(alocarButton);
					getRootPane().setDefaultButton(alocarButton);
				}
			}
			{
				JPanel RightBtnPane = new JPanel();
				BtnPane.add(RightBtnPane, BorderLayout.EAST);
				{
					JButton sairButton = new JButton("Sair");
					RightBtnPane.add(sairButton);
					sairButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
				}
			}
		}
		{
			JPanel logPanel = new JPanel();
			getContentPane().add(logPanel, BorderLayout.CENTER);
			logPanel.setLayout(new BorderLayout(0, 0));
			
			logTextArea = new JTextArea();
			logTextArea.setLineWrap(true);
			
			JScrollPane logScrollPane = new JScrollPane(logTextArea);
			logPanel.add(logScrollPane, BorderLayout.CENTER);
		}
	}
	
}
