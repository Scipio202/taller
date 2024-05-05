package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.AccesoUsuario;
import javafx.scene.Node;
import javafx.stage.Window;


public class PantallaInicioControlador {
	@FXML
	private TextField usuario_TxtField_pInicio;
	
	@FXML
	private TextField clave_TxtField_pInicio;
	
	@FXML
	private Button aceptar_button_pInicio;
	
	@FXML
	private Label errorLabel_pInicio;
	
	@FXML
	private Button olvido_button_pInicio;
	
	@FXML
	private Label llama_instalador_Label_pInicio;
	
	// Aquí puedo inyectar dependencias necesarias, como servicios para interactuar con la base de datos
	private AccesoUsuario accesoUsuario = new AccesoUsuario();
	
	public PantallaInicioControlador() {
		
	}

    public PantallaInicioControlador(AccesoUsuario accesoUsuario) {
        this.accesoUsuario = accesoUsuario;
    }
	
   // Pantalla1C_Controlador p1C_control = new Pantalla1C_Controlador();
    
	@FXML
	private void initialize() {
		
		// Aquí puedo realizar inicializaciones adicionales, si es necesario
		
		errorLabel_pInicio.setVisible(false);
		if(Pantalla1C_Controlador.hacerVisible() == true) {
			llama_instalador_Label_pInicio.setVisible(true);
		}else {
			llama_instalador_Label_pInicio.setVisible(false);
		}
		
	}
	
	@FXML
    private void handleaceptar_button_pInicioAction() {
        String usuario = usuario_TxtField_pInicio.getText();
        String clave = clave_TxtField_pInicio.getText();
        Pantalla1AControlador pantalla1AControlador = new Pantalla1AControlador();
        Pantalla1Controlador pantalla1Controlador = new Pantalla1Controlador();

        // Aquí tengo que agregar la lógica para comprobar si es el primer acceso o no,
        // y realizar las acciones correspondientes
        
        boolean esPrimeraVez = verificarSiEsPrimeraVez();
        
        if (esPrimeraVez) {
            // Realizar acciones para el primer acceso
            // Por ejemplo, abrir la Pantalla 1A, pero previamente hay que cerrar Inicio
        	//pantalla1AControlador.abrirPantalla1A();
        	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantalla1A());
        	cerrarPantallaInicio();
        	
        } else {
            // Realizar acciones para los accesos posteriores
            boolean credencialesValidas = accesoUsuario.verificarUsuarioClave(usuario, clave);
            
            if (credencialesValidas) {
                // Las credenciales son válidas, proceder a la siguiente pantalla
                // Por ejemplo, abrir la ventana principal de la aplicación
            	//pantalla1Controlador.abrirPantalla1();
            	AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1());
            	cerrarPantallaInicio();
            } else {
                // Las credenciales son inválidas, mostrar un mensaje de error
               
                errorLabel_pInicio.setVisible(true);
            }
        }
        
    }
	
	private boolean verificarSiEsPrimeraVez() {
        // Aquí implemento la lógica para verificar si es la primera vez que se accede
        // verifico si la base de datos está vacía
        // Retorna true si es la primera vez, false si no lo es
		return accesoUsuario.verificarSiEsPrimeraVez();
		
    }
		
		
	@FXML
    private void handleolvido_button_pInicioAction() {
        //  agregar la lógica para manejar el evento de "He olvidado la contraseña"
		AperturaVentana.abrirVentana(AperturaVentana.getRutaPantallaP1c());
    	cerrarPantallaInicio();
    }
	
	 private Stage obtenerStage() {
	        // Obtiene el Node raíz del Scene actual
	        Window nodo = usuario_TxtField_pInicio.getScene().getWindow();

	        // Verifica si el Node raíz es un Stage
	        if (nodo instanceof Stage) {
	            return (Stage) nodo; // Devuelve el Stage si es un Stage
	        } else {
	            return null; // Devuelve null si no es un Stage
	        }
	    }
	 
	 private void cerrarPantallaInicio() {
	        Stage stage = obtenerStage();
	        if (stage != null) {
	            stage.close(); // Cierra la ventana de la pantalla de inicio
	        }
	    }
	 
	 public void mostrarMensajeInstalador() {
		    llama_instalador_Label_pInicio.setVisible(true);
		    System.out.println("prueba de entrada");
		}

	
	
	
}
