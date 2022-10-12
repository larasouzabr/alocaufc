package test_services;

import modules.services.CreateSalaService;
import modules.entities.Sala;
import modules.numeroBloco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCadastrarSala{
    @Test
    public void cadastrar_sala_com_sucesso() {
        int qtdCadeiras = 5;
        int projetor = 2;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(null, numeroBloco.BLOCO1, numSala, qtdCadeiras, projetor, arCondicionado);

        CreateSalaService createSalaService = new CreateSalaService();
        assertNotNull(createSalaService.execute(newSala));
    }
    @Test
    public void erro_ao_cadastrar_sala_com_bloco_nulo() {
        int qtdCadeiras = 5;
        int projetor = 2;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(null, null, numSala, qtdCadeiras, projetor, arCondicionado);
        CreateSalaService createSalaService = new CreateSalaService();
//
        Error error = assertThrows(Error.class, () -> createSalaService.execute(newSala));
        assertEquals("Bloco não pode ser nulo", error.getMessage());
    }
    @Test
    public void erro_ao_criar_sala_que_ja_existe() {
        int qtdCadeiras = 5;
        int projetor = 2;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(null, numeroBloco.BLOCO1, numSala, qtdCadeiras, projetor, arCondicionado);
        CreateSalaService createSalaService = new CreateSalaService();
//
        Error error = assertThrows(Error.class, () -> createSalaService.execute(newSala));
        assertEquals("Sala já cadastrada", error.getMessage());
    }
}