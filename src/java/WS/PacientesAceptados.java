/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import DataAccess.DAO.ConsultaDAO;
import DataAccess.DAO.ConsultaDAOImpl;
import DataAccess.DAO.PacienteDAO;
import DataAccess.DAO.PacienteDAOImpl;
import DataAccess.DAO.UsuarioDAOImpl;
import DataAccess.Entity.Consulta;
import DataAccess.Entity.Rol;
import DataAccess.Entity.Usuario;
import DataAccess.Entity.Paciente;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Familia López Ochoa
 */
@WebService(serviceName = "PacientesAceptados")
public class PacientesAceptados {
    ConsultaDAOImpl consulta = new ConsultaDAOImpl() {};
    UsuarioDAOImpl usrdao = new UsuarioDAOImpl();
    Rol rol = new Rol("Empresa");
    Usuario cos = new Usuario(rol, "cso","cso","1223334444");
    ArrayList<Consulta> cs = new ArrayList<Consulta>();
    /**
     * Web service operation
     */
    /***@WebMethod(operationName = "evaluarPacientes")
    public String evaluarPacientes(Integer idEmpresa, String password, String documento) {
        
         
        /*******cos = usrdao.findUsuario(documento);
        String pwd = cos.getClave();
        String usrDoc = cos.getDocumento();
        ArrayList<Consulta> resultset = new ArrayList<Consulta>();
        cs = consulta.buscarConsultasEmpresa(idEmpresa);
        if(cs!=null){
           //resultset = consulta.buscarConsultasEmpresa(idEmpresa);
          return "..."+cs.size();//return "Operacion exitosa";
          
        }else{
           return "Fallida"; 
        }
        //return resultset;  
        return "";

        
    }******/

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "listaPacientes")
    public String listaPacientes(String idEmpresa) {
        String rs = "";
        PacienteDAO pacienteDAO = new PacienteDAOImpl();
        ArrayList<Paciente> pacientes = pacienteDAO.buscarPacientes(idEmpresa);
        if (!pacientes.isEmpty()) {
            for (Paciente paciente : pacientes) {
                rs.concat(paciente.toString());
            }
            return rs;
        }
        return "No hay pacientes registrados para esta empresa";
    }
}
