package com.gabrielbatista.crochedemainha.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielbatista.crochedemainha.domain.Adresses;
import com.gabrielbatista.crochedemainha.domain.Order;
import com.gabrielbatista.crochedemainha.domain.enums.StatusPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date instant;
	
	private Double totalValue;
	
	private Date deliveryForecast;
	
	private StatusPedido status;
	
	private Adresses deliveryAddress;
	
	private List<OrderedItemDTO> itens = new ArrayList<>();
	
	public OrderDTO(Order obj) {

	}

	
}
