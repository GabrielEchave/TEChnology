package clases;
import java.sql.*;


public class Conexion {
    public static Connection conectar(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_ds", "root", "ADMIN");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local"+e);
        }
        return (null);
    }
}
