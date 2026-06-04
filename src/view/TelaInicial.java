package view;
import javax.swing.JOptionPane;
import model.Personagem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Personagem;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 356);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMaga = new JButton("Maga");
		btnMaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personagem heroina = new Personagem ("Maga", 35, 15, 18,20, 6);
				JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhida!");
				TelaBatalha telaBatalha = new TelaBatalha(heroina);
				telaBatalha.setVisible(true);

				dispose();
			}
		});
		btnMaga.setBounds(213, 221, 122, 23);
		contentPane.add(btnMaga);
		
		JButton btnArqueira = new JButton("Arqueira");
		btnArqueira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personagem heroina = new Personagem ("Arqueira", 30, 12, 12, 15, 8);
				JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhida!");
				TelaBatalha telaBatalha = new TelaBatalha(heroina);
				telaBatalha.setVisible(true);

				dispose();
			}
		});
		btnArqueira.setBounds(213, 121, 122, 23);
		btnArqueira.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(btnArqueira);
		
		JButton btnGuerreiro = new JButton("Guerreiro");
		btnGuerreiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personagem heroina = new Personagem ("Guerreiro", 40, 14, 16, 20, 12);
				JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhida!");	
				TelaBatalha telaBatalha = new TelaBatalha(heroina);
				telaBatalha.setVisible(true);

				dispose();
			}
		});
		btnGuerreiro.setBounds(213, 173, 122, 23);
		contentPane.add(btnGuerreiro);
		
		JTextArea txtrSelecioneAClasse = new JTextArea();
		txtrSelecioneAClasse.setBackground(new Color(255, 255, 255));
		txtrSelecioneAClasse.setText("Selecione a classe do seu personagem: ");
		txtrSelecioneAClasse.setBounds(108, 47, 350, 22);
		contentPane.add(txtrSelecioneAClasse);

	}
}
