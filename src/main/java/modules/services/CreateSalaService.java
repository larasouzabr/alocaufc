package modules.services;

import modules.entities.Sala;
import modules.repositories.SalaRepository;
import modules.repositories.impl.SalaRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateSalaService {

    private final SalaRepository repository;

    public CreateSalaService(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aloca-ufc");
        EntityManager em = emf.createEntityManager();
        this.repository = new SalaRepositoryJPA(em);
    }
    public Sala execute(Sala sala){

        if(sala.getId() != null) {
            Sala salaExistente = this.repository.obterPorId(sala.getId());

            if (salaExistente != null) {
                throw new Error("Sala já cadastrada");
            }
        }

        if(sala.getBloco() == null) {
            throw new Error("Bloco não pode ser nulo");
        }

        return repository.adicionar(sala);
    }
}
