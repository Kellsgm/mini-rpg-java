package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
					frame.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaMapa() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(866, 643);
		setPreferredSize(new Dimension(30 * 50, 30 * 50));
		setLocationRelativeTo(null);;

		mapa = new Mapa();
		mapa.carregarMapa("mapas/mapa1.txt");
		heroina = PersonagemFactory.criarArqueira("mayra");

		painelMapa = new PainelMapa(heroina, mapa);
		JScrollPane scrollPane = new JScrollPane(painelMapa);
		setContentPane(scrollPane);

		setFocusable(true);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					heroina.moverDireita(mapa);

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					heroina.moverEsquerda(mapa);

				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					heroina.moverCima(mapa);

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					heroina.moverBaixo(mapa);
				}

				painelMapa.repaint();
			}
		});
	}
}