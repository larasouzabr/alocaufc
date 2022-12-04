package modules.test_services;

import com.alocaufc.buiders.SalaBuilder;
import com.alocaufc.entities.Horario;
import com.alocaufc.entities.Sala;
import com.alocaufc.entities.enums.Bloco;
import com.alocaufc.services.SalaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCadastrarSala {
    @Test
    public void cadastrar_sala_com_sucesso() {
        Sala sala = SalaBuilder.builder()
                .setTitulo("Sala 1")
                .setBloco(Bloco.BLOCO_1)
                .setLugares(53)
                .hasArCondicionado()
                .hasProjetor()
                .build();

        SalaService salaService = new SalaService();
        assertNotNull(salaService.create(sala));
    }
    @Test
    public void erro_ao_cadastrar_sala_com_bloco_nulo() {
        Sala sala = SalaBuilder.builder()
                .setTitulo("Sala 3")
                .setLugares(53)
                .hasArCondicionado()
                .hasProjetor()
                .build();

        SalaService salaService = new SalaService();
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> salaService.create(sala));
        assertEquals("Bloco não pode ser nulo", error.getMessage());
    }
    @Test
    public void erro_ao_criar_sala_que_ja_existe() {
        SalaService salaService = new SalaService();

        Sala sala = salaService.obterPorId(1L);

        for(Horario h: sala.getHorarios()){
            System.out.println(h.getHoraInicio() + " - " + h.getHoraFim());
        }

        Error error = assertThrows(Error.class, () -> salaService.create(sala));
        assertEquals("Sala já cadastrada", error.getMessage());

    }
}