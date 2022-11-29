package modules.test_services;

import modules.entities.Horario;
import modules.entities.Sala;
import modules.entities.enums.Bloco;
import modules.repositories.SalaRepository;
import modules.repositories.impl.SalaRepositoryJPA;
import org.junit.jupiter.api.Test;

import javax.persistence.*;


public class TestFodas {
    @Test
     void ai(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aloca-ufc");
        EntityManager em = emf.createEntityManager();

        SalaRepository sr = new SalaRepositoryJPA(em);

        System.out.println(sr.findById(6L));
//        Sala sala = sr.save(new Sala(6L, "Sala 4", 1, 45, true, true, null, ""));
//        ts.save(new Sala(null, "Sala 2", 1, 45, true, true, null, ""));
//
//        for (Sala s : sr.findByBloco(1)) {
//            for(Horario h: s.getHorarios()) {
//                System.out.println(h.getHoraInicio() + " " + h.getHoraFim() + " | " + Bloco.valueOf(h.getDiaSemana()));
//            }
//        }
//        System.out.println(sr.findById(6L).getTitulo());

//        for(Sala e: s) {
//            System.out.println(ts.findById(2L));
//        }

//        Query query = em.createQuery("SELECT s from Sala2 s");
//        List<Sala> salas =  query.getResultList();
//        for (Sala s : salas) {
//            System.out.println(s.getTitulo() + " - " +s.getBloco());
//            for(Horario h: s.getHorarios()) {
//                System.out.println(DiaSemana.valueOf(h.getDiaSemana()) + ": " + h.getHoraInicio() + " - " + h.getHoraFim() + " -> " + h.getAula().getDisciplina());
//            }
//        }
//        Aula aula = new Aula(null, "Projeto Integrado I", "01A");
//        Sala sala = new Sala(null, "Sala 1", 1, 45, true, true, null, "");
//        em.getTransaction().begin();
//        em.persist(aula);
//        em.persist(sala);
//        em.persist(new Horario(null, 4, LocalTime.parse("10:00"), LocalTime.parse("12:00"), "", sala, aula));
//        em.getTransaction().commit();
    }


}
