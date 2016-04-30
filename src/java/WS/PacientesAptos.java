/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Rol;
import DataAccess.Entity.Usuario;
import static java.lang.Math.cos;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Familia López Ochoa
 */
@WebService(serviceName = "PacientesAptos")
public class PacientesAptos {
    ConsultaDAOImpl consultaDAO = new ConsultaDAOImpl();
    UsuarioDAOImpl usrdao = new UsuarioDAOImpl();
    Rol rol = new Rol("Empresa");
    Usuario cos = new Usuario(rol, "cso","cso","1223334444");
    ArrayList<Consulta> consultSet = new ArrayList<Consulta>();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "aptos")
    public String aptos(Integer idEmpresa) {
        //cos = usrdao.findUsuario(documento);
        //String pwd = cos.getClave();
        //String usrDoc = cos.getDocumento();
        //ArrayList<Consulta> resultset = new ArrayList<Consulta>();
        consultSet = consultaDAO.buscarConsultasEmpresa(idEmpresa);
        if(!consultSet.isEmpty()){
            System.out.println("size " + consultSet.size());
           //resultset = consulta.buscarConsultasEmpresa(idEmpresa);
          return "..."+consultSet.size();//return "Operacion exitosa";
          
        }else{
           return "Fallida"; 
        }
    }
    /**
     * This is a sample web service operation
     */
    
    
}
