package modules.repositories;

import modules.entities.Sala;

import java.util.List;

public interface SalaRepository {
    List<Sala> obterTodos();
    Sala obterPorId(int id);
    Sala adicionar(Sala sala);
    Sala atualizar(Sala sala);
    void remover(Sala sala);

}
