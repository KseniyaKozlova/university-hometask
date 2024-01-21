package by.itacademy.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import static by.itacademy.utils.Constants.PERSISTENCE_UNIT_NAME;

public class JPAUtil {
    private static final EntityManager ENTITY_MANAGER = buildEntityManager();

    private static EntityManager buildEntityManager() {
        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER;
    }
}
