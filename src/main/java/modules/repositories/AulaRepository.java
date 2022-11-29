package modules.repositories;

import modules.entities.Aula;
import modules.entities.Horario;

import java.util.List;

public interface AulaRepository {
        List<Aula> findAll();
        Aula findById(Long id);
        Aula save(Aula aula);
        void remove(Aula aula);
        Boolean isTimeAvailable(Aula aula);

}
