/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Compras;
import _models.Fornecedores;
import _models.ProdutosServicos;
import _models.RelatamItensCompras;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jackson
 */
public class FornecedoresDAO {    
    public void inserir(Fornecedores f){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();      
            sessao.save(f);
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
    
    public void alterar(Fornecedores f){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(f);
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
    
    
      public boolean delete(Fornecedores f){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        boolean s =false;
        try {
            sessao.beginTransaction();
                    List<Fornecedores> lista1 = new ArrayList<>();
            lista1 = sessao.createCriteria(Compras.class)
                    .add(Restrictions.eq("fornecedores", f)).list();
            if (lista1.isEmpty()) {
                sessao.delete(f);
                s = true;
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
          return s;
    }
      
      
      
  
    
      public List<Fornecedores> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Fornecedores> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Fornecedores.class).list();
              
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
          public Fornecedores buscarFornecedores(ProdutosServicos p) {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        Fornecedores fornecedor = new Fornecedores();
        try {
            sessaL.beginTransaction();
            fornecedor = (Fornecedores) sessaL.createCriteria(Fornecedores.class)
                    .add(Restrictions.eq("produtos_servicos", p)).uniqueResult();
            Hibernate.initialize(fornecedor.getCodfor());
            Hibernate.initialize(fornecedor.getNomefor());
            Hibernate.initialize(fornecedor.getCpfCnpjfor());
            
            sessaL.flush();
            sessaL.getTransaction().commit();
        } catch (HibernateException h) {
            fornecedor = null;
            System.out.println("Erro " + h);
            sessaL.getTransaction().rollback();
        } finally {
            sessaL.close();
        }
        return fornecedor;
    }
    
}
