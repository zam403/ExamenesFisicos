/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alfonso
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    public Usuario crearUsuario(Usuario usuario){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try{
            temp = session.beginTransaction();
            session.persist(usuario);
            temp.commit();
        }catch(Exception e){
            System.out.println(e);
            temp.rollback();
        }
        System.out.println(usuario.getIdUsuario());
        return usuario;
    }    
    
    @Override
    public Usuario findUsuario(String documento) {
        Usuario u = new Usuario();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Usuario where documento = '" + documento + "'";
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            u = (Usuario) session.createQuery(sql).uniqueResult();
            temp.commit();
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        if (u != null) {
            System.out.println(u.getNombre() + " OK " + sql);
        }
        System.out.println("Finalizo la busqueda");
        return u;
    }
    
    @Override
    public List<Usuario> findAllUsers() {
        List<Usuario> users = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Usuario u left join fetch u.rol";
        Transaction temp = null;
        try {
            //session.beginTransaction();
            temp = session.beginTransaction();
            users = session.createQuery(sql).list();
            //session.beginTransaction().commit();
            temp.commit();
        } catch (Exception e) {
            //session.beginTransaction().rollback();
            temp.rollback();
            System.out.println(e);
        }
        return users;
    }

    @Override
    public boolean create(Usuario usuario) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            session.save(usuario);
            temp.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            temp.rollback();
            System.out.println(e);
        }
        return flag;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            session.update(usuario);
            temp.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            temp.rollback();
            System.out.println(e);
        }
        return flag;
    }

    @Override
    public boolean delete(Integer id) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            Usuario usuario = (Usuario) session.load(Usuario.class, id);
            session.delete(usuario);
            temp.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            temp.rollback();
            System.out.println(e);
        }
        return flag;
    }

}
