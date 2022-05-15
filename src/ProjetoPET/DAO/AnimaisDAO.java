/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Animais;
import _models.Clientes;
import _models.RelatamItensVendas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jackson
 */
public class AnimaisDAO {
    
    public void inserir(Animais a){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();      
            sessao.save(a);
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
    
    public void alterar(Animais a){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(a);
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
    
 public boolean delete(Animais a) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        boolean s = false;
        try {
            sessao.beginTransaction();
            List<Animais> lista1 = new ArrayList<>();
            lista1 = sessao.createCriteria(RelatamItensVendas.class)
                    .add(Restrictions.eq("animais", a)).list();
            if (lista1.isEmpty()) {
                sessao.delete(a);
                s = true;
            }
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
        return s;
    }

    
      public List<Animais> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Animais> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Animais.class).list();
              for (Animais a:lista1){
                  Hibernate.initialize(a.getCores());                  
                  Hibernate.initialize(a.getRacas());                  
                  Hibernate.initialize(a.getRacas().getEspecies());
                  Hibernate.initialize(a.getClientes());
                  
              }
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
      
      public List<Animais> listaAnimaisPorCliente(Clientes c) {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<Animais> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();

            lista1 = sessaL.createCriteria(Animais.class)
                    .add(Restrictions.eq("clientes", c)).list();
             for (Animais a:lista1){
                  Hibernate.initialize(a.getCores());                  
                 Hibernate.initialize(a.getRacas());
                 Hibernate.initialize(a.getRacas().getEspecies());
                  Hibernate.initialize(a.getClientes());
                  
              }
            sessaL.flush();
            sessaL.getTransaction().commit();
        } catch (HibernateException h) {
            lista1 = null;
            System.out.println("Erro " + h);
            sessaL.getTransaction().rollback();
        } finally {
            sessaL.close();
        }
        return lista1;
    }
      

    
    
}
