import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import web.Scrape;

public class GUIController {
	
	private Scrape scraper;
	
	private ArrayList<String[]> utilities;
	
	@FXML
	TextField zip;
	
	@FXML
	ChoiceBox<String> utility;
	
	@FXML
	Button zipButton;
	
	@FXML
	void getZip(ActionEvent event) throws IOException {
		System.out.println("dlhfbdsakl");
		scraper = new Scrape(zip.getText());
		utilities = scraper.getUtilities();
		for(String[] str : utilities) {
			utility.getItems().add(str[0]);
		}
	}
	
	@FXML
	void getUtility(ActionEvent event) throws IOException {
		String url = null;
		for(String[] arr : utilities) {
			if(arr[0].equals(utility.getValue())) {
				url = arr[3];
			}
		}
		ArrayList<String[]> contaminants = scraper.getContaminants(url);
		System.out.println(contaminants);
	}

}
