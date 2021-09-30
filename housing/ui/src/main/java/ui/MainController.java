package ui;

import core.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

	@FXML public Label email, listHouse;
	private Main main;
	
	public void sendMain(Main main) {
		this.main = main;
	}
	

}
