package com.alocaufc.services;

import com.alocaufc.entities.Aula;
import com.alocaufc.entities.Horario;
import com.alocaufc.entities.Sala;
import com.alocaufc.repositories.HorarioRepository;
import com.alocaufc.repositories.impl.HorarioRepositoryJPA;
import com.alocaufc.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public void generateHorarios(Sala sala) {
        List<Horario> horarios = new ArrayList<>();
        for(int i = 2; i <= 6; i++) {
            horarios.add(create(new Horario(null, i, LocalTime.parse("08:00"), LocalTime.parse("10:00"), "", sala, null)));
            horarios.add(create(new Horario(null, i, LocalTime.parse("10:00"), LocalTime.parse("12:00"), "", sala, null)));
            horarios.add(create(new Horario(null, i, LocalTime.parse("12:00"), LocalTime.parse("13:30"), "", sala, null)));
            horarios.add(create(new Horario(null, i, LocalTime.parse("13:30"), LocalTime.parse("15:30"), "", sala, null)));
            horarios.add(create(new Horario(null, i, LocalTime.parse("15:30"), LocalTime.parse("17:30"), "", sala, null)));
            horarios.add(create(new Horario(null, i, LocalTime.parse("18:00"), LocalTime.parse("20:00"), "", sala, null)));
            horarios.add(create(new Horario(null, i, LocalTime.parse("20:00"), LocalTime.parse("22:00"), "", sala, null)));
        }

        sala.setHorarios(horarios);
    }

    public Horario getById(Long id) {
        return repository.findById(id);
    }
}
