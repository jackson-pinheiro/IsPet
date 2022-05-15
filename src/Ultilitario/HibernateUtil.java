/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ultilitario;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Jackson
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static SessionFactory construtorDeConexao(){
        try {
            Configuration configuracao=new Configuration();
            configuracao.configure("/hibernate.cfg.xml");
            ServiceRegistry service =new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
            sessionFactory=configuracao.buildSessionFactory(service);
            
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null){
            sessionFactory=construtorDeConexao();
        }
        return sessionFactory;
    }
    
    public static void fecharConexao(){
        sessionFactory.close();
    }
}
