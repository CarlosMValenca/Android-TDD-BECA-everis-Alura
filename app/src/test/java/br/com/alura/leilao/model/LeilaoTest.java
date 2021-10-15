package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    private Leilao console = new Leilao("Console");
    private Usuario alex = new Usuario("Alex");
    private Usuario fran = new Usuario("Fran");

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // executar ação esperada
        String descrcaoDevolvida = console.getDescricao();
        // testar resultado esperado
        assertEquals("Console", descrcaoDevolvida);
    }

    // [nome do metodo] + [estado do teste] + [resultado esperado]
    // [deve]+[resultado esperado]+[estado de teste]
    @Test
    public void deve_DevolvendoMaiorLance_QuandoRecebeApenasUmLance(){
        // verifica se devolve maior lance com apenas um lance
        console.propoe(new Lance(alex, 200.00));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();
        // O delta pega a diferença entre os valores com ponto flutuante e se ele for maior, significa que os valores são equivalentes.
        assertEquals(200.00, maiorLanceDevolvidoDoConsole, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        // verifica se devolve maior lance com mais de um lance em ordem crescente
        console.propoe(new Lance(alex, 100.00));
        console.propoe(new Lance(fran, 200.00));

        double maiorLanceDevolvidoDoComputador = console.getMaiorLance();

        assertEquals(200.00, maiorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void deve_DevolveMaiorLance_LanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        // verifica se devolve maior lance com mais de um lance em ordem decrescente
        console.propoe(new Lance(alex, 10000.00));
        console.propoe(new Lance(fran, 9000.00));

        double maiorLanceDevolvidoDoCarro = console.getMaiorLance();

        assertEquals(10000.00, maiorLanceDevolvidoDoCarro, 0.0001);
    }

    @Test
    public void deve_DevolvendoMenorLance_QuandoRecebeApenasUmLance(){
        console.propoe(new Lance(alex, 200.00));

        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(200.00, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        console.propoe(new Lance(alex, 100.00));
        console.propoe(new Lance(fran, 200.00));

        double menorLanceDevolvidoDoComputador = console.getMenorLance();

        assertEquals(100.00, menorLanceDevolvidoDoComputador, 0.0001);
    }

    @Test
    public void deve_DevolveMenorLance_LanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        console.propoe(new Lance(alex, 10000.00));
        console.propoe(new Lance(fran, 9000.00));

        double menorLanceDevolvidoDoCarro = console.getMenorLance();

        assertEquals(9000.00, menorLanceDevolvidoDoCarro, 0.0001);
    }
}