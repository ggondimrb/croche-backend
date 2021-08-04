package com.gabrielbatista.crochedemainha.domain.enums;

public enum StatusPedido {
	PEDIDO_REALIZADO(1, "Pedido Realizado"),
	PAGAMENTO_CONFIRMADO(2, "Paagamento Confirmado"),
	EM_SEPARACAO(3, "Em separação"),
	EM_TRANSPORTE(4, "Em transporte"),
	CANCELADO(5, "Cancelado");
	
	private int cod;
	private String descricao;
	
	//construtor enum tem q ser do tipo private
	private StatusPedido(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusPedido toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (StatusPedido x:StatusPedido.values()) {
			if (x.cod == cod) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
}
