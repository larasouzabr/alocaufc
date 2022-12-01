package com.alocaufc.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManager em;

    private EntityManagerUtil() {
    }

    public static EntityManager get() {
        if(em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("aloca-ufc");
            em = emf.createEntityManager();
        }

        return em;
    }

    public static void close() {
        if(em != null) {
            em.close();
        }
    }
}
