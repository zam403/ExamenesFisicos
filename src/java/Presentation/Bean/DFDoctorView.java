/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alex
 */
@ManagedBean(name = "dFDoctorView")
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
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        this.doctors = usuarioDAO.findAllDoctors();
        return doctors;
    }       

    public void selectDoctorFromDialog(Usuario usuario) {
        usuario = this.selectedDoctor;
        System.out.println("idConsult2: " + idConsult);
        try {
            ConsultaDAO consultaDAO = new ConsultaDAOImpl();
            Consulta updateConsulta = consultaDAO.findConsult(Integer.parseInt(this.idConsult));
            System.out.println("Consulta encontrada: " + updateConsulta.getIdConsulta()
                    + " " + updateConsulta.getPaciente().getNombre());
            System.out.println("doctor: " + usuario.getNombre());
            updateConsulta.setUsuario(usuario);
            RequestContext.getCurrentInstance().closeDialog(updateConsulta);
        } catch (NullPointerException e) {
            RequestContext.getCurrentInstance().closeDialog(null);
        }
    }
    
}
