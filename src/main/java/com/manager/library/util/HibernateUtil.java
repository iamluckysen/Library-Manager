package com.manager.library.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try{
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        else{
            System.out.println("Session Factory is initialized");
        }
        }catch (Exception e){
            System.out.println("Error in creating Hibernate SessionFactory" + e.getMessage());
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
