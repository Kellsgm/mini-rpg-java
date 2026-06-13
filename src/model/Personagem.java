package model;

import java.util.ArrayList;

public class Personagem {

	private String nome;
	private String Classe;
	private int vida;
	private int vidaMaxima;
	private int danoMinimo;
	private int danoMaximo;
	private int forca;
	private int defesa;
	private int moedas;
	private int id;
	private Item itemEquipado;
	private ArrayList<Item> inventario;
	private int posicaoX;
	private int posicaoY;

	public Personagem(String nome, String classe, int vidaMaxima, int danoMinimo, int danoMaximo, int forca, int defesa, int moedas) {
		this.nome = nome;
		this.Classe = classe;
		this.vida = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
		this.danoMaximo = danoMaximo;
		this.danoMinimo = danoMinimo;
		this.forca = forca;
		this.defesa = defesa;
		this.moedas = moedas;
		this.posicaoX = 0;
		this.posicaoY = 0;
		inventario = new ArrayList<>();
	}

	// ========= GETTERS AND SETTERS ========
	public String getClasse() {
		return Classe;
	}

	public void setClasse(String classe) {
		Classe = classe;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public int getDanoMinimo() {
		return danoMinimo;
	}

	public void setDanoMinimo(int danoMinimo) {
		this.danoMinimo = danoMinimo;
	}

	public int getDanoMaximo() {
		return danoMaximo;
	}

	public void setDanoMaximo(int danoMaximo) {
		this.danoMaximo = danoMaximo;
	}

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public int getDefesa() {
		return defesa;
	}

	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}

	public ArrayList<Item> getInventario() {
		return inventario;
	}

	public int getMoedas() {
		return moedas;

	}

	public void setMoedas(int moedas) {
		this.moedas = moedas;
	}
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}
	
	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	// ======== METODOS ==========
	public boolean estaVivo() {
		return vida > 0;

	}

	public void receberDano(int dano) {
		vida -= dano;
		if (vida < 0) {
			vida = 0;
		}
	}

	public void curar(int quantidade) {
		vida += quantidade;
		if (vida > vidaMaxima) {
			vida = vidaMaxima;
		}
	}

	public void adicionarItem(Item item) {
		inventario.add(item);
	}

	public void removerItem(Item item) {
		inventario.remove(item);
	}

	public void equiparItem(Item item) {
		this.itemEquipado = item;
	}

	public Item getItemEquipado() {
		return itemEquipado;
	}

	public void ganharMoedas(int quantidade) {
		moedas += quantidade;
	}

	public boolean gastarMoedas(int quantidade) {
	    if (moedas >= quantidade) {
	        moedas -= quantidade;
	        return true;
	    }
	    return false;
	}
	public void restaurarVida() {
	    this.vida = this.vidaMaxima;
	}
	
	public void moverDireita(Mapa mapa) {
	    int novoX = posicaoX + 1;
	    int novoY = posicaoY;

	    if (mapa.posicaoValida(novoX, novoY)) {
	        posicaoX = novoX;
	        posicaoY = novoY;
	    }
	}
	
	public void moverEsquerda(Mapa mapa) {
		int novoX = posicaoX -1;
		int novoY = posicaoY;
		if (mapa.posicaoValida(novoX, novoY)) {
			posicaoX = novoX;
			posicaoY = novoY;
		}
	}
	
	public void moverCima(Mapa mapa) {
		int novoX = posicaoX;
		int novoY = posicaoY - 1;
		if (mapa.posicaoValida(novoX, novoY)) {
			posicaoX = novoX;
			posicaoY = novoY;
		}
	}
	public void moverBaixo(Mapa mapa) {
		int novoX = posicaoX;
		int novoY = posicaoY + 1;
		if (mapa.posicaoValida(novoX, novoY)) {
			posicaoX = novoX;
			posicaoY = novoY;
		}
	}
	}
	


