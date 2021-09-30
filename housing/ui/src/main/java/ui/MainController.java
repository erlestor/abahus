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
	public void setEmail(String email) {
		this.email.setText(email);
	}

	public void sendListHouse(String listHouse) {
		this.listHouse.setText(listHouse);
	}
	

}
