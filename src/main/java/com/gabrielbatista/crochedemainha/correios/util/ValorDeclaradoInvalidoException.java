package com.gabrielbatista.crochedemainha.correios.util;

import com.gabrielbatista.crochedemainha.correios.frete.CorreiosFreteDTO;

public class ValorDeclaradoInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ValorDeclaradoInvalidoException() {
		super("Valor declarado deve ser maior que "+CorreiosFreteDTO.VALOR_DECLARADO_MINIMO.setScale(2));
	}
	
	
	
}
