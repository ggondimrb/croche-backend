package com.gabrielbatista.crochedemainha.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.domain.enums.TipoCliente;
import com.gabrielbatista.crochedemainha.dto.ClienteNewDTO;
import com.gabrielbatista.crochedemainha.repositories.ClienteRepository;
import com.gabrielbatista.crochedemainha.resources.exceptions.FieldMessage;
import com.gabrielbatista.crochedemainha.services.validations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getType().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CPF Inválido"));
		}
		
		if (objDto.getType().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCpf())) {
			list.add(new FieldMessage("cpf", "CNPJ Inválido"));
		}
		
		Optional <Cliente> cpf = repo.findByCpfOuCnpj(objDto.getCpf());
		
		if (!cpf.isEmpty()) {
			list.add(new FieldMessage("mail","Cpf já existente"));
		}
		
		Optional <Cliente> aux = repo.findByEmail(objDto.getMail());
		
		if (!aux.isEmpty()) {
			list.add(new FieldMessage("mail","E-mail já existente"));
		}	
		
		if(objDto.getPassword().length() < 6) {
			list.add(new FieldMessage("password","A senha deve conter pelo menos 6 dígitos"));
		}
		
		if(!objDto.getPassword().equals(objDto.getConfirmPassword())) {
			list.add(new FieldMessage("password","Senhas não correspondem"));
		}
		
		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
