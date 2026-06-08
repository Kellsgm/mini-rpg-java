package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Personagem;
import model.PersonagemFactory;

public class TelaPersonagens extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Personagem heroina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPersonagens frame = new TelaPersonagens();
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
	public TelaPersonagens() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 366);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPersonagem = new JLabel("Escolha seu personagem: ");
		lblPersonagem.setFont(new Font("Calibri", Font.BOLD, 13));
		lblPersonagem.setBounds(121, 38, 254, 42);
		contentPane.add(lblPersonagem);
		
		JButton btnArqueiro = new JButton("Arqueiro");
		btnArqueiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 heroina = PersonagemFactory.criarArqueira();
					JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhido!");
					TelaBatalha telaBatalha = new TelaBatalha(heroina);
			        telaBatalha.setVisible(true);

			        dispose();
				
			}
		});
		btnArqueiro.setBounds(190, 114, 169, 20);
		contentPane.add(btnArqueiro);
		
		JButton btnGuerreiro = new JButton("Guerreiro");
		btnGuerreiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 heroina = PersonagemFactory.criarGuerreira();
					JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhido!");
					TelaBatalha telaBatalha = new TelaBatalha(heroina);
			        telaBatalha.setVisible(true);

			        dispose();
			}
		});
		btnGuerreiro.setBounds(190, 158, 169, 20);
		contentPane.add(btnGuerreiro);
		
		JButton btnMaga = new JButton("Maga");
		btnMaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 heroina = PersonagemFactory.criarMaga();
					JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhido!");
					TelaBatalha telaBatalha = new TelaBatalha(heroina);
			        telaBatalha.setVisible(true);

			        dispose();
			}
		});
		btnMaga.setBounds(190, 206, 170, 20);
		contentPane.add(btnMaga);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(heroina);
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(10, 293, 89, 23);
		contentPane.add(btnVoltar);

	}
}
