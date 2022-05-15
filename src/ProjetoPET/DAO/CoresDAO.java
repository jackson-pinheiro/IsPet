/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Cores;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jackson
 */
public class CoresDAO {
    
    public void inserir(Cores c){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(c);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Erro "+e);
            sessao.getTransaction().rollback();
        }
        finally{
            sessao.close();
        }
    }
    
    public void alterar(Cores c){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(c);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Erro "+e);
            sessao.getTransaction().rollback();
        }
        finally{
            sessao.close();
        }
    }
    
    
      public void delete(Cores c){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.delete(c);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Erro "+e);
            sessao.getTransaction().rollback();
        }
        finally{
            sessao.close();
        }
    }
    
      public List<Cores> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Cores> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Cores.class).list();
              sessaL.flush();
              sessaL.getTransaction().commit();
          } catch (HibernateException e) {
                lista1=null;
                System.out.println("Erro " + e);
                sessaL.getTransaction().rollback();
          }
          finally{
                sessaL.close();
            }
            return lista1;
      }
      
      
      
      
    
}
