/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.pacienteController;
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
        pacienteController p = new pacienteController();
        this.patients = p.listPatients();
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
        pacienteController p = new pacienteController();
        String msgs = p.updatePatient(this.selectedPatient);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deletePatient(ActionEvent actionEvent) {
        pacienteController p = new pacienteController();
        String msgs = p.deletePatient(this.selectedPatient);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
