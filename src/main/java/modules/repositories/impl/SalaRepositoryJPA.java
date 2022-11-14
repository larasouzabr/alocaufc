package modules.repositories.impl;

import modules.entities.Sala;
import modules.repositories.SalaRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class SalaRepositoryJPA implements SalaRepository {
    private EntityManager em;

    public SalaRepositoryJPA(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Sala> obterTodos() {
        return null;
    }

    @Override
    public Sala obterPorId(int id) {
        return em.find(Sala.class, id);
    }

    @Override
    public Sala adicionar(Sala sala) {
        try {
            em.getTransaction().begin();
            em.persist(sala);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        }

        return sala;
    }

    @Override
    public Sala atualizar(Sala sala) {
        try {
            em.getTransaction().begin();
            em.merge(sala);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
        }

        return sala;
    }

    @Override
    public void remover(Sala sala) {

    }
}
