package model;

public class PersonagemFactory {

	public static Personagem criarArqueira() {

		Personagem arqueira = new Personagem("Arqueira", 35, 5, 8, 6, 5, 15);

		arqueira.adicionarItem(ItemFactory.criarArcoLongo());
		arqueira.adicionarItem(ItemFactory.criarPocaoPequena());
		arqueira.adicionarItem(ItemFactory.criarArmaduraCouro());
		return arqueira;
	}

	public static Personagem criarGuerreira() {
		Personagem guerreira = new Personagem("Guerreira", 50, 4, 7, 8, 7, 15);
		guerreira.adicionarItem(ItemFactory.criarCajado());
		guerreira.adicionarItem(ItemFactory.criarPocaoPequena());
		guerreira.adicionarItem(ItemFactory.criarArmaduraCouro());
		return guerreira;
	}

	public static Personagem criarMaga() {
		Personagem maga = new Personagem("Maga", 25, 8, 12, 9, 3, 15);
		maga.adicionarItem(ItemFactory.criarCajado());
		maga.adicionarItem(ItemFactory.criarPocaoPequena());
	maga.adicionarItem(ItemFactory.criarArmaduraCouro());
		return maga;
	}

}
