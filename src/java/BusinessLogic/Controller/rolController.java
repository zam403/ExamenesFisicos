/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.RolDAO;
import DataAccess.DAO.RolDAOImpl;
import DataAccess.Entity.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Alex
 */
public class rolController {
    
    private List<SelectItem> selectRol;
    
    public List<SelectItem> getRols() {
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
