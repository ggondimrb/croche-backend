package com.gabrielbatista.crochedemainha.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}

	public ItemPedido(Order order, Produto produto, Double desconto, Integer quantidade) {
		super();
		this.order = order;
		this.produto = produto;
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = (produto.getPreco() * quantidade) - desconto;
	}
	
	
	
}
