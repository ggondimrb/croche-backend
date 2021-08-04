package com.gabrielbatista.crochedemainha.correios.util;

public class CepDestinoNuloOuVazioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CepDestinoNuloOuVazioException() {
		super("Cep origem deve ser preenchido.");
	}
	
	
	
}
