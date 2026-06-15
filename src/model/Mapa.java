package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Mapa {
	private ArrayList<Inimigo> inimigos = new ArrayList<>();
    private int[][] casas = new int[30][30];

    public void carregarMapa(String caminhoArquivo) {

        try (BufferedReader leitor =
                new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {

                for (int coluna = 0; coluna < linha.length(); coluna++) {

                    int valor = Character.getNumericValue(linha.charAt(coluna));

                    if (valor == 4) {
                        inimigos.add(new Inimigo(coluna, numeroLinha));
                        casas[numeroLinha][coluna] = 0;
                    } else {
                        casas[numeroLinha][coluna] = valor;
                    }
                }

                numeroLinha++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCasa(int x, int y) {
        return casas[y][x];
    }
    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }
    

    public boolean posicaoValida(int x, int y) {

        boolean dentroDoMapa =
                x >= 0 &&
                x < casas[0].length &&
                y >= 0 &&
                y < casas.length;

        if (!dentroDoMapa) {
            return false;
        }

        return casas[y][x] != 1;
    }
  
    
}