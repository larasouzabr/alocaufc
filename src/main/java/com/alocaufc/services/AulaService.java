package com.alocaufc.services;

import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Horario;
import com.alocaufc.repositories.AulaRepository;
import com.alocaufc.repositories.impl.AulaRepositoryJPA;
import com.alocaufc.services.validation.AulaInsertValidation;
import com.alocaufc.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AulaService {
    private final AulaRepository repository;
    private final AulaInsertValidation validator;

    public AulaService(){
        EntityManagerUtil emu = EntityManagerUtil.getInstance();
        EntityManager em = emu.getEntityManager();
        this.repository = new AulaRepositoryJPA(em);
        this.validator = new AulaInsertValidation(em);
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

    public Aula alocar(List<Horario> horarios, Aula aula) {
        HorarioService horarioService = new HorarioService();
        for(Horario horario: horarios) {
            horario.setAula(aula);
            horarioService.update(horario);
        }
        return aula;
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
