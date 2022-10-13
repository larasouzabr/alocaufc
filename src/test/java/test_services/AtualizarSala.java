package test_services;

import modules.services.UpdateSalaService;
import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtualizarSala {

    int qtdCadeiras = 5;
    boolean projetor = true;
    boolean arCondicionado = true;
    int numSala = 1;
    Sala sala = new Sala(null, NumeroBloco.BLOCO_1, numSala, qtdCadeiras, projetor, arCondicionado);

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
