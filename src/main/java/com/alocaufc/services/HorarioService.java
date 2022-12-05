package com.alocaufc.services;

import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Horario;
import com.alocaufc.repositories.HorarioRepository;
import com.alocaufc.repositories.impl.HorarioRepositoryJPA;
import com.alocaufc.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class HorarioService {
    private HorarioRepository repository;

    public HorarioService(){
        EntityManagerUtil emu = EntityManagerUtil.getInstance();
        EntityManager em = emu.getEntityManager();
        repository = new HorarioRepositoryJPA(em);
    }

    public List<Horario> getByAula(Aula aula) {
        return this.repository.findByAula(aula);
    }

    public Horario update(Horario horario) {
        return repository.save(horario);
    }

    public Horario create(Horario horario) {
        return repository.save(horario);
    }

    public Aula alocar(List<Horario> horarios, Aula aula) {
        HorarioService horarioService = new HorarioService();
        for(Horario horario: horarios) {
            horario.setAula(aula);
            horarioService.update(horario);
        }
        return aula;
    }

    public void desalocar(List<Horario> horarios) {
        HorarioService horarioService = new HorarioService();
        for(Horario horario: horarios) {
            horario.setAula(null);
            horarioService.update(horario);
        }
    }
}
