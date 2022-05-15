/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Especies;
import _models.Racas;
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
public class RacasDAO {

    public void inserir(Racas r) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(r);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void alterar(Racas r) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(r);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void delete(Racas r) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.delete(r);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public List<Racas> lista() {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<Racas> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();
            lista1 = sessaL.createCriteria(Racas.class).list();
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

    public List<Racas> listaRacaPorEspecie(Especies e) {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<Racas> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();

            lista1 = sessaL.createCriteria(Racas.class)
                    .add(Restrictions.eq("especies", e)).list();
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
