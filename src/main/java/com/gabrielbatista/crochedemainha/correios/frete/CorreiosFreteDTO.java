package com.gabrielbatista.crochedemainha.correios.frete;

import java.math.BigDecimal;

import com.gabrielbatista.crochedemainha.correios.embalagem.Embalagem;

public class CorreiosFreteDTO {
	
	public static final BigDecimal VALOR_DECLARADO_MINIMO = new BigDecimal(18.5);
	
	private String idConsulta;
	
	private String nCdEmpresa = "";
	
	private String sDsSenha = "";
	
	private String StrRetorno = "xml";
	
	private String nIndicaCalculo = "3";
	
	private String nCdServico;
	
	private String sCepOrigem;
	
	private String sCepDestino;
	
	private BigDecimal nVlPeso = new BigDecimal(0.3);//kg
	
	private Integer nCdFormato = 1;//caixa/pacote=1, rolo/prisma=2, Envelope=3
	
	private BigDecimal nVlComprimento = new BigDecimal(16);//cm
	
	private BigDecimal nVlAltura = new BigDecimal(2);//cm
	
	private BigDecimal nVlLargura = new BigDecimal(11);//cm
	
	private BigDecimal nVlDiametro;//cm
	
	private String sCdMaoPropria = "n";
	
	private BigDecimal nVlValorDeclarado;
	
	private String sCdAvisoRecebimento = "n";
	
	public CorreiosFreteDTO() {
		super();
	}
	
	
	
	public String getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getStrRetorno() {
		return StrRetorno;
	}

	public void setStrRetorno(String strRetorno) {
		StrRetorno = strRetorno;
	}

	public String getnIndicaCalculo() {
		return nIndicaCalculo;
	}

	public void setnIndicaCalculo(String nIndicaCalculo) {
		this.nIndicaCalculo = nIndicaCalculo;
	}

	public String getnCdEmpresa() {
		return nCdEmpresa;
	}

	public void setnCdEmpresa(String nCdEmpresa) {
		this.nCdEmpresa = nCdEmpresa;
	}

	public String getsDsSenha() {
		return sDsSenha;
	}

	public void setsDsSenha(String sDsSenha) {
		this.sDsSenha = sDsSenha;
	}

	public String getnCdServico() {
		return nCdServico;
	}

	public void setnCdServico(String nCdServico) {
		this.nCdServico = nCdServico;
	}

	public String getsCepOrigem() {
		return sCepOrigem;
	}

	public void setsCepOrigem(String sCepOrigem) {
		this.sCepOrigem = sCepOrigem;
	}

	public String getsCepDestino() {
		return sCepDestino;
	}

	public void setsCepDestino(String sCepDestino) {
		this.sCepDestino = sCepDestino;
	}

	public BigDecimal getnVlPeso() {
		return nVlPeso;
	}

	public void setnVlPeso(BigDecimal nVlPeso) {
		this.nVlPeso = nVlPeso;
	}

	public Integer getnCdFormato() {
		return nCdFormato;
	}

	public void setnCdFormato(Integer nCdFormato) {
		this.nCdFormato = nCdFormato;
	}

	public BigDecimal getnVlComprimento() {
		return nVlComprimento;
	}

	public void setnVlComprimento(BigDecimal nVlComprimento) {
		this.nVlComprimento = nVlComprimento;
	}

	public BigDecimal getnVlAltura() {
		return nVlAltura;
	}

	public void setnVlAltura(BigDecimal nVlAltura) {
		this.nVlAltura = nVlAltura;
	}

	public BigDecimal getnVlLargura() {
		return nVlLargura;
	}

	public void setnVlLargura(BigDecimal nVlLargura) {
		this.nVlLargura = nVlLargura;
	}

	public BigDecimal getnVlDiametro() {
		return nVlDiametro;
	}

	public void setnVlDiametro(BigDecimal nVlDiametro) {
		this.nVlDiametro = nVlDiametro;
	}

	public String getsCdMaoPropria() {
		return sCdMaoPropria;
	}

	public void setsCdMaoPropria(String sCdMaoPropria) {
		this.sCdMaoPropria = sCdMaoPropria;
	}

	public BigDecimal getnVlValorDeclarado() {
		return nVlValorDeclarado;
	}

	public void setnVlValorDeclarado(BigDecimal nVlValorDeclarado) {
		this.nVlValorDeclarado = nVlValorDeclarado;
	}

	public String getsCdAvisoRecebimento() {
		return sCdAvisoRecebimento;
	}

	public void setsCdAvisoRecebimento(String sCdAvisoRecebimento) {
		this.sCdAvisoRecebimento = sCdAvisoRecebimento;
	}

	@Override
	public String toString() {
		return "CorreiosFreteDTO [idConsulta=" + idConsulta + ", nCdEmpresa=" + nCdEmpresa + ", sDsSenha=" + sDsSenha
				+ ", StrRetorno=" + StrRetorno + ", nIndicaCalculo=" + nIndicaCalculo + ", nCdServico=" + nCdServico
				+ ", sCepOrigem=" + sCepOrigem + ", sCepDestino=" + sCepDestino + ", nVlPeso=" + nVlPeso
				+ ", nCdFormato=" + nCdFormato + ", nVlComprimento=" + nVlComprimento + ", nVlAltura=" + nVlAltura
				+ ", nVlLargura=" + nVlLargura + ", nVlDiametro=" + nVlDiametro + ", sCdMaoPropria=" + sCdMaoPropria
				+ ", nVlValorDeclarado=" + nVlValorDeclarado + ", sCdAvisoRecebimento=" + sCdAvisoRecebimento + "]";
	}
	
	public Boolean ehValorDeclaradoValido() {
		
		if( this.nVlValorDeclarado == null ) {
			return true;
		}
		
		Boolean ehMaiorQueMinimo = this.nVlValorDeclarado.compareTo(VALOR_DECLARADO_MINIMO) == 1;
		
		if( ehMaiorQueMinimo ) {
			return true;
		}
		
		Boolean ehIgualAoMinimo = this.nVlValorDeclarado.compareTo(VALOR_DECLARADO_MINIMO) == 0;
		
		if( ehIgualAoMinimo ) {
			return true;
		}
		
		return false;
	}
	
	public void comEmbalagem(Embalagem embalagem) {
		
		this.idConsulta = embalagem.getId();
		
		this.nVlComprimento = new BigDecimal(embalagem.getDimensoes().getComprimento());
		this.nVlLargura = new BigDecimal(embalagem.getDimensoes().getLargura());
		this.nVlAltura = new BigDecimal(embalagem.getDimensoes().getAltura());
		
		this.nVlPeso = new BigDecimal(embalagem.calcularPeso());
		
	}
	
}