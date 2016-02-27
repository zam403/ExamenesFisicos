/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Alfonso
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String nombre;
    private String clave;
    private boolean logeado = false;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private UsuarioDAO usuarioDAO;

    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void login(ActionEvent actionEvent) {
        this.usuarioDAO = new UsuarioDAOImpl();
        this.usuario = new Usuario();
        
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        if (this.nombre != null) {
            this.setUsuario(this.usuarioDAO.findUsuario(nombre));            
        }
        if (this.usuario != null && this.usuario.getClave().equals(clave)) {//this.nombre.equals("admin") && this.clave.equals("admin")
            this.logeado = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.usuario.getNombre()+" "+this.usuario.getApellido());
        } else {
            System.out.println("entro al else");
            this.logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Credenciales no válidas");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", this.logeado);
        if (this.logeado) {            
            context.addCallbackParam("view", "menu_principal.xhtml");
        }
    }

    public void logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }
}
