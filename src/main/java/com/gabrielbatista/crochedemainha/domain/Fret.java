package com.gabrielbatista.crochedemainha.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fret implements Serializable {	
	private static final long serialVersionUID = 1L;

	private String codigo;
	
	private String valor;
	
}
