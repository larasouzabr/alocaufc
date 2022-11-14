package modules.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManager get() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("aloca-ufc");
        }
        em = emf.createEntityManager();

        return em;
    }

    public static void close() {
        if(em != null) {
            em.close();
        }
    }
}
