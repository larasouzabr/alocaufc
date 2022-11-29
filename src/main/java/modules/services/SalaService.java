package modules.services;

import modules.entities.Sala;
import modules.repositories.SalaRepository;
import modules.repositories.impl.SalaRepositoryJPA;
import modules.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SalaService {
    private final SalaRepository repository;

    public SalaService(){
        EntityManager em = EntityManagerUtil.get();
        this.repository = new SalaRepositoryJPA(em);
    }

    public Sala create(Sala sala){
        if(sala.getId() != null) {
            Sala salaExistente = this.repository.findById(sala.getId());

            if (salaExistente != null) {
                throw new Error("Sala já cadastrada");
            }
        }

        if(sala.getBloco() == null) {
            throw new IllegalArgumentException("Bloco não pode ser nulo");
        }

        return repository.save(sala);
    }

    public Sala update(Sala sala) {
        Sala salaExistente = this.repository.findById(sala.getId());

        return repository.save(salaExistente);
    }

    public Sala obterPorId(Long id) {
        return this.repository.findById(id);
    }

    public List<Sala> getAll() {
        return this.repository.findAll();
    }
}
