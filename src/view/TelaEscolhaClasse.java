package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Personagem;
import model.PersonagemFactory;

public class TelaEscolhaClasse extends JFrame {

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
					TelaEscolhaClasse frame = new TelaEscolhaClasse();
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
	public TelaEscolhaClasse() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Seleção de clase");
		setSize(866, 643);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);

		JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		painelStatus.setOpaque(false);

		JLabel lblPersonagem = new JLabel("Escolha sua classe: ");
		lblPersonagem.setFont(new Font("Calibri", Font.BOLD, 13));

		painelStatus.add(lblPersonagem);
		contentPane.add(painelStatus, BorderLayout.NORTH);

		JPanel painelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		painelCentro.setOpaque(false);

		JButton btnArqueira = new JButton("Arqueira");
		btnArqueira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCriarPersonagem("Arqueira");
				TelaCriarPersonagem telaCriarPersonagem = new TelaCriarPersonagem("Arqueira");
				telaCriarPersonagem.setVisible(true);
				dispose();
			}
		});

	

		JButton btnGuerreira = new JButton("Guerreira");
		btnGuerreira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new TelaCriarPersonagem("Guerreira");
				TelaCriarPersonagem telaCriarPersonagem = new TelaCriarPersonagem("Guerreira");
				telaCriarPersonagem.setVisible(true);
				dispose();
			}
		});
		

		JButton btnMaga = new JButton("Maga");
		btnMaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCriarPersonagem("Maga");
				TelaCriarPersonagem telaCriarPersonagem = new TelaCriarPersonagem("Maga");
				telaCriarPersonagem.setVisible(true);
				dispose();
			}
		});
		painelCentro.add(btnArqueira);
		painelCentro.add(btnGuerreira);
		painelCentro.add(btnMaga);

		contentPane.add(painelCentro, BorderLayout.CENTER);

		JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
		painelSul.setOpaque(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal telaPrincipal= new TelaPrincipal();
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
	
		painelSul.add(btnVoltar);
		contentPane.add(painelSul, BorderLayout.SOUTH);
	}
}
