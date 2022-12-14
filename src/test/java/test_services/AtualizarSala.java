package test_services;

import modules.sala.services.UpdateSalaService;
import modules.sala.entities.Sala;
import modules.numeroBloco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtualizarSala {

    int qtdCadeiras = 5;
    int projetor = 2;
    boolean arCondicionado = true;
    int numSala = 1;
    Sala sala = new Sala(numeroBloco.BLOCO1, numSala, qtdCadeiras, projetor, arCondicionado);

    @Test
    public void atualizarInformacoesRecorrentes() {
        UpdateSalaService updateSalaService = new UpdateSalaService();
        assertNotNull(updateSalaService.atualizaQtdCadeiras(sala, 8));
        assertNotNull(updateSalaService.atualizaProjetor(sala, 1));
        assertNotNull(updateSalaService.atualizaArCondicionado(sala, false));
        assertEquals(8, sala.getQtdCadeiras());
        assertEquals(1, sala.getProjetor());
        assertEquals(false, sala.getArCondidionado());
    }
}
