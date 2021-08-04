package com.gabrielbatista.crochedemainha.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielbatista.crochedemainha.domain.Adresses;
import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.domain.enums.Perfil;
import com.gabrielbatista.crochedemainha.domain.enums.TipoCliente;
import com.gabrielbatista.crochedemainha.dto.ClienteDTO;
import com.gabrielbatista.crochedemainha.dto.ClienteNewDTO;
import com.gabrielbatista.crochedemainha.repositories.ClienteRepository;
import com.gabrielbatista.crochedemainha.repositories.EnderecoRepository;
import com.gabrielbatista.crochedemainha.security.UserSS;
import com.gabrielbatista.crochedemainha.services.exceptions.AuthorizationException;
import com.gabrielbatista.crochedemainha.services.exceptions.DataIntegrityException;
import com.gabrielbatista.crochedemainha.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Cliente find(String id) {
		
//		UserSS user = UserService.authenticated();
//		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso Negado");
//		}
		
		Optional<Cliente> obj;
		
		if(id.contains("@")) {
			obj = repo.findByEmail(id);
		} else {
			obj = repo.findById(Integer.parseInt(id));	
		}
		
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
				
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getAdresses());
		return obj;
				
	}
	
	public Cliente update(Cliente obj) {
//		Cliente newObj = find(obj.getId().toString());
//		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id.toString());
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel excluir por que há entidades relacionadas");
			// TODO: handle exception
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Cliente> obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		
		return obj.get();
	}
	
	//Page: lib do spring para realizar paginacao de busca dos itens do banco
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//metodo aux para validacao dos campos 
	public Cliente fromDTO(ClienteDTO objDto) {
		Optional <Cliente> cliente = repo.findByEmail(objDto.getEmail());
		cliente.get().getTelefones().clear();
		cliente.get().getTelefones().addAll(Arrays.asList(objDto.getCellphone()));
		cliente.get().setName(objDto.getName());
		return cliente.get();		
	}
	
	//metodo aux para validacao dos campos 
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getName(), objDto.getMail(), objDto.getCpf(), TipoCliente.toEnum(objDto.getType()), pe.encode(objDto.getPassword()));
		Adresses end = new Adresses(null, objDto.getLogradouro(), objDto.getNum(), objDto.getComplement(), objDto.getBairro(), objDto.getLocalidade(), objDto.getCep(), true, cli);
		cli.getAdresses().add(end);
		cli.getTelefones().add(objDto.getPhone());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
	}
	
}
