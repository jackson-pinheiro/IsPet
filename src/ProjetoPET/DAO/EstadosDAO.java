/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Estados;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jackson
 */
public class EstadosDAO {
    
    public void inserir(Estados e){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();      
            sessao.save(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro "+h);
            sessao.getTransaction().rollback();
        }
        finally{
            sessao.close();
        }
    }
    
    public void alterar(Estados e){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro "+h);
            sessao.getTransaction().rollback();
        }
        finally{
            sessao.close();
        }
    }
    
    
      public void delete(Estados e){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.delete(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro "+h);
            sessao.getTransaction().rollback();
        }
        finally{
            sessao.close();
        }
    }
    
      public List<Estados> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Estados> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Estados.class).list();
              sessaL.flush();
              sessaL.getTransaction().commit();
          } catch (HibernateException h) {
                lista1=null;
                System.out.println("Erro " + h);
                sessaL.getTransaction().rollback();
          }
          finally{
                sessaL.close();
            }
            return lista1;
      }
      
    
}
