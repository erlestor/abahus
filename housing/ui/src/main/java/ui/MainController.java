package ui;

import core.*;

import java.io.IOException;
import java.util.Collection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class MainController {

	@FXML public Label email, listHouse, myHousesList;
	private Main main;

	@FXML
    public void showAvailableHousing() {
		listHouse.setText(main.getHousesAsString(main.getAvailableHousing()));
    }

	@FXML
    public void showMyHouses() {
		System.out.println("initializing MainController");
		if (main != null) {
			myHousesList.setText(main.getHousesAsString(main.getMyHouses()));
		}
    }
	
	public void sendMain(Main main) {
		this.main = main;
		showAvailableHousing();
		showMyHouses();
	}
	public void setEmail(String email) {
		this.email.setText(email);
	}

	public void sendListHouse(String listHouse) {
		this.listHouse.setText(listHouse);
	}
	

}
