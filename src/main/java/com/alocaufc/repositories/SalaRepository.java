package com.alocaufc.repositories;

import com.alocaufc.entities.Sala;

import java.util.List;

public interface SalaRepository {
    List<Sala> findAll();
    List<Sala> findByBloco(Integer bloco);
    Sala findById(Long id);
    Sala save(Sala sala);
    void remove(Sala sala);


}
