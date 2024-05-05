package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.AccesoUsuario;
import modelo.Pantalla1C_modelo;

public class Pantalla1C_Controlador {

	@FXML
	private Label label_nombre_de_mi;
	
	@FXML
	private TextField resp1_txtField_p1C;
	
	@FXML
	private Label label_el_año;
	
	@FXML
	private TextField resp2_txtField_p1C;
	
	@FXML
	private Button aceptar_button_p1C;
	
	
	private static boolean visible = false;
		
	AccesoUsuario accesoUsuario = new AccesoUsuario();
	Pantalla1C_modelo pantalla_1c = new Pantalla1C_modelo();
	PantallaInicioControlador controladorInicio = new PantallaInicioControlador();
	
	@FXML
	public void initialize() {
		
		  // Obtener las preguntas finales desde el modelo y establecerlas en los Label
        String pregunta1Final = pantalla_1c.obtenerPregunta1Final();
        String pregunta2Final = pantalla_1c.obtenerPregunta2Final();
        
        label_nombre_de_mi.setText(pregunta1Final);
        label_el_año.setText(pregunta2Final);
        
	}
	
	@FXML
	public void headleaceptar_button_p1CAction() {
		// Obtener las respuestas guardadas en la base de datos
        String respuesta1BD = pantalla_1c.obtenerRespuesta1();
        String respuesta2BD = pantalla_1c.obtenerRespuesta2();

        // Obtener las respuestas ingresadas por el usuario
        String respuesta1Usuario = resp1_txtField_p1C.getText();
        String respuesta2Usuario = resp2_txtField_p1C.getText();

        // Comparar las respuestas
        if (respuesta1Usuario.equals(respuesta1BD) && respuesta2Usuario.equals(respuesta2BD)) {
            // Si las respuestas son correctas, abrir la pantalla 1D y cerrar la pantalla actual
            AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1d());
            cerrarPantallaActual();
        } else {
            // Si las respuestas son incorrectas, abrir la pantalla de inicio y mostrar el mensaje
            visible = true;
        	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaInicio());
            
           // controladorInicio.mostrarMensajeInstalador();
            cerrarPantallaActual();
	 }
	}
	
	 private void cerrarPantallaActual() {
	        // Cerrar la ventana actual de la pantalla 1C
	        Stage stage = (Stage) aceptar_button_p1C.getScene().getWindow();
	        stage.close();
	    }
	 
	 public static boolean hacerVisible() {
		 return visible;
	 }
}
