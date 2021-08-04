package com.gabrielbatista.crochedemainha.resources;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbatista.crochedemainha.correios.CorreiosFrete;
import com.gabrielbatista.crochedemainha.correios.ResultadoFrete;
import com.gabrielbatista.crochedemainha.correios.embalagem.Embalagem;
import com.gabrielbatista.crochedemainha.correios.embalagem.Item;
import com.gabrielbatista.crochedemainha.correios.frete.ServicoXml;
import com.gabrielbatista.crochedemainha.correios.frete.TipoServico;

@RestController
@RequestMapping(value="/cep")
public class FreteResource {
	
	CorreiosFrete correiosFrete;
	
	@GetMapping("/{cep}/{peso}")
	@ResponseStatus(HttpStatus.OK)
	public ServicoXml calculatePriceAndDeadline(@PathVariable String cep, @PathVariable float peso) throws UnsupportedOperationException {
		
		correiosFrete = 
				CorreiosFrete
				.novo()
				.comTipoServico(TipoServico.PAC.getCodigo())
				.comCepOrigemDestino("53030020", cep)
				.addEmbalagem(Embalagem.CORREIOS_TIPO_1)
				.addItem(new Item(10, 10, 10, peso), 1)
				;
		
		//correiosFrete.comValorDeclarado(new BigDecimal(20.51));
		
		List<ResultadoFrete> lista = correiosFrete.calcPrecoPrazo();
		
		if(lista.isEmpty()) {
			return null;
		}
		
		return lista.get(0).getServicosXml().get(0);
	}

}
