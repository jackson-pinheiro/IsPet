/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetoPET.DAO;
import Ultilitario.HibernateUtil;
import _models.TipoAcesso;
import _models.Usuarios;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jackson
 */
public class UsuariosDAO {
    
    public void inserir(Usuarios u){
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            sessao.beginTransaction();   
             TipoAcesso tipo =new TipoAcesso();
            tipo = (TipoAcesso) sessao.createCriteria(TipoAcesso.class)
                    .add(Restrictions.eq("codacess",2)).uniqueResult();
            u.setTipoAcesso(tipo);
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
    
    public void alterar(Usuarios u){
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
    
    
      public void delete(Usuarios u){
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
    
      public List<Usuarios> lista(){
          Session sessaL = HibernateUtil.getSessionFactory().openSession();
            List<Usuarios> lista1=new ArrayList<>();
            try {
              sessaL.beginTransaction();
              lista1 = sessaL.createCriteria(Usuarios.class).list();
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
      
    
      public Usuarios buscauser(String senha, String usuario) {
        Session sessaL = HibernateUtil.getSessionFactory().openSession();
        Usuarios user = new Usuarios();
        try {
            sessaL.beginTransaction();
            user = (Usuarios)sessaL.createCriteria(Usuarios.class)
                    .add(Restrictions.eq("useruser", usuario))
                    .add(Restrictions.eq("senha", senha)).uniqueResult();
            sessaL.flush();
            sessaL.getTransaction().commit();
        } catch (HibernateException h) {
            user = null;
            System.out.println("Erro " + h);
            sessaL.getTransaction().rollback();
        } finally {
            sessaL.close();
        }
        return user;
    }
    
}

