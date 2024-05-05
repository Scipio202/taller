package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pantalla1D_modelo {

	public boolean guardarClave(String nuevaClave) {
		
		String sql = "update acceso set clave = ? order by id_usuario limit 1";
		
		 try (
		            //  conexión a la base de datos utilizando la clase Conexion
		            Connection conn = Conexion.obtenerConexion();
		            
		            // Prepara la declaración SQL con parámetros
		            PreparedStatement statement = conn.prepareStatement(sql)
		        ) {
		            // Establece los parámetros en la consulta SQL
		            statement.setString(1, nuevaClave);
		          
		            
		            // Ejecuta la consulta SQL para actualizar la contraseña
		            int filasActualizadas = statement.executeUpdate();
		            // Verificar si se actualizó correctamente (debería ser 1, ya que solo se actualiza una fila)
		           return filasActualizadas == 1;
		        } catch (SQLException e) {
		        	 e.printStackTrace();
		             return false; // En caso de error
		        }
		// return true; //return de prueba para simular el correcto guardado en la BD
	}
}
