/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Clientes;
import _models.Endereco;
import _models.Fornecedores;
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
public class EnderecoDAO {

    public void inserir(Endereco e) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(e.getClientes());
            sessao.save(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void alterar(Endereco e) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(e.getClientes());
            sessao.update(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void delete(Endereco e) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.delete(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public List<Endereco> lista() {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<Endereco> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();
            lista1 = sessaL.createCriteria(Endereco.class).list();
            for (Endereco e : lista1) {
                Hibernate.initialize(e.getRuaed());
                Hibernate.initialize(e.getCidades());
                Hibernate.initialize(e.getCidades().getEnderecos());
                Hibernate.initialize(e.getClientes());
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

    public Endereco buscarEd(Clientes c) {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        Endereco ed = new Endereco();
        try {
            sessaL.beginTransaction();
            ed = (Endereco) sessaL.createCriteria(Endereco.class)
                    .add(Restrictions.eq("clientes", c)).uniqueResult();
            Hibernate.initialize(ed.getCidades());
            Hibernate.initialize(ed.getCidades().getEstados());
            sessaL.flush();
            sessaL.getTransaction().commit();
        } catch (HibernateException h) {
            ed = null;
            System.out.println("Erro " + h);
            sessaL.getTransaction().rollback();
        } finally {
            sessaL.close();
        }
        return ed;
    }
    
        public void inserirFornecedor(Endereco e) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(e.getFornecedores());
            sessao.save(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }
    public Endereco buscarEdFornecedor(Fornecedores f) {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        Endereco ed = new Endereco();
        try {
            sessaL.beginTransaction();
            ed = (Endereco) sessaL.createCriteria(Endereco.class)
                    .add(Restrictions.eq("fornecedores", f)).uniqueResult();
            Hibernate.initialize(ed.getCidades());
            Hibernate.initialize(ed.getCidades().getEstados());
            sessaL.flush();
            sessaL.getTransaction().commit();
        } catch (HibernateException h) {
            ed = null;
            System.out.println("Erro " + h);
            sessaL.getTransaction().rollback();
        } finally {
            sessaL.close();
        }
        return ed;
    }
    
        public void alterarEdFornecedor(Endereco e) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(e.getFornecedores());
            sessao.update(e);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

}
