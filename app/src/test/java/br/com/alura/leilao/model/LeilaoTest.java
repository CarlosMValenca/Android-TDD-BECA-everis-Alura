package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");
    private final Usuario FRAN = new Usuario("Fran");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // executar ação esperada
        String descrcaoDevolvida = CONSOLE.getDescricao();
        // testar resultado esperado
        assertEquals("Console", descrcaoDevolvida);
    }

    // [nome do metodo] + [estado do teste] + [resultado esperado]
    // [deve]+[resultado esperado]+[estado de teste]
    @Test
    public void deve_DevolvendoMaiorLance_QuandoRecebeApenasUmLance(){
        // verifica se devolve maior lance com apenas um lance
        CONSOLE.propoe(new Lance(ALEX, 200.00));

        double maiorLanceDevolvidoDoConsole = CONSOLE.getMaiorLance();
        // O delta pega a diferença entre os valores com ponto flutuante e se ele for maior, significa que os valores são equivalentes.
        assertEquals(200.00, maiorLanceDevolvidoDoConsole, DELTA);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        // verifica se devolve maior lance com mais de um lance em ordem crescente
        CONSOLE.propoe(new Lance(ALEX, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.00));

        double maiorLanceDevolvidoDoComputador = CONSOLE.getMaiorLance();

        assertEquals(200.00, maiorLanceDevolvidoDoComputador, DELTA);
    }

    @Test
    public void deve_DevolvendoMenorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.00));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(200.00, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(ALEX, 100.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.00));

        double menorLanceDevolvidoDoComputador = CONSOLE.getMenorLance();

        assertEquals(100.00, menorLanceDevolvidoDoComputador, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(ALEX, 200.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.00));
        CONSOLE.propoe(new Lance(ALEX, 400.00));

        List<Lance> tresMaioresLancesDevolvidos =  CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.00, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.00));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.00, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances(){
        CONSOLE.propoe(new Lance(ALEX, 300.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.00));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.00, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresLances_QuandoReceberMaisDeTresLances(){
        CONSOLE.propoe(new Lance(ALEX, 300.00));
        CONSOLE.propoe(new Lance(FRAN, 400.00));
        CONSOLE.propoe(new Lance(ALEX, 500.00));
        CONSOLE.propoe(new Lance(FRAN, 600.00));

        final List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600.00, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500.00, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.00, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(ALEX, 700.00));

        final List<Lance> tresMaioresLancesDevolvidosParaCincoLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.00, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.00, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.00, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.00, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance(){
        exception.expect(RuntimeException.class);
        exception.expectMessage("Lance foi menor que maior lance");
        CONSOLE.propoe(new Lance(ALEX, 500.00));
        CONSOLE.propoe(new Lance(FRAN, 400.00));

    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance(){
        CONSOLE.propoe(new Lance(ALEX, 500.00));
        try {
            CONSOLE.propoe(new Lance(ALEX, 600.00));
            fail("Era esperada uma RuntimeException");
        } catch(RuntimeException exception){
            assertEquals("Mesmo usuario do ultimo lance", exception.getMessage());
        }

    }

    @Test
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances(){
        CONSOLE.propoe(new Lance(ALEX, 100.00));
        CONSOLE.propoe(new Lance(FRAN, 200.00));
        CONSOLE.propoe(new Lance(ALEX, 300.00));
        CONSOLE.propoe(new Lance(FRAN, 400.00));
        CONSOLE.propoe(new Lance(ALEX, 500.00));
        CONSOLE.propoe(new Lance(FRAN, 600.00));
        CONSOLE.propoe(new Lance(ALEX, 700.00));
        CONSOLE.propoe(new Lance(FRAN, 800.00));
        CONSOLE.propoe(new Lance(ALEX, 900.00));
        CONSOLE.propoe(new Lance(FRAN, 1000.00));

        try {
            CONSOLE.propoe(new Lance(ALEX, 1100.00));
            fail("Era esperada uma RuntimeException");
        } catch (RuntimeException exception){
            assertEquals("Usuario ja deu cinco lances", exception.getMessage());
        }



    }
}