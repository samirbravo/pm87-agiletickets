package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	
	CINEMA(0.05, 0.10), SHOW(0.05, 0.10), TEATRO(), BALLET(0.50, 0.20, 0.10), ORQUESTRA(0.50, 0.20, 0.10);
	
	private double percentualMinimo;
	private double fatorAumento;
	private double percentualDuracao;
	
	TipoDeEspetaculo(){
		
	}
	
	TipoDeEspetaculo(double percentualMinimo, double fatorAumento){
		this.percentualMinimo = percentualMinimo;
		this.fatorAumento = fatorAumento;
	}
	
	TipoDeEspetaculo(double percentualMinimo, double fatorAumento, double percentualDuracao){
		this.percentualMinimo = percentualMinimo;
		this.fatorAumento = fatorAumento;
		this.percentualDuracao = percentualDuracao;
	}
	
	public double getPercentualMinimo() {
		return percentualMinimo;
	}
	public double getFatorAumento() {
		return fatorAumento;
	}
	public double getPercentualDuracao() {
		return percentualDuracao;
	}
	
	
}
