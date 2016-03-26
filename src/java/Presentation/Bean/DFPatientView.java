/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.PacienteDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Paciente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "dFPatientView")
//@RequestScoped
@ViewScoped
public class DFPatientView {

    private List<Paciente> patients;
    private Paciente selectedPatient;
    private String idConsult;

    public Paciente getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Paciente selectedPatient) {
        this.selectedPatient = selectedPatient;
    }   

    public String getIdConsult() {
        return idConsult;
    }

    public void setIdConsult(String idConsult) {
        this.idConsult = idConsult;
    }    

    public List<Paciente> getPatients() {
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        this.patients = pacienteDAO.findAllPatients();
        return patients;
    }       

    public void selectPatientFromDialog(Paciente paciente) {
        paciente = this.selectedPatient;
        System.out.println("idConsult2: " + idConsult);
        try {
            ConsultaDAO consultaDAO = new ConsultaDAOImpl();
            Consulta updateConsulta = consultaDAO.findConsult(Integer.parseInt(this.idConsult));
            System.out.println("Consulta encontrada: " + updateConsulta.getIdConsulta() +
                    " " + updateConsulta.getPaciente().getNombre());
            System.out.println("paciente: " + paciente.getNombre());
            updateConsulta.setPaciente(paciente);
            RequestContext.getCurrentInstance().closeDialog(updateConsulta);
        } catch (NullPointerException e) {
            RequestContext.getCurrentInstance().closeDialog(0);
        }
    }
    
}
