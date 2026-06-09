package model;

import javax.swing.JOptionPane;

public class PersonagemFactory {

	public static Personagem criarArqueira(String nome) {

		Personagem arqueira = new Personagem(nome, "Arqueira", 35, 5, 8, 6, 5, 15);

		arqueira.adicionarItem(ItemFactory.criarArcoLongo());
		arqueira.adicionarItem(ItemFactory.criarPocaoPequena());
		arqueira.adicionarItem(ItemFactory.criarArmaduraCouro());
		return arqueira;
	}

	public static Personagem criarGuerreira(String nome) {
		Personagem guerreira = new Personagem(nome, "Guerreira", 50, 4, 7, 8, 7, 15);
		guerreira.adicionarItem(ItemFactory.criarCajado());
		guerreira.adicionarItem(ItemFactory.criarPocaoPequena());
		guerreira.adicionarItem(ItemFactory.criarArmaduraCouro());
		return guerreira;
	}

	public static Personagem criarMaga(String nome) {
		Personagem maga = new Personagem(nome, "Maga", 25, 8, 12, 9, 3, 15);
		maga.adicionarItem(ItemFactory.criarCajado());
		maga.adicionarItem(ItemFactory.criarPocaoPequena());
		maga.adicionarItem(ItemFactory.criarArmaduraCouro());
		return maga;
	}

	public static Personagem criarPorClasse(String ClasseEscolhida, String nome) {

		switch (ClasseEscolhida) {
		case "Arqueira":
			return criarArqueira(nome);
			
		case "Maga":
		 return	criarMaga(nome);
		

		case "Guerreira":
			return criarGuerreira(nome);
		default:
		
		    throw new IllegalArgumentException("Classe inexistente: " + ClasseEscolhida);

		}
	
		
	}

}
