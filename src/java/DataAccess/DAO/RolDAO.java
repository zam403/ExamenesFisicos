/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Rol;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface RolDAO {
    
    public List<Rol> selectItems();
    
}
