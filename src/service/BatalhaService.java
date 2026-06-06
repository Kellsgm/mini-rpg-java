package service;

import java.util.Random;
import java.util.Scanner;

import model.Personagem;

public class BatalhaService {
	private static Random random = new Random();

	public static String atacar(Personagem atacante, Personagem defensor) {
	    int chanceCritico = random.nextInt(10) + 1;

	    int dano = random.nextInt(atacante.getDanoMaximo() - atacante.getDanoMinimo() + 1) 
	            + atacante.getDanoMinimo();

	    int bonusItem = 0;

	    if (atacante.getItemEquipado() != null 
	            && atacante.getItemEquipado().getTipo().equalsIgnoreCase("Ataque")) {
	        bonusItem = atacante.getItemEquipado().getValor();
	    }

	    dano = dano + atacante.getForca() + bonusItem;

	    if (chanceCritico <= 9) {
	        defensor.receberDano(dano);

	        return atacante.getNome() + " atacou " + defensor.getNome() 
	                + " causando " + dano + " de dano!";
	    } else {
	        int danoCritico = dano * 2;
	        defensor.receberDano(danoCritico);

	        return atacante.getNome() + " atacou com dano crítico " 
	                + defensor.getNome() + " causando " + danoCritico + " de dano!";
	    }
	
	}
	public static String especial(Personagem atacante, Personagem defensor) {
		int dano = atacante.getDanoMaximo() * 2;
		defensor.receberDano(dano);
	return atacante.getNome() + "deu um golpe especial e causou " + dano + " de danos!";
	}

	public static void mostrarStatus(Personagem personagem) {
		System.out
				.println(personagem.getNome() + " | Vida: " + personagem.getVida() + "/" + personagem.getVidaMaxima());
	}

	public static void iniciarBatalha(Personagem heroina, Personagem boss) {
		int rodada = 1;

		System.out.println(" --- Inicio da Batalha --- ");
		mostrarStatus(heroina);
		mostrarStatus(boss);

		while (heroina.estaVivo() && boss.estaVivo()) {

			System.out.println(" \n=== Rodada " + rodada + " ===");
			atacar(heroina, boss);
			if (boss.estaVivo()) {
				atacar(boss, heroina);
			}

			mostrarStatus(heroina);
			mostrarStatus(boss);

			rodada++;
		}
		System.out.println("\n === Fim da batalha ===");

		if (heroina.estaVivo()) {
			System.out.println(heroina.getNome() + " Venceu!");
		} else {
			System.out.println(boss.getNome() + " Venceu.");
		}

	}

	public static void iniciarBatalhaInterativa(Personagem heroina, Personagem boss, Scanner sc) {
		int rodada = 1;
		int pocoes = 3;

		System.out.println(" --- Inicio da Batalha --- ");
		while (heroina.estaVivo() && boss.estaVivo()) {

			System.out.println("\n=== Rodada " + rodada + " ===");

			int opc;
			boolean defendendo = false;

			mostrarStatus(heroina);
			mostrarStatus(boss);
			do {
				System.out.println("\nEscolha sua ação:");
				System.out.println("1 - Atacar");
				System.out.println("2 - Curar");
				System.out.println("3 - Defender");
				System.out.println("0 - Fugir");
				System.out.print("Opção: ");
				opc = sc.nextInt();
				if (opc < 0 || opc > 3) {
					System.out.println("Opção inválida!");
				}
			} while (opc < 0 || opc > 3);

			switch (opc) {
			case 1:
				atacar(heroina, boss);
				break;
			case 2:
				if (pocoes > 0) {
					int vidaAntes = heroina.getVida();
					int cura = random.nextInt(4) + 3;
					heroina.curar(cura);
					int vidaDepois = heroina.getVida();

					System.out.println(heroina.getNome() + " se curou em " + cura + " pontos de vida!");
					System.out.println("Vida: " + vidaAntes + " -> " + vidaDepois);
					pocoes--;
					System.out.println("quantidade de poções restantes: " + pocoes);
				} else {
					System.out.println("nao há poções");
				}
				break;
			case 3:
				defender(heroina, boss);
				break;
			case 0:
				System.out.println(heroina.getNome() + " fugiu da batalha");
				return;
			}

			if (boss.estaVivo()) {
				defender(boss, heroina);
			}
			rodada++;
		}
		if (heroina.estaVivo()) {
			System.out.println(heroina.getNome() + " venceu!");
		} else {
			System.out.println(boss.getNome() + " venceu!");
		}

	}

	public static String defender(Personagem atacante, Personagem defensor) {
		// faz mais sentido nao ter critico nesse ataque
		int ataque = atacante.getForca() + random.nextInt(6);
		int defesa = defensor.getDefesa() + random.nextInt(6);
		if (ataque > defesa) {
			int dano = random.nextInt(atacante.getDanoMaximo() - atacante.getDanoMinimo() + 1)+ atacante.getDanoMinimo();
			defensor.receberDano(dano);
			return " A defesa falhou! Recebu um total de " + dano + " de dano!";
		} else {
			int dano = random.nextInt(atacante.getDanoMaximo() - atacante.getDanoMinimo() + 1)
					+ atacante.getDanoMinimo();
			dano = dano / 2;
			defensor.receberDano(dano);
			return "Defesa bem-sucedida! "
		       + defensor.getNome()
		       + " recebeu apenas "
		       + dano
		       + " de dano!";

		}
	}
	public static String curar(Personagem personagem) {
	 
	    if (personagem.getVida() == personagem.getVidaMaxima()) {
	        return personagem.getNome() + " já está com a vida cheia!";
	    }

	    int cura = random.nextInt(4) + 3;
	    personagem.curar(cura);


	    return personagem.getNome() + " recuperou vida!";
	}
	
}
