/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Usuario;
import java.util.List;

/**
 *
 * @author Alfonso
 */
public interface UsuarioDAO {
    
    public Usuario crearUsuario(Usuario usuario);
    public Usuario findUsuario(String documento);
    public List<Usuario> findAllUsers();
    public boolean create(Usuario usuario);
    public boolean update(Usuario usuario);
    public boolean delete(Integer id);
    
}
