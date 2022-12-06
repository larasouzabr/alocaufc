package com.alocaufc.services.validation;

import com.alocaufc.entities.Aula;
import com.alocaufc.repositories.AulaRepository;
import com.alocaufc.repositories.impl.AulaRepositoryJPA;

import javax.persistence.EntityManager;

public class AulaInsertValidation {
    private final AulaRepository repository;

    public AulaInsertValidation(EntityManager em){
        this.repository = new AulaRepositoryJPA(em);
    }

    public boolean isValid(Aula aula) {
        return this.repository.isTimeAvailable(aula);
    }
}
