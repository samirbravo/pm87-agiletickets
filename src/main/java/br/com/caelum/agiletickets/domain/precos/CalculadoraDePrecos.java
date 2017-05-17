package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;

		if (sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.CINEMA)
				|| sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.SHOW)) {
			preco = calcularPrecoGeral(sessao, 0.05, 0.10);
		} else if (sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.BALLET)
				|| sessao.getEspetaculo().getTipo().equals(TipoDeEspetaculo.ORQUESTRA)) {
			preco = calcularPrecoComTempo(sessao, 0.50, 0.20, 60, 0.10);
		} else {
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calcularPrecoGeral(Sessao sessao, double percentualMinimo, double fatorAumento) {

		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())
				/ sessao.getTotalIngressos().doubleValue() <= percentualMinimo) {
			return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(fatorAumento)));
		} else {
			return sessao.getPreco();
		}
	}
	
	private static BigDecimal calcularPrecoComTempo(Sessao sessao, double percentualMinimo, double fatorAumento, int duracao, double percentualDuracao){
		BigDecimal preco;
		
		preco = calcularPrecoGeral(sessao, percentualMinimo, fatorAumento);
		
		if (sessao.getDuracaoEmMinutos() > duracao) {
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(percentualDuracao)));
		}
		
		return preco;
	}

}