import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Menu extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		
		Stage window = primaryStage;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/GUI Stuff.fxml"));
		Parent content = loader.load();
		window.setScene(new Scene(content));
		window.show();
		
	}
    
}