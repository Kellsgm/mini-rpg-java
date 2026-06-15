package model;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.TelaLoja;

public class PainelMapa extends JPanel {

	private Personagem heroina;
	private Mapa mapa;

	public PainelMapa(Personagem heroina, Mapa mapa) {
		this.heroina = heroina;
		this.mapa = mapa;
		setPreferredSize(new Dimension(30 * 50, 30 * 50));
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    int tamanhoCasa = 50;

	    for (int linha = 0; linha < 30; linha++) {
	        for (int coluna = 0; coluna < 30; coluna++) {

	            int x = coluna * tamanhoCasa;
	            int y = linha * tamanhoCasa;

	            if (mapa.getCasa(coluna, linha) == 1) {
	                g.fillRect(x, y, tamanhoCasa, tamanhoCasa);
	            }

	            if (mapa.getCasa(coluna, linha) == 3) {
	                g.drawString("LOJA", x + 8, y + 25);
	            }

	            g.drawRect(x, y, tamanhoCasa, tamanhoCasa);
	        }
	    }

	    for (Inimigo inimigo : mapa.getInimigos()) {
	        int xInimigo = inimigo.getPosicaoX() * tamanhoCasa;
	        int yInimigo = inimigo.getPosicaoY() * tamanhoCasa;

	        g.fillOval(xInimigo, yInimigo, tamanhoCasa, tamanhoCasa);
	    }

	    int xHeroina = heroina.getPosicaoX() * tamanhoCasa;
	    int yHeroina = heroina.getPosicaoY() * tamanhoCasa;

	    g.fillRect(xHeroina, yHeroina, tamanhoCasa, tamanhoCasa);
	
	}
}