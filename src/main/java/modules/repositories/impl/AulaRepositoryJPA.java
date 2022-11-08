package modules.repositories.impl;

import modules.entities.Aula;
import modules.repositories.AulaRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class AulaRepositoryJPA implements AulaRepository {
    private EntityManager em;

    public AulaRepositoryJPA(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<Aula> obterTodos() {
        return null;
    }

    @Override
    public Aula obterPorId(int id) {
        return em.find(Aula.class, id);
    }

    @Override
    public Aula adicionar(Aula aula) {
        try {
            em.getTransaction().begin();
            em.persist(aula);
            em.getTransaction().commit();
        } catch(IllegalStateException e) {

        } finally {
            em.close();
        }

        return aula;
    }

    @Override
    public Aula atualizar(Aula sala) {
        return null;
    }

    @Override
    public void remover(Aula sala) {

    }
}
