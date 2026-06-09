package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Personagem;
import model.PersonagemFactory;
import service.BatalhaService;

public class TelaBatalha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblMensagem;
	private JLabel lblVidaHeroina;
	private JLabel lblVidaBoss;
	private Personagem heroina;
	private Personagem boss;
	private BatalhaService batalhaService;
	private JButton btnJogarNovamente;
	private JButton btnAtacar;
	private JButton btnCurar;
	private JButton btnDefender;
	private JButton btnEspecial;
	private boolean especialUsado = false;
	private int pocoes = 3;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	// Método usado apenas para testes da TelaBatalha
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Personagem heroiTeste = PersonagemFactory.criarArqueira("Bianca");
					Personagem boss = new Personagem("Carrasco", "Boss", 40, 5, 5, 5, 5, 0);

					TelaBatalha frame = new TelaBatalha(heroiTeste);
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
	public TelaBatalha(Personagem heroina) {
		this.heroina = heroina;

		this.boss = new Personagem("Carrasco", "Boss", 50, 6, 10, 8, 6, 0);
		this.batalhaService = new BatalhaService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(866, 643);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(121, 39, 4));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridBagLayout());

		setContentPane(contentPane);

		JPanel painelCentro = new JPanel();
		JPanel painelNavegacao = new JPanel();
		painelNavegacao.setBackground(new Color(121, 39, 4));
		JPanel painelStatus = new JPanel();
		painelStatus.setBackground(new Color(121, 39, 4));
		JPanel painelAcoes = new JPanel();
		painelAcoes.setBackground(new Color(121, 39, 4));
		
		contentPane.add(painelCentro);

		painelCentro.add(painelStatus);
		painelCentro.add(painelAcoes);
		painelCentro.add(Box.createVerticalGlue());
		painelCentro.add(painelNavegacao);
		painelCentro.setOpaque(false);
		
		painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));

		
		// ====== PAINEL STATUS =======
		
		lblVidaHeroina = new JLabel("Vida da heroina: ");
		lblVidaHeroina.setForeground(new Color(255, 255, 255));
		lblVidaHeroina.setText("Vida da heroína: " + heroina.getVida() + "/" + heroina.getVidaMaxima());
		lblVidaHeroina.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelStatus.add(lblVidaHeroina);

		lblVidaBoss = new JLabel("vida do boss: ");
		lblVidaBoss.setForeground(new Color(255, 255, 255));
		lblVidaBoss.setBackground(new Color(255, 255, 255));
		lblVidaBoss.setText("Vida do boss: " + boss.getVida() + "/" + boss.getVidaMaxima());
		
		lblVidaBoss.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelStatus.add(lblVidaBoss);
		
		lblMensagem = new JLabel("");
		lblMensagem.setForeground(new Color(255, 255, 255));

		lblMensagem.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelStatus.add(lblMensagem);
		
		
		// ====== PAINEL AÇÕES =======
		
		btnAtacar = new JButton("Atacar");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String mensagem = batalhaService.atacar(heroina, boss);
				System.out.println("Boss atacou! Vida heroi: " + heroina.getVida());
				atualizarTela();

				if (!boss.estaVivo()) {
					lblMensagem.setText("<html>" + mensagem + "<br>" + boss.getNome() + " morreu!</html>");
					desativarBotoes();
					heroina.ganharMoedas(10);
					lblMensagem.setText("<html>" + mensagem + "<br>" + boss.getNome()
							+ " morreu!<br>Você ganhou 10 moedas!</html>");
					return;
				}

				String mensagemBoss = batalhaService.atacar(boss, heroina);
				atualizarTela();

				if (!heroina.estaVivo()) {
					lblMensagem.setText("<html>" + mensagem + "<br>" + mensagemBoss + "<br>" + heroina.getNome()
							+ " morreu!</html>");
					desativarBotoes();
					return;
				}

				lblMensagem.setText("<html>" + mensagem + "<br>" + mensagemBoss + "</html>");
			}
		});

		btnAtacar.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelAcoes.add(btnAtacar);
		btnCurar = new JButton("Curar");
		
		btnCurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagemCura = curarHeroi();
				lblMensagem.setText(mensagemCura);
				atualizarTela();

			}
		});
	
		btnCurar.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelAcoes.add(btnCurar);
		btnDefender = new JButton("Defender");
		
		btnDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String mensagemDefender = BatalhaService.defender(boss, heroina);
				atualizarTela();

				if (!heroina.estaVivo()) {
					lblMensagem.setText("<html>" + mensagemDefender + "<br>" + heroina.getNome() + " morreu!</html>");
					desativarBotoes();
					return;
				}

				lblMensagem.setText("<html>" + mensagemDefender + "</html>");
			}
		});
		
		btnDefender.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelAcoes.add(btnDefender);
	
		btnEspecial = new JButton("Especial");
		btnEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (especialUsado) {
					lblMensagem.setText("Você já usou o especial nessa batalha!");
					return;
				}

				String mensagemEspecial = BatalhaService.especial(heroina, boss);
				especialUsado = true;
				atualizarTela();

				if (!boss.estaVivo()) {
					lblMensagem.setText("<html>" + mensagemEspecial + "<br>" + boss.getNome() + " morreu!</html>");
					desativarBotoes();
					return;
				}

				String mensagemBoss = batalhaService.atacar(boss, heroina);
				atualizarTela();

				if (!heroina.estaVivo()) {
					lblMensagem.setText("<html>" + mensagemEspecial + "<br>" + mensagemBoss + "<br>" + heroina.getNome()
							+ " morreu!</html>");
					desativarBotoes();
					return;
				}

				lblMensagem.setText("<html>" + mensagemEspecial + "<br>" + mensagemBoss + "</html>");
			}

		});
	
		btnEspecial.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelAcoes.add(btnEspecial);
	
		// ====== PAINEL NAVEGAÇÃO =======
		
		
		

		btnJogarNovamente = new JButton("Jogar Novamente");
		btnJogarNovamente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBatalha telaBatalha = new TelaBatalha(heroina);
				telaBatalha.setVisible(true);
				dispose();
			}
		});
		btnJogarNovamente.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelNavegacao.add(btnJogarNovamente);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(heroina);
				telaInicial.setVisible(true);
				dispose();
			}
		});
	
		btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
		painelNavegacao.add(btnVoltar);
		

	}

	private void atualizarTela() {
		lblVidaHeroina.setText("Vida da heroína: " + heroina.getVida() + "/" + heroina.getVidaMaxima());
		lblVidaBoss.setText("Vida da boss: " + boss.getVida() + "/" + boss.getVidaMaxima());
	}

	private void desativarBotoes() {
		btnAtacar.setEnabled(false);
		btnCurar.setEnabled(false);
		btnDefender.setEnabled(false);
		btnEspecial.setEnabled(false);

	}

	private String curarHeroi() {
		if (heroina.getVida() == heroina.getVidaMaxima()) {
			return heroina.getNome() + " já está com a vida cheia!";
		}

		if (pocoes <= 0) {
			return "Você não tem mais poções!";
		}

		int cura = 5;
		heroina.curar(cura);
		pocoes--;

		return heroina.getNome() + " recuperou " + cura + " de vida! Poções restantes: " + pocoes;
	}

}
