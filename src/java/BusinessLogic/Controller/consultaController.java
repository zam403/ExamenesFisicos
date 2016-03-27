/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Usuario;
import java.util.List;

/**
 *
 * @author Alex
 */
public class consultaController {
    
    private List<Consulta> consults;
    private List<Usuario> doctors;
    
    public List<Consulta> listConsults() {
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        this.consults = consultaDAO.findAllConsults();
        return consults;
    }
    
    public List<Usuario> listDoctors() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        this.doctors = usuarioDAO.findAllDoctors();
        return doctors;
    }
    
    public String updateConsult(Consulta consulta) {
        System.out.println("consul: " + consulta.getIdConsulta());
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        String msgs;
        if (consultaDAO.update(consulta)) {
            msgs = "Consulta modificada éxitosamente.";
        } else {
            msgs = "Error, la consulta no pudo modicarse.";
        }
        return msgs;
    }
    
    public String updateDoctor(Usuario doctor, Integer idConsulta) {
        String msgs;
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        Consulta consulta = consultaDAO.findConsult(idConsulta);
        if (consulta != null) {
            if (doctor != null) {
                consulta.setUsuario(doctor);
                if (consultaDAO.update(consulta)) {
                    msgs = "Consulta modificada éxitosamente.";
                } else {
                    msgs = "Error, la consulta no pudo modicarse.";
                }
            } else {
                msgs = "No seleccionó ningun doctor.";
            }
        } else {
            msgs = "Error, consulta no encontrada.";
        }
        return msgs;
    }

    public String deleteConsult(Consulta consulta) {
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        String msgs;
        if (consultaDAO.delete(consulta.getIdConsulta())) {
            msgs = "Consulta eliminada éxitosamente.";
        } else {
            msgs = "Error, la consulta no pudo eliminarse.";
        }
        return msgs;
    }
    
    public Consulta findConsult(Integer idConsult) {
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        Consulta updateConsulta = consultaDAO.findConsult(idConsult);
        return updateConsulta;
    }
    
}
