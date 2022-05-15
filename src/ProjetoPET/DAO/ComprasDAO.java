/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Compras;
import _models.RelatamItensCompras;
import _models.Vendas;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Jackson
 */
public class ComprasDAO {
    public void inserir(Compras c){
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
        public void salvarItensCompras(Compras v, ObservableList<RelatamItensCompras> listaItens){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();      
            sessao.save(v);
            
            for (RelatamItensCompras item : listaItens) {              
                item.setCompras(v);
                sessao.save(item);
            }
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
    
    public void alterar(Compras c){
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
    
    
      public void delete(Compras c){
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
    
      public List<Compras> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Compras> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Compras.class).list();
                   for (Compras c:lista1){
                  Hibernate.initialize(c.getCodcompra());                  
                  Hibernate.initialize(c.getDatacompra());
                  Hibernate.initialize(c.getTotalcompra());
                  Hibernate.initialize(c.getFornecedores());
                  
              }
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
