package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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


	private JButton btnAtacar;
	private JButton btnCurar;
	private JButton btnDefender;
	private JButton btnEspecial;
	

	private boolean especialUsado = false;
	private int pocoes = 3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personagem heroiTeste = PersonagemFactory.criarArqueira("Bianca");
					TelaBatalha frame = new TelaBatalha(heroiTeste);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaBatalha(Personagem heroina) {
		this.heroina = heroina;
		this.boss = new Personagem("Carrasco", "Boss", 50, 6, 10, 8, 6, 0);
		this.batalhaService = new BatalhaService();

		setTitle("Batalha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(866, 643);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(121, 39, 4));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);

		JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		painelStatus.setOpaque(false);

		lblVidaHeroina = new JLabel();
		lblVidaHeroina.setForeground(Color.WHITE);
		lblVidaHeroina.setFont(new Font("Arial", Font.BOLD, 14));

		lblVidaBoss = new JLabel();
		lblVidaBoss.setForeground(Color.WHITE);
		lblVidaBoss.setFont(new Font("Arial", Font.BOLD, 14));

		painelStatus.add(lblVidaHeroina);
		painelStatus.add(lblVidaBoss);

		contentPane.add(painelStatus, BorderLayout.NORTH);

		JPanel painelCentro = new JPanel(new BorderLayout());
		painelCentro.setOpaque(false);

		lblMensagem = new JLabel("A batalha começou!");
		lblMensagem.setForeground(Color.WHITE);
		lblMensagem.setFont(new Font("Arial", Font.BOLD, 18));
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);

		painelCentro.add(lblMensagem, BorderLayout.CENTER);
		contentPane.add(painelCentro, BorderLayout.CENTER);

		JPanel painelSul = new JPanel();
		painelSul.setOpaque(false);
		painelSul.setLayout(new BoxLayout(painelSul, BoxLayout.Y_AXIS));

		JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		painelAcoes.setOpaque(false);

		JPanel painelNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
		painelNavegacao.setOpaque(false);

		btnAtacar = new JButton("Atacar");
		btnCurar = new JButton("Curar");
		btnDefender = new JButton("Defender");
		btnEspecial = new JButton("Especial");

		configurarBotao(btnAtacar);
		configurarBotao(btnCurar);
		configurarBotao(btnDefender);
		configurarBotao(btnEspecial);
	

		painelAcoes.add(btnAtacar);
		painelAcoes.add(btnCurar);
		painelAcoes.add(btnDefender);
		painelAcoes.add(btnEspecial);

		painelSul.add(painelAcoes);
		painelSul.add(painelNavegacao);

		contentPane.add(painelSul, BorderLayout.SOUTH);

		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = batalhaService.atacar(heroina, boss);
				atualizarTela();

				if (!boss.estaVivo()) {
					heroina.ganharMoedas(10);
					lblMensagem.setText("<html><center>" + mensagem + "<br>" + boss.getNome()
							+ " morreu!<br>Você ganhou 10 moedas!</center></html>");
					desativarBotoes();
					return;
				}

				String mensagemBoss = batalhaService.atacar(boss, heroina);
				atualizarTela();

				if (!heroina.estaVivo()) {
					lblMensagem.setText("<html><center>" + mensagem + "<br>" + mensagemBoss + "<br>" + heroina.getNome()
							+ " morreu!</center></html>");
					desativarBotoes();
					return;
				}

				lblMensagem.setText("<html><center>" + mensagem + "<br>" + mensagemBoss + "</center></html>");
			}
		});

		btnCurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagemCura = curarHeroi();
				lblMensagem.setText("<html><center>" + mensagemCura + "</center></html>");
				atualizarTela();
			}
		});

		btnDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagemDefender = BatalhaService.defender(boss, heroina);
				atualizarTela();

				if (!heroina.estaVivo()) {
					lblMensagem.setText("<html><center>" + mensagemDefender + "<br>" + heroina.getNome()
							+ " morreu!</center></html>");
					desativarBotoes();
					return;
				}

				lblMensagem.setText("<html><center>" + mensagemDefender + "</center></html>");
			}
		});

		btnEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (especialUsado) {
					lblMensagem.setText("<html><center>Você já usou o especial nessa batalha!</center></html>");
					return;
				}

				String mensagemEspecial = BatalhaService.especial(heroina, boss);
				especialUsado = true;
				atualizarTela();

				if (!boss.estaVivo()) {
					lblMensagem.setText(
							"<html><center>" + mensagemEspecial + "<br>" + boss.getNome() + " morreu!</center></html>");
					desativarBotoes();
					return;
				}

				String mensagemBoss = batalhaService.atacar(boss, heroina);
				atualizarTela();

				if (!heroina.estaVivo()) {
					lblMensagem.setText("<html><center>" + mensagemEspecial + "<br>" + mensagemBoss + "<br>"
							+ heroina.getNome() + " morreu!</center></html>");
					desativarBotoes();
					return;
				}

				lblMensagem.setText("<html><center>" + mensagemEspecial + "<br>" + mensagemBoss + "</center></html>");
			}
		});
	}

	private void configurarBotao(JButton botao) {
		botao.setPreferredSize(new Dimension(140, 35));
		botao.setFont(new Font("Arial", Font.BOLD, 13));
		botao.setFocusPainted(false);
		botao.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	private void atualizarTela() {
		lblVidaHeroina.setText("❤ " + heroina.getNome() + ": " + heroina.getVida() + "/" + heroina.getVidaMaxima());

		lblVidaBoss.setText("❤ " + boss.getNome() + ": " + boss.getVida() + "/" + boss.getVidaMaxima());
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