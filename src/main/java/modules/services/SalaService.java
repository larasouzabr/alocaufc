package modules.services;

import modules.entities.Sala;
import modules.repositories.SalaRepository;
import modules.repositories.impl.SalaRepositoryJPA;
import modules.utils.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SalaService {
    private final SalaRepository repository;

    public SalaService(){
        EntityManager em = EntityManagerUtil.get();
        this.repository = new SalaRepositoryJPA(em);
    }

    public Sala create(Sala sala){
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

    public Sala update(Sala sala) {
        Sala salaExistente = this.repository.obterPorId(sala.getId());

        return repository.atualizar(salaExistente);
    }

    public Sala obterPorId(int id) {
        return this.repository.obterPorId(id);
    }
}
