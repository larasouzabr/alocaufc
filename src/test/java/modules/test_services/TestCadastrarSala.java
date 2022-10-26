package modules.test_services;

import modules.entities.Sala;
import modules.entities.enums.NumeroBloco;
import modules.services.SalaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCadastrarSala {
    @Test
    public void cadastrar_sala_com_sucesso() {
        int qtdCadeiras = 5;
        boolean projetor = true;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(null, NumeroBloco.valueOf(1), numSala, qtdCadeiras, projetor, arCondicionado);

        SalaService salaService = new SalaService();
        assertNotNull(salaService.create(newSala));
    }
    @Test
    public void erro_ao_cadastrar_sala_com_bloco_nulo() {
        int qtdCadeiras = 5;
        boolean projetor = true;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(null, null, numSala, qtdCadeiras, true, arCondicionado);
        SalaService salaService = new SalaService();
//
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> salaService.create(newSala));
        assertEquals("Bloco não pode ser nulo", error.getMessage());
    }
    @Test
    public void erro_ao_criar_sala_que_ja_existe() {
        SalaService salaService = new SalaService();

        Sala sala = salaService.obterPorId(2);

        Error error = assertThrows(Error.class, () -> salaService.create(sala));
        assertEquals("Sala já cadastrada", error.getMessage());

    }
}