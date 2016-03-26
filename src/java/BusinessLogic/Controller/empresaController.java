/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.PacienteDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Paciente;
import DataAccess.Entity.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class empresaController {

    public String registrar(Usuario empresa, String documento, String nombre, String apellido,
            String criterio) {
        String msgs;
        System.out.println("id Usuario Logeado 2: " + empresa.getIdUsuario());
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        Paciente paciente = new Paciente(empresa, nombre, apellido, documento);

        if (pacienteDAO.registrarPaciente(paciente) != null) {
            Consulta consulta = new Consulta();
            Usuario doctor = new Usuario();
            doctor.setIdUsuario(1);

            consulta.setCriterio(criterio);
            consulta.setPaciente(paciente);
            consulta.setUsuario(doctor);

            ConsultaDAO consultaDAO = new ConsultaDAOImpl();

            if (consultaDAO.crearConsulta(consulta) != null) {
                System.out.println("La consulta ha sido creada correctamente para el paciente " + paciente.getNombre() + " " + paciente.getApellido());
                msgs = "Paciente registrado exitosamente";
            } else {
                System.out.println("No fue posible crear la consulta para el paciente " + paciente.getNombre() + " " + paciente.getApellido());
                msgs = "No fue posible registrar el paciente.";
            }
        } else {
            msgs = "No fue posible registrar el paciente.";
        }

        return msgs;
    }
    
    public ArrayList<Consulta> findAllPatients(Usuario empresa) {
        ArrayList<Consulta> listPatients;
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        listPatients = consultaDAO.buscarConsultasEmpresa(empresa.getIdUsuario());
        return listPatients;
    }

}
