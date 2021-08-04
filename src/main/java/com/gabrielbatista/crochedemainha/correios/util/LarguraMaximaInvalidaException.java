package com.gabrielbatista.crochedemainha.correios.util;

public class LarguraMaximaInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LarguraMaximaInvalidaException() {
		super("Largura máxima deve ser 105 cm.");
	}
	
	
	
}
