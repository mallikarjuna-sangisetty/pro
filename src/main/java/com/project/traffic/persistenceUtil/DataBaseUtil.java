package com.project.traffic.persistenceUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DataBaseUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static Session session = null;
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new AnnotationConfiguration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession(){
        session =sessionFactory.openSession();
        return session;
    }

    public static void closeSession(){
        session.close();
    }
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
