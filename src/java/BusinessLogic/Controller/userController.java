/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Usuario;
import java.util.List;

/**
 *
 * @author Alex
 */
public class userController {
    
    List<Usuario> users;
    
    public List<Usuario> getUsers() {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        this.users = usuarioDAO.findAllUsers();
        return users;
    }
    
    public String createUser(Usuario user) {
        String msgs;
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        if (usuarioDAO.findUsuario(user.getDocumento()) != null) {
            msgs = "Ya existe un usuario creado con éste documento. Por favor, verifique el documento ingresado.";
        } else if (usuarioDAO.create(user)) {
            System.out.println(user.toString());
            msgs = "Usuario creado éxitosamente.";
        } else {
            msgs = "Error, el usuario no pudo crearse.";
        }
        return msgs;
    }
   
    public String updateUser(Usuario user) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String msgs;
        if (usuarioDAO.findUsuario(user.getDocumento()) != null && 
                !(usuarioDAO.findUsuario(user.getDocumento()).getIdUsuario().equals(user.getIdUsuario()))) {
            msgs = "Ya existe un usuario creado con éste documento. Por favor, verifique el documento ingresado.";
        } else if (usuarioDAO.update(user)) {
            msgs = "Usuario modificado éxitosamente.";
        } else {
            msgs = "Error, el usuario no pudo modicarse.";
        }
        return msgs;
    }
    
    public String deleteUser(Usuario user) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String msgs;
        if (usuarioDAO.delete(user.getIdUsuario())) {
            msgs = "Usuario eliminado éxitosamente.";
        } else {
            msgs = "Error, el usuario no pudo eliminarse.";
        }
        return msgs;
    }
    
}
