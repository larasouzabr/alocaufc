package com.alocaufc.services;

import com.alocaufc.entities.Aula;
import com.alocaufc.repositories.AulaRepository;
import com.alocaufc.repositories.impl.AulaRepositoryJPA;
import com.alocaufc.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AulaService {
    private final AulaRepository repository;

    public AulaService(){
        EntityManagerUtil emu = EntityManagerUtil.getInstance();
        EntityManager em = emu.getEntityManager();
        this.repository = new AulaRepositoryJPA(em);
    }

    public Aula create(Aula aula) {
        if(aula.getId() != null) {
            Aula aulaExistente = this.repository.findById(aula.getId());

            if (aulaExistente != null) {
                throw new Error("Aula já cadastrada");
            }
        }

        try{
            return repository.save(aula);
        } catch(IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public Aula update(Aula aula) {
        Aula aulaExistente = repository.findById(aula.getId());

        if(aulaExistente == null) {
            throw new Error("Aula não existe");
        }

        return repository.save(aula);
    }

    public Aula obterPorId(long id) {
        return repository.findById(id);
    }

    public List<Aula> getAll() {
        return repository.findAll();
    }

    public void delete(Aula aula) {
        repository.remove(aula);
    }
}
