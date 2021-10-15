package br.com.alura.leilao.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

    @Test
    public void getDescricao() {
        // criar cenário de teste
        Leilao console =  new Leilao("Console");
        // executar ação esperada
        String descrcaoDevolvida = console.getDescricao();
        // testar resultado esperado
        assertEquals("Console", descrcaoDevolvida);
    }

    @Test
    public void getMaiorLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.00));

        double maiorLanceDevolvido = console.getMaiorLance();
        // O delta pega a diferença entre os valores com ponto flutuante e se ele for maior, significa que os valores são equivalentes.
        assertEquals(200.00, maiorLanceDevolvido, 0.0001);
    }
}