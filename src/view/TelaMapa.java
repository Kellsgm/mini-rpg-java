package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.Inimigo;
import model.Mapa;
import model.PainelMapa;
import model.Personagem;
import model.PersonagemFactory;

public class TelaMapa extends JFrame {

	private static final long serialVersionUID = 1L;

	private Mapa mapa;
	private Personagem heroina;
	private PainelMapa painelMapa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMapa frame = new TelaMapa();
					frame.setVisible(true);

					frame.painelMapa.requestFocusInWindow();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public TelaMapa() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(866, 643);
		setLocationRelativeTo(null);

		mapa = new Mapa();
		mapa.carregarMapa("mapas/mapa1.txt");

		heroina = PersonagemFactory.criarArqueira("mayra");
		heroina.setPosicaoX(2);
		heroina.setPosicaoY(2);

		painelMapa = new PainelMapa(heroina, mapa);
		painelMapa.setPreferredSize(new Dimension(30 * 50, 30 * 50));
		painelMapa.setFocusable(true);

		JScrollPane scrollPane = new JScrollPane(painelMapa);
		setContentPane(scrollPane);

		painelMapa.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_D) {
					heroina.moverDireita(mapa);

				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					heroina.moverEsquerda(mapa);

				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					heroina.moverCima(mapa);

				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					heroina.moverBaixo(mapa);
				} else if (e.getKeyCode() == KeyEvent.VK_B) {
				    TelaInventario inventario = new TelaInventario(heroina);
				    inventario.setVisible(true);
				    inventario.addWindowListener(new java.awt.event.WindowAdapter() {
				        @Override
				        public void windowClosed(java.awt.event.WindowEvent e) {
				            painelMapa.requestFocusInWindow();
				        }
				    });
				}
				int x = heroina.getPosicaoX();
				int y = heroina.getPosicaoY();

				if (mapa.getCasa(x, y) == 3) {
					System.out.println("Pressione E para entrar na loja");
				}

				if (e.getKeyCode() == KeyEvent.VK_E) {
					if (mapa.getCasa(x, y) ==  3) {
						TelaLoja loja = new TelaLoja();
						loja.setVisible(true);
					}
				}
				for (Inimigo inimigo : mapa.getInimigos()) {
				    if (heroina.getPosicaoX() == inimigo.getPosicaoX()
				            && heroina.getPosicaoY() == inimigo.getPosicaoY()) {

				        TelaBatalha batalha = new TelaBatalha(heroina);
				        batalha.setVisible(true);
				    }
				}
				
				painelMapa.repaint();
			}
		});
	}
}