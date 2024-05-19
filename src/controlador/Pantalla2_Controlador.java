package controlador;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modelo.Pantalla2_modelo;

public class Pantalla2_Controlador {

	 @FXML
	    private TextField matr_txtField_p2;

	    @FXML
	    private TextField color_txtField_p2;

	    @FXML
	    private ComboBox<String> selcc_marca_comboBox_p2;

	    @FXML
	    private ComboBox<String> selcc_modelo_comboBox_p2;

	    @FXML
	    private RadioButton gasol_radButton_p2;

	    @FXML
	    private RadioButton diesel_radButton_p2;

	    @FXML
	    private RadioButton hibr_radButton_p2;

	    @FXML
	    private RadioButton elect_radButton_p2;

	    @FXML
	    private RadioButton otroCombust_radButton_p2;

	    @FXML
	    private TextField nombre_txtField_p2;

	    @FXML
	    private TextField dni_txtField_p2;

	    @FXML
	    private TextField apell1_txtField_p2;

	    @FXML
	    private TextField telef_txtField_p2;

	    @FXML
	    private TextField apell2_txtField_p2;

	    @FXML
	    private TextField correoE_txtField_p2;

	    @FXML
	    private Button guardDatos_button_p2;

	    @FXML
	    private Button volvSinGuard_Button_p2;
	    
	    //botones de pc3
	    
	    @FXML
	    private Button aceptar_button_c3;

	    @FXML
	    private Button cancelar_button_c3;

	    private Pantalla2_modelo pantalla2_modelo = new Pantalla2_modelo();
	   
	   

	    @FXML
	    private void initialize() {
	    	selcc_marca_comboBox_p2.setEditable(true);    	 
	    	 cargarMarcas();
	    	 
	    	 // Configurar el evento de cambio en el ComboBox de marcas
	    	 selcc_marca_comboBox_p2.setOnAction(event -> cargarModelosPorMarca());

	         // Configurar RadioButtons de tipo de combustible
	         ToggleGroup tipoCombustibleGroup = new ToggleGroup();
	         gasol_radButton_p2.setToggleGroup(tipoCombustibleGroup);
	         diesel_radButton_p2.setToggleGroup(tipoCombustibleGroup);
	         hibr_radButton_p2.setToggleGroup(tipoCombustibleGroup);
	         elect_radButton_p2.setToggleGroup(tipoCombustibleGroup);
	         otroCombust_radButton_p2.setToggleGroup(tipoCombustibleGroup);

	         // Opcional: Seleccionar un tipo de combustible por defecto
	        // gasol_radButton_p2.setSelected(true);
	    	
	    }

	   
	    
	    @FXML
	    public void handleGuardarDatosButtonAction() {
	    	
	    	if (camposVehiculoCompletos()) {
	            guardarDatos();
	            abrirPantallaP1();
	            pantalla2_modelo.cerrarConexion();
	            cerrarVentana();
	        }	    	
	        
	      }
	    
	    	    
	   
	    
	    
	    private boolean camposVehiculoCompletos() {
	        // Verificar si todos los campos obligatorios del vehículo están llenos + DNI
	        return !matr_txtField_p2.getText().isEmpty() && 
	        		!color_txtField_p2.getText().isEmpty() && 
	        		selcc_marca_comboBox_p2.getValue() != null && 
	        		!selcc_marca_comboBox_p2.getValue().isEmpty() &&
	        		selcc_modelo_comboBox_p2.getValue() != null &&
	        		!selcc_modelo_comboBox_p2.getValue().isEmpty() &&
	        		obtenerTipoCombustibleSeleccionado() != null &&
	        		!dni_txtField_p2.getText().isEmpty();
	    }
	    
	    private void guardarDatos() {
	       
	    	String nombre = nombre_txtField_p2.getText();
	        String dni = dni_txtField_p2.getText().toLowerCase();
	        String apellido1 = apell1_txtField_p2.getText();
	        String telefono = telef_txtField_p2.getText();
	        String apellido2 = apell2_txtField_p2.getText();
	        String correoElectronico = correoE_txtField_p2.getText();
	        pantalla2_modelo.guardarDatosPropietario(nombre, dni, apellido1, telefono, apellido2, correoElectronico);
	      
	        
	        int id_propietario = pantalla2_modelo.obtenerIdPropietario(dni);
	        
	    	
	    	// Obtener los valores de los campos del vehículo
	        String matricula = matr_txtField_p2.getText().toLowerCase();
	        String color = color_txtField_p2.getText().toLowerCase();
	        String marca = selcc_marca_comboBox_p2.getValue().toLowerCase();
	        String modelo = selcc_modelo_comboBox_p2.getValue().toLowerCase();
	        String combustible = obtenerTipoCombustibleSeleccionado(); 
	        
	        // Guardar los datos en la base de datos utilizando el modelo
	        pantalla2_modelo.guardarDatosVehiculo(matricula, color, marca, modelo, combustible, id_propietario);	           
	       
	        int id_vehiculo = pantalla2_modelo.obtenerIdVehiculo(matricula);
	        
	        pantalla2_modelo.guardarDatosReparacion(id_vehiculo);
	    }

	    @FXML
	    public void handleVolverSinGuardarButtonAction() {
	    	
	    	abrirPantallaP1();	        
	    	cerrarVentana();
	    }

	    private String obtenerTipoCombustibleSeleccionado() {
	    	if (gasol_radButton_p2.isSelected()) {
	            return "Gasolina";
	        } else if (diesel_radButton_p2.isSelected()) {
	            return "Diésel";
	        } else if (hibr_radButton_p2.isSelected()) {
	            return "Híbrido";
	        } else if (elect_radButton_p2.isSelected()) {
	            return "Eléctrico";
	        } else if (otroCombust_radButton_p2.isSelected()) {
	            return "Otro";
	        } else {
	            return null; // Ninguno seleccionado
	        }
	    }
	    @FXML
	    private void cargarMarcas() {
	        // Obtener todas las marcas de vehículos de la base de datos
	        List<String> marcas = pantalla2_modelo.obtenerMarcasVehiculos();
	        
	        // Configurar el ComboBox para mostrar las marcas obtenidas y permitir edición
	        selcc_marca_comboBox_p2.getItems().addAll(marcas);
	        //selcc_marca_comboBox_p2.setEditable(true);
	    }
	    
	   /* @FXML
	    private List<String> obtenerMarcasVehiculos() {
	        return pantalla2_modelo.obtenerMarcasVehiculos();
	    }*/
	    
	    @FXML
	    private void cargarModelosPorMarca() {
	        // Implementar lógica para cargar los modelos de vehículos correspondientes a la marca seleccionada
	       
	    	String marcaSeleccionada = selcc_marca_comboBox_p2.getValue();
	    	 if (marcaSeleccionada != null) {
	    	List<String> modelosPorMarca = pantalla2_modelo.obtenerModelosVehiculo(marcaSeleccionada);
	        selcc_modelo_comboBox_p2.getItems().clear(); // Limpiar ComboBox de modelos
	        selcc_modelo_comboBox_p2.getItems().addAll(modelosPorMarca); // Agregar modelos al ComboBox
	    	 }
	    	 selcc_modelo_comboBox_p2.setEditable(true);
	    }
	    
	    private void abrirPantallaP1() {
	        AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());
	    }
	    
	    private void cerrarVentana() {
	        Stage stage = (Stage) volvSinGuard_Button_p2.getScene().getWindow();
	        stage.close();
	    }

	    /////////////////////////////////////////////////////////////////////////////////////
	    
	  
}
