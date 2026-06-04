package model;

public class Item {
	private String nome;
	private String tipo;
	private int valor;

	public Item(String nome, String tipo, int valor) {
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public int getValor() {
		return valor;
	}
	@Override
	public String toString() {
	    return nome + " (" + tipo + ") - Valor: " + valor;
	}
}
