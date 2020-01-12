package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
@SuppressWarnings("restriction")
public class Menu extends Application{

	public void start(Stage primaryStage) throws Exception {
		
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI Stuff.fxml"));
		Parent content = loader.load();
		window.setScene(new Scene(content));
		window.show();
		
	}
    
}