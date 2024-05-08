package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pantalla1_Modelo {

	public boolean comprobarMatricula(String matricula) {
		
		 Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = Conexion.obtenerConexion();
	            String sql = "SELECT * FROM caract_vehiculo WHERE LOWER(matricula) = ?";
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, matricula.toLowerCase());
	            resultSet = statement.executeQuery();
	            
	            // Si hay al menos una fila en el resultado, la matrícula existe en la base de datos
	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		
		//return true; //return para pruebas
	}
	
	public boolean comprobarReparacionAbierta(String matricula) {
		
		 	Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet resultSet = null;

		    try {
		        connection = Conexion.obtenerConexion();
		        String sql = "SELECT COUNT(*) AS count " +
	                     "FROM datos_reparacion dr " +
	                     "INNER JOIN caract_vehiculo cv ON dr.id_vehiculo = cv.id_vehiculo " +
	                     "WHERE LOWER(cv.matricula) = ? AND dr.inicio_reparacion = TRUE";
		        statement = connection.prepareStatement(sql);
		        statement.setString(1, matricula.toLowerCase());
		        resultSet = statement.executeQuery();
		        
		        if (resultSet.next()) {
		            int count = resultSet.getInt("count");
		            // Si count es mayor que cero, hay al menos una reparación abierta
		            return count > 0;
		        }
		        // Si no hay filas en el resultado, no hay una reparación abierta
		        return false;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    } finally {
		        try {
		            if (resultSet != null) resultSet.close();
		            if (statement != null) statement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		
		//return false; //return para pruebas
	}
}
