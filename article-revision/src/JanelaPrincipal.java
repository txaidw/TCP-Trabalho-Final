import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JanelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal window = new JanelaPrincipal();
					window.frame.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 144, 120);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAlocarArtigos = new JButton("Alocar artigos");
		btnAlocarArtigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlocarArtigos alocarArtigos = new AlocarArtigos();
				alocarArtigos.setVisible(true);
			}
		});
		frame.getContentPane().add(btnAlocarArtigos);
		
		JButton btnAtribuirNotas = new JButton("Atribuir Notas");
		frame.getContentPane().add(btnAtribuirNotas);
		
		JButton btnSelecionarArtigos = new JButton("Selecionar Artigos");
		btnSelecionarArtigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnSelecionarArtigos);
	}

}
