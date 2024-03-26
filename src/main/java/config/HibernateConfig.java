package config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateConfig {

    public static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {

        if (sessionFactory == null) {
            try {
                return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Throwable ex) {

                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        } else {
            return getSessionFactory();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
