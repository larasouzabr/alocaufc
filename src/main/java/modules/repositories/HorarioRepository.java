package modules.repositories;

import modules.entities.Aula;
import modules.entities.Horario;
import modules.entities.Sala;

import java.util.List;

public interface HorarioRepository {
    List<Horario> findAll();
    List<Horario> findBySala(Sala sala);
    List<Horario> findBySalaAndDiaSemana(Sala sala, Integer diaSemana);
    List<Horario> findByAula(Aula aula);
    Horario findById(Long id);
    Horario save(Horario horario);
    void remove(Horario Horario);


}
