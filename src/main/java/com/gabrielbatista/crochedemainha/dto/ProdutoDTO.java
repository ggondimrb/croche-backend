package com.gabrielbatista.crochedemainha.dto;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.gabrielbatista.crochedemainha.domain.Categoria;
import com.gabrielbatista.crochedemainha.domain.Imagem;
import com.gabrielbatista.crochedemainha.domain.Produto;
import com.gabrielbatista.crochedemainha.domain.enums.Cor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private char ehTamanhoUnico;
	private Double preco;
	private String precoFormatado;
	private Categoria categoria;
	
	private Set<Cor> cores = new HashSet<>(); 
	private List<ImagemDTO> imagens = new ArrayList<>();
	
	private List<String> urlImagens = new ArrayList<>();
	
	private double peso;
	
	public ProdutoDTO() {
		
	}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();
		ehTamanhoUnico = obj.getEhTamanhoUnico();
		preco = obj.getPreco();
		cores = obj.getCores();
		categoria = obj.getCategoria();
		peso = obj.getPeso();
		
		Locale ptBr = new Locale("pt", "BR");
		precoFormatado = NumberFormat.getCurrencyInstance(ptBr).format(preco);
		
		for(Imagem imagem: obj.getImagens()) {
			ImagemDTO imagemDTO = new ImagemDTO(imagem);
			imagens.add(imagemDTO);
			urlImagens.add(imagemDTO.getUrl());
		}
		
	}

}
