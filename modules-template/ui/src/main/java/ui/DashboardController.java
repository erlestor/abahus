package ui;

import core.Main;// husk å importere logikk

import java.io.IOException;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DashboardController {
	
	@FXML private TextField registerEmail, createPassword, confirmPassword, logInEmail, passwordLogIn;
	private Main main;

    @FXML
    public void handleCreateUser(ActionEvent event) throws IOException {
    	String eMail = registerEmail.getText();
    	String create = createPassword.getText();
    	String confirm = confirmPassword.getText();
    	
    	this.main = new Main(eMail, create, confirm);
    	
    	this.newStage();
    	
    	//Dette må gi melding om at noe er feil hvis det ikke burde funke
    	
    }
    
    @FXML
    public void handleLogIn(ActionEvent event) throws IOException {
    	String eMail = logInEmail.getText();
    	String password = passwordLogIn.getText();
    	
    	this.main = new Main(eMail, password);
    	
    	this.newStage();
    	
    	//Dette må gi melding om at noe er feil hvis det ikke burde funke
    	
    }
    
    private void newStage() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
    	Parent root = (Parent)loader.load();
    	
    	MainController mainController = loader.getController();
    	mainController.sendMain(this.main);
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();
    }
}
