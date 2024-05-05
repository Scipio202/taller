package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoUsuario {
	
	 // Aquí pongo métodos para interactuar con la base de datos de usuarios
    // Por ejemplo:
	
	 public boolean verificarSiEsPrimeraVez() {
		 boolean vacio = false;
	        try (Connection connection = Conexion.obtenerConexion()) {
	            String sql = "SELECT COUNT(*) FROM acceso";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        int cantidadRegistros = resultSet.getInt(1);
	                        if (cantidadRegistros == 0) {
	                        	vacio = true;
	                        	System.out.println("Prueba de entrada en BD");
	                        }
	                        
	                    }
	                }
	            }
	        } catch (SQLException e) {
	        	 System.err.println("Error al conectar con la base de datos. Por favor, contacta "
	        	 		+ "al administrador del sistema.");
	            e.printStackTrace();
	            //Comprobar que devuelve en caso de no poder conectar con la BBDD.
	        }
	       return vacio;// En caso de error, asumimos que no es la primera vez que se accede
	       //¡¡para pruebas escribir return false/true y comentar todo lo demás!!
		// return false;
	    }

    public boolean verificarUsuarioClave(String usuario, String clave) {
        // Lógica para verificar las credenciales en la base de datos
    	boolean permisoAcceso = false;
    	try {
			Connection connection = Conexion.obtenerConexion();
			String sql = "Select nombre_usuario, clave from acceso where nombre_usuario = ? LIMIT 1";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String claveAlmacenada = resultSet.getString("clave");
				if(clave.equals(claveAlmacenada)) {
					permisoAcceso = true; //contraseña correcta
				}
			}
			resultSet.close();
	        statement.close();
	        connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		//if de prueba
    	/*if(usuario.equals("a") && clave.equals("b")) {
    		permisoAcceso = true;
    	}*/
        return permisoAcceso;
    	
    }
    
    public boolean verificarPreguntasSeguridad(String respuesta1, String respuesta2) {
    	boolean respuestaCorrecta = false;
    	
    	return respuestaCorrecta;
    }

    // Otros métodos para agregar usuarios, recuperar información, etc.

}
