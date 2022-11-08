package modules.services;

import modules.entities.Aula;
import modules.entities.Sala;
import modules.repositories.AulaRepository;
import modules.repositories.impl.AulaRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AulaService {
    private final AulaRepository repository;

    public AulaService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aloca-ufc");
        EntityManager em = emf.createEntityManager();
        this.repository = new AulaRepositoryJPA(em);
    }

    public Aula alocar(Aula aula) {
        if(aula.getId() != null) {
            Aula aulaExistente = this.repository.obterPorId(aula.getId());

            if (aulaExistente != null) {
                throw new Error("Aula já cadastrada");
            }
        }

        if(aula.getSala() == null) {
            throw new IllegalArgumentException("Sala não pode ser nula");
        }

        if(aula.getDiaDaSemana() == null) {
            throw new IllegalArgumentException("Dia da semana não pode ser nulo");
        }

        return repository.adicionar(aula);
    }
}
