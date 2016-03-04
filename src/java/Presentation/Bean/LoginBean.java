/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Rol;
import DataAccess.Entity.Usuario;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
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
        FacesMessage msg;
        String ruta = "";
        if (this.getDocumento().isEmpty()) {
            this.logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Debe inidicar un usuario");
            System.out.println("Falta nombre");
        } else if (this.getClave().isEmpty()) {
            this.logeado = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Debe indicar una contraseña");
            System.out.println("Falta clave");
        } else {
            System.out.println("Tiene ambos campos");
            {
                this.usuarioDAO = new UsuarioDAOImpl();
                this.usuario = new Usuario();
                this.setUsuario(this.usuarioDAO.findUsuario(documento));
                if (this.usuario != null && this.usuario.getClave().equals(clave)) {//this.documento.equals("admin") && this.clave.equals("admin")
                    this.logeado = true;
                    context.addCallbackParam("idUsuario", this.usuario.getIdUsuario());
                    System.out.println("id Usuario Logeado 1: " + context.getCallbackParams().get("idUsuario"));
                    if (this.usuario.getRol().getIdRol() == 1) {
                        ruta = MyUtil.basePathLogin() + "views/Admin/menuAdmin.xhtml";
                    } else if (this.usuario.getRol().getIdRol() == 2) {
                        ruta = MyUtil.basePathLogin() + "menu_doctor.xhtml";
                    } else {
                        ruta = MyUtil.basePathLogin() + "menu_empresa.xhtml";
                    }
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", this.usuario.getNombre());// + " " + this.usuario.getApellido());
                } else {
                    System.out.println("entro al else");
                    this.logeado = false;
                    msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                            "Credenciales no válidas");
                }
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("estaLogeado", this.logeado);
        if (this.logeado) {
            System.out.println("Entro logeado = true");
            context.addCallbackParam("ruta", ruta);
        }
    }

    public void nuevoUsuario(ActionEvent actionEvent) {
        FacesMessage msg;
        RequestContext context = RequestContext.getCurrentInstance();
        String ruta = MyUtil.basePathLogin();

        this.usuarioDAO = new UsuarioDAOImpl();
        this.usuario = new Usuario();
        Rol rol = new Rol();
        rol.setIdRol(3);
        this.usuario.setDocumento(documento);
        this.usuario.setNombre(nombres);
        this.usuario.setApellido(apellidos);
        this.usuario.setClave(clave);
        this.usuario.setRol(rol);

        this.setUsuario(usuarioDAO.crearUsuario(usuario));

        if (this.usuario.getIdUsuario() != null) {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "El nuevo usuario ha sido registrado con éxito");
            ruta = ruta + "login.xhtml";
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "No fue posible registrar el nuevo usuario, por favor intente nuevamente");
            ruta = ruta + "registro.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("view", ruta);
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
        String ruta = MyUtil.basePathLogin() + "login.xhtml";
        context.addCallbackParam("estaLogeado", this.logeado);
        context.addCallbackParam("view", ruta);
    }
}
