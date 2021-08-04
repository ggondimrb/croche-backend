package com.gabrielbatista.crochedemainha.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.gabrielbatista.crochedemainha.security.UserSS;

//classe para retornar o usuario logado
public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
