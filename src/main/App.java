package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
			//Carga de la vista desde el archivo FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/inicio.fxml")); 
			VBox root = loader.load();
			
			//Crear la escena con la vista cargada
			Scene scene = new Scene(root);
			
			//Escena en el escenario ppal.
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
