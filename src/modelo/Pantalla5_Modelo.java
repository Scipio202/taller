package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pantalla5_Modelo {

	//buscar por matrícula
	public List<String[]> obtenerReparacionesPorMatricula(String matricula) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String[]> reparaciones = new ArrayList<>();

        try {
            connection = Conexion.obtenerConexion();
            String sql = "SELECT cv.matricula, cv.marca, cv.modelo, dr.motivo_visita, dr.observaciones, dr.fecha_inicio_repar, dr.fecha_fin_repar "
            		+ "FROM caract_vehiculo cv "
            		+ "JOIN datos_reparacion dr ON cv.id_vehiculo = dr.id_vehiculo "
            		+ "WHERE cv.matricula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, matricula);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] reparacion = new String[7];
                reparacion[0] = resultSet.getString("matricula");
                reparacion[1] = resultSet.getString("marca");
                reparacion[2] = resultSet.getString("modelo");
                reparacion[3] = resultSet.getString("motivo_visita");
                reparacion[4] = resultSet.getString("observaciones");
                reparacion[5] = resultSet.getTimestamp("fecha_inicio_repar").toString();
                Timestamp fechaFin = resultSet.getTimestamp("fecha_fin_repar");
                reparacion[6] = (fechaFin != null) ? fechaFin.toString() : "Reparación abierta";
                reparaciones.add(reparacion);
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

        return reparaciones;
    }
	
	//buscar por marca
	public List<String[]> obtenerReparacionesPorMarca(String marca) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String[]> reparaciones = new ArrayList<>();

        try {
            connection = Conexion.obtenerConexion();
            String sql = "SELECT cv.matricula, cv.marca, cv.modelo, dr.motivo_visita, dr.observaciones, dr.fecha_inicio_repar, dr.fecha_fin_repar "
            		+ "FROM caract_vehiculo cv "
            		+ "JOIN datos_reparacion dr ON cv.id_vehiculo = dr.id_vehiculo "
            		+ "WHERE cv.marca = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, marca);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] reparacion = new String[7];
                reparacion[0] = resultSet.getString("matricula");
                reparacion[1] = resultSet.getString("marca");
                reparacion[2] = resultSet.getString("modelo");
                reparacion[3] = resultSet.getString("motivo_visita");
                reparacion[4] = resultSet.getString("observaciones");
                reparacion[5] = resultSet.getTimestamp("fecha_inicio_repar").toString();
                Timestamp fechaFin = resultSet.getTimestamp("fecha_fin_repar");
                reparacion[6] = (fechaFin != null) ? fechaFin.toString() : "Reparación abierta";
                reparaciones.add(reparacion);
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

        return reparaciones;
    }
	
	//buscar por marca y modelo
	
    public List<String[]> obtenerReparacionesPorMarcaYModelo(String marca, String modelo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String[]> reparaciones = new ArrayList<>();

        try {
            connection = Conexion.obtenerConexion();
            String query = "SELECT cv.matricula, cv.marca, cv.modelo, dr.motivo_visita, dr.observaciones, dr.fecha_inicio_repar, dr.fecha_fin_repar "
                         + "FROM caract_vehiculo cv "
                         + "JOIN datos_reparacion dr ON cv.id_vehiculo = dr.id_vehiculo "
                         + "WHERE cv.marca = ? AND cv.modelo = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String[] reparacion = new String[7];
                reparacion[0] = resultSet.getString("matricula");
                reparacion[1] = resultSet.getString("marca");
                reparacion[2] = resultSet.getString("modelo");
                reparacion[3] = resultSet.getString("motivo_visita");
                reparacion[4] = resultSet.getString("observaciones");
                //reparacion[5] = resultSet.getString("fecha_inicio_repar");
                //reparacion[6] = resultSet.getString("fecha_fin_repar");
                reparacion[5] = resultSet.getTimestamp("fecha_inicio_repar").toString();
                Timestamp fechaFin = resultSet.getTimestamp("fecha_fin_repar");
                reparacion[6] = (fechaFin != null) ? fechaFin.toString() : "Reparación abierta";
                reparaciones.add(reparacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          
        	try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
        	}catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        
        return reparaciones;
    }
	
    //buscar por motivo reparación
 // Método para buscar reparaciones por motivo de reparación
    public List<String[]> obtenerReparacionesPorMotivo(String motivo) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String[]> reparaciones = new ArrayList<>();

        try {
            connection = Conexion.obtenerConexion();

            // Crear un arreglo con los términos de búsqueda
            String[] terminos = motivo.split("\\s+");
            StringBuilder queryBuilder = new StringBuilder("SELECT cv.matricula, cv.marca, cv.modelo, dr.motivo_visita, dr.observaciones, dr.fecha_inicio_repar, dr.fecha_fin_repar ")
                    .append("FROM caract_vehiculo cv ")
                    .append("JOIN datos_reparacion dr ON cv.id_vehiculo = dr.id_vehiculo ")
                    .append("WHERE ");

            // Construir las condiciones LIKE para cada término
            for (int i = 0; i < terminos.length; i++) {
                queryBuilder.append("dr.motivo_visita LIKE ? ");
                if (i < terminos.length - 1) {
                    queryBuilder.append("OR ");
                }
            }

            statement = connection.prepareStatement(queryBuilder.toString());

            // Establecer los valores de las condiciones LIKE
            for (int i = 0; i < terminos.length; i++) {
                statement.setString(i + 1, "%" + terminos[i] + "%");
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] reparacion = new String[7];
                reparacion[0] = resultSet.getString("matricula");
                reparacion[1] = resultSet.getString("marca");
                reparacion[2] = resultSet.getString("modelo");
                reparacion[3] = resultSet.getString("motivo_visita");
                reparacion[4] = resultSet.getString("observaciones");
                reparacion[5] = resultSet.getTimestamp("fecha_inicio_repar").toString();
                Timestamp fechaFin = resultSet.getTimestamp("fecha_fin_repar");
                reparacion[6] = (fechaFin != null) ? fechaFin.toString() : "Reparación abierta";
                reparaciones.add(reparacion);
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

        return reparaciones;
    }

	
	//buscar entre fechas indicadas
    public List<String[]> obtenerReparacionesEntreFechas(Date fechaInicio, Date fechaFin) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String[]> reparaciones = new ArrayList<>();

        try {
            connection = Conexion.obtenerConexion();
            LocalDateTime fechaFinLDT = fechaFin.toLocalDate().atTime(23, 59, 59);
            Timestamp fechaFinTimestamp = Timestamp.valueOf(fechaFinLDT);
            String sql = "SELECT cv.matricula, cv.marca, cv.modelo, dr.motivo_visita, dr.observaciones, dr.fecha_inicio_repar, dr.fecha_fin_repar "
                       + "FROM caract_vehiculo cv "
                       + "JOIN datos_reparacion dr ON cv.id_vehiculo = dr.id_vehiculo "
                       + "WHERE dr.fecha_inicio_repar BETWEEN ? AND ?";

            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
           // statement.setDate(2, new java.sql.Date(fechaFin.getTime()));
            statement.setTimestamp(2, fechaFinTimestamp);
            
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] reparacion = new String[7];
                reparacion[0] = resultSet.getString("matricula");
                reparacion[1] = resultSet.getString("marca");
                reparacion[2] = resultSet.getString("modelo");
                reparacion[3] = resultSet.getString("motivo_visita");
                reparacion[4] = resultSet.getString("observaciones");
                reparacion[5] = resultSet.getTimestamp("fecha_inicio_repar").toString();
                Timestamp fechaFinReparacion = resultSet.getTimestamp("fecha_fin_repar");
                reparacion[6] = (fechaFinReparacion != null) ? fechaFinReparacion.toString() : "Reparación abierta";
                reparaciones.add(reparacion);
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

        return reparaciones;
    }

	
	//buscar entre una fecha hasta hoy
	
	//buscar todo hasta una fecha concreta
    public List<String[]> obtenerReparacionesHastaFecha(Date fechaFin) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String[]> reparaciones = new ArrayList<>();

        try {
            connection = Conexion.obtenerConexion();
            
            LocalDateTime fechaFinLDT = fechaFin.toLocalDate().atTime(23, 59, 59);
            Timestamp fechaFinTimestamp = Timestamp.valueOf(fechaFinLDT);
            
            String sql = "SELECT cv.matricula, cv.marca, cv.modelo, dr.motivo_visita, dr.observaciones, dr.fecha_inicio_repar, dr.fecha_fin_repar "
                       + "FROM caract_vehiculo cv "
                       + "JOIN datos_reparacion dr ON cv.id_vehiculo = dr.id_vehiculo "
                       + "WHERE dr.fecha_inicio_repar <= ?";

            statement = connection.prepareStatement(sql);
           // statement.setDate(1, new java.sql.Date(fechaFin.getTime()));
            statement.setTimestamp(1, fechaFinTimestamp);
            
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String[] reparacion = new String[7];
                reparacion[0] = resultSet.getString("matricula");
                reparacion[1] = resultSet.getString("marca");
                reparacion[2] = resultSet.getString("modelo");
                reparacion[3] = resultSet.getString("motivo_visita");
                reparacion[4] = resultSet.getString("observaciones");
                reparacion[5] = resultSet.getTimestamp("fecha_inicio_repar").toString();
                Timestamp fechaFinReparacion = resultSet.getTimestamp("fecha_fin_repar");
                reparacion[6] = (fechaFinReparacion != null) ? fechaFinReparacion.toString() : "Reparación abierta";
                reparaciones.add(reparacion);
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

        return reparaciones;
    }

	
}
