/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.PacienteDAOImpl;
import DataAccess.Entity.Paciente;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alex
 */
@Named(value = "pacienteBean")
@RequestScoped
public class pacienteBean {
    
    private List<Paciente> patients;
    private Paciente selectedPatient;

    /**
     * Creates a new instance of pacienteBean
     */
    public pacienteBean() {
        this.patients = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        selectedPatient = new Paciente();
    }

    public List<Paciente> getPatients() {
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        this.patients = pacienteDAO.findAllPatients();
        return patients;
    }

    public void setPatients(List<Paciente> patients) {
        this.patients = patients;
    }

    public Paciente getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Paciente selectedPatient) {
        this.selectedPatient = selectedPatient;
    }
    
    public void updatePatient(ActionEvent actionEvent) {
        System.out.println("paciente: " + this.selectedPatient.getIdPaciente());
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        String msgs;
        if (pacienteDAO.update(this.selectedPatient)) {
            msgs = "Paciente modificado éxitosamente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msgs = "Error, el paciente no puedo modicarse.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deletePatient(ActionEvent actionEvent) {
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        String msgs;
        if (pacienteDAO.delete(this.selectedPatient.getIdPaciente())) {
            msgs = "Paciente eliminado éxitosamente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msgs = "Error, el paciente no puedo eliminarse.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
}
