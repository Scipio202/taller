package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pantalla1D_modelo;

public class Pantalla1D_Controtador {

	@FXML
	private TextField restContr_txtField_p1D;
	
	@FXML
	private Button guardar_button_p1D;
	
	@FXML Label caract_mn4_label_p1D;
	
	private Pantalla1D_modelo p1d_modelo = new Pantalla1D_modelo();
	
	@FXML
	private void initialize() {
		caract_mn4_label_p1D.setVisible(false);
	}
	
	
	@FXML
	public void handlerPantalla1D_ControtadorAction() {
		
		String clave = restContr_txtField_p1D.getText();
		
		 if (clave.length() < 4) {
	            // Si tiene menos de 4 caracteres, hacer visible el mensaje de error
	            caract_mn4_label_p1D.setVisible(true);
	        } else {
	            // Si tiene al menos 4 caracteres, continuar con el proceso de guardar la clave
		
		 boolean guardadoExitoso = p1d_modelo.guardarClave(clave);

	        // Verificar si el guardado fue exitoso
	        if (guardadoExitoso) {
	            // Abrir la pantalla de inicio
	        	 AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaInicio());

	            // Cerrar la ventana actual de la pantalla 1D
	            cerrarPantallaActual();
	        } else {
	            // Manejar el caso en que el guardado no fue exitoso
	            // Por ejemplo, mostrar un mensaje de error
	        	 AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaInicio());
	        	 cerrarPantallaActual();
	        	}
	        }   
	}
	
	 private void cerrarPantallaActual() {
	        // Cerrar la ventana actual de la pantalla 1D
	        Stage stage = (Stage) guardar_button_p1D.getScene().getWindow();
	        stage.close();
	    }
}
