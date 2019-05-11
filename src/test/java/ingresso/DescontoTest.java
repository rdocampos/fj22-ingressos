package ingresso;

import br.com.caelum.ingresso.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

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

		Ingresso Ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, new Lugar());

		BigDecimal precoEsperado = new BigDecimal("32.50");

		Assert.assertEquals(precoEsperado, Ingresso.getPreco());
	}

	@Test
	public void deve_conceder_desconto_de_30_por_cento_para_ingressos_de_clientes_de_banco() {

		Ingresso Ingresso = new Ingresso(sessao, TipoDeIngresso.BANCO, new Lugar());

		BigDecimal precoEsperado = new BigDecimal("22.75");

		Assert.assertEquals(precoEsperado, Ingresso.getPreco());

	}

	@Test
	public void deve_conceder_desconto_de_50_por_cento_para_ingressos_de_estudante() {

		Ingresso Ingresso = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, new Lugar());

		BigDecimal precoEsperado = new BigDecimal("16.25");

		Assert.assertEquals(precoEsperado, Ingresso.getPreco());

	}

}
