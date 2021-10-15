package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");

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
    public void deve_DevolveMaiorLance_LanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        // verifica se devolve maior lance com mais de um lance em ordem decrescente
        CONSOLE.propoe(new Lance(ALEX, 10000.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 9000.00));

        double maiorLanceDevolvidoDoCarro = CONSOLE.getMaiorLance();

        assertEquals(10000.00, maiorLanceDevolvidoDoCarro, DELTA);
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
    public void deve_DevolveMenorLance_LanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        CONSOLE.propoe(new Lance(ALEX, 10000.00));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 9000.00));

        double menorLanceDevolvidoDoCarro = CONSOLE.getMenorLance();

        assertEquals(9000.00, menorLanceDevolvidoDoCarro, DELTA);
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
}