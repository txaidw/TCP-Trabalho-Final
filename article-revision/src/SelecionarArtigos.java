import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SelecionarArtigos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4278031498071383470L;
	
	private final JPanel contentPanel = new JPanel();
	private final ComiteServico comiteServico;
	private JComboBox<String> conferenciaCb;
	private JList<String> aprovadosList;
	private DefaultListModel<String> aprovadosListModel;
	private JList<String> reprovadosList;
	private DefaultListModel<String> reprovadosListModel;
	private JButton gerarButton;
	
	/**
	 * Create the dialog.
	 */
	public SelecionarArtigos(ComiteServico comiteServico) {
		this.comiteServico = comiteServico;
		initializeGui();
	}

	// métodos privados
	
	private void initializeGui() {
		setTitle("Selecionar artigos");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JScrollPane aprovadosScrlPane = new JScrollPane();
			contentPanel.add(aprovadosScrlPane);
			{
				aprovadosListModel = new DefaultListModel<>();
				aprovadosList = new JList<String>(aprovadosListModel);
				aprovadosScrlPane.setViewportView(aprovadosList);
			}
		}
		{
			JScrollPane reprovadosScrlPane = new JScrollPane();
			contentPanel.add(reprovadosScrlPane);
			{
				reprovadosListModel = new DefaultListModel<>();
				reprovadosList = new JList<String>(reprovadosListModel);
				reprovadosScrlPane.setViewportView(reprovadosList);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel leftBtnPane = new JPanel();
				buttonPane.add(leftBtnPane, BorderLayout.WEST);
				{
					JLabel lblConferncia = new JLabel("Confer\u00EAncia:");
					leftBtnPane.add(lblConferncia);
				}
				{
					conferenciaCb = new JComboBox<String>();
					if (comiteServico.getConferenciaSiglasSobRevisao().size() > 0) {
						for (String siglaConferencia : comiteServico.getConferenciaSiglasSobRevisao())
							conferenciaCb.addItem(siglaConferencia);
					} else {
						conferenciaCb.addItem("Nenhuma alocação");
						conferenciaCb.setEnabled(false);
					}


					leftBtnPane.add(conferenciaCb);
				}
				{
					gerarButton = new JButton("Gerar relat\u00F3rio");
					gerarButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							aprovadosListModel.clear();
							reprovadosListModel.clear();
							
							Conferencia conferencia = comiteServico.getConferencia((String)conferenciaCb.getSelectedItem());
							
							if (comiteServico.todosAvaliados(conferencia.getSigla())) {
								for (Artigo artigo : conferencia.getArtigos()) {
									float media = comiteServico.calculaMedia(artigo.getId());
									if (media >= 0) {
										aprovadosListModel.addElement(artigo.getTitulo());
									} else {
										reprovadosListModel.addElement(artigo.getTitulo());
									}
									System.out.println(artigo.getTitulo() + media);
								}
							} else {
								JOptionPane optionPane = new JOptionPane("Existem artigos que não foram avaliados.", JOptionPane.WARNING_MESSAGE);
								JDialog dialog = optionPane.createDialog("O relatório não pode ser gerado");
								dialog.setAlwaysOnTop(true);
								dialog.setVisible(true);
							}
						}
					});
					if (!conferenciaCb.isEnabled())
						gerarButton.setEnabled(false);
					
					leftBtnPane.add(gerarButton);
					gerarButton.setActionCommand("OK");
					getRootPane().setDefaultButton(gerarButton);
				}
			}
			{
				JPanel rightBtnPane = new JPanel();
				buttonPane.add(rightBtnPane, BorderLayout.EAST);
				{
					JButton prontoButton = new JButton("Pronto");
					prontoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
						}
					});
					rightBtnPane.add(prontoButton);
					prontoButton.setActionCommand("Cancel");
				}
			}
		}
		{
			JPanel LabelsPane = new JPanel();
			LabelsPane.setBorder(new EmptyBorder(5, 10, 0, 10));
			getContentPane().add(LabelsPane, BorderLayout.NORTH);
			LabelsPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblAprovados = new JLabel("Aprovados:");
				LabelsPane.add(lblAprovados);
			}
			{
				JLabel lblReprovados = new JLabel("Reprovados:");
				LabelsPane.add(lblReprovados);
			}
		}
	}
}
