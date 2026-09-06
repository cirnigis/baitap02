package doranie.com.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {

    private static final EntityManagerFactory categoryFactory =
            Persistence.createEntityManagerFactory("jpa-hibernate-sqlserver");

    private static final EntityManagerFactory userFactory =
            Persistence.createEntityManagerFactory("jpa-hibernate-user");

    public static EntityManager getCategoryEntityManager() {
        return categoryFactory.createEntityManager();
    }

    public static EntityManager getUserEntityManager() {
        return userFactory.createEntityManager();
    }
}