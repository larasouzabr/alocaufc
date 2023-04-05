package com.alocaufc.repositories;

import com.alocaufc.entities.Aula;

import java.util.List;

public interface AulaRepository {
        List<Aula> findAll();
        Aula findById(Long id);
        Aula save(Aula aula);
        void remove(Aula aula);
        Boolean isTimeAvailable(Aula aula);

}
