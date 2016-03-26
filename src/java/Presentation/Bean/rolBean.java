/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean;

import BusinessLogic.Controller.rolController;
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
        rolController c = new rolController();
        this.selectRol = c.getRols();
        return selectRol;
    }    
    
}
