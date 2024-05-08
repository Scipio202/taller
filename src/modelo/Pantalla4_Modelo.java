package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pantalla4_Modelo {
	
	String matricula;
	
	public String obtenerColor() {
        String color = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            String sql = "SELECT color FROM caract_vehiculo WHERE matricula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, matricula);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                color = resultSet.getString("color");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return color;
    }

    public String obtenerMarca() {
        String marca = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            String sql = "SELECT marca FROM caract_vehiculo WHERE matricula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, matricula);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                marca = resultSet.getString("marca");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return marca;
    }

    public String obtenerModelo() {
        String modelo = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            String sql = "SELECT modelo FROM caract_vehiculo WHERE matricula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, matricula);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                modelo = resultSet.getString("modelo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return modelo;
    }
	
	public void setMatricula(String matriculaP4C) {
		
		matricula = matriculaP4C;
	}
	
	public void guardarMotivoReparacion(String motivo_repar) {
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO datos_reparacion (motivo_visita, inicio_reparacion, fin_reparacion, fecha_inicio_repar, id_vehiculo) VALUES (?, ?, ?, NOW(), ?)");
        ) {
            statement.setString(1, motivo_repar);
            statement.setBoolean(2, true);
            statement.setBoolean(3, false);
            statement.setInt(4, obtenerIdVehiculo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	private int obtenerIdVehiculo() {
        int idVehiculo = -1;
        try (Connection connection = Conexion.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement("SELECT id_vehiculo FROM caract_vehiculo WHERE matricula = ?");
        ) {
            statement.setString(1, matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idVehiculo = resultSet.getInt("id_vehiculo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idVehiculo;
    }
	
	private int obtenerIdReparacion(String matricula) {
	    int idReparacion = -1;
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = Conexion.obtenerConexion();
	        String sql = "SELECT id_reparacion FROM datos_reparacion WHERE id_vehiculo = (SELECT id_vehiculo FROM caract_vehiculo WHERE matricula = ?) AND inicio_reparacion = true";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, matricula);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            idReparacion = resultSet.getInt("id_reparacion");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return idReparacion;
	}
	
	public void guardarTextoReparacion(String textoReparacion) {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        // Obtener el ID de reparación usando la matrícula y la condición de inicio de reparación
	        int idReparacion = obtenerIdReparacion(matricula);

	        // Si no se encuentra un ID de reparación válido, no se puede guardar el texto de la reparación
	        if (idReparacion == -1) {
	            System.out.println("No se encontró una reparación abierta para el vehículo con la matrícula: " + matricula);
	            return;
	        }

	        connection = Conexion.obtenerConexion();
	        String sql = "UPDATE datos_reparacion SET observaciones = ? WHERE id_reparacion = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, textoReparacion);
	        statement.setInt(2, idReparacion);
	        int rowsAffected = statement.executeUpdate();

	        // Si no se actualiza ninguna fila, es probable que esta sea una nueva reparación
	        // En ese caso, inserta una nueva fila en la tabla
	        if (rowsAffected == 0) {
	            System.out.println("No se encontró una reparación abierta para la matrícula: " + matricula);
	            return;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void finalizarReparacion(String fechaFinReparacion) {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        connection = Conexion.obtenerConexion();
	        String sql = "UPDATE datos_reparacion SET fin_reparacion = ?, inicio_reparacion = ?, fecha_fin_repar = ? WHERE id_reparacion = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setBoolean(1, true); // Fin de reparación = true
	        statement.setBoolean(2, false); // Inicio de reparación = false
	        statement.setString(3, fechaFinReparacion); // Fecha de finalización de reparación
	        statement.setInt(4, obtenerIdReparacion(matricula)); // Obtener el ID de la reparación
	        
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected == 0) {
	            System.out.println("No se encontró una reparación abierta para la matrícula: " + matricula);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public String obtenerMotivoReparacion(String matricula) {
        String motivoReparacion = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            String sql = "SELECT motivo_visita FROM datos_reparacion WHERE id_vehiculo = (SELECT id_vehiculo FROM caract_vehiculo WHERE matricula = ?) AND inicio_reparacion = true";
            statement = connection.prepareStatement(sql);
            statement.setString(1, matricula);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                motivoReparacion = resultSet.getString("motivo_visita");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return motivoReparacion;
    }
	
	 public String obtenerTextoReparacion(String matricula) {
	        String textoReparacion = null;
	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = Conexion.obtenerConexion();
	            String sql = "SELECT observaciones FROM datos_reparacion WHERE id_vehiculo = (SELECT id_vehiculo FROM caract_vehiculo WHERE matricula = ?) AND inicio_reparacion = true";
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, matricula);
	            resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                textoReparacion = resultSet.getString("observaciones");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return textoReparacion;
	    }

		
}
