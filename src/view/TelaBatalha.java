package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Personagem heroi;
	private Personagem boss;
	private BatalhaService batalhaService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personagem heroiTeste = new Personagem("Arqueira", 35, 5, 6, 6, 5);
					Personagem boss = new Personagem("Carrasco", 40, 5, 5, 5, 5);
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
	public TelaBatalha(Personagem heroi) {
		this.heroi = heroi;

		this.boss = new Personagem("Carrasco", 40, 40, 4, 8, 4);
		this.batalhaService = new BatalhaService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnAtacar = new JButton("Atacar");
		btnAtacar.setBounds(45, 145, 89, 23);
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = batalhaService.atacar(heroi, boss);
				lblMensagem.setText("TESTE");

//				if (boss.estaVivo()) {
//				    String mensagemBoss = BatalhaService.atacar(boss, heroi);
//				    lblMensagem.setText("<html>" + mensagem + "<br>" + mensagemBoss + "</html>");
//				} else {
//				    lblMensagem.setText("<html>" + mensagem + "<br>" + boss.getNome() + " morreu!</html>");
//				}

				atualizarTela();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAtacar);

		JButton btnCurar = new JButton("Curar");
		btnCurar.setBounds(178, 145, 89, 23);
		btnCurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String mensagemCura = BatalhaService.curar(heroi);
			        lblMensagem.setText(mensagemCura);
			        atualizarTela();

			}
		});
		contentPane.add(btnCurar);

		JButton btnDefender = new JButton("Defender");
		btnDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagemDefender = BatalhaService.defender(heroi, boss);
				lblMensagem.setText(mensagemDefender);
				atualizarTela();
			}
		});
		btnDefender.setBounds(309, 145, 89, 23);
		contentPane.add(btnDefender);

		lblVidaHeroina = new JLabel("Vida da heroina: ");
		lblVidaHeroina.setText("Vida da heroína: " + heroi.getVida() + "/" + heroi.getVidaMaxima());
		lblVidaHeroina.setBounds(45, 48, 149, 14);
		contentPane.add(lblVidaHeroina);

		lblVidaBoss = new JLabel("vida do boss: ");
		lblVidaBoss.setText("Vida do boss: " + boss.getVida() + "/" + boss.getVidaMaxima());
		lblVidaBoss.setBounds(45, 101, 123, 14);
		contentPane.add(lblVidaBoss);

		lblMensagem = new JLabel("");
		lblMensagem.setBounds(20, 180, 400, 80);
		contentPane.add(lblMensagem);
	}

	private void atualizarTela() {
		lblVidaHeroina.setText("Vida da heroína: " + heroi.getVida() + "/" + heroi.getVidaMaxima());
		lblVidaBoss.setText("Vida da boss: " + boss.getVida() + "/" + boss.getVidaMaxima());
	}

}
