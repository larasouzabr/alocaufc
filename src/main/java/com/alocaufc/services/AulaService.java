package com.alocaufc.services;

import com.alocaufc.entities.Aula;
import com.alocaufc.repositories.AulaRepository;
import com.alocaufc.repositories.impl.AulaRepositoryJPA;
import com.alocaufc.services.validation.AulaInsertValidation;
import com.alocaufc.utils.EntityManagerUtil;

import javax.persistence.EntityManager;

public class AulaService {
    private final AulaRepository repository;
    private final AulaInsertValidation validator;

    public AulaService(){
        EntityManager em = EntityManagerUtil.get();
        this.repository = new AulaRepositoryJPA(em);
        this.validator = new AulaInsertValidation(em);
    }

    public Aula alocar(Aula aula) {
        if(aula.getId() != null) {
            Aula aulaExistente = this.repository.findById(aula.getId());

            if (aulaExistente != null) {
                throw new Error("Aula já cadastrada");
            }
        }

//        if(aula.getSala() == null) {
//            throw new IllegalArgumentException("Sala não pode ser nula");
//        }
//
//        if(aula.getDiaDaSemana() == null) {
//            throw new IllegalArgumentException("Dia da semana não pode ser nulo");
//        }

        if(!validator.isValid(aula)){
            throw new IllegalArgumentException("Sala já está lotada para o horário escolhido");
        }

        try{
            return repository.save(aula);
        } catch(IllegalStateException e) {
            throw new IllegalStateException("teste");
        }

    }

    public Aula obterPorId(long id) {
        return repository.findById(id);
    }

    public Aula update(Aula aula) {
        Aula aulaExistente = this.repository.findById(aula.getId());

        if(!validator.isValid(aula)){
            throw new IllegalArgumentException("Sala já está lotada para o horário escolhido");
        }

        return repository.save(aulaExistente);
    }
}
