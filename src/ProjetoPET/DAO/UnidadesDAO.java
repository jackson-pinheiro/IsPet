/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;
import Ultilitario.HibernateUtil;
import _models.Unidades;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jackson
 */
public class UnidadesDAO {
    
    public void inserir(Unidades u){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();      
            sessao.save(u);
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
    
    public void alterar(Unidades u){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(u);
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
    
    
      public void delete(Unidades u){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.delete(u);
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
    
      public List<Unidades> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Unidades> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Unidades.class).list();
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
