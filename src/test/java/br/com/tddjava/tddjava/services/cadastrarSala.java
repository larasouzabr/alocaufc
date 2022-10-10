package br.com.tddjava.tddjava.services;

import br.com.tddjava.tddjava.modules.sala.services.CreateSalaService;
import br.com.tddjava.tddjava.modules.sala.entities.Sala;
import br.com.tddjava.tddjava.modules.numeroBloco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadastrarSala{
    @Test
    public void cadastrarSalaComSucesso() {
        int qtdCadeiras = 5;
        int projetor = 2;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(numeroBloco.BLOCO1, numSala, qtdCadeiras, projetor, arCondicionado);

        CreateSalaService createSalaService = new CreateSalaService();
        assertNotNull(createSalaService.execute(newSala));
    }
}