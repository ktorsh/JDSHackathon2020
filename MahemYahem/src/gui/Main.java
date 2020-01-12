package gui;


import javafx.stage.Stage;

public class Main {

	public static void main(String[] args){
		
		Menu thing = new Menu();
		Stage stage = new Stage();
		try {
			thing.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
