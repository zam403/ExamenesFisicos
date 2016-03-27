/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.consultaController;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Usuario;
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
@Named(value = "consultaBean")
@RequestScoped
public class consultaBean {

    private List<Consulta> consults;
    private Consulta selectedConsult;
    private List<Usuario> doctors;
    private Usuario selectedDoctor;

    /**
     * Creates a new instance of consultaBean
     */
    public consultaBean() {
        this.consults = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        selectedConsult = new Consulta();
        selectedDoctor = new Usuario();
    }

    public List<Consulta> getConsults() {
        consultaController c = new consultaController();
        this.consults = c.listConsults();
        return consults;
    }

    public void setConsults(List<Consulta> consults) {
        this.consults = consults;
    }

    public Consulta getSelectedConsult() {
        return selectedConsult;
    }

    public void setSelectedConsult(Consulta selectedConsult) {
        this.selectedConsult = selectedConsult;
    }

    public List<Usuario> getDoctors() {
        consultaController c = new consultaController();
        this.doctors = c.listDoctors();
        return doctors;
    }

    public void setDoctors(List<Usuario> doctors) {
        this.doctors = doctors;
    }

    public Usuario getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Usuario selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public void updateConsult(ActionEvent actionEvent) {
        System.out.println("consul: " + selectedConsult.getIdConsulta());
        consultaController c = new consultaController();
        String msgs = c.updateConsult(this.selectedConsult);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void updateDoctor(ActionEvent actionEvent) {
        consultaController c = new consultaController();
        String msgs = c.updateDoctor(this.selectedDoctor, this.selectedConsult.getIdConsulta());
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteConsult(ActionEvent actionEvent) {
        consultaController c = new consultaController();
        String msgs = c.deleteConsult(this.selectedConsult);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
