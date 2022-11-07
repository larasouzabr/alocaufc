package modules.test_services;

import modules.entities.Sala;
import modules.entities.enums.DiaDaSemana;
import modules.entities.enums.NumeroBloco;
import modules.services.SalaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAtualizarAula {

    @Test
    public void atualiza_informacoes_recorrentes(){
        SalaService salaService = new SalaService();
        AulaService aulaService = new AulaService();

        Sala sala = salaService.obterPorId(2);
        Aula aula = AulaService.obterPorId(2);
        aula.setNumSala(sala.getNumSala());
        aula.setDisciplina("FBD");
        aula.setTurma("02A");
        aula.setDia(DiaDaSemana.QUARTA);
        aula.setNumBloco(NumeroBloco.BLOCO_2);
        aula.setHorario("13:30");

        aula = aulaService.update(aula);
        assertEquals(sala.getNumSala(), aula.getNumSala());
        assertEquals("FBD", aula.getDisciplina());
        assertEquals("02A", aula.getTurma());
        assertEquals("13:30", aula.getHorario());
        assertEquals(DiaDaSemana.QUARTA, aula.getDia());
        assertEquals(NumeroBloco.BLOCO_2, aula.getBloco());

    }

    public void erro_ao_atualizar_aula_para_sala_lotada() {
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(2);

        AulaService aulaService = new AulaService();

        Aula aula = AulaService.obterPorId(1);
        aula.setNumSala(sala.getNumSala());
        aula.setHorario("13:30");
        aula = aulaService.update(aula);

        Aula aula2 = AulaService.obterPorId(2);
        aula.setNumSala(sala.getNumSala());
        aula.setHorario("13:30");

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> aulaService.update(aula2));
        assertEquals("Sala já está lotada para o horário escolhido", error.getMessage());
    }
}
