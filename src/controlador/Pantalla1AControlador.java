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
import modelo.GuardarDatos_p1A_modelo;

public class Pantalla1AControlador {
	
	 	@FXML
	    private TextField usuario_txtField_p1A;

	    @FXML
	    private TextField clave_txtField_p1A;

	    @FXML
	    private TextField preg1_txtField_p1A;

	    @FXML
	    private TextField resp1_txtField_p1A;

	    @FXML
	    private TextField preg2_txtField_p1A;

	    @FXML
	    private TextField resp2_txtField_p1A;

	    @FXML
	    private Label caractMn4_p1A;

	    @FXML
	    private Label noVacio_label_p1A;

	    @FXML
	    private Button guardar_button_p1A;

	    @FXML
	    private Label error4digit_label_p1A;
	    
	    @FXML
	    private Button volverPant_Inic_button_p1A;

	    @FXML
	    private Label error_guardBD_label_p1A;
	    
	    public void initialize() {
	        // Oculta los mensajes de error al principio	       
	        noVacio_label_p1A.setVisible(false);
	        caractMn4_p1A.setVisible(false);
	        error_guardBD_label_p1A.setVisible(false);
	    }
	    
	    @FXML
	    private void handle_guardar_button_p1A_action() {
	        String usuario = usuario_txtField_p1A.getText();
	        String clave = clave_txtField_p1A.getText();
	        String pregunta1 = preg1_txtField_p1A.getText();
	        String respuesta1 = resp1_txtField_p1A.getText();
	        String pregunta2 = preg2_txtField_p1A.getText();
	        String respuesta2 = resp2_txtField_p1A.getText();

	        // Verifica si los campos de usuario y contraseña cumplen con los requisitos
	        if (usuario.length() < 4 || clave.length() < 4) {	           
	            caractMn4_p1A.setVisible(true);
	            return; // Sale del método para evitar la validación de preguntas y respuestas
	        } else {	            
	            caractMn4_p1A.setVisible(false);
	        }

	        // Verifica si los campos de preguntas y respuestas cumplen con los requisitos
	        if (pregunta1.isEmpty() || respuesta1.isEmpty() || pregunta2.isEmpty() || respuesta2.isEmpty()) {
	            noVacio_label_p1A.setVisible(true);
	            return; // Sale del método si hay algún campo vacío
	        } else {
	            noVacio_label_p1A.setVisible(false);
	        }

	        // Guarda los datos en la base de datos o realiza cualquier otra acción necesaria
	        GuardarDatos_p1A_modelo guardarDatos_BD = new GuardarDatos_p1A_modelo();
	       boolean guardadoExitoso = guardarDatos_BD.guardarDatos(usuario, clave, pregunta1, respuesta1, pregunta2, respuesta2);
	       
	       if (guardadoExitoso) {
	            // Si el guardado fue exitoso, cierra la ventana actual
	    	   	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaInicio());
	    	   	
	            Stage stage = (Stage) guardar_button_p1A.getScene().getWindow();
	            stage.close();
	        } else {
	            // Si hubo un error en el guardado, muestra el mensaje de error
	            error_guardBD_label_p1A.setVisible(true);
	        }
	        
	      

	       
	    }
	    @FXML
	       private void handle_volverPant_Inic_button_p1A_action() {
	    	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaInicio());
    	   	
            Stage stage = (Stage) volverPant_Inic_button_p1A.getScene().getWindow();
            stage.close();
	    	   
	       }
	
}
