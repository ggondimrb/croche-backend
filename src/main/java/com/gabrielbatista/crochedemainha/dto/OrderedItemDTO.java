package com.gabrielbatista.crochedemainha.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderedItemDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Double descount;
	private Integer amount;
	private Double price;
	private String color;
	private String size;
	
	private ProdutoDTO product;
	
}
