package com.gabrielbatista.crochedemainha.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielbatista.crochedemainha.domain.enums.Cor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	private String descricao;
	private char ehTamanhoUnico;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="cores")
	private Set<Integer> cores = new HashSet<>(); 
	
	@OneToMany(mappedBy="produto")
	private List<Imagem> imagens = new ArrayList<>();
	
	private Double preco;
	
	@ManyToOne
	private Categoria categoria;
	
	@JsonIgnore
	@OneToMany(mappedBy="produto")
	private Set<ItemPedido> itens = new HashSet<>();
	
	private double peso;
	
	public Produto (Integer id, String nome, double preco, String descricao, char ehTamanhoUnico, Categoria categoria, double peso) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.ehTamanhoUnico = ehTamanhoUnico;
		this.categoria = categoria;
		this.peso = peso;
	}
	
	@JsonIgnore
	public List<Order> getOrders() {
		List<Order> lista = new ArrayList<>();
		for (ItemPedido x : itens) {
			lista.add(x.getOrder());
		}
		return lista;
	}
	
	public void addCor(Cor cor) {
		cores.add(cor.getCod());
	}
	
	public Set<Cor> getCores() {
		return cores.stream().map(x -> Cor.toEnum(x)).collect(Collectors.toSet());
	}
}
