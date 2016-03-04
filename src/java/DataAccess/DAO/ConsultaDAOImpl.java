/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Consulta;
import java.util.ArrayList;
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
        String sql = "select consulta FROM Consulta as consulta join consulta.paciente where consulta.paciente.usuario = " + id_empresa.toString();
        Transaction temp = null;
        try {
            System.out.println("Antes de iniciar sesion...");
            temp = session.beginTransaction();
            System.out.println("Despues de iniciar sesion...");
            model = (ArrayList<Consulta>) session.createQuery(sql).list();
            System.out.println("Realizo la consulta...");
            System.out.println("Tamaño: "+ model.size() +" Modelo consultas" + model.get(0).getPaciente().getNombre());
            temp.commit();
            System.out.println("Realizo commit");
            System.out.println("Tamaño: "+ model.size() +" Modelo consultas" + model.get(0).getPaciente().getNombre());
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
    
}
