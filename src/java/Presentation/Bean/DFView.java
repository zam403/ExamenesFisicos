/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Paciente;
import DataAccess.Entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "dFView")
//@ViewScoped
public class DFView implements Serializable{
    
    private String idConsult;
    private Consulta selectedConsult;
    private List<Consulta> consults;
    
    public List<Consulta> getConsults() {
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        this.consults = consultaDAO.findAllConsults();
        return consults;
    }

    public void setConsults(List<Consulta> consults) {
        this.consults = consults;
    }
    
    public String getIdConsult() {
        return idConsult;
    }

    public void setIdConsult(String idConsult) {
        this.idConsult = idConsult;
    }

    public Consulta getSelectedConsult() {
        return selectedConsult;
    }

    public void setSelectedConsult(Consulta selectedConsult) {
        this.selectedConsult = selectedConsult;
    }

    public void choosePatient() {
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        System.out.println("idConsulta: " + idConsult);
        values.add(idConsult);
        params.put("idConsult", values);
        RequestContext.getCurrentInstance().openDialog("selectPatient", options, params);
    }

    public void onPatientChosen(SelectEvent event) {
        Consulta consulta = (Consulta) event.getObject();
        FacesMessage message;
        String msgs;
        try {
            ConsultaDAO consultaDAO = new ConsultaDAOImpl();
            if (consultaDAO.update(consulta)) {
                msgs = "Consulta modificada éxitosamente.";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            } else {
                msgs = "La consulta no puedo modificarse.";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            }
        } catch (NullPointerException e) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No selecciono ningun paciente", null);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void chooseDoctor() {
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        System.out.println("idConsulta: " + idConsult);
        values.add(idConsult);
        params.put("idConsult", values);
        RequestContext.getCurrentInstance().openDialog("selectDoctor", options, params);
    }

    public void onDoctorChosen(SelectEvent event) {
        Consulta consulta = (Consulta) event.getObject();
        FacesMessage message;
        String msgs;
        try {
            ConsultaDAO consultaDAO = new ConsultaDAOImpl();
            if (consultaDAO.update(consulta)) {
                msgs = "Consulta modificada éxitosamente.";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            } else {
                msgs = "La consulta no puedo modificarse.";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
            }
        }catch (NullPointerException i) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No selecciono ningun doctor", null);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
