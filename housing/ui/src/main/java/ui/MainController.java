package ui;

import core.House;
import core.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.List;

public class MainController {

	@FXML public Label email, listHouse;
	@FXML public Pane myHousesList;
	private Main main;

	@FXML
    public void showAvailableHousing() {
		listHouse.setText(main.getHousesAsString(main.getAvailableHousing()));
    }

	@FXML
    public void showMyHouses() {
		System.out.println("initializing MainController");
		if (main != null) {
			List<House> housing = main.getAvailableHousing();
			for (int i = 0; i < housing.size(); i++) {
				Label l = new Label();
				l.setTranslateY(myHousesList.getTranslateY() + 30*i);
				l.setText(housing.get(i).getLocation());
				myHousesList.getChildren().add(l);
			}
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
