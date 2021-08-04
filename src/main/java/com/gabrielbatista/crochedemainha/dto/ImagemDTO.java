package com.gabrielbatista.crochedemainha.dto;

import java.io.Serializable;

import com.gabrielbatista.crochedemainha.domain.Imagem;
import com.gabrielbatista.crochedemainha.resources.utils.URL;

public class ImagemDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String url;
	
	public ImagemDTO() {
		
	}
	
	public ImagemDTO(Imagem obj) {
		id = obj.getId();
		url = URL.decodeUrlImage(obj.getName());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
