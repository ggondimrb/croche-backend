package com.gabrielbatista.crochedemainha.dto;

import java.io.Serializable;

import com.gabrielbatista.crochedemainha.domain.Cliente;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String street;
	private String num;
	private String complement;
	private String district;
	private String cep;
	private Boolean ehPrincipal;
	private Cliente cliente;

}
