/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Rol;
import DataAccess.Entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Alex
 */
public class loginController {

    Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String Login(String username, String pass) {
        RequestContext context = RequestContext.getCurrentInstance();
        String ruta = "";
        String msgs;
        boolean logged = false;
        if (username.isEmpty() || pass.isEmpty()) {
            msgs = "Por favor ingrese todos los valores en el formulario.";
        } else {
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            this.usuario = usuarioDAO.findUsuario(username);
            if (this.usuario != null && this.usuario.getClave().equals(pass)) {
                logged = true;

                if (this.usuario.getRol().getIdRol() == 1) {
                    ruta = MyUtil.basePathLogin() + "views/Admin/menuAdmin.xhtml";
                } else if (this.usuario.getRol().getIdRol() == 2) {
                    ruta = MyUtil.basePathLogin() + "views/Doctor/menu_doctor.xhtml";
                } else {
                    ruta = MyUtil.basePathLogin() + "views/Empresa/menu_empresa.xhtml";
                }

                context.addCallbackParam("idUsuario", this.usuario.getIdUsuario());
                context.addCallbackParam("estaLogeado", logged);
                context.addCallbackParam("ruta", ruta);

                FacesContext context2 = FacesContext.getCurrentInstance();
                ExternalContext extContext = context2.getExternalContext();

                String url = extContext.encodeActionURL(context2.getApplication().getViewHandler().getActionURL(context2, ruta));
                extContext.getSessionMap().put("USER_KEY", this.usuario);
                msgs = "Bienvenido(a)" + this.usuario.getNombre();
            } else {
                msgs = "Credenciales no válidas";
            }
        }

        return msgs;
    }

    public String crearEmpresa(String nombre, String apellido, String documento, String clave) {
        RequestContext context = RequestContext.getCurrentInstance();
        String ruta = MyUtil.basePathLogin();
        String msgs;

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        Rol rol = new Rol();
        rol.setIdRol(3);
        Usuario usuario = new Usuario(rol, nombre, apellido, documento);
        usuario.setClave(clave);
        if (usuarioDAO.crearUsuario(usuario) != null) {
            msgs = "El nuevo usuario ha sido registrado con éxito";
            ruta = ruta + "login.xhtml";
        } else {
            msgs = "No fue posible registrar el nuevo usuario, por favor intente nuevamente";
            ruta = ruta + "registro.xhtml";
        }
        context.addCallbackParam("view", ruta);
        return msgs;
    }

}
