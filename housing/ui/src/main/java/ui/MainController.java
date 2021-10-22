package ui;

import jsonworker.*;
import core.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.List;

public class MainController {

	@FXML public Label email, listHouse;
	@FXML public Pane myHousesList;
	@FXML public TextField newHouseText;
	private Main main;

	@FXML
    public void showAvailableHousing() {
		listHouse.setText(main.getHousesAsString(main.getAvailableHousing()));
    }

	@FXML
    public void showMyHouses() {
		if (main != null) {
			List<House> housing = main.getMyHouses();
			myHousesList.getChildren().clear();
			for (int i = 0; i < housing.size(); i++) {
				House house = housing.get(i);
				HBox housePane = new HBox(5);
				housePane.setTranslateY(myHousesList.getTranslateY() + 60*i);
				myHousesList.getChildren().add(housePane);
				
				Label houseLabel = new Label("Location: " + house.getLocation() + "\n" + "Owner: " + house.getUser().getEmail() + "\n");
				housePane.getChildren().add(houseLabel);

				Button deleteButton = new Button("delete");
				deleteButton.setOnMouseClicked(c-> handleRemoveHouse(house));
				housePane.getChildren().add(deleteButton);
			}
		}
    }

	@FXML
	public void handleAddHouse() {
		try {
			main.hostNewHouse(newHouseText.getText());
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		newHouseText.setText("");
		showMyHouses();

	}

	@FXML
	public void handleRemoveHouse(House house) {
		try {
			main.removeHouse(house);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showMyHouses();
	}

	public void handleLogOut() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Dashboard.fxml"));
        Parent parent = fxmlLoader.load();
		Stage stage = new Stage();
        stage.setTitle("Housing");
        stage.setScene(new Scene(parent));
        stage.show();

		Stage currentStage = (Stage) email.getScene().getWindow();
		currentStage.close();
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

	public Main getMain(){
		return this.main; 
	}
	

}
