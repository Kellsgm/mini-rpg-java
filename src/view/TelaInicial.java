package view;

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

import dao.PersonagemDAO;
import model.Personagem;
import model.PersonagemFactory;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 356);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMaga = new JButton("Maga");
		btnMaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 heroina = PersonagemFactory.criarMaga();
				JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhida!");

			}
		});
		btnMaga.setBounds(213, 156, 122, 23);
		contentPane.add(btnMaga);

		JButton btnArqueira = new JButton("Arqueira");
		btnArqueira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 heroina = PersonagemFactory.criarArqueira();
				JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhida!");
			
				
			}
		});
		btnArqueira.setBounds(213, 88, 122, 23);
		btnArqueira.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(btnArqueira);

		JButton btnGuerreiro = new JButton("Guerreiro");
		btnGuerreiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 heroina = PersonagemFactory.criarGuerreira();
				JOptionPane.showMessageDialog(null, heroina.getNome() + " escolhida!");
				
			}
		});
		btnGuerreiro.setBounds(213, 122, 122, 23);
		contentPane.add(btnGuerreiro);

		JTextArea txtrSelecioneAClasse = new JTextArea();
		txtrSelecioneAClasse.setBackground(new Color(255, 255, 255));
		txtrSelecioneAClasse.setText("Selecione a classe do seu personagem: ");
		txtrSelecioneAClasse.setBounds(108, 47, 350, 22);
		contentPane.add(txtrSelecioneAClasse);

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
			
			}
		});
		btnBatalha.setBounds(92, 257, 122, 23);
		contentPane.add(btnBatalha);

		JButton btnInventario = new JButton("Inventário");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (heroina == null) {
				    JOptionPane.showMessageDialog(null, "Escolha uma personagem primeiro!");
				    return;
				}
				TelaInventario telaInventario = new TelaInventario(heroina);
				telaInventario.setVisible(true);
				

			}
		});
		btnInventario.setBounds(224, 257, 122, 23);
		contentPane.add(btnInventario);

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
		btnSalvar.setBounds(356, 257, 113, 23);
		contentPane.add(btnSalvar);

	}
}
