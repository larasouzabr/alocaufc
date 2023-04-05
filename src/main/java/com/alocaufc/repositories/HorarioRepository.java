package com.alocaufc.repositories;

import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Horario;
import com.alocaufc.entities.Sala;

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
