/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.empresaController;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Paciente;
import java.awt.event.ActionEvent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Alfonso
 */
@Named(value = "empresaBean")
@SessionScoped
public class empresaBean implements Serializable {

    private Paciente paciente;
    private Consulta consulta;
    private String nombre;
    private String apellido;
    private String documento;
    private String criterio;
 
    private ArrayList<Consulta> diagnosticos;
    private List<Consulta> consultas;
    
    @Inject
    LoginBean loginbean;

    public ArrayList<Consulta> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(ArrayList<Consulta> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    
    public void registrarPaciente(ActionEvent actionEvent){
        empresaController e = new empresaController();
        String msgs = e.registrar(loginbean.getUsuario(), documento, nombre, apellido, criterio);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrar Paciente", msgs);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void consultarDiagnosticoPacientes(ActionEvent actionEvent){
        System.out.println("Pacientes idUusario "+loginbean.getUsuario().getIdUsuario().toString());
        empresaController e = new empresaController();
        this.diagnosticos = e.findAllPatients(loginbean.getUsuario());
        if (!this.diagnosticos.isEmpty()) {
            this.consultas = this.diagnosticos.subList(0, this.diagnosticos.size());
            //this.setConsultas(this.diagnosticos.subList(0, this.diagnosticos.size()));
            System.out.println("idConsulta list(0) " + this.consultas.get(0).getIdConsulta());
        }
    }
}
