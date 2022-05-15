/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Animais;
import _models.Clientes;
import _models.Compras;
import _models.RelatamItensVendas;
import _models.Vendas;
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
public class ClientesDAO {

    public void inserir(Clientes c) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(c);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Erro " + e);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void alterar(Clientes c) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(c);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Erro " + e);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

public boolean delete(Clientes a) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        boolean s = false;
        try {
            sessao.beginTransaction();
            List<Clientes> lista1 = new ArrayList<>();
            lista1 = sessao.createCriteria(Vendas.class)
                    .add(Restrictions.eq("clientes", a)).list();
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
    

    public List<Clientes> lista() {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<Clientes> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();
            lista1 = sessaL.createCriteria(Clientes.class).list();
            for (Clientes e : lista1) {
                Hibernate.initialize(e.getEnderecos());

            }
            sessaL.flush();
            sessaL.getTransaction().commit();
        } catch (HibernateException e) {
            lista1 = null;
            System.out.println("Erro " + e);
            sessaL.getTransaction().rollback();
        } finally {
            sessaL.close();
        }
        return lista1;
    }

}
