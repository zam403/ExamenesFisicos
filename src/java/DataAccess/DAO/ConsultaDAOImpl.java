/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Consulta;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Alfonso
 */
public class ConsultaDAOImpl implements ConsultaDAO{

    @Override
    public Consulta crearConsulta(Consulta consulta) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            session.persist(consulta);
            temp.commit();
            System.out.println(consulta.getIdConsulta());
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        return consulta;
    }

    @Override
    public ArrayList<Consulta> buscarConsultasEmpresa(Integer id_empresa) {
        ArrayList<Consulta> model = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Consulta as c join fetch  c.paciente p join fetch c.usuario d where c.paciente.usuario = " + id_empresa.toString();
        Transaction temp = null;
        try {
            System.out.println("Antes de iniciar sesion...");
            temp = session.beginTransaction();
            System.out.println("Despues de iniciar sesion...");
            model = (ArrayList<Consulta>) session.createQuery(sql).list();
            System.out.println("Realizo la consulta...");;
            temp.commit();
            System.out.println("Realizo commit...");
            System.out.println("Tamaño: "+ model.size());
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        if (model.size() > 0) {
            System.out.println(model.get(0).getPaciente().getNombre() + " OK " + sql);
        }
        System.out.println("Finalizo la busqueda");
        return model;
    }

    @Override
    public boolean update(Consulta consulta) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            session.update(consulta);
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
            Consulta consulta = (Consulta) session.load(Consulta.class, id);
            session.delete(consulta);
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
    public List<Consulta> findAllConsults() {
        List<Consulta> consults = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Consulta c left join fetch c.paciente left join fetch c.usuario";
        Transaction temp = null;
        try {
            //session.beginTransaction();
            temp = session.beginTransaction();
            consults = session.createQuery(sql).list();
            //session.beginTransaction().commit();
            temp.commit();
        } catch (Exception e) {
            //session.beginTransaction().rollback();
            temp.rollback();
            System.out.println(e);
        }
        return consults;
    }

    @Override
    public Consulta findConsult(Integer id) {
        Consulta consulta = new Consulta();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Consulta c left join fetch c.paciente left join fetch c.usuario where c.id = '" + id + "'";
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            consulta = (Consulta) session.createQuery(sql).uniqueResult();
            temp.commit();
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        return consulta;
    }
    
}
