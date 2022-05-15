/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;

import Ultilitario.HibernateUtil;
import _models.Fornecedores;
import _models.ProdutosServicos;
import _models.RelatamItensVendas;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jackson
 */
public class ProdutosServicosDAO {

    public void inserir(ProdutosServicos p) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.save(p);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void alterarEstoqueVenda(ObservableList<ProdutosServicos> listaItens) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            for (ProdutosServicos item : listaItens) {
                sessao.update(item);
            }
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public void alterar(ProdutosServicos p) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();
            sessao.update(p);
            sessao.flush();
            sessao.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println("Erro " + h);
            sessao.getTransaction().rollback();
        } finally {
            sessao.close();
        }
    }

    public boolean delete(ProdutosServicos p) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        boolean s = false;
        try {
            sessao.beginTransaction();
            List<ProdutosServicos> lista1 = new ArrayList<>();
            lista1 = sessao.createCriteria(RelatamItensVendas.class)
                    .add(Restrictions.eq("produtosServicos", p)).list();
            if (lista1.isEmpty()) {
                sessao.delete(p);
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

    public List<ProdutosServicos> lista() {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<ProdutosServicos> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();
            lista1 = sessaL.createCriteria(ProdutosServicos.class).list();
            for (ProdutosServicos a : lista1) {
                Hibernate.initialize(a.getUnidades());
                Hibernate.initialize(a.getPrecoVend());

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
    public List<ProdutosServicos> listadeprodutos() {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        List<ProdutosServicos> lista1 = new ArrayList<>();
        try {
            sessaL.beginTransaction();
            lista1 = sessaL.createCriteria(ProdutosServicos.class).list();
            for (ProdutosServicos a : lista1) {
                Hibernate.initialize(a.getUnidades());
                Hibernate.initialize(a.getPrecoVend());

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
