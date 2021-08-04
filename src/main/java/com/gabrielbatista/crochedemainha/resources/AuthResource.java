package com.gabrielbatista.crochedemainha.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbatista.crochedemainha.dto.EmailDTO;
import com.gabrielbatista.crochedemainha.security.JWTUtil;
import com.gabrielbatista.crochedemainha.security.UserSS;
import com.gabrielbatista.crochedemainha.services.AuthService;
import com.gabrielbatista.crochedemainha.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	// endpoint chamado apenas por usuarios autenticados
	@GetMapping("/refresh_token")
	@ResponseStatus(HttpStatus.OK)
	public List<String> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
//		response.addHeader("Authorization", "Bearer " + token);
//		response.addHeader("acess-control-expose-headers", "Authorization");
		List<String> listaRetorno = new ArrayList<>();
        if(token != null) {
        	listaRetorno.add(token);
        	listaRetorno.add(user.getUsername());
        	return listaRetorno; 	
        }
        
        return null;
		
	}
	
	@PostMapping("/forgot")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void forgot(@Valid @RequestBody EmailDTO emailDto) {
		service.sendNewPassword(emailDto.getMail());
	}
	
	@PostMapping("/change_mail")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changeMail(@Valid @RequestBody EmailDTO emailDto) {
		service.changeMail(emailDto);
	}
	
	@PostMapping("/change_password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(@Valid @RequestBody EmailDTO emailDto) {
		service.changePassword(emailDto);
	}
}
