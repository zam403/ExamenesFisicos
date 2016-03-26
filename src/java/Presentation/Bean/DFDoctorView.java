/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.consultaController;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "dFDoctorView")
@javax.faces.bean.ViewScoped
public class DFDoctorView {

    private List<Usuario> doctors;
    private Usuario selectedDoctor;
    private String idConsult;

    public String getIdConsult() {
        return idConsult;
    }

    public void setIdConsult(String idConsult) {
        this.idConsult = idConsult;
    }

    public Usuario getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Usuario selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }       

    public List<Usuario> getDoctors() {
        consultaController c = new consultaController();
        this.doctors = c.listDoctors();
        return doctors;
    }       

    public void selectDoctorFromDialog(Usuario usuario) {
        usuario = this.selectedDoctor;
        System.out.println("idConsult2: " + idConsult);
        try {
            consultaController c = new consultaController();
            Consulta consulta = c.findConsult(Integer.parseInt(this.idConsult));
            System.out.println("Consulta encontrada: " + consulta.getIdConsulta()
                    + " " + consulta.getPaciente().getNombre());
            System.out.println("doctor: " + usuario.getNombre());
            consulta.setUsuario(usuario);
            RequestContext.getCurrentInstance().closeDialog(consulta);
        } catch (NullPointerException e) {
            RequestContext.getCurrentInstance().closeDialog(0);
        }
    }
    
}
