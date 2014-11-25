import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class AlocarArtigos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField numRevisoresTxtFld;
	private JTextField ConferenciaTxtFld;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlocarArtigos dialog = new AlocarArtigos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlocarArtigos() {
		setModal(false);
		setTitle("Alocar artigos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 511, 297);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblConferncia = new JLabel("Confer\u00EAncia");
				buttonPane.add(lblConferncia);
			}
			{
				ConferenciaTxtFld = new JTextField();
				buttonPane.add(ConferenciaTxtFld);
				ConferenciaTxtFld.setColumns(10);
			}
			{
				JLabel lblNmeroDeRevisores = new JLabel("N\u00FAmero de revisores:");
				buttonPane.add(lblNmeroDeRevisores);
			}
			{
				numRevisoresTxtFld = new JTextField();
				buttonPane.add(numRevisoresTxtFld);
				numRevisoresTxtFld.setColumns(10);
			}
			{
				JButton alocarButton = new JButton("Alocar");
				alocarButton.setActionCommand("OK");
				buttonPane.add(alocarButton);
				getRootPane().setDefaultButton(alocarButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			textField_2 = new JTextField();
			getContentPane().add(textField_2, BorderLayout.CENTER);
			textField_2.setColumns(10);
		}
	}

}
