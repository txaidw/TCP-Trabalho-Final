import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JanelaPrincipal {

	private JFrame frmRevisoDeArtigos;
	private final Database database;
	private final ComiteServico comite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal window = new JanelaPrincipal();
					window.frmRevisoDeArtigos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaPrincipal() {
		database = new Database(true);
		comite = new ComiteServico(database);

		initializeGui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeGui() {

		frmRevisoDeArtigos = new JFrame();
		frmRevisoDeArtigos.setResizable(false);
		frmRevisoDeArtigos.setTitle("Revis\u00E3o de artigos");
		frmRevisoDeArtigos.setBounds(100, 100, 478, 118);
		frmRevisoDeArtigos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAlocarArtigos = new JButton("Alocar artigos");
		btnAlocarArtigos.setBounds(10, 11, 143, 71);
		btnAlocarArtigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlocarArtigos alocarArtigos = new AlocarArtigos(comite);
				alocarArtigos.setVisible(true);
			}
		});
		
		frmRevisoDeArtigos.getContentPane().setLayout(null);
		frmRevisoDeArtigos.getContentPane().add(btnAlocarArtigos);
		
		JButton btnAtribuirNotas = new JButton("Atribuir Notas");
		btnAtribuirNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtribuirNotas atribuirNotas = new AtribuirNotas(comite);
				atribuirNotas.setVisible(true);
			}
		});
		btnAtribuirNotas.setBounds(163, 11, 143, 71);
		frmRevisoDeArtigos.getContentPane().add(btnAtribuirNotas);
		
		JButton btnSelecionarArtigos = new JButton("Selecionar Artigos");
		btnSelecionarArtigos.setBounds(316, 11, 143, 71);
		btnSelecionarArtigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmRevisoDeArtigos.getContentPane().add(btnSelecionarArtigos);
	}

}
