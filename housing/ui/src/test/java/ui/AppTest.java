package ui; 

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import org.testfx.api.FxToolkit;


public class AppTest extends ApplicationTest {

    private DashboardController dashboardController; 
    private Parent root; 
    private String eMail = "email@email.com";
    private String password = "password";
    private String correctPasswordConfirm = "password";
    private String incorrertPasswordConfirm = "notEqual";
    
    private final Button createUser = (Button) getRootNode().lookup("#createUser");
    private final Button logIn = (Button) getRootNode().lookup("#logIn");
    private final Label error = (Label) getRootNode().lookup("#error");
    private final TextField logInMail = (TextField) getRootNode().lookup("#logInEmail");
    private final TextField passwordField = (TextField) getRootNode().lookup("#passwordLogIn");
    private final TextField registerMail = (TextField) getRootNode().lookup("#registerEmail");
    private final TextField createPassword = (TextField) getRootNode().lookup("#createPassword");
    private final TextField confirmPassword = (TextField) getRootNode().lookup("#confirmPassword");
    
    

    @Override 
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Dashboard.fxml"));
        root = fxmlLoader.load();
        dashboardController = fxmlLoader.getController();
        stage.setScene(new Scene (root));
        stage.show();

    }

    private Parent getRootNode(){
        return root; 
    }
    
    private void helpLogIn(String passwordCheck) {
        clickOn(logInMail).write(eMail);
        clickOn(passwordField).write(passwordCheck);
        clickOn(logIn);
    }

    //checks that the email and password are the same as those written in the textfields. 
    @Test 
    public void testHandleLogIn() {
        //log in with wrong password 
        helpLogIn("456");
        Assertions.assertEquals(error.getText(), "password must match confirmation");

        //log in with correct password 
        helpLogIn(password); 
        Assertions.assertEquals(eMail, logInMail.getText()); // getRootNode().lookup("#logInEmail").MANGLER);
        Assertions.assertEquals(password, passwordField.getText()); //getRootNode().lookup("#passwordLogIn").MANGLER);

        Assertions.assertNotNull(dashboardController.getMainController());

    }

    //checks that mainController is empty befor the login button is pressed. 
    //checks that the same main is sent from dashboardController to mainController. 
    @Test
    public void testSendMain(){
        Assertions.assertNull(dashboardController.getMainController()); 
        helpLogIn(password);
        Assertions.assertEquals(dashboardController.getMain(), dashboardController.getMainController().getMain());
    }

    private void helpRegisterUser(String confirmationPassword){
        
        clickOn(registerMail).write(eMail);

        
        clickOn(createPassword).write(password);

        
        clickOn(confirmPassword).write(confirmationPassword);

        
        clickOn(createUser);

    }

    //checks that the createUser method works and gives error message.
    @Test
    public void testHandleCreateUser(){
        helpRegisterUser(incorrertPasswordConfirm);
        Assertions.assertEquals(error.getText(), "password must match confirmation");

        clickOn(confirmPassword).write(correctPasswordConfirm);
        clickOn(createUser); 

        Assertions.assertEquals(eMail, registerMail.getText()); 
        Assertions.assertEquals(password, createPassword.getText()); 
        Assertions.assertEquals(password, confirmPassword.getText()); 

        Assertions.assertNotNull(dashboardController.getMainController());

    }

}