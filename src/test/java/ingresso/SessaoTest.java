package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoTest {

	@Test
	public void deve_gerar_horario_termino_da_sessao() {
		Filme filme = new Filme();
		filme.setDuracao(120);

		Sessao sessao = new Sessao();
		sessao.setFilme(filme);
		sessao.setHorario(LocalTime.of(10, 0, 0));

		LocalTime horaFim = sessao.getHorarioTermino();
		Assert.assertEquals(LocalTime.of(12, 0, 0), horaFim);
	}

	@Test
	public void o_preco_da_sessao_deve_ser_igual_a_soma_do_preco_da_sala_mais_o_preco_do_filme() {

		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
		Filme filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI",
				new BigDecimal("12.0"));

		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(
				filme.getPreco());

		Sessao sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);

		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
	}

}
