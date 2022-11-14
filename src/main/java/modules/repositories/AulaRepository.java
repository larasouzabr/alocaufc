package modules.repositories;

import modules.entities.Aula;

import java.util.List;

public interface AulaRepository {
        List<Aula> obterTodos();
        Aula obterPorId(int id);
        Aula adicionar(Aula sala);
        Aula atualizar(Aula sala);
        void remover(Aula sala);
        Aula findByAvailability(Aula aula);

}
