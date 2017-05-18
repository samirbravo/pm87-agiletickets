package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarQueCriaUmaUnicaSessaoSeDataInicialEhIgualADataFinalEPeriodicidadeDiaria(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate dataHoje = LocalDate.now();
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = ivete.criaSessoes(dataHoje, dataHoje, horario, periodicidade);
		
		assertEquals(1, sessoes.size());
	}
	
	@Test
	public void deveInformarQueCriaUmaUnicaSessaoSeDataInicialEhIgualADataFinalEPeriodicidadeSemanal(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate dataHoje = LocalDate.now();
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = ivete.criaSessoes(dataHoje, dataHoje, horario, periodicidade);
		
		assertEquals(1, sessoes.size());
	}
	
	@Test
	public void deveInformarQueCriaSessaoComDiaDiferenteEm5DiasPeriodoDiario(){
		
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusDays(4);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(inicio, sessoes.get(0).getInicio().toLocalDate());
		assertEquals(inicio.plusDays(1), sessoes.get(1).getInicio().toLocalDate());
		assertEquals(inicio.plusDays(2), sessoes.get(2).getInicio().toLocalDate());
		assertEquals(inicio.plusDays(3), sessoes.get(3).getInicio().toLocalDate());
		assertEquals(inicio.plusDays(4), sessoes.get(4).getInicio().toLocalDate());
	}
	
	
	@Test
	public void deveInformarQueCriaSessaoComDiaDiferenteEm5SemanasPeriodoSemanal(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusWeeks(4);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(inicio, sessoes.get(0).getInicio().toLocalDate());
		assertEquals(inicio.plusWeeks(1), sessoes.get(1).getInicio().toLocalDate());
		assertEquals(inicio.plusWeeks(2), sessoes.get(2).getInicio().toLocalDate());
		assertEquals(inicio.plusWeeks(3), sessoes.get(3).getInicio().toLocalDate());
		assertEquals(inicio.plusWeeks(4), sessoes.get(4).getInicio().toLocalDate());
	}
	
	
	@Test
	public void deveInformarSeCria5SessoesEm5Diarias(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusDays(4);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(5, sessoes.size());
	}
	
	@Test
	public void deveInformarSeCria1SessaoEm1Semana(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusDays(6);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(1, sessoes.size());
	}
	
	@Test
	public void deveInformarSeCria2SessoesEm2Semanas(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusWeeks(1);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(2, sessoes.size());
	}
	
	@Test
	public void deveInformarSeCria5SessoesEm5Semanas(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.plusWeeks(4);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(5, sessoes.size());
	}
	
	@Test
	public void deveInformarSeDataInicialEhMaiorQueDataFinalEmDiaria(){
		Espetaculo ivete = new Espetaculo();
		
		LocalDate inicio = LocalDate.now();
		LocalDate fim = inicio.minusDays(1);
		LocalTime horario = LocalTime.now();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = ivete.criaSessoes(inicio, fim, horario, periodicidade);
		
		assertEquals(0, sessoes.size());
	}
	
	
	
	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}
	
	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
