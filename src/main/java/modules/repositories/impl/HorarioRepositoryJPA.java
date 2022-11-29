package modules.repositories.impl;

import modules.entities.Aula;
import modules.entities.Horario;
import modules.entities.Sala;
import modules.repositories.HorarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;

public class HorarioRepositoryJPA implements HorarioRepository {
    private EntityManager em;

    public HorarioRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Horario> findAll() {
        Query query = em.createQuery("SELECT h from Horario h");
        List<Horario> horarios = query.getResultList();
        return horarios;
    }

    @Override
    public List<Horario> findBySala(Sala sala) {
        String query = "SELECT h FROM Horario h WHERE h.sala = :sala";
        TypedQuery<Horario> h = (TypedQuery<Horario>) em.createQuery(query);
        h.setParameter("sala", sala);
        List<Horario> horarios = h.getResultList();
        return horarios;
    }

    @Override
    public List<Horario> findBySalaAndDiaSemana(Sala sala, Integer diaSemana) {
        String query = "SELECT h FROM Horario h WHERE h.sala = :sala AND h.diaSemana = :diaSemana";
        TypedQuery<Horario> h = (TypedQuery<Horario>) em.createQuery(query);
        h.setParameter("sala", sala);
        h.setParameter("diaSemana", diaSemana);
        List<Horario> horarios = h.getResultList();
        return horarios;
    }

    @Override
    public List<Horario> findByAula(Aula aula) {
        String query = "SELECT h FROM Horario h WHERE h.aula = :aula";
        TypedQuery<Horario> h = (TypedQuery<Horario>) em.createQuery(query);
        h.setParameter("aula", aula);
        List<Horario> horarios = h.getResultList();
        return horarios;
    }

    @Override
    public Horario findById(Long id) {
        return em.getReference(Horario.class, id);
    }

    @Override
    public Horario save(Horario horario) {
        if(horario.getId() == null) {
            transaction(horario, e -> em.persist(e));
        } else {
            transaction(horario, e -> em.merge(e));
        }

        return horario;
    }

    @Override
    public void remove(Horario horario) {
        transaction(horario, e -> em.remove(e));
    }

    private void transaction(Horario horario, Consumer consumer) {
        try {
            em.getTransaction().begin();
            consumer.accept(horario);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
