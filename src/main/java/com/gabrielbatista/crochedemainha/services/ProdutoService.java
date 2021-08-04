package com.gabrielbatista.crochedemainha.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielbatista.crochedemainha.domain.Imagem;
import com.gabrielbatista.crochedemainha.domain.Produto;
import com.gabrielbatista.crochedemainha.repositories.ProdutoRepository;
import com.gabrielbatista.crochedemainha.security.UserSS;
import com.gabrielbatista.crochedemainha.services.exceptions.AuthorizationException;
import com.gabrielbatista.crochedemainha.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImagemService imagemService;
	
	@Value("${img.prefix.product}")
	private String prefix;
	
	@Value("${img.product.size}")
	private Integer size;
	
	public Produto buscar(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
				
	}
	
	public Page<Produto> search(String nome, String categoria, Integer page, Integer linesPerPage, String orderBy, String direction) {		
		
		page -= 1;
		if(page < 0) {
			page = 0;
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.search(nome.toUpperCase(), categoria.toUpperCase(), pageRequest);
	}
	
	public URI uploadProductPicture(Integer id, MultipartFile multipartFile) {
		
		Optional<Produto> produto = repo.findById(id);
		Random rand = new Random(); 
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + id + rand.nextInt(1000) + ".jpg";
		
		Imagem image = new Imagem(null,fileName,produto.get());
		
		imagemService.insert(image);
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
	}
}
