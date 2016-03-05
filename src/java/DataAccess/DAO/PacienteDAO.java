/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfonso
 */
public interface PacienteDAO{
    
    public Paciente registrarPaciente(Paciente paciente);
    public ArrayList<Paciente> buscarPacientes(String id_empresa);
    public List<Paciente> findAllPatients();
    
}
