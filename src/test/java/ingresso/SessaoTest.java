package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.caelum.ingresso.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SessaoTest {

    private Sala sala;
    private Filme filme;
    private Sessao sessao;

    @Before
    public void construtorDependencias() {
        sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
        filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI",
                new BigDecimal("12.0"));
        sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);

    }

    @Test
    public void deve_gerar_horario_termino_da_sessao() {
        filme.setDuracao(120);

        sessao.setHorario(LocalTime.of(10, 0, 0));

        LocalTime horaFim = sessao.getHorarioTermino();
        Assert.assertEquals(LocalTime.of(12, 0, 0), horaFim);
    }

    @Test
    public void o_preco_da_sessao_deve_ser_igual_a_soma_do_preco_da_sala_mais_o_preco_do_filme() {

        BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(
                filme.getPreco());

        Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
    }

    @Test
    public void garante_que_o_lugar_A1_esta_ocupado_e_os_lugares_A2_e_A3_disponiveis() {
        Lugar a1 = new Lugar("A", 1);
        Lugar a2 = new Lugar("A", 2);
        Lugar a3 = new Lugar("A", 3);

        Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);

        Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());

        sessao.setIngressos(ingressos);

        Assert.assertFalse(sessao.isDisponivel(a1));
        Assert.assertTrue(sessao.isDisponivel(a2));
        Assert.assertTrue(sessao.isDisponivel(a3));
    }

}
