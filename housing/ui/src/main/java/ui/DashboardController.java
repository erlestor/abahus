package ui;

import core.*;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashboardController extends MainController{
	
	@FXML private TextField registerEmail, createPassword, confirmPassword, logInEmail, passwordLogIn;
	@FXML private Label error;
	private Main main;
	private MainController mainController;

    @FXML
    public void handleCreateUser(ActionEvent event) throws IOException {
    	String eMail = registerEmail.getText();
    	String create = createPassword.getText();
    	String confirm = confirmPassword.getText();
    	
		try {
			this.main = new Main(eMail, create, confirm);
    	
			this.newStage();
		}
    	catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			error.setText(e.getMessage());
		}
    	
    }
    
    @FXML
    public void handleLogIn(ActionEvent event) throws IOException {
    	String eMail = logInEmail.getText();
    	String password = passwordLogIn.getText();
    	
    	try {
			System.out.println("heisann");
			this.main = new Main(eMail, password);
			System.out.println("heisann");
			this.newStage();
		}
    	catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			error.setText(e.getMessage());
		}
    }
    
    private void newStage() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
    	Parent root = (Parent)loader.load();
    	
    	mainController = loader.getController();


		mainController.setEmail(main.getCurrentUser().getEmail()); //det må sannsynligvis gjøres noe her for å sammarbeide med FXML-fila
		//mainController.listHouse.setText(main.getAvailableHousing()); // det må lages en metode for å konvertere lista om til en string

    	mainController.sendMain(this.main);
    	Stage stage = new Stage();
		stage.setTitle("Housing");
    	stage.setScene(new Scene(root));
    	stage.show();
    }

	public MainController getMainController(){
		return mainController; 
	}

}
