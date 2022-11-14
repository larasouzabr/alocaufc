package modules.services.validation;

import modules.entities.Aula;
import modules.repositories.AulaRepository;
import modules.repositories.impl.AulaRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AulaInsertValidation {
    private final AulaRepository repository;

    public AulaInsertValidation(EntityManager em){
        this.repository = new AulaRepositoryJPA(em);
    }

    public boolean isValid(Aula aula) {

        Aula a = this.repository.findByAvailability(aula);

        return a == null;
    }
}
