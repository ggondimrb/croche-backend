package com.gabrielbatista.crochedemainha.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gabrielbatista.crochedemainha.services.validations.ClienteInsert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpf;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max = 120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String phone;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email
	private String mail;
	
	private String password;
	
	private String confirmPassword;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String num;	
	
	private String complement;
	
	private Integer type;	
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;

	@NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;	
	
	private String bairro;
	
	private String localidade;
	
	private Integer cidadeId;
	
	private String telefone2;
	
	private String telefone3;
	
	public ClienteNewDTO() {
		
	}

}
