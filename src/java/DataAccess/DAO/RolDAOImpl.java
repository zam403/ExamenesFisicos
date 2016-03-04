/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Rol;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alex
 */
public class RolDAOImpl implements RolDAO{

    @Override
    public List<Rol> selectItems() {
        List<Rol> rols = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Rol";
        Transaction temp = null;
        try {
            //session.beginTransaction();
            temp = session.beginTransaction();
            rols = session.createQuery(sql).list();
            //session.beginTransaction().commit();
            temp.commit();
        } catch (Exception e) {
            //session.beginTransaction().rollback();
            temp.rollback();
            System.out.println(e);
        }
        return rols;
    }
    
}
