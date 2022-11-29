package modules.repositories.impl;

import modules.entities.Aula;
import modules.entities.Horario;
import modules.repositories.AulaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;

public class AulaRepositoryJPA implements AulaRepository {
    private EntityManager em;

    public AulaRepositoryJPA(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<Aula> findAll() {
        Query query = em.createQuery("SELECT a from Aula a");
        List<Aula> aulas = query.getResultList();
        return aulas;
    }

    @Override
    public Aula findById(Long id) {
        return em.find(Aula.class, id);
    }

    @Override
    public Aula save(Aula aula) {
        if(aula.getId() == null) {
            transaction(aula, e -> em.persist(e));
        } else {
            transaction(aula, e -> em.merge(e));
        }
        return aula;
    }


    @Override
    public void remove(Aula aula) {
        transaction(aula, e -> em.remove(e));
    }

    @Override
    public Boolean isTimeAvailable(Aula aula) {
//        try {
//            String query = "SELECT a FROM Aula a " +
//                    "WHERE a.sala = :sala AND a.diaDaSemana = :diaDaSemana AND a.horarioAula = :horarioAula";
//            TypedQuery<Aula> a = (TypedQuery<Aula>) em.createQuery(query);
//            a.setParameter("sala", aula.getSala());
//            a.setParameter("diaDaSemana", aula.getDiaDaSemana().getNumero());
//            a.setParameter("horarioAula", aula.getHorarioAula());
//
//            return a.getSingleResult() == null;
//        } catch(NoResultException e) {
//            return true;
//        }
return false;
    }

    private void transaction(Aula aula, Consumer consumer) {
        try {
            em.getTransaction().begin();
            consumer.accept(aula);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
