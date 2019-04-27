package ingresso;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
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

}
