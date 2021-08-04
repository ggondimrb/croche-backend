package com.gabrielbatista.crochedemainha.correios.util;

public class TipoServicoNuloOuVazioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public TipoServicoNuloOuVazioException() {
		super("Cep origem deve ser preenchido.");
	}
	
	
	
}
