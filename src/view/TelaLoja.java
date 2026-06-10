package view;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaLoja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLoja frame = new TelaLoja();
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
	public TelaLoja() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 402);
		setSize(866, 643);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(10, 232, 85, 21);
		contentPane.add(btnVoltar);
		
		JButton btnPoçãoGrande = new JButton("Poção grande");
		btnPoçãoGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPoçãoGrande.setBounds(20, 68, 115, 20);
		contentPane.add(btnPoçãoGrande);
		
		JButton btnPocaoMedia = new JButton("Poção média");
		btnPocaoMedia.setBounds(146, 68, 134, 20);
		contentPane.add(btnPocaoMedia);
		
		JButton btnPocaoPequena = new JButton("Poção pequena");
		btnPocaoPequena.setBounds(290, 68, 150, 20);
		contentPane.add(btnPocaoPequena);

	}

}
