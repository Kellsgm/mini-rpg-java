package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PersonagemDAO;
import model.Personagem;

public class TelaInicial extends JFrame {

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
	 * 
	 */
	public TelaInicial(Personagem heroina) {
		this();
		this.heroina = heroina;
	}

	public TelaInicial() {

		setTitle("Menu inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(866, 643);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

//        JButton btnTelaInicio = new JButton("Tela Inicial");
//		btnTelaInicio.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				TelaPrincipal telaPrincipal = new TelaPrincipal();
//				telaPrincipal.setVisible(true);
//				dispose();
//			}
//		});
//		btnTelaInicio.setBounds(224, 126, 122, 23);
//		contentPane.add(btnTelaInicio);
		
		JPanel painelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		painelCentro.setOpaque(false);

		JButton btnBatalha = new JButton("Batalha");
		btnBatalha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroina == null) {
					JOptionPane.showMessageDialog(null, "Escolha uma personagem primeiro!");
					return;
				}
				heroina.setVida(heroina.getVidaMaxima());
				TelaBatalha telaBatalha = new TelaBatalha(heroina);
				telaBatalha.setVisible(true);
				dispose();

			}
		});
		painelCentro.add(btnBatalha);
		contentPane.add(painelCentro, BorderLayout.CENTER);

		JButton btnInventario = new JButton("Inventário");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroina == null) {
					JOptionPane.showMessageDialog(null, "Escolha uma personagem primeiro!");
				}
				TelaInventario telaInventario = new TelaInventario(heroina);
				telaInventario.setVisible(true);

			}
		});
		painelCentro.add(btnInventario);
		contentPane.add(painelCentro, BorderLayout.CENTER);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (heroina == null) {
					JOptionPane.showMessageDialog(null, "Escolha uma personagem primeiro!");
					return;
				}

				PersonagemDAO dao = new PersonagemDAO();
				boolean sucesso;

				if (heroina.getId() == 0) {
					sucesso = dao.salvar(heroina);
				} else {
					sucesso = dao.atualizar(heroina);
				}

				if (sucesso) {
					JOptionPane.showMessageDialog(null, "Personagem salvo com sucesso!");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar personagem!");
				}
			}
		});
		painelCentro.add(btnSalvar);
		contentPane.add(painelCentro, BorderLayout.CENTER);

//		JButton btnPersonagens = new JButton("Personagens");
//		btnPersonagens.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				TelaEscolhaClasse telaPersonagens = new TelaEscolhaClasse();
//				telaPersonagens.setVisible(true);
//
//			}
//		});
//		btnPersonagens.setBounds(224, 63, 122, 20);
//		contentPane.add(btnPersonagens);

		JButton btnLoja = new JButton("Loja");
		btnLoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaLoja telaLoja = new TelaLoja();
				telaLoja.setVisible(true);
			}
		});
		painelCentro.add(btnLoja);

	}
}
