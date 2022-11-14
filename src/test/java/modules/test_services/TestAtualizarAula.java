package modules.test_services;

import modules.entities.Aula;
import modules.entities.Sala;
import modules.entities.enums.DiaDaSemana;
import modules.services.AulaService;
import modules.services.SalaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAtualizarAula {

    @Test
    public void atualiza_informacoes_recorrentes(){
        SalaService salaService = new SalaService();
        AulaService aulaService = new AulaService();

        Sala sala = salaService.obterPorId(1);
        Aula aula = aulaService.obterPorId(2);
        aula.setSala(sala);
        aula.setDisciplina("FBD");
        aula.setTurma("02A");
        aula.setDiaDaSemana(DiaDaSemana.QUARTA);
        aula.setHorarioAula("13:30");

        aula = aulaService.update(aula);
        assertEquals(sala, aula.getSala());
        assertEquals("FBD", aula.getDisciplina());
        assertEquals("02A", aula.getTurma());
        assertEquals("13:30", aula.getHorarioAula());
        assertEquals(DiaDaSemana.QUARTA, aula.getDiaDaSemana());
    }

    @Test
    public void erro_ao_atualizar_aula_para_sala_lotada() {
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(1);

        AulaService aulaService = new AulaService();

        Aula aula = aulaService.obterPorId(1);
        aula.setSala(sala);
        aula.setHorarioAula("10:00");
        aula = aulaService.update(aula);

        Aula aula2 = aulaService.obterPorId(2);
        aula.setSala(sala);
        aula.setHorarioAula("10:30");

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> aulaService.update(aula2));
        assertEquals("Sala já está lotada para o horário escolhido", error.getMessage());
    }
}
