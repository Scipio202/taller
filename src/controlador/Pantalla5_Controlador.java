package controlador;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Pantalla2_modelo;
import modelo.Pantalla5_Modelo;

public class Pantalla5_Controlador {

	@FXML
	private TextField matricula_txtF_p5;
	
	@FXML
	private ComboBox<String> marca_comBox_p5;
	
	@FXML
	private ComboBox<String> modelo_comBox_p5;
	
	@FXML
	private TextField motiv_repar_txtF_p5;
	
	@FXML
	private DatePicker fech_inic_datePick_p5;
	
	@FXML
	private DatePicker fech_fin_datePick_p5;
	
	@FXML
	private Button buscar_button_p5;
	
	@FXML
	private Button volver_pantGral_button_p5;
	
	
	Pantalla2_modelo pantalla2_modelo = new Pantalla2_modelo();
	Pantalla5_Modelo pantalla5_modelo = new Pantalla5_Modelo();
	private byte funcionBoton = 0;
	
	
	public void initialize() {
		marca_comBox_p5.setEditable(true);
		//fech_inic_datePick_p5.setEditable(true);
		pantallaSinSeleccion();
		seleccConsulta();
		cargarMarcas();
	}
	
	public void seleccConsulta() {
		
		 matricula_txtF_p5.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (!newValue.isEmpty()) {
	                // Si se ingresa una matrícula, deshabilitar todo menos el botón de búsqueda
	                marca_comBox_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(true);
	                motiv_repar_txtF_p5.setDisable(true);
	                fech_inic_datePick_p5.setDisable(true);
	                fech_fin_datePick_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 1;
	            } else {
	                // Si se borra la matrícula, habilitar todos los campos excepto el ComboBox de modelo y deshabilitar el botón de búsqueda
	            	pantallaSinSeleccion();
	            }
	        });

	        // Listener para el ComboBox de marca
	        marca_comBox_p5.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	                // Si se selecciona una marca, deshabilitar todo menos el ComboBox de modelo y el botón de búsqueda
	                matricula_txtF_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(false);
	                motiv_repar_txtF_p5.setDisable(true);
	                fech_inic_datePick_p5.setDisable(true);
	                fech_fin_datePick_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 2;
	                // Luego cargar modelos según la marca seleccionada
	                cargarModelosPorMarca();
	            } else {
	                // Si se deselecciona la marca, habilitar todos los campos excepto el ComboBox de modelo y deshabilitar el botón de búsqueda
	            	pantallaSinSeleccion();
	            }
	        });
	        
	     // Listener para manejar la deselección de elementos en el ComboBox de marcas
	        marca_comBox_p5.getEditor().setOnKeyReleased(event -> {
	            if (marca_comBox_p5.getEditor().getText().isEmpty()) {
	                pantallaSinSeleccion();
	            }
	        });

	        // Listeners para los otros campos (no se necesitan para DatePicker porque se utilizan los valores nulos para manejar su estado)
	        motiv_repar_txtF_p5.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (!newValue.isEmpty()) {
	                // Si se ingresa un motivo de reparación, deshabilitar todo menos el botón de búsqueda
	                matricula_txtF_p5.setDisable(true);
	                marca_comBox_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(true);
	                fech_inic_datePick_p5.setDisable(true);
	                fech_fin_datePick_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 3;
	            } else {
	                // Si se borra el motivo de reparación, habilitar todos los campos excepto el ComboBox de modelo y deshabilitar el botón de búsqueda
	            	pantallaSinSeleccion();
	            }
	        });
	        
	     // Listener para el DatePicker de la fecha de inicio de reparación
	        fech_inic_datePick_p5.valueProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null && fech_fin_datePick_p5.getValue() != null) {
	                // Si se selecciona una fecha de inicio, deshabilitar todo menos la fecha de fin y el botón de búsqueda
	            	System.out.println("FI 1.4");
	                matricula_txtF_p5.setDisable(true);
	                marca_comBox_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(true);
	                motiv_repar_txtF_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 4;
	                
	            } else if (newValue != null ) {
	                // Si solo se selecciona una fecha de inicio, habilitar el botón de búsqueda
	            	System.out.println("FI 1.5");
	                matricula_txtF_p5.setDisable(true);
	                marca_comBox_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(true);
	                motiv_repar_txtF_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 5; 
	                
	            } else {
	                // Si se borra la fecha de inicio, habilitar todos los campos excepto el ComboBox de modelo y deshabilitar el botón de búsqueda
	            	pantallaSinSeleccion();
	            }
	        });
	        
	     // Listener para manejar la deselección de elementos en el DatePicker de fecha de inicio
	        fech_inic_datePick_p5.getEditor().setOnKeyReleased(event -> {
	            if (fech_inic_datePick_p5.getEditor().getText().isEmpty()) {
	                pantallaSinSeleccion();
	            }
	        });

	        
	     // Listener para el DatePicker de la fecha de fin de reparación
	        fech_fin_datePick_p5.valueProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null && fech_inic_datePick_p5.getValue() != null) {
	                // Si se selecciona una fecha de fin, deshabilitar todo menos la fecha de inicio y el botón de búsqueda
	            	System.out.println("Ff 2.5.1");
	                matricula_txtF_p5.setDisable(true);
	                marca_comBox_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(true);
	                motiv_repar_txtF_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 4;
	                
	            } else if (newValue != null) {
	                // Si solo se selecciona una fecha de fin, habilitar el botón de búsqueda
	            	System.out.println("Ff 2.5.2");
	                matricula_txtF_p5.setDisable(true);
	                marca_comBox_p5.setDisable(true);
	                modelo_comBox_p5.setDisable(true);
	                motiv_repar_txtF_p5.setDisable(true);
	                buscar_button_p5.setDisable(false);
	                funcionBoton = 5; 
	                
	            } else {
	                // Si se borra la fecha de fin, habilitar todos los campos excepto el ComboBox del modelo y deshabilitar el botón de búsqueda
	            	pantallaSinSeleccion();
	            }
	        });
	        
	        // Listener para manejar la deselección de elementos en el DatePicker de fecha de finalización
	        fech_fin_datePick_p5.getEditor().setOnKeyReleased(event -> {
	            if (fech_fin_datePick_p5.getEditor().getText().isEmpty()) {
	                pantallaSinSeleccion();
	            }
	        });
	}
	
	 @FXML
	    private void cargarMarcas() {
	        // Obtener todas las marcas de vehículos de la base de datos
	        List<String> marcas = pantalla2_modelo.obtenerMarcasVehiculos();
	        
	        marca_comBox_p5.getItems().clear(); 
	        // Configurar el ComboBox para mostrar las marcas obtenidas
	        marca_comBox_p5.getItems().addAll(marcas);
	        
	    }
	 @FXML
	    private void volver_pantGral_button_p5Action() {
	      
		 AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());
	    	cerrarPantallaActual();
		
	    }
	 
	 
	 @FXML
	    private void buscar_button_p5Action() {
	       //en función de la variable funcionBoton, solicitará un servicio u otro al modelo.
		 
		 conectorBoton();
	    }
	 
	 @FXML
	    private void cargarModelosPorMarca() {
	        // Implementar lógica para cargar los modelos de vehículos correspondientes a la marca seleccionada
	       
	    	String marcaSeleccionada = marca_comBox_p5.getValue();
	    	 if (marcaSeleccionada != null) {
	    	List<String> modelosPorMarca = pantalla2_modelo.obtenerModelosVehiculo(marcaSeleccionada);
	    	modelo_comBox_p5.getItems().clear(); // Limpiar ComboBox de modelos
	    	modelo_comBox_p5.getItems().addAll(modelosPorMarca); // Agregar modelos al ComboBox
	    	 }
	    	 modelo_comBox_p5.setEditable(true);
	    }
	 
	 private void pantallaSinSeleccion() {
		 
		 matricula_txtF_p5.setDisable(false);
         marca_comBox_p5.setDisable(false);
         modelo_comBox_p5.setDisable(true);
         motiv_repar_txtF_p5.setDisable(false);
         fech_inic_datePick_p5.setDisable(false);
         fech_fin_datePick_p5.setDisable(false);
         buscar_button_p5.setDisable(true);
         funcionBoton = 0;
		 
	 }
	 
	 private void conectorBoton() {
		 
		 List<String[]> reparaciones;
		 
		 switch(funcionBoton) {
		 
		 case 1:			 
			 // Obtener la matrícula ingresada
			    String matricula = matricula_txtF_p5.getText();

			    // Llamar al método del modelo para obtener las reparaciones por matrícula
			    reparaciones = pantalla5_modelo.obtenerReparacionesPorMatricula(matricula);
			    
			 // Mostrar las reparaciones en la tabla de Pantalla6_Controlador
	            mostrarEnPantalla6(reparaciones);
	            cerrarPantallaActual();
			 break;
			 
		 case 2:
			 
			 String modeloSeleccionado = modelo_comBox_p5.getValue();
			 ;
			 if(modeloSeleccionado == null || modeloSeleccionado.isEmpty()) {
				  reparaciones  = pantalla5_modelo.obtenerReparacionesPorMarca(marca_comBox_p5.getValue());
			 }else {
				 reparaciones  = pantalla5_modelo.obtenerReparacionesPorMarcaYModelo(marca_comBox_p5.getValue(), modelo_comBox_p5.getValue());
			 }	
			 	mostrarEnPantalla6(reparaciones);
	            cerrarPantallaActual();
			 break;	
			 
		 case 3:
			
			// Buscar por motivo de reparación
	            String motivo = motiv_repar_txtF_p5.getText();
	            reparaciones = pantalla5_modelo.obtenerReparacionesPorMotivo(motivo);
	            mostrarEnPantalla6(reparaciones);
	            cerrarPantallaActual();
			 break;
			 
		 case 4:
			
			 // Buscar por fechas entre fechaInicio y fechaFin
	            Date fechaInicio = Date.valueOf(fech_inic_datePick_p5.getValue());
	            Date fechaFin = Date.valueOf(fech_fin_datePick_p5.getValue());
	            System.out.println("Buscando entre fechas: " + fechaInicio + " y " + fechaFin);
	            reparaciones = pantalla5_modelo.obtenerReparacionesEntreFechas(fechaInicio, fechaFin);
	            mostrarEnPantalla6(reparaciones);
	            cerrarPantallaActual();
			 break;
	 
		 case 5:
			
			// Buscar desde fechaInicio hasta hoy o hasta una fecha específica
			 	Date fechaInicio5;
			 	Date fechaFin5;
             if (fech_inic_datePick_p5.getValue() != null) {
                  fechaInicio5 = Date.valueOf(fech_inic_datePick_p5.getValue());
                  fechaFin5 = Date.valueOf(LocalDate.now()); // Usa LocalDate.now() para obtener la fecha actual sin tiempo
                  System.out.println("Buscando desde fecha 5: " + fechaInicio5 + " hasta hoy: " + fechaFin5);
                 reparaciones = pantalla5_modelo.obtenerReparacionesEntreFechas(fechaInicio5, fechaFin5);
             } else if (fech_fin_datePick_p5.getValue() != null) {
                  fechaFin5 = Date.valueOf(fech_fin_datePick_p5.getValue());
                  System.out.println("Buscando hasta fecha: " + fechaFin5);
                 reparaciones = pantalla5_modelo.obtenerReparacionesHastaFecha(fechaFin5);
             } else {
                 reparaciones = new ArrayList<>();
             }
             mostrarEnPantalla6(reparaciones);
             cerrarPantallaActual();
			 break;
			 
		default:
			 System.out.println("Caso 6");
			break;
		 }
		 
	 }
	 
	 private void mostrarEnPantalla6(List<String[]> reparaciones) {
		    // Abrir la pantalla 6 y asociarle su controlador
		    Pantalla6_Controlador pantalla6Controlador = new Pantalla6_Controlador(reparaciones);
		    AperturaVentana.abrirVentanaObj(AperturaVentana.getRutaPantallaP6(), pantalla6Controlador);

		    // Pasar los datos a la pantalla 6
		    pantalla6Controlador.mostrarReparaciones(reparaciones);
		}
	 
	 private void cerrarPantallaActual() {
	        // Cerrar la ventana actual de la pantalla 1
	        Stage stage = (Stage) buscar_button_p5.getScene().getWindow();
	        stage.close();
	    }
}
