package com.alocaufc.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerUtil INSTANCE;

    private EntityManager em;

    private EntityManagerUtil() {
        if(em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("aloca-ufc");
            em = emf.createEntityManager();
        }
    }

    public static EntityManagerUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EntityManagerUtil();
        }
        return INSTANCE;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void close() {
        if(em != null) {
            em.close();
        }
    }
}
