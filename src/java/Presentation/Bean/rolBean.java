/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import DataAccess.DAO.RolDAO;
import DataAccess.DAO.RolDAOImpl;
import DataAccess.Entity.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Alex
 */
@Named(value = "rolBean")
@RequestScoped
public class rolBean {   
    
    private List<SelectItem> selectRol;

    /**
     * Creates a new instance of rolBean
     */
    public rolBean() {
    }

    public List<SelectItem> getSelectRol() {
        this.selectRol = new ArrayList<SelectItem>();
        RolDAO rolDAO = new RolDAOImpl();
        List<Rol> rols = rolDAO.selectItems();
        for (Rol rol : rols) {
            SelectItem selectItem = new SelectItem(rol.getIdRol(), rol.getRol());
            this.selectRol.add(selectItem);
        }
        return selectRol;
    }    
    
}
