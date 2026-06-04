package model;

public class PersonagemFactory {

	public static Personagem criarArqueira() {
		return new Personagem("Arqueira",35, 5, 8, 6, 5, 15);
	}

	public static Personagem criarGuerreira() {
		return new Personagem("Guerreira", 50, 4, 7, 8, 7, 15);
	}

	public static Personagem criarMaga() {
		return new Personagem("Maga", 25, 8, 12, 9, 3, 15);
	}

}
