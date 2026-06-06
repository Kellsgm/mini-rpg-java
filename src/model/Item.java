package model;

public class Item {
	private String nome;
	private String tipo;
	private int valor;
	private int preco;

	public Item(String nome, String tipo, int valor, int preco) {
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
		this.preco = preco;
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
	
	
	
	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
	    return nome + " (" + tipo + ") - Valor: " + valor;
	}
}
