package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.*;


public class TestManejoPersonas {

    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            
            Persona cambioPersona = new Persona(2,"Omar","Lopez", "l@test", "000000");
            personaDAO.actualizar(cambioPersona);
            
            Persona nuevaPersona = new Persona("jk","r","jk@test","93/4");
            personaDAO.insertar(nuevaPersona);
            
            
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
            
        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Entramos al Rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
