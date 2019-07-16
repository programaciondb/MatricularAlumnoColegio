/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matriculascolegio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author davidbousquet
 */
public class Controlador {
    
    
    public static int generarMatricula(Connection conexion) throws SQLException {
        Statement statement;
        String query;
        ResultSet resultSet = null;
        query = "insert into matricula (fecha, valor) values (DATE_FORMAT(now(), \"%Y-%m-%d\"), 4000)";
        
        try {
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            System.out.println("Dato insertado, matricula generada");
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        query = "SELECT idmatricula FROM `matricula` WHERE idmatricula=(SELECT MAX(idmatricula) FROM `matricula`)";
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("id de ultima matricula leido");
            if(resultSet.next()) {
                return (int) resultSet.getObject("idmatricula");
            }
            
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return (int) resultSet.getObject("idmatricula");
        
    }
    
    public static void cancelarMatricula(Connection conexion, int idMatricula) {
        Statement statement;
        String query;
        query="DELETE FROM `matricula` WHERE (`idmatricula` = '"+idMatricula+"')";
        
        try {
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            System.out.println("Matricula eliminada exitosamente");
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void matricularAlumno(Connection conexion, Alumno alumno) {
        Statement statement;
        String query;
        query = "INSERT INTO `alumno` (`rut`, `nombre`, `fecha_nacimiento`, `sexo`, `correo`, `telefono`, `n.apoderado`, `direccion`, `matricula_idmatricula`, `curso_idcurso`) VALUES ('"+alumno.getRut()+"','"+alumno.getNombre()+" "+alumno.getApellido()+"','"+alumno.getFechaNac()+"','"+alumno.getSexo()+"','"+alumno.getCorreo()
                +"','"+alumno.getTelefono()+"','"+alumno.getNombreApoderado()+"','"+alumno.getDireccion()+"','"+alumno.getIdMatricula()+"','"+alumno.getIdCurso()+"');";
        
        /*
        INSERT INTO `alumno` (`rut`, `nombre`, `fecha_nacimiento`, `sexo`, `correo`, `telefono`, `n.apoderado`, `direccion`, `matricula_idmatricula`, `curso_idcurso`) VALUES ('18238273', 'David Bousquet', '1993-3-30', 'M', 'david@correo.com', '929392', 'bernardita P', 'San Eugenio 1401', '31', '14T');
        */
        try {
            statement = conexion.createStatement();
            statement.executeUpdate(query);
            System.out.println("Alumno matriculado");
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
