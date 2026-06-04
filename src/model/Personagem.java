package model;

public class Personagem {

	private String nome; 
	private int vida; 
	private int vidaMaxima;
	private int danoMinimo;
	private int danoMaximo; 
	private int forca; 
	private int defesa; 
	
	public Personagem(String nome, int vidaMaxima, int danoMinimo, int danoMaximo, int forca, int defesa) {
		this.nome = nome; 
		this.vida = vidaMaxima;
		this.vidaMaxima = vidaMaxima;
		this.danoMaximo = danoMaximo;
		this.danoMinimo = danoMinimo;
		this.forca = forca;
		this.defesa = defesa;
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

	public boolean estaVivo() {
		return vida > 0;
		
	}
	
	public void receberDano(int dano) {
		vida -= dano;
		if (vida < 0) {
			vida = 0;
		}
	}
	public void curar (int quantidade) {
		vida += quantidade;
		if (vida > vidaMaxima) {
			vida = vidaMaxima;
		}
	}}
