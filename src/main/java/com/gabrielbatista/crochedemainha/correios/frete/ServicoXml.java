package com.gabrielbatista.crochedemainha.correios.frete;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServicoXml {
	
	@XmlElement(name = "Codigo")
	private String codigo;
	
	@XmlElement(name = "Valor")
	private String valor;
	
	@XmlElement(name = "PrazoEntrega")
	private String prazoEntrega;
	
	@XmlElement(name = "ValorSemAdicionais")
	private String valorSemAdicionais;
	
	@XmlElement(name = "ValorMaoPropria")
	private String valorMaoPropria;

	@XmlElement(name = "ValorAvisoRecebimento")
	private String valorAvisoRecebimento;
	
	@XmlElement(name = "ValorValorDeclarado")
	private String valorValorDeclarado;
	
	@XmlElement(name = "EntregaDomiciliar")
	private String entregaDomiciliar;
	
	@XmlElement(name = "EntregaSabado")
	private String entregaSabado;
	
	private String obsFim;
	
	@XmlElement(name = "Erro")
	private String erro;
	
	@XmlElement(name = "MsgErro")
	private String msgErro;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getValorSemAdicionais() {
		return valorSemAdicionais;
	}

	public void setValorSemAdicionais(String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}

	public String getValorMaoPropria() {
		return valorMaoPropria;
	}

	public void setValorMaoPropria(String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}

	public String getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}

	public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}

	public String getValorValorDeclarado() {
		return valorValorDeclarado;
	}

	public void setValorValorDeclarado(String valorValorDeclarado) {
		this.valorValorDeclarado = valorValorDeclarado;
	}

	public String getEntregaDomiciliar() {
		return entregaDomiciliar;
	}

	public void setEntregaDomiciliar(String entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}

	public String getEntregaSabado() {
		return entregaSabado;
	}

	public void setEntregaSabado(String entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	public String getObsFim() {
		return obsFim;
	}

	public void setObsFim(String obsFim) {
		this.obsFim = obsFim;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	@Override
	public String toString() {
		return "ServicoXml [codigo=" + codigo + ", valor=" + valor + ", prazoEntrega=" + prazoEntrega
				+ ", valorSemAdicionais=" + valorSemAdicionais + ", valorMaoPropria=" + valorMaoPropria
				+ ", valorAvisoRecebimento=" + valorAvisoRecebimento + ", valorValorDeclarado=" + valorValorDeclarado
				+ ", entregaDomiciliar=" + entregaDomiciliar + ", entregaSabado=" + entregaSabado + ", obsFim=" + obsFim
				+ ", erro=" + erro + ", msgErro=" + msgErro + "]";
	}
	
}
