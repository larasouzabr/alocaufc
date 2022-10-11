package test_services;

import modules.sala.services.CreateSalaService;
import modules.sala.entities.Sala;
import modules.numeroBloco;
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
    @Test
    public void erroAoCadastrarSalaComBlocoNulo() {
        int qtdCadeiras = 5;
        int projetor = 2;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(null, numSala, qtdCadeiras, projetor, arCondicionado);
        CreateSalaService createSalaService = new CreateSalaService();

        Error error = assertThrows(Error.class, () -> createSalaService.execute(newSala));
        assertEquals("Bloco não pode ser nulo", error.getMessage());
    }
    @Test
    public void erroAoCriarSalaQueJaExiste() {
        int qtdCadeiras = 5;
        int projetor = 2;
        boolean arCondicionado = true;
        int numSala = 1;

        Sala newSala = new Sala(numeroBloco.BLOCO1, numSala, qtdCadeiras, projetor, arCondicionado);
        CreateSalaService createSalaService = new CreateSalaService();

        Error error = assertThrows(Error.class, () -> createSalaService.execute(newSala));
        assertEquals("Sala já cadastrada", error.getMessage());
    }
}