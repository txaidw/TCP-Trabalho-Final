import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


public class AlocacaoDeArtigos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlocacaoDeArtigos frame = new AlocacaoDeArtigos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlocacaoDeArtigos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[195.00,grow][][][86px]", "[23px][14px,grow]"));
		
		JLabel lblConfncia = new JLabel("Conferencia:");
		contentPane.add(lblConfncia, "flowx,cell 0 0,alignx left,aligny center");
		
		JLabel lblNmeroDeRevisores = new JLabel("N\u00FAmero de revisores:");
		contentPane.add(lblNmeroDeRevisores, "cell 1 0,aligny center");
		
		JButton btnAlocarArtigos = new JButton("Alocar artigos");
		btnAlocarArtigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Database database = new Database(true);
				Comite comite = new Comite(database);
				String conferencia = textField.getText();
				int numero = Integer.parseInt(textField_1.getText());
				comite.alocaArtigos(conferencia, numero);
				for (String logLine : comite.getLog()) {
					textArea.append(logLine);
					textArea.append("\n");
				}
			}
		});
		contentPane.add(btnAlocarArtigos, "cell 3 0,alignx left,aligny center");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 0 0,alignx left,aligny center");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 2 0,alignx left,aligny center");
		textField_1.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, "cell 0 1 4 1,grow");
	}

}
