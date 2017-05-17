package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		
		BigDecimal precoSessao = sessao.getPreco();
		Integer totalIngresso = sessao.getTotalIngressos();
		Integer ingressosReservados = sessao.getIngressosReservados();
		TipoDeEspetaculo tipoEspetaculo = sessao.getEspetaculo().getTipo();
		
		if ((totalIngresso - ingressosReservados) / totalIngresso.doubleValue() <= tipoEspetaculo.getPercentualMinimo()) {
			precoSessao = precoSessao.add(precoSessao.multiply(BigDecimal.valueOf(tipoEspetaculo.getFatorAumento())));
		} 
		
		if (tipoEspetaculo.equals(TipoDeEspetaculo.BALLET) || tipoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if (sessao.getDuracaoEmMinutos() > 60) {
				precoSessao = precoSessao.add(sessao.getPreco().multiply(BigDecimal.valueOf(tipoEspetaculo.getPercentualDuracao())));
			}
		}

		return precoSessao.multiply(BigDecimal.valueOf(quantidade));
	}

	

}