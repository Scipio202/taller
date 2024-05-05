package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuardarDatos_p1A_modelo {
	
	public boolean guardarDatos(String usuario, String clave, String preg1, String resp1, 
			String preg2,String resp2) {
		try {
			Connection connection = Conexion.obtenerConexion();
			String sql = "INSERT INTO acceso (nombre_usuario, clave, pregunta_1, respuesta_1, pregunta_2, respuesta_2) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			 	statement.setString(1, usuario);
	            statement.setString(2, clave);
	            statement.setString(3, preg1);
	            statement.setString(4, resp1);
	            statement.setString(5, preg2);
	            statement.setString(6, resp2);
	            
	            // ejecución de la consulta
	          int filasAfectadas = statement.executeUpdate();
	            
	            statement.close();
	            connection.close();
	            //devuelve true si inserta una fila, éxito en al operación
	            return filasAfectadas > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false; //false en caso de error.
		}
	}

}
