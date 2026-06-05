package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Personagem;
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
	private JButton btnVoltar;
	

	/**
	 * Launch the application.
	 */
	// Método usado apenas para testes da TelaBatalha
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personagem heroiTeste = new Personagem("Arqueira", 35, 5, 6, 6, 5, 15);
					Personagem boss = new Personagem("Carrasco", 40, 5, 5, 5, 5, 0);
					TelaBatalha frame = new TelaBatalha(heroiTeste);
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

		this.boss = new Personagem("Carrasco", 50, 6, 10, 8, 6, 0);
		this.batalhaService = new BatalhaService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnAtacar = new JButton("Atacar");
		btnAtacar.setBounds(28, 145, 89, 23);

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
		contentPane.setLayout(null);
		contentPane.add(btnAtacar);

		btnCurar = new JButton("Curar");
		btnCurar.setBounds(226, 145, 89, 23);
		btnCurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagemCura = curarHeroi();
				lblMensagem.setText(mensagemCura);
				atualizarTela();

			}
		});
		contentPane.add(btnCurar);

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
		btnDefender.setBounds(325, 145, 89, 23);
		contentPane.add(btnDefender);

		lblVidaHeroina = new JLabel("Vida da heroina: ");
		lblVidaHeroina.setText("Vida da heroína: " + heroina.getVida() + "/" + heroina.getVidaMaxima());
		lblVidaHeroina.setBounds(45, 48, 149, 14);
		contentPane.add(lblVidaHeroina);

		lblVidaBoss = new JLabel("vida do boss: ");
		lblVidaBoss.setText("Vida do boss: " + boss.getVida() + "/" + boss.getVidaMaxima());
		lblVidaBoss.setBounds(45, 101, 123, 14);
		contentPane.add(lblVidaBoss);

		lblMensagem = new JLabel("");
		lblMensagem.setBounds(38, 185, 317, 80);
		contentPane.add(lblMensagem);

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial telaInicial = new TelaInicial(heroina);
				telaInicial.setVisible(true);
			}
		});
		btnVoltar.setBounds(10, 276, 89, 23);
		contentPane.add(btnVoltar);

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
		            lblMensagem.setText("<html>" + mensagemEspecial + "<br>" + mensagemBoss + "<br>" + heroina.getNome() + " morreu!</html>");
		            desativarBotoes();
		            return;
		        }

		        lblMensagem.setText("<html>" + mensagemEspecial + "<br>" + mensagemBoss + "</html>");
		    }
		   
		});
		btnEspecial.setBounds(127, 145, 89, 23);
		contentPane.add(btnEspecial);
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
