package com.alocaufc.repositories.impl;

import com.alocaufc.entities.Sala;
import com.alocaufc.repositories.SalaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;

public class SalaRepositoryJPA implements SalaRepository {
    private EntityManager em;

    public SalaRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Sala> findAll() {
        Query query = em.createQuery("SELECT s from Sala s");
        List<Sala> salas = query.getResultList();
        return salas;
    }

    @Override
    public List<Sala> findByBloco(Integer bloco) {
            String query = "SELECT s FROM Sala s WHERE s.bloco = :bloco";
            TypedQuery<Sala> s = (TypedQuery<Sala>) em.createQuery(query);
            s.setParameter("bloco", bloco);
            List<Sala> salas = s.getResultList();
            return salas;
    }

    @Override
    public Sala findById(Long id) {
        return em.find(Sala.class, id);
    }

    @Override
    public Sala save(Sala sala) {
            if (sala.getId() == null) {
                transaction(sala, e -> em.persist(e));
            } else {
                transaction(sala, e -> em.merge(e));
            }
        return sala;
    }


    @Override
    public void remove(Sala sala) {
        transaction(sala, e -> em.remove(e));
    }

    private void transaction(Sala sala, Consumer consumer) {
        try {
            em.getTransaction().begin();
            consumer.accept(sala);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
