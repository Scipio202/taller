package controlador;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.WrappingTextTableCell;


public class Pantalla6_Controlador implements Initializable{

		@FXML
	    private TableView<String[]> resultados_tableView;

	    @FXML
	    private TableColumn<String[], String> column_matricula_p6;

	    @FXML
	    private TableColumn<String[], String> column_marca_p6;

	    @FXML
	    private TableColumn<String[], String> column_modelo_p6;

	    @FXML
	    private TableColumn<String[], String> column_motivo_p6;

	    @FXML
	    private TableColumn<String[], String> column_informe_p6;

	    @FXML
	    private TableColumn<String[], String> column_fecha_inicio_p6;

	    @FXML
	    private TableColumn<String[], String> column_fecha_fin_p6;

	    @FXML
	    private Button btn_volver_consultas_p6;

	    @FXML
	    private Button btn_volver_general_p6;

	    @FXML
	    private Button btn_guardar_archivo_p6;

	    @FXML
	    private Button btn_imprimir_p6;
	    
	    private List<String[]> reparaciones; // Lista para almacenar las reparaciones

	    // Constructor para recibir las reparaciones
	    public Pantalla6_Controlador(List<String[]> reparaciones) {
	        this.reparaciones = reparaciones;
	        
	        // Configurar las columnas de la tabla
    	 	
	    }

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	       
	    	 resultados_tableView.getStylesheets().add(getClass().getResource("/util/tableStyles.css").toExternalForm());
	    	configurarColumnas();
	    	configurarEnvolturaTexto();
	    	}
	    
	    private void configurarColumnas() {
	    	
	    	column_matricula_p6.setCellValueFactory(cellData -> {if (cellData.getValue().length > 0) {
    	        return new SimpleStringProperty(cellData.getValue()[0]);
    	    } else {
    	        return new SimpleStringProperty("");
    	    }} );
    	    column_marca_p6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
    	    column_modelo_p6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
    	    column_motivo_p6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));
    	    
    	    column_informe_p6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[4]));
    	    column_fecha_inicio_p6.setCellValueFactory(cellData -> {if (cellData.getValue().length > 5) { // Verificar que el arreglo tenga al menos 6 elementos
    	        return new SimpleStringProperty(cellData.getValue()[5]);
    	    } else {
    	        return new SimpleStringProperty(""); // Devolver una cadena vacía si no hay suficientes elementos
    	    }});
    	    column_fecha_fin_p6.setCellValueFactory(cellData -> {if (cellData.getValue().length > 6) { // Verificar que el arreglo tenga al menos 7 elementos
    	        return new SimpleStringProperty(cellData.getValue()[6]);
    	    } else {
    	        return new SimpleStringProperty(""); // Devolver una cadena vacía si no hay suficientes elementos
    	    }});
	    }


	    public void mostrarReparaciones(List<String[]> reparaciones) {
	        // Limpiar la tabla
	        resultados_tableView.getItems().clear();

	        // Agregar los datos de las reparaciones a la tabla
	        for (String[] reparacion : reparaciones) {
	            resultados_tableView.getItems().add(reparacion);
	        }
	    }
	    
	    private void configurarEnvolturaTexto() {
	    	//column_informe_p6.setCellFactory(column -> new WrappingTextTableCell());
	    	 column_informe_p6.setCellFactory(WrappingTextTableCell.forTableColumn());
	    	    
	    }

	    // Métodos para manejar eventos de los botones u otras acciones
	    @FXML
	    private void btn_volver_consultas_p6Action() {
	    	System.out.println("Prueba volver P5");
	    	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP5());
	    	cerrarPantallaActual();
	    	
	    }
	    
	    @FXML
	    private void btn_volver_general_p6() {
	    	System.out.println("Prueba volver P1");
	    	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());
	    	cerrarPantallaActual();
	    }
	    
	    @FXML
	    private void btn_guardar_archivo_p6() { 
	    	System.out.println("Entrada método guardar archivo");
	    	 // Obtener la fecha actual y formatearla
	        LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String fechaFormateada = fechaActual.format(formatter);

	        // Crear el nombre del archivo con la fecha
	        String nombreArchivo = "Informe de Taller con fecha de " + fechaFormateada + ".txt";
	        File archivo = new File(nombreArchivo);

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
	            // Escribir la cabecera de la tabla con tabulaciones
	            writer.write("MATRÍCULA\tMARCA\tMODELO\t\tMOTIVO REPARACIÓN\tFECHA INICIO\t\tFECHA FIN\t\t\tINFORME");
	            writer.newLine();

	            // Escribir cada fila de la tabla con tabulaciones
	            for (String[] reparacion : reparaciones) {
	            	String tabulacionesModelo = reparacion[2].length() > 9 ? "\t" : "\t\t";
	            	System.out.println("Tamaño modelo: " + reparacion[2].length());
	            	 // Calcular la longitud total actual de la línea hasta reparacion[2]
	                int longitudModelo = reparacion[0].length() + 2*8 + reparacion[1].length() + 1*8 + reparacion[2].length();

	                System.out.println("Longitud modelo: " + longitudModelo);
	                // Verificar si reparacion[2] está a menos de 3 espacios de reparacion[3]
	                int espaciosHastaMotivo = 48 - longitudModelo;
	                System.out.println("Espacio hasta motivo" + espaciosHastaMotivo);
	                if (espaciosHastaMotivo < 5) {
	                    tabulacionesModelo = "\t\t";
	                }
	                writer.write(
	                    reparacion[0] + "\t\t" +  // Matrícula
	                    reparacion[1] + "\t" +    // Marca
	                    reparacion[2] + tabulacionesModelo +  // Modelo
	                    reparacion[3] + "\t\t" +  // Motivo Reparación
	                    reparacion[5] + "\t" +    // Fecha Inicio
	                    reparacion[6] + "\t\t" +  // Fecha Fin
	                    reparacion[4]              // Informe
	                );
	                writer.newLine();
	            }

	            System.out.println("Archivo guardado correctamente en: " + archivo.getAbsolutePath());

	            // Abrir el archivo automáticamente después de guardarlo
	            if (Desktop.isDesktopSupported()) {
	                Desktop.getDesktop().open(archivo);
	            } else {
	                System.out.println("La apertura automática de archivos no está soportada en esta plataforma.");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @FXML
	    private void btn_format_html_p6Action() {
	    	// Obtener la fecha actual y formatearla
	        LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String fechaFormateada = fechaActual.format(formatter);

	        // Crear el nombre del archivo con la fecha
	        String nombreArchivo = "Informe de Taller con fecha de " + fechaFormateada + ".html";
	        File archivo = new File(nombreArchivo);

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
	            // Escribir la cabecera del archivo HTML
	            writer.write("<html><head><title>Informe de Taller</title></head><body>");
	            writer.write("<h1>Informe de Taller con fecha de " + fechaFormateada + "</h1>");
	            writer.write("<table border='1'>");

	            // Escribir la cabecera de la tabla en HTML con negrita
	            writer.write("<tr><th>Matrícula</th><th>Marca</th><th>Modelo</th><th>Motivo Reparación</th><th>Fecha Inicio</th><th>Fecha Fin</th><th>Informe</th></tr>");

	            // Escribir cada fila de la tabla en HTML
	            for (String[] reparacion : reparaciones) {
	                writer.write("<tr>");
	                writer.write("<td>" + reparacion[0] + "</td>");
	                writer.write("<td>" + reparacion[1] + "</td>");
	                writer.write("<td>" + reparacion[2] + "</td>");
	                writer.write("<td>" + reparacion[3] + "</td>");
	                writer.write("<td>" + reparacion[5] + "</td>");
	                writer.write("<td>" + reparacion[6] + "</td>");
	                writer.write("<td>" + reparacion[4] + "</td>");
	                writer.write("</tr>");
	            }

	            // Cerrar la tabla y el archivo HTML
	            writer.write("</table></body></html>");

	            System.out.println("Archivo guardado correctamente en: " + archivo.getAbsolutePath());

	            // Abrir el archivo automáticamente después de guardarlo
	            if (Desktop.isDesktopSupported()) {
	                Desktop.getDesktop().open(archivo);
	            } else {
	                System.out.println("La apertura automática de archivos no está soportada en esta plataforma.");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    private void cerrarPantallaActual() {
	        // Cerrar la ventana actual de la pantalla 1
	        Stage stage = (Stage) btn_volver_consultas_p6.getScene().getWindow();
	        stage.close();
	    }
}
