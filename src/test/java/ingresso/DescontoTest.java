package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.DescontoParaEstudantes;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public class DescontoTest {

	private Filme filme;
	private Sala sala;
	private Sessao sessao;

	@Before
	public void preparaSessoes() {
		this.filme = new Filme("Rogue	One", Duration.ofMinutes(120), "SCI-FI",
				new BigDecimal("12.0"));
		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}

	@Test
	public void nao_deve_conceder_desconto_para_ingresso_normal() {

		Ingresso Ingresso = new Ingresso(sessao, new SemDesconto());

		BigDecimal precoEsperado = new BigDecimal("32.50");

		Assert.assertEquals(precoEsperado, Ingresso.getPreco());
	}

	@Test
	public void deve_conceder_desconto_de_30_por_cento_para_ingressos_de_clientes_de_banco() {

		Ingresso Ingresso = new Ingresso(sessao, new DescontoParaBancos());

		BigDecimal precoEsperado = new BigDecimal("22.75");

		Assert.assertEquals(precoEsperado, Ingresso.getPreco());

	}

	@Test
	public void deve_conceder_desconto_de_50_por_cento_para_ingressos_de_estudante() {

		Ingresso Ingresso = new Ingresso(sessao, new DescontoParaEstudantes());

		BigDecimal precoEsperado = new BigDecimal("16.25");

		Assert.assertEquals(precoEsperado, Ingresso.getPreco());

	}

}
