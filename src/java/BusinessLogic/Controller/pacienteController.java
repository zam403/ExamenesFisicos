/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.PacienteDAOImpl;
import DataAccess.Entity.Paciente;
import java.util.List;

/**
 *
 * @author Alex
 */
public class pacienteController {
    
    private List<Paciente> patients;
    
    public List<Paciente> listPatients() {
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        this.patients = pacienteDAO.findAllPatients();
        return patients;
    }
    
    public String updatePatient(Paciente paciente) {
        System.out.println("paciente: " + paciente.getIdPaciente());
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        String msgs;
        if (pacienteDAO.findPatient(paciente.getDocumento())!=null && 
                !(pacienteDAO.findPatient(paciente.getDocumento()).getIdPaciente().equals(paciente.getIdPaciente()))) {
            msgs = "Ya existe un paciente creado con el documento ingresado. Por favor verifiquelo";
        } else if (pacienteDAO.update(paciente)) {
            msgs = "Paciente modificado éxitosamente.";
        } else {
            msgs = "Error, el paciente no pudo modicarse.";
        }
        return msgs;
    }

    public String deletePatient(Paciente paciente) {
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        String msgs;
        if (pacienteDAO.delete(paciente.getIdPaciente())) {
            msgs = "Paciente eliminado éxitosamente.";
        } else {
            msgs = "Error, el paciente no pudo eliminarse.";
        }
        return msgs;
    }
    
}
