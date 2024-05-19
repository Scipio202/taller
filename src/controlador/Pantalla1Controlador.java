package controlador;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Pantalla1_Modelo;


public class Pantalla1Controlador {

	  @FXML
	    private TextField ingr_matric_txtField_p1;

	    @FXML
	    private Button compr_button_p1;

	    @FXML
	    private Button nuevaReparacionButton;

	    @FXML
	    private Button continuarReparacionButton;

	    @FXML
	    private Label intr_matr_label_p1;

	    private Pantalla1_Modelo pantalla1_modelo = new Pantalla1_Modelo();	   	    
	    
	    String matriculaP4 = "";

	    @FXML
	    private void initialize() {
	        // Ocultar el mensaje de error al inicio
	        intr_matr_label_p1.setVisible(false);
	    }

	    @FXML
	    public void handleComprobarButtonAction() {
	        String matricula = ingr_matric_txtField_p1.getText().toLowerCase(); // Convertir a minúsculas
	        matriculaP4 = matricula;
	       
	        // Validar que se haya ingresado una matrícula
	        if (matricula.isEmpty()) {
	            // Mostrar el mensaje de error
	            intr_matr_label_p1.setVisible(true);
	            System.out.println("Prueba de acceso");
	        } else {
	            // Comprobar si la matrícula existe en la base de datos
	            boolean matriculaExiste = pantalla1_modelo.comprobarMatricula(matricula);

	            if (!matriculaExiste) {
	                // Si la matrícula no existe, abrir la pantalla 2 para registrarla
	                AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP2());
	                cerrarPantallaActual();
	            } else {
	                // Si la matrícula existe, comprobar si tiene una reparación abierta
	                boolean tieneReparacionAbierta = pantalla1_modelo.comprobarReparacionAbierta(matricula);

	                if (tieneReparacionAbierta) {
	                    // Si tiene una reparación abierta, activar el botón de continuar reparación
	                    continuarReparacionButton.setDisable(false);
	                    nuevaReparacionButton.setDisable(true);
	                } else {
	                    // Si no tiene una reparación abierta, activar el botón de nueva reparación
	                    continuarReparacionButton.setDisable(true);
	                    nuevaReparacionButton.setDisable(false);
	                }
	            }
	        }
	    }
	    
	   
	    
	    public void handlerNuevaReparAction() { // Botón Nueva Reparación
	    	 
	    	String matricula = ingr_matric_txtField_p1.getText().toLowerCase();
	    	 Pantalla4_Controlador pantalla4Controlador = new Pantalla4_Controlador(matricula, true);
	         AperturaVentana.abrirVentanaObj(AperturaVentana.getRutaPantallaP4(), pantalla4Controlador);
	    	
	    	cerrarPantallaActual();
	    }
	    
	    public void handlerContReparAction() { // Botón Continuar Reparación 
	    	
	    	String matricula = ingr_matric_txtField_p1.getText().toLowerCase();
	    	 Pantalla4_Controlador pantalla4Controlador = new Pantalla4_Controlador(matricula, false);
	         AperturaVentana.abrirVentanaObj(AperturaVentana.getRutaPantallaP4(), pantalla4Controlador);
	    	
	    	cerrarPantallaActual();
	    }
	    
	    public void handlerHistorialAction() { // Botón Acceso a Consultas
	    		    	 
	         AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP5());;
	    	
	    	cerrarPantallaActual();
	    }

	    private void cerrarPantallaActual() {
	        // Cerrar la ventana actual de la pantalla 1
	        Stage stage = (Stage) compr_button_p1.getScene().getWindow();
	        stage.close();
	    }
	
}
