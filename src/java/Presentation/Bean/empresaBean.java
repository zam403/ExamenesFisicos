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
import DataAccess.Entity.Usuario;
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
    //private PacienteDAO pacientedao;
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
        FacesMessage msg;
        Integer temp = (Integer) loginbean.getUsuario().getIdUsuario();
        
        System.out.println("id Usuario Logeado 2: " + temp);
        
        PacienteDAO pacientedao = new PacienteDAOImpl();
        this.paciente = new Paciente();
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario((Integer) temp);
        
        this.paciente.setUsuario(usuario);
        this.paciente.setDocumento(documento);
        this.paciente.setNombre(nombre);
        this.paciente.setApellido(apellido);
        
        this.setPaciente(pacientedao.registrarPaciente(paciente));
        
        if (this.paciente.getIdPaciente() != null) {
            Consulta consulta = new Consulta();
           
            Usuario doctor = new Usuario();
            doctor.setIdUsuario(1);
            
            consulta.setCriterio(criterio);
            consulta.setPaciente(paciente);
            consulta.setUsuario(doctor);
            
            ConsultaDAO consultaDAO = new ConsultaDAOImpl();
            this.setConsulta(consultaDAO.crearConsulta(consulta));
            
            if(this.consulta.getIdConsulta() != null){
                System.out.println("La consulta ha sido creada correctamente para el paciente " + this.paciente.getNombre() + " " + this.paciente.getApellido());
            }else{
                System.out.println("NO fue posible crear la consulta para el paciente " + this.paciente.getNombre() + " " + this.paciente.getApellido());
            }
            
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrar Paciente", "El nuevo paciente ha sido registrado con éxito");
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrar Paciente", "No fue posible registrar el nuevo paciente, por favor intente nuevamente");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void consultarDiagnosticoPacientes(ActionEvent actionEvent){
        System.out.println("Pacientes idUusario "+loginbean.getUsuario().getIdUsuario().toString());
        ConsultaDAO consultaDAO = new ConsultaDAOImpl();
        this.diagnosticos = new ArrayList();
        this.setDiagnosticos(consultaDAO.buscarConsultasEmpresa(loginbean.getUsuario().getIdUsuario()));
        System.out.println("Tamaño consultas: "+ this.diagnosticos.size());
        System.out.println("idConsulta arraylist(0) "+this.diagnosticos.get(0).getIdConsulta());
        this.setConsultas(this.diagnosticos.subList(0, this.diagnosticos.size()));
        System.out.println("idConsulta list(0) "+this.consultas.get(0).getIdConsulta());
    }
}
