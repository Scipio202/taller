package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	 private static final String URL = "jdbc:mysql://localhost:3306/taller_mecanico";
	    private static final String USUARIO = "root";
	    private static final String CONTRASEÑA = "my1066dC%";

	    public static Connection obtenerConexion() throws SQLException {
	        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
	    }
}
