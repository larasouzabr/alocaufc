package modules.test_services;
import modules.entities.Sala;
import modules.entities.enums.DiaDaSemana;
import modules.entities.enums.NumeroBloco;
import modules.services.SalaService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlocarAula {
    @Test
    public void alocar_aula_em_sala(){
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(1);

        int numSala = sala.getNumSala();
        String horarioAula = "15:30";
        String turma = "01A";
        String disciplina = "Projeto Integrado I";

        Aula aula = new Aula(NumeroBloco.valueOf(1), numSala, horarioAula, DiaDaSemana.SEGUNDA, disciplina, turma);
        AulaService alocaAula = new AulaService;
        assertNotNull(alocaAula.alocar(aula));
    }

    @Test
    public void erro_ao_alocar_aula_com_bloco_nulo() {
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(1);

        int numSala = sala.getNumSala();
        String horarioAula = "15:30";
        String turma = "01A";
        String disciplina = "Projeto Integrado I";

        Aula aula = new Aula(null, numSala, horarioAula, DiaDaSemana.SEGUNDA, disciplina, turma);
        AulaService alocaAula = new AulaService;

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> aulaService.aloca(aula));
        assertEquals("Bloco não pode ser nulo", error.getMessage());
    }

    @Test
    public void erro_ao_alocar_aula_com_dia_nulo() {
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(1);

        int numSala = sala.getNumSala();
        String horarioAula = "15:30";
        String turma = "01A";
        String disciplina = "Projeto Integrado I";

        Aula aula = new Aula(NumeroBloco.valueOf(1), numSala, horarioAula, null, disciplina, turma);
        AulaService alocaAula = new AulaService;

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> aulaService.aloca(aula));
        assertEquals("Dia da semana não pode ser nulo", error.getMessage());
    }

    @Test
    public void erro_ao_adicionar_aula_em_horario_lotado(){
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(1);

        int numSala = sala.getNumSala();
        String horarioAula = "15:30";
        String turma = "01A";
        String disciplina = "Projeto Integrado I";

        Aula aula = new Aula(NumeroBloco.valueOf(1), numSala, horarioAula, DiaDaSemana.SEGUNDA, disciplina, turma);

        String disciplina2 = "Lógica";
        String turma2 = "02A";
        Aula newAula = new Aula(NumeroBloco.valueOf(1), numSala, horarioAula, DiaDaSemana.SEGUNDA, disciplina2, turma2);

        AulaService alocaAula = new AulaService;
        alocaAula.aloca(aula);

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> aulaService.aloca(aula2));
        assertEquals("Sala já está lotada para o horário escolhido", error.getMessage());
    }
}
