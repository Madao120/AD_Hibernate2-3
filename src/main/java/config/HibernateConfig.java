package config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    static {
        try {
            standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure("properties.xml")
                    .build();

            sessionFactory = new MetadataSources(standardServiceRegistry)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();

            if (standardServiceRegistry != null) {
                StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
            }

            throw new ExceptionInInitializerError(e);
        }

    }
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
