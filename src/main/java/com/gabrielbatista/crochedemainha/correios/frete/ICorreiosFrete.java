package com.gabrielbatista.crochedemainha.correios.frete;

import java.util.List;

import com.gabrielbatista.crochedemainha.correios.util.CorreiosFreteException;

public interface ICorreiosFrete {
	
	public List<ServicoXml> calcPrecoPrazo(CorreiosFreteDTO correiosFreteDTO) throws CorreiosFreteException;
	
}