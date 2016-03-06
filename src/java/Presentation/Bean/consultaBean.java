/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.Entity.Consulta;
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

    /**
     * Creates a new instance of consultaBean
     */
    public consultaBean() {
        this.consults = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        selectedConsult = new Consulta();
    }

    public List<Consulta> getConsults() {
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        this.consults = consultaDAO.findAllConsults();
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

    public void updateConsult(ActionEvent actionEvent) {
        System.out.println("consul: " + selectedConsult.getIdConsulta());
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        String msgs;
        if (consultaDAO.update(this.selectedConsult)) {
            msgs = "Consulta modificada éxitosamente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msgs = "Error, la consulta no pudo modicarse.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteConsult(ActionEvent actionEvent) {
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        String msgs;
        if (consultaDAO.delete(this.selectedConsult.getIdConsulta())) {
            msgs = "Consulta eliminada éxitosamente.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msgs = "Error, la consulta no pudo eliminarse.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgs, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
}
