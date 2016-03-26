/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Paciente;
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
public class PacienteDAOImpl implements PacienteDAO {

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            session.persist(paciente);
            temp.commit();
            System.out.println(paciente.getIdPaciente());
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        return paciente;
    }

    @Override
    public ArrayList<Paciente> buscarPacientes(String id_empresa) {
        ArrayList<Paciente> model = new ArrayList();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Paciente where Usuario_idUsuario = '" + id_empresa + "'";
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            model = (ArrayList<Paciente>) session.createQuery(sql).list();
            temp.commit();
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        if (model != null) {
            System.out.println(model.get(0).getNombre() + " OK " + sql);
        }
        System.out.println("Finalizo la busqueda");
        return model;
    }

    @Override
    public List<Paciente> findAllPatients() {
        List<Paciente> patients = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Paciente p left join fetch p.usuario";
        Transaction temp = null;
        try {
            //session.beginTransaction();
            temp = session.beginTransaction();
            patients = session.createQuery(sql).list();
            //session.beginTransaction().commit();
            temp.commit();
        } catch (Exception e) {
            //session.beginTransaction().rollback();
            temp.rollback();
            System.out.println(e);
        }
        return patients;
    }

    @Override
    public boolean update(Paciente paciente) {
        boolean flag;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            //session.saveOrUpdate(paciente);
            session.update(paciente);
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
            Paciente paciente = (Paciente) session.load(Paciente.class, id);
            session.delete(paciente);
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
    public Paciente findPatient(String document) {
        Paciente p = new Paciente();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Paciente where documento = '" + document + "'";
        Transaction temp = null;
        try {
            temp = session.beginTransaction();
            p = (Paciente) session.createQuery(sql).uniqueResult();
            temp.commit();
        } catch (Exception e) {
            System.out.println(e);
            temp.rollback();
        }
        return p;
    }

}
