package com.gabrielbatista.crochedemainha.domain.enums;

public enum Cor {
	VERMELHO(1,"Vermelho"),
	AZUL(2,"Azul"),
	VERDE(3,"##015b38"), 
	ROXO(4,"#60537e"), 
	ROSA(5,"#c7809b"), 
	VINHO(6,"#711c38"), 
	MARROM(7,"#604d49"),
	LARANJA(8,"#cd2312");
	
	private int cod;
	private String descricao;
	
	//construtor enum tem q ser do tipo private
	private Cor(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Cor toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (Cor x:Cor.values()) {
			if (x.cod == cod) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
}
