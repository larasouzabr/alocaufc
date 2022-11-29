package modules.test_services;

import modules.entities.Sala;
import modules.entities.enums.Bloco;
import modules.services.SalaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCadastrarSala {
    @Test
    public void cadastrar_sala_com_sucesso() {
        Sala newSala = new Sala(
                null,
                "Sala 1",
                Bloco.BLOCO_1.getNumero(),
                40,
                true,
                true,
                null,
                ""
        );
        SalaService salaService = new SalaService();
        assertNotNull(salaService.create(newSala));
    }
    @Test
    public void erro_ao_cadastrar_sala_com_bloco_nulo() {
        Sala newSala = new Sala(
                null,
                "Sala 1",
                null,
                40,
                true,
                true,
                null,
                "")
        ;

        SalaService salaService = new SalaService();
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> salaService.create(newSala));
        assertEquals("Bloco não pode ser nulo", error.getMessage());
    }
    @Test
    public void erro_ao_criar_sala_que_ja_existe() {
        SalaService salaService = new SalaService();

        Sala sala = salaService.obterPorId(1L);

        Error error = assertThrows(Error.class, () -> salaService.create(sala));
        assertEquals("Sala já cadastrada", error.getMessage());

    }
}