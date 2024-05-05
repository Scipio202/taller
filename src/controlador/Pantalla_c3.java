package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Pantalla2_modelo;

public class Pantalla_c3 {

	/* 	@FXML
	    private Button aceptar_button_c3;

	    @FXML
	    private Button cancelar_button_c3;
	    
	   // private Pantalla2_Controlador pantalla2Controlador;
	    private Pantalla2_modelo pantalla2_modelo = new Pantalla2_modelo();
	    
	    String matricula;
        String color;
        String marca;
        String modelo;
        String combustible; 
        String nombre;
        String dni;
        String apellido1;
        String telefono;
        String apellido2;
        String correoElectronico;
       
	    
	    
	    @FXML
	    private void initialize() {
	    	
	    }
	    
	    	    
	    public void datosVehiculo(String matricula, String color, String marca, String modelo, 
			String combustible) {
	    	
	    	this.matricula = matricula;
	    	this.color = color;
	    	this.marca = marca;
	    	this.modelo = modelo;
	    	this.combustible = combustible;
	    	
	    }
	    
	    public void datosPropietario(String nombre, String dni, String apellido1, 
				String telefono, String apellido2, String correoElectronico) {
	    	
	    	this.nombre = nombre;
	    	this.dni = dni;
	    	this.apellido1 = apellido1;
	    	this.telefono = telefono;
	    	this.apellido2 = apellido2;
	    	this.correoElectronico = correoElectronico;
	    }
	    
	   
	    
	    @FXML    
	    private void aceptarButtonAction() {
	    	
	    	pantalla2_modelo.guardarDatosPropietario(nombre, dni, apellido1, telefono, apellido2, correoElectronico);
	    	int id_propietario = pantalla2_modelo.obtenerIdPropietario(dni);
	    	pantalla2_modelo.guardarDatosVehiculo(matricula, color, marca, modelo, combustible, id_propietario);
	    	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());	    	

	         // Cerrar la ventana c3_Finalizar_rellenar_datos.fxml
	         cerrarVentana();
	    }
	    
	    @FXML
	    private void cancelarButtonAction() {
	    	
	    	cerrarVentana();
	    }
	    
	    private void cerrarVentana() {
	        Stage stage = (Stage) aceptar_button_c3.getScene().getWindow();
	        stage.close();
	    }
/////////////////////////////////////////////////////////////////////////////////
	    
	   */ 
}
