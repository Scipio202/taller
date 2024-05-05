package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pantalla1C_modelo {

	 public String obtenerPregunta1Final() {
	        String pregunta1Final = null;
	        try {
	            // Establecer conexión con la base de datos
	            Connection connection = Conexion.obtenerConexion();
	            
	            // Consulta SQL para obtener la pregunta final 1
	            String sql = "SELECT pregunta_1 FROM acceso LIMIT 1";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            
	            // Ejecutar la consulta
	            ResultSet resultSet = statement.executeQuery();
	            
	            // Obtener el resultado de la consulta
	            if (resultSet.next()) {
	                pregunta1Final = resultSet.getString("pregunta_1");
	            }
	            
	            // Cerrar conexiones
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return pregunta1Final;
		// return "FINAL PREGUNTA 1";// return de prueba
	    }

	    public String obtenerPregunta2Final() {
	        String pregunta2Final = null;
	        try {
	            // Establecer conexión con la base de datos
	            Connection connection = Conexion.obtenerConexion();
	            
	            // Consulta SQL para obtener la pregunta final 2
	            String sql = "SELECT pregunta_2 FROM acceso LIMIT 1";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            
	            // Ejecutar la consulta
	            ResultSet resultSet = statement.executeQuery();
	            
	            // Obtener el resultado de la consulta
	            if (resultSet.next()) {
	                pregunta2Final = resultSet.getString("pregunta_2");
	            }
	            
	            // Cerrar conexiones
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return pregunta2Final;
	    	//return "FINAL PREGUNTA 2"; return de pruebas
	    }
	    
	    public String obtenerRespuesta1() {
		        String respuesta1 = null;
		        try {
		            // Establecer conexión con la base de datos
		            Connection connection = Conexion.obtenerConexion();
		            
		            // Consulta SQL para obtener la pregunta final 1
		            String sql = "SELECT respuesta_1 FROM acceso LIMIT 1";
		            PreparedStatement statement = connection.prepareStatement(sql);
		            
		            // Ejecutar la consulta
		            ResultSet resultSet = statement.executeQuery();
		            
		            // Obtener el resultado de la consulta
		            if (resultSet.next()) {
		                respuesta1 = resultSet.getString("respuesta_1");
		            }
		            
		            // Cerrar conexiones
		            resultSet.close();
		            statement.close();
		            connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return respuesta1;
			 //return "respuesta1";  //return de pruebas
		    }
	    
	    public String obtenerRespuesta2() {
	        String respuesta2 = null;
	        try {
	            // Establecer conexión con la base de datos
	            Connection connection = Conexion.obtenerConexion();
	            
	            // Consulta SQL para obtener la pregunta final 1
	            String sql = "SELECT respuesta_2 FROM acceso LIMIT 1";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            
	            // Ejecutar la consulta
	            ResultSet resultSet = statement.executeQuery();
	            
	            // Obtener el resultado de la consulta
	            if (resultSet.next()) {
	                respuesta2 = resultSet.getString("respuesta_2");
	            }
	            
	            // Cerrar conexiones
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return respuesta2;
		// return "respuesta2";  //return de pruebas
	    }
}
