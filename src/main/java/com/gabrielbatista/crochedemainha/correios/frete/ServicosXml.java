package com.gabrielbatista.crochedemainha.correios.frete;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@XmlRootElement(name = "Servicos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServicosXml {
	
	static Logger logger = Logger.getLogger(ServicosXml.class);
	
	@XmlElement(name = "cServico")
	private List<ServicoXml> servicos = new ArrayList<ServicoXml>();
	
	public List<ServicoXml> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<ServicoXml> servicos) {
		this.servicos = servicos;
	}
	
	public static List<ServicoXml> fromXml(String xml){
		
		ServicosXml servicos = new ServicosXml();
		
		try {
			
			JAXBContext jaxbContext     = JAXBContext.newInstance( ServicosXml.class );
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			servicos = (ServicosXml) jaxbUnmarshaller.unmarshal(new StringReader(xml));
			
		} catch (Exception e) {
			logger.error("erro ao ler xml: "+xml, e);
		}
		
		return servicos.getServicos();
	}
	
}