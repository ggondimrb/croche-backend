//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.11 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2020.12.22 às 08:51:08 PM BRT 
//


package com.gabrielbatista.crochedemainha.schemas.correios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "CalcPrecoPrazo")
public class PrecoPrazoDetailsRequest {

    @XmlElement(required = true)
    protected int nCdFormato;
    
    protected double nVlComprimento;
    
    protected double nVlAltura;
    
    protected double nVlLargura;
    
    protected double nVlDiametro;
    
    protected double nVlValorDeclarado;

	public int getnCdFormato() {
		return nCdFormato;
	}

	public void setnCdFormato(int nCdFormato) {
		this.nCdFormato = nCdFormato;
	}

	public double getnVlComprimento() {
		return nVlComprimento;
	}

	public void setnVlComprimento(double nVlComprimento) {
		this.nVlComprimento = nVlComprimento;
	}

	public double getnVlAltura() {
		return nVlAltura;
	}

	public void setnVlAltura(double nVlAltura) {
		this.nVlAltura = nVlAltura;
	}

	public double getnVlLargura() {
		return nVlLargura;
	}

	public void setnVlLargura(double nVlLargura) {
		this.nVlLargura = nVlLargura;
	}

	public double getnVlDiametro() {
		return nVlDiametro;
	}

	public void setnVlDiametro(double nVlDiametro) {
		this.nVlDiametro = nVlDiametro;
	}

	public double getnVlValorDeclarado() {
		return nVlValorDeclarado;
	}

	public void setnVlValorDeclarado(double nVlValorDeclarado) {
		this.nVlValorDeclarado = nVlValorDeclarado;
	}
    
}
