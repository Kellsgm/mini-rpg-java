package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Mapa {

    private int[][] casas = new int[30][30];

    public void carregarMapa(String caminhoArquivo) {

        try (BufferedReader leitor =
                new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;
            int numeroLinha = 0;

            while ((linha = leitor.readLine()) != null) {

                for (int coluna = 0; coluna < linha.length(); coluna++) {

                    casas[numeroLinha][coluna] =
                            Character.getNumericValue(
                                    linha.charAt(coluna));

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