package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pantalla4_Modelo;

public class Pantalla4_Controlador {

	@FXML
	private Label matricula_label_p4;
	
	@FXML
	private Label color_label_p4;
	
	@FXML
	private Label marca_label_p4;
	
	@FXML
	private Label modelo_label_p4;

 	@FXML
    private TextField motivo_reparac_txtF_p4;

    @FXML
    private Button iniciar_reparac_button_p4;

    @FXML
    private Button fin_reparac_button_p4;

    @FXML
    private Button guardar_txtA_button_p4;

    @FXML
    private TextArea datos_reparac_txtA_p4;

    @FXML
    private Button volver_pant_gral_button_p4;
    
   // Enlace_p1_p4 enlace = new Enlace_p1_p4();
    private String matricula;
    private boolean iniciarReparacion;
    private String textoDatos = "a";
    Pantalla4_Modelo p4_modelo = new Pantalla4_Modelo();
    
    public Pantalla4_Controlador(String matricula, boolean iniciarReparacion) {
    	
    	this.matricula = matricula;
    	this.iniciarReparacion = iniciarReparacion;
    }

    @FXML
    private void initialize() {
        System.out.println(getMatricula() + isInicioReparacion());
        p4_modelo.setMatricula(matricula);
        matricula_label_p4.setText(matricula);
       
        String color = p4_modelo.obtenerColor();
        String marca = p4_modelo.obtenerMarca();
        String modelo = p4_modelo.obtenerModelo();
        System.out.println(color + marca + modelo);
        color_label_p4.setText(color);
        marca_label_p4.setText(marca);
        modelo_label_p4.setText(modelo);
        inicializarReparacion();
    }
    
    public void setInicioReparación(boolean iniciarReparacion) {
    	
    	this.iniciarReparacion = iniciarReparacion;
    }
    
    
    
    public void inicializarReparacion() {
        System.out.println("prueba de entrada en inicializarReparacion");
    	if (iniciarReparacion) {
            // Habilitar elementos para iniciar reparación
            motivo_reparac_txtF_p4.setDisable(false);
            iniciar_reparac_button_p4.setDisable(false);
            fin_reparac_button_p4.setDisable(true);
            guardar_txtA_button_p4.setDisable(true);
            datos_reparac_txtA_p4.setDisable(true);
        } else {
            // Habilitar elementos para continuar reparación
            motivo_reparac_txtF_p4.setDisable(true);
            iniciar_reparac_button_p4.setDisable(true);
            fin_reparac_button_p4.setDisable(false);
            guardar_txtA_button_p4.setDisable(false);
            datos_reparac_txtA_p4.setDisable(false);
            
            mostrarReparacion();
        }
        
        this.iniciarReparacion = iniciarReparacion;
    }
    
    public void mostrarReparacion() {
    	
    	String txtMotivoRep = p4_modelo.obtenerMotivoReparacion(matricula);
        String txtObserv = p4_modelo.obtenerTextoReparacion(matricula);
        
        motivo_reparac_txtF_p4.setText(txtMotivoRep);
        datos_reparac_txtA_p4.setText(txtObserv);
    }
    
    public void setMatricula(String matriculaP1) {
    	
    	matricula = matriculaP1;
    	
    	
    }
    
    public String getMatricula() {
    	return matricula;
    }
    
    public boolean isInicioReparacion() {
    	return iniciarReparacion;
    }
    @FXML
    public void boton_pantGralAction() {
    	
    	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());
    	cerrarPantallaActual();
    }
    
    @FXML
    public void boton_iniciar_reparac_button_p4Action() { 
    	System.out.println("Entrada en el botón iniciar Reparación");
    	//Guardar el txtfield motivo de la reparación + activar botón guardar y txtArea
    	String motivoReparacion = motivo_reparac_txtF_p4.getText().trim(); // Obtener el texto y eliminar espacios en blanco al inicio y al final
    	 if (motivoReparacion.isEmpty()) {
    	        // Si el campo está vacío, no hacer nada
    	        return;
    	    }
    	 
    	 p4_modelo.guardarMotivoReparacion(motivoReparacion);
    	 motivo_reparac_txtF_p4.setDisable(true);
    	 iniciar_reparac_button_p4.setDisable(true);
    	 //habilitar zona de datos reparación y botón guardar.
    	 datos_reparac_txtA_p4.setDisable(false);
    	 guardar_txtA_button_p4.setDisable(false);
    	
    }
    
    @FXML
    public void boton_guardar_txtA_button_p4Action() {
    	
    	String datosReparacion = datos_reparac_txtA_p4.getText();
        if (!datosReparacion.isEmpty()) {
        	p4_modelo.guardarTextoReparacion(datosReparacion);
        	datos_reparac_txtA_p4.setDisable(false);
        	guardar_txtA_button_p4.setDisable(false);
        	fin_reparac_button_p4.setDisable(false);
            textoDatos = datosReparacion;
        }
    }
    
    @FXML
    public void boton_fin_reparac_button_p4Action() {
    	
    	String datosReparacion = datos_reparac_txtA_p4.getText();
    	if(!textoDatos.equals(datosReparacion)) {
    		
    		fin_reparac_button_p4.setDisable(true);
    	}else {
    		
    		LocalDateTime fechaFinReparacion = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaConFormato = fechaFinReparacion.format(formato);
            
            p4_modelo.finalizarReparacion(fechaConFormato);
            
            AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());
            cerrarPantallaActual();
    	}
    }
    
    private void cerrarPantallaActual() {
        // Cerrar la ventana actual de la pantalla 1
        Stage stage = (Stage) volver_pant_gral_button_p4.getScene().getWindow();
        stage.close();
    }
}
