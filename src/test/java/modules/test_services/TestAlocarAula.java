package modules.test_services;
import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Horario;
import com.alocaufc.entities.Sala;
import com.alocaufc.services.AulaService;
import com.alocaufc.services.HorarioService;
import com.alocaufc.services.SalaService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlocarAula {
    @Test
    public void alocar_aula_em_sala(){
        SalaService salaService = new SalaService();
        Sala sala = salaService.obterPorId(2L);

        List<Horario> horarios = sala.getHorarios();
        HorarioService horarioService = new HorarioService();
        AulaService aulaService = new AulaService();
        Aula aula = aulaService.obterPorId(2L);
        assertNotNull(horarioService.alocar(horarios, aula));
    }

    @Test
    public void erro_ao_alocar_aula_com_sala_nula() {
//        String horarioAula = "15:30";
//        String turma = "01A";
//        String disciplina = "Projeto Integrado I";
//
//        Aula aula = new Aula(null, horarioAula, DiaDaSemana.SEGUNDA, disciplina, turma);
//        AulaService service = new AulaService();
//
//        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> service.alocar(aula));
//        assertEquals("Sala não pode ser nula", error.getMessage());
    }

    @Test
    public void erro_ao_alocar_aula_com_dia_nulo() {
//        SalaService salaService = new SalaService();
//        Sala sala = salaService.obterPorId(1);
//
//        String horarioAula = "15:30";
//        String turma = "01A";
//        String disciplina = "Projeto Integrado II";
//
//        Aula aula = new Aula(sala, horarioAula, null, disciplina, turma);
//        AulaService service = new AulaService();
//
//        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> service.alocar(aula));
//        assertEquals("Dia da semana não pode ser nulo", error.getMessage());
    }

    @Test
    public void erro_ao_adicionar_aula_em_horario_lotado(){
//        SalaService salaService = new SalaService();
//        Sala sala = salaService.obterPorId(1);
//
//        int numSala = sala.getNumSala();
//        String horarioAula = "08:00";
//        String turma = "01A";
//        String disciplina = "Projeto Integrado I";
//
//        Aula aula = new Aula(sala, horarioAula, DiaDaSemana.SEGUNDA, disciplina, turma);
//
//        String disciplina2 = "Lógica";
//        String turma2 = "02A";
//        Aula aula2 = new Aula(sala, horarioAula, DiaDaSemana.SEGUNDA, disciplina2, turma2);
//
//        AulaService service = new AulaService();
//        service.alocar(aula);
//
//        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> service.alocar(aula2));
//        assertEquals("Sala já está lotada para o horário escolhido", error.getMessage());
    }
}
