/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.loginController;
import DataAccess.DAO.UsuarioDAO;
import DataAccess.Entity.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Alfonso
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    //private static final long serialVersionUID = -2152389656664659476L;
    private String documento;
    private String clave;
    private boolean logeado;
    private Usuario usuario;
    private String nombres;
    private String apellidos;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        
        loginController l = new loginController();
        String msgs = l.Login(documento, clave);
        this.usuario = l.getUsuario();
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", msgs);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void nuevoUsuario(ActionEvent actionEvent) {
        loginController l = new loginController();
        String msgs = l.crearEmpresa(nombres, apellidos, documento, clave);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", msgs);
        this.usuario = l.getUsuario();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void registrarUsuario(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrar nuevo usuario");
        String ruta = MyUtil.basePathLogin() + "registro.xhtml";
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("view", ruta);
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        this.logeado = false;
        RequestContext context = RequestContext.getCurrentInstance();
        String ruta = "";        
        ruta = MyUtil.baseurl();        
        context.addCallbackParam("estaLogeado", this.logeado);
        context.addCallbackParam("view", ruta);
        System.out.println("Cerro sesion.." + ruta);
    }
}
