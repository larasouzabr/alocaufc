package modules.test_services;

import com.alocaufc.entities.enums.Bloco;
import com.alocaufc.services.SalaService;
import com.alocaufc.entities.Sala;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAtualizarSala {
    @Test
    public void atualiza_informacoes_recorrentes() {
        SalaService salaService = new SalaService();

        Sala sala = salaService.obterPorId(1L);
        sala.setLugares(20);
        sala.setProjetor(false);
        sala.setArCondicionado(false);
        sala.setBloco(Bloco.BLOCO_2);

        sala = salaService.update(sala);
        assertEquals(20, sala.getLugares());
        assertEquals(false, sala.getProjetor());
        assertEquals(false, sala.getArCondicionado());
    }
}
