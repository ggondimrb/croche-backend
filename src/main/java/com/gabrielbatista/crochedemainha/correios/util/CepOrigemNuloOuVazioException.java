package com.gabrielbatista.crochedemainha.correios.util;

public class CepOrigemNuloOuVazioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CepOrigemNuloOuVazioException() {
		super("Cep origem deve ser preenchido.");
	}
	
	
	
}
