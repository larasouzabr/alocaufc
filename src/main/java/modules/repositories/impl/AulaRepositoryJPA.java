package modules.repositories.impl;

import modules.entities.Aula;
import modules.repositories.AulaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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
        } catch(Exception e) {
            em.getTransaction().rollback();
        }



        return aula;
    }

    @Override
    public Aula atualizar(Aula aula) {
        try {
            em.getTransaction().begin();
            em.merge(aula);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

    return aula;
    }

    @Override
    public void remover(Aula sala) {

    }

    @Override
    public Aula findByAvailability(Aula aula) {
        try {
            String query = "SELECT a FROM Aula a " +
                    "WHERE a.sala = :sala AND a.diaDaSemana = :diaDaSemana AND a.horarioAula = :horarioAula";
            TypedQuery<Aula> a = (TypedQuery<Aula>) em.createQuery(query);
            a.setParameter("sala", aula.getSala());
            a.setParameter("diaDaSemana", aula.getDiaDaSemana().getNumero());
            a.setParameter("horarioAula", aula.getHorarioAula());

            return a.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }

    }
}
