/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Usuario;
import java.util.ArrayList;
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
        Usuario model = new Usuario();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Usuario where documento = '" + documento + "'";
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            model = (Usuario) session.createQuery(sql).uniqueResult();
            temp.commit();
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        if (model != null) {
            System.out.println(model.getNombre() + " OK " + sql);
        }
        System.out.println("Finalizo la busqueda");
        return model;
    }

}
