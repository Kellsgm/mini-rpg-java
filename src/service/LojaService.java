package service;

import model.Item;
import model.Personagem;

public class LojaService {
	 public boolean venderItem(Personagem personagem, Item item) {
	        if (personagem.gastarMoedas(item.getPreco())) {
	            personagem.adicionarItem(item);
	            return true;
	        }

	        return false;

}}
