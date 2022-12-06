package com.alocaufc.services;

import com.alocaufc.entities.Sala;
import com.alocaufc.repositories.SalaRepository;
import com.alocaufc.repositories.impl.SalaRepositoryJPA;
import com.alocaufc.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class SalaService {
    private final SalaRepository repository;

    public SalaService(){
        EntityManagerUtil emu = EntityManagerUtil.getInstance();
        EntityManager em = emu.getEntityManager();
        this.repository = new SalaRepositoryJPA(em);
    }

    public Sala create(Sala sala){
        if(sala.getId() != null) {
            Sala salaExistente = this.repository.findById(sala.getId());

            if (salaExistente != null) {
                throw new Error("Sala já cadastrada");
            }
        }

        if(sala.getBloco() == null) {
            throw new IllegalArgumentException("Bloco não pode ser nulo");
        }

        return repository.save(sala);
    }

    public Sala update(Sala sala) {
        Sala salaExistente = this.repository.findById(sala.getId());

        return repository.save(salaExistente);
    }

    public Sala obterPorId(Long id) {
        return this.repository.findById(id);
    }

    public List<Sala> getAll() {
        return this.repository.findAll();
    }

    public List<Sala> getByBloco(int bloco) { return this.repository.findByBloco(bloco); }
}
