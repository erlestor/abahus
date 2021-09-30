package ui;

import core.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

	@FXML public Label email, listHouse;
	private Main main;
	
	public void sendMain(Main main) {
		this.main = main;
	}
	

}
