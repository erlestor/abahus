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

    final Label error = (Label) getRootNode().lookup("#error");
    

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
    
    // @BeforeEach 
    // public void setStrings(){
    //     final Label error = (Label) getRootNode().lookup("#error");
    // }

    private void helpLogIn(String passwordCheck) {
        final TextField mail = (TextField) getRootNode().lookup("#logInEmail");
        clickOn(mail).write(eMail);

        final TextField passwordField = (TextField) getRootNode().lookup("#passwordLogIn");
        clickOn(passwordField).write(passwordCheck);

        final Button logIn = (Button) getRootNode().lookup("#logIn");
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
        Assertions.assertEquals(eMail, getRootNode().lookup("#logInEmail"));
        Assertions.assertEquals(password, getRootNode().lookup("#passwordLogIn"));

        Assertions.assertNotNull(dashboardController.getMainController());

    }

    //checks that mainController is empty befor the login button is pressed. 
    //checks that the same main is sent from dashboardController to mainController. 
    @Test
    public void testSendMain(){
        Assertions.assertNull(dashboardController.getMainController()); 
        helpLogIn();
        Assertions.assertEquals(dashboardController.getMain(), dashboardController.getMainController().getMain());
    }

    private void helpRegisterUser(String confirmationPassword){
        final TextField mail = (TextField) getRootNode().lookup("#registerEmail");
        clickOn(mail).write(eMail);

        final TextField createPassword = (TextField) getRootNode().lookup("#createPassword");
        clickOn(createPassword).write(password);

        final TextField confirmPassword = (TextField) getRootNode().lookup("#confirmPassword");
        clickOn(confirmPassword).write(confirmationPassword);

        final Button createUser = (Button) getRootNode().lookup("#createUser");
        clickOn(createUser);

    }

    //checks that the createUser method works and gives error message.
    @Test
    public void testHandleCreateUser(){
        helpRegisterUser(incorrertPasswordConfirm);
        Assertions.assertEquals(error.getText(), "password must match confirmation");

        clickOn(confirmPassword).write(correctPasswordConfirm);
        clickOn(createUser); 

        Assertions.assertEquals(eMail, getRootNode().lookup("#registerEmail").getText());
        Assertions.assertEquals(password, getRootNode().lookup("#createPassword").getText());
        Assertions.assertEquals(password, getRootNode().lookup("#confirmPassword").getText());

        Assertions.assertNotNull(dashboardController.getMainController());

    }

}