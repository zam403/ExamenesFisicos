/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Consulta;
import java.util.ArrayList;

/**
 *
 * @author Alfonso
 */
public interface ConsultaDAO {
    
    public Consulta crearConsulta(Consulta consulta);
    public ArrayList<Consulta> buscarConsultasEmpresa(Integer id_empresa);
    
}
