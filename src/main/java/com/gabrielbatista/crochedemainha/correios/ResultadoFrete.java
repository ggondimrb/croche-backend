package com.gabrielbatista.crochedemainha.correios;

import java.util.List;

import com.gabrielbatista.crochedemainha.correios.embalagem.Embalagem;
import com.gabrielbatista.crochedemainha.correios.frete.ServicoXml;

public class ResultadoFrete {

	private Embalagem embalagem;
	
	private List<ServicoXml> servicosXml;
	
	public ResultadoFrete(Embalagem embalagem, List<ServicoXml> servicosXml) {
		super();
		this.embalagem = embalagem;
		this.servicosXml = servicosXml;
	}

	public Embalagem getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}

	public List<ServicoXml> getServicosXml() {
		return servicosXml;
	}

	public void setServicosXml(List<ServicoXml> servicosXml) {
		this.servicosXml = servicosXml;
	}

	@Override
	public String toString() {
		return "ResultadoFrete [embalagem=" + embalagem + ", servicosXml=" + servicosXml + "]";
	}
	
	
}