package com.gabrielbatista.crochedemainha.correios.util;

public class ItemIndefinidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ItemIndefinidoException() {
		super("Nenhuma item foi adicionado.");
	}
	
	
	
}
