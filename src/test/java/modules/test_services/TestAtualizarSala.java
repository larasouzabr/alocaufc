package modules.test_services;

import modules.services.SalaService;
import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAtualizarSala {
    @Test
    public void atualizarInformacoesRecorrentes() {
        SalaService salaService = new SalaService();

        Sala sala = salaService.obterPorId(2);
        sala.setQtdCadeiras(8);
        sala.setProjetor(false);
        sala.setArCondicionado(false);
        sala.setNumBloco(NumeroBloco.BLOCO_2);

        sala = salaService.update(sala);
        assertEquals(8, sala.getQtdCadeiras());
        assertEquals(false, sala.getProjetor());
        assertEquals(false, sala.getArCondidionado());
    }
}
