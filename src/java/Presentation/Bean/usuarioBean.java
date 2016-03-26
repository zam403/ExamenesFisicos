/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.userController;
import DataAccess.Entity.Usuario;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alex
 */
@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean {

    private List<Usuario> users;
    private Usuario selectedUser;

    /**
     * Creates a new instance of usuarioBean
     */
    public usuarioBean() {
        this.users = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        selectedUser = new Usuario();
    }

    public List<Usuario> getUsers() {
        userController c = new userController();
        this.users = c.getUsers();
        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public Usuario getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Usuario selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void createUser(ActionEvent actionEvent) {
        userController c = new userController();
        String msgs = c.createUser(this.selectedUser);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void updateUser(ActionEvent actionEvent) {
        userController c = new userController();
        String msgs = c.updateUser(this.selectedUser);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteUser(ActionEvent actionEvent) {
        userController c = new userController();
        String msgs = c.deleteUser(this.selectedUser);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msgs, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
