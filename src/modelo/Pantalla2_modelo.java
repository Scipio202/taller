package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pantalla2_modelo {
	
	  private Connection conexion;

	    public Pantalla2_modelo() {
	        // Inicializar la conexión a la base de datos
	        try {
	            conexion = Conexion.obtenerConexion();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	public void guardarDatosVehiculo(String matricula, String color, String marca, String modelo, 
			String combustible, int id_propietario) {
		
		 try {
		        String query = "INSERT INTO caract_vehiculo (matricula, color, marca, modelo, "
		        		+ "combustible, id_propietario) VALUES (?, ?, ?, ?, ?, ?)";
		        PreparedStatement statement = conexion.prepareStatement(query);
		        statement.setString(1, matricula);
		        statement.setString(2, color);
		        statement.setString(3, marca);
		        statement.setString(4, modelo);
		        statement.setString(5, combustible);
		        statement.setInt(6, id_propietario);
		        statement.executeUpdate();
		        // Aquí puedes agregar cualquier lógica adicional después de guardar los datos
		        // Por ejemplo, mostrar un mensaje de éxito o actualizar la vista
		    } catch (SQLException e) {
		        // Manejo de excepciones, por ejemplo, mostrar un mensaje de error o registrar la excepción
		        e.printStackTrace();
		    }
	}
	
	public int obtenerIdPropietario(String dni) {
	    int id_Propietario = -1; // Valor por defecto en caso de error

	    try {
	        String query = "SELECT id_propietario FROM dato_propietario WHERE dni = ?";
	        PreparedStatement statement = conexion.prepareStatement(query);
	        statement.setString(1, dni);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            id_Propietario = resultSet.getInt("id_propietario");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return id_Propietario;
	}
	
	public void guardarDatosReparacion(int id_vehiculo) {
	    try {
	        // Obtener la fecha actual
	        LocalDateTime fechaActual = LocalDateTime.now();
	        
	        // Convertir la fecha actual a formato compatible con MySQL (java.sql.Timestamp)
	        Timestamp timestamp = Timestamp.valueOf(fechaActual);

	        // Insertar los datos en la tabla datos_reparacion
	        String query = "INSERT INTO datos_reparacion (motivo_visita, inicio_reparacion, fin_reparacion, fecha_inicio_repar, fecha_fin_repar, id_vehiculo) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = conexion.prepareStatement(query);
	        statement.setString(1, "Apertura ficha");
	        statement.setBoolean(2, false);
	        statement.setBoolean(3, true);
	        statement.setTimestamp(4, timestamp);
	        statement.setTimestamp(5, timestamp);
	        statement.setInt(6, id_vehiculo);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public int obtenerIdVehiculo(String matricula) {
	    int idVehiculo = -1; // Valor por defecto en caso de error

	    try {
	        String query = "SELECT id_vehiculo FROM caract_vehiculo WHERE matricula = ?";
	        PreparedStatement statement = conexion.prepareStatement(query);
	        statement.setString(1, matricula);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            idVehiculo = resultSet.getInt("id_vehiculo");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idVehiculo;
	}

	
	public void guardarDatosPropietario(String nombre, String dni, String apellido1, 
			String telefono, String apellido2, String correoElectronico) {
		
		 try {
		        String query = "INSERT INTO dato_propietario (nombre, dni, apellido_1, telefono, "
		        		+ "apellido_2, correo_electr) VALUES (?, ?, ?, ?, ?, ?)";
		        PreparedStatement statement = conexion.prepareStatement(query);
		        statement.setString(1, nombre);
		        statement.setString(2, dni);
		        statement.setString(3, apellido1);
		        statement.setString(4, telefono);
		        statement.setString(5, apellido2);
		        statement.setString(6, correoElectronico);
		        statement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	public List<String> obtenerMarcasVehiculos() {
        List<String> marcas = new ArrayList<>();
        // Consultar la base de datos para obtener las marcas de vehículos
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT DISTINCT marca FROM caract_vehiculo");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String marca = resultSet.getString("marca");
                marcas.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marcas;
    }
	
	 public List<String> obtenerModelosVehiculo(String marcaSeleccionada) {
	        List<String> modelos = new ArrayList<>();
	        // Consultar la base de datos para obtener los modelos de vehículos de una marca específica
	        try {
	            PreparedStatement statement = conexion.prepareStatement("SELECT modelo FROM caract_vehiculo WHERE marca = ?");
	            statement.setString(1, marcaSeleccionada);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                String modelo = resultSet.getString("modelo");
	                modelos.add(modelo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return modelos;
	    }
	
	 public void cerrarConexion() {
	        // Cerrar la conexión a la base de datos
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
