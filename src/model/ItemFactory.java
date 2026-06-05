package model;

public class ItemFactory {
	

	    public static Item criarArcoLongo() {
	        return new Item("Arco Longo", "arma", 2);
	    }

	    public static Item criarEspadaCurta() {
	        return new Item("Espada Curta", "arma", 3);
	    }

	    public static Item criarCajado() {
	        return new Item("Cajado Simples", "arma", 2);
	    }

	    public static Item criarPocaoPequena() {
	        return new Item("Poção Pequena", "pocao", 10);
	    }

	    public static Item criarArmaduraCouro() {
	        return new Item("Armadura de Couro", "armadura", 2);
	    }
	

}
