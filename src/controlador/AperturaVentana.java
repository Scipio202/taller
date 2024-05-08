package controlador;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AperturaVentana {
	 // rutas de las vistas como variables privadas
    private static final String RUTA_PANTALLA_P1A = "/vista/p1A_primer_ingr_nom_contr.fxml";
    private static final String RUTA_PANTALLA_C1 = "/vista/c1_Confirmación.fxml";
    private static final String RUTA_PANTALLA_C2 = "/vista/c2_finalizar_reparacion.fxml";
    private static final String RUTA_PANTALLA_C3 = "/vista/c3_Finalizar_rellenar_datos.fxml";
    private static final String RUTA_PANTALLA_C4 = "/vista/c4_Guardar_arch_txt.fxml";
    private static final String RUTA_PANTALLA_INICIO = "/vista/inicio.fxml";
    private static final String RUTA_PANTALLA_P1 = "/vista/p1_Compr_matric.fxml";
    private static final String RUTA_PANTALLA_P1B = "/vista/p1B_usua_contr_camb_contr.fxml";
    private static final String RUTA_PANTALLA_P1C = "/vista/p1C_Recuperacion_Contraseña.fxml";
    private static final String RUTA_PANTALLA_P1D = "/vista/p1D_Restabl_Usuar_Contr.fxml";
    private static final String RUTA_PANTALLA_P2 = "/vista/p2_Datos_Veh_Prop.fxml";
    private static final String RUTA_PANTALLA_P4 = "/vista/p4_Reparacion.fxml";
    private static final String RUTA_PANTALLA_P5 = "/vista/p5_Consultas.fxml";
    private static final String RUTA_PANTALLA_P6 = "/vista/p6_Visualización_de_Consultas.fxml";
    
    public static String getRutaPantalla1A() {
        return RUTA_PANTALLA_P1A;
    }

    public static String getRutaPantallaC1() {
		return RUTA_PANTALLA_C1;
	}

	public static String getRutaPantallaC2() {
		return RUTA_PANTALLA_C2;
	}

	public static String getRutaPantallaC3() {
		return RUTA_PANTALLA_C3;
	}

	public static String getRutaPantallaC4() {
		return RUTA_PANTALLA_C4;
	}

	public static String getRutaPantallaInicio() {
		return RUTA_PANTALLA_INICIO;
	}

	public static String getRutaPantallaP1() {
		return RUTA_PANTALLA_P1;
	}

	public static String getRutaPantallaP1b() {
		return RUTA_PANTALLA_P1B;
	}

	public static String getRutaPantallaP1c() {
		return RUTA_PANTALLA_P1C;
	}

	public static String getRutaPantallaP1d() {
		return RUTA_PANTALLA_P1D;
	}

	public static String getRutaPantallaP2() {
		return RUTA_PANTALLA_P2;
	}

	public static String getRutaPantallaP4() {
		return RUTA_PANTALLA_P4;
	}

	public static String getRutaPantallaP5() {
		return RUTA_PANTALLA_P5;
	}

	public static String getRutaPantallaP6() {
		return RUTA_PANTALLA_P6;
	}

	// Método para abrir una ventana usando la ruta especificada
    public static void abrirVentana(String rutaFXML) {
        try {
            // Carga el archivo FXML de la ruta especificada
            FXMLLoader loader = new FXMLLoader(AperturaVentana.class.getResource(rutaFXML));
            VBox root = loader.load();

            // Crea un nuevo Scene
            Scene scene = new Scene(root);

            // Crea un nuevo Stage y establece el Scene
            Stage stage = new Stage();
            stage.setScene(scene);

            // Muestra el Stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void abrirVentanaObj(String rutaFXML, Object controlador) {
        try {
            FXMLLoader loader = new FXMLLoader(AperturaVentana.class.getResource(rutaFXML));
            loader.setController(controlador);
            VBox root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Métodos getter para acceder a las rutas de las vistas
   

}
