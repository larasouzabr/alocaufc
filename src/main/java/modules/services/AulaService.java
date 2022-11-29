package modules.services;

import modules.entities.Aula;
import modules.entities.Sala;
import modules.repositories.AulaRepository;
import modules.repositories.impl.AulaRepositoryJPA;
import modules.services.validation.AulaInsertValidation;
import modules.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AulaService {
    private final AulaRepository repository;
    private final AulaInsertValidation validator;

    public AulaService(){
        EntityManager em = EntityManagerUtil.get();
        this.repository = new AulaRepositoryJPA(em);
        this.validator = new AulaInsertValidation(em);
    }

    public Aula alocar(Aula aula) {
        if(aula.getId() != null) {
            Aula aulaExistente = this.repository.findById(aula.getId());

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

        if(!validator.isValid(aula)){
            throw new IllegalArgumentException("Sala já está lotada para o horário escolhido");
        }

        try{
            return repository.save(aula);
        } catch(IllegalStateException e) {
            throw new IllegalStateException("teste");
        }

    }

    public Aula obterPorId(long id) {
        return repository.findById(id);
    }

    public Aula update(Aula aula) {
        Aula aulaExistente = this.repository.findById(aula.getId());

        if(!validator.isValid(aula)){
            throw new IllegalArgumentException("Sala já está lotada para o horário escolhido");
        }

        return repository.save(aulaExistente);
    }
}
