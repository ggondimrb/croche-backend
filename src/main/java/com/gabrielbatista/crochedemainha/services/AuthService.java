package com.gabrielbatista.crochedemainha.services;

import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.dto.EmailDTO;
import com.gabrielbatista.crochedemainha.repositories.ClienteRepository;
import com.gabrielbatista.crochedemainha.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Optional <Cliente> cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.get().setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente.get());
		emailService.sendNewPasswordEmail(cliente.get(), newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { //gera um digito
			return (char) (rand.nextInt(10) + 48);
		} 
		else if (opt == 1) { //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

	public void changeMail(@Valid EmailDTO emailDto) {
		Optional <Cliente> cliente = clienteRepository.findByEmail(emailDto.getMail());
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		cliente.get().setEmail(emailDto.getNewMail());
		clienteRepository.save(cliente.get());
		
	}
	
	public void changePassword(@Valid EmailDTO emailDto) {
		Optional <Cliente> cliente = clienteRepository.findByEmail(emailDto.getMail());
		
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		if (!pe.matches(emailDto.getPassword(), cliente.get().getSenha())) {
			throw new ObjectNotFoundException("Senha atual incorreta");
		}
		
		if (!emailDto.getNewPassword().equals(emailDto.getConfirmNewPassword())) {
			throw new ObjectNotFoundException("Senhas informadas não são iguais");
		}
		
		
		cliente.get().setSenha(pe.encode(emailDto.getNewPassword()));
		clienteRepository.save(cliente.get());
		
	}

}
