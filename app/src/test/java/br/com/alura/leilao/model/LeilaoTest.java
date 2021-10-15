package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        // criar cenário de teste
        Leilao console =  new Leilao("Console");
        // executar ação esperada
        String descrcaoDevolvida = console.getDescricao();
        // testar resultado esperado
        assertEquals("Console", descrcaoDevolvida);
    }

    // [nome do metodo] + [estado do teste] + [resultado esperado]
    @Test
    public void getMaiorLanceQuandoREcebeApenasUmLanceDevolvendoMaiorLance(){
        // verifica se devolve maior lance com apenas um lance
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.00));

        double maiorLanceDevolvidoDoConsole = console.getMaiorLance();
        // O delta pega a diferença entre os valores com ponto flutuante e se ele for maior, significa que os valores são equivalentes.
        assertEquals(200.00, maiorLanceDevolvidoDoConsole, 0.0001);

    }

    @Test
    public void getMaiorLanceQuandoRecebeMaisDeUmLanceEmOrdemCrescenteDevolveMaiorLance(){
        // verifica se devolve maior lance com mais de um lance em ordem crescente
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.00));
        computador.propoe(new Lance(new Usuario("Fran"), 200.00));

        double maiorLanceDevolvidoDoComputador = computador.getMaiorLance();

        assertEquals(200.00, maiorLanceDevolvidoDoComputador, 0.0001);

    }

    @Test
    public void getMaiorLanceLanceQuandoRecebeMaisDeUmLanceEmOrdemDecrescenteDevolveMaiorLance(){
        // verifica se devolve maior lance com mais de um lance em ordem decrescente
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 10000.00));
        carro.propoe(new Lance(new Usuario("Fran"), 9000.00));

        double maiorLanceDevolvidoDoCarro = carro.getMaiorLance();

        assertEquals(10000.00, maiorLanceDevolvidoDoCarro, 0.0001);
    }
}