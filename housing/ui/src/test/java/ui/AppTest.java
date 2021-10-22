// package ui; 

// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.control.Button;
// import javafx.scene.control.ListView;
// import javafx.stage.Stage;

// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

// import org.testfx.api.FxToolkit;


// public class AppTest extends ApplicationTest {

    private DashboardController dashboardController; 
    private Parent root; 
    private String eMail = "email@email.com";
    private String password = "password";
    private String correctPasswordConfirm = "password";
    private String incorrertPasswordConfirm = "notEqual";
    
    private Button createUser;
    private Button logIn;
    private Label error;
    private TextField logInMail;
    private TextField passwordField;
    private TextField registerMail;
    private TextField createPassword;
    private TextField confirmPassword;
    
    

//     @Override 
//     public void start(Stage stage) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Dashboard.fxml"));
//         root = fxmlLoader.load();
//         dashboardController = fxmlLoader.getController();
//         stage.setScene(new Scene (root));
//         stage.show();

        createUser = (Button) getRootNode().lookup("#createUser");
        logIn = (Button) getRootNode().lookup("#logIn");
        error = (Label) getRootNode().lookup("#error");
        logInMail = (TextField) getRootNode().lookup("#logInEmail");
        passwordField = (TextField) getRootNode().lookup("#passwordLogIn");
        registerMail = (TextField) getRootNode().lookup("#registerEmail");
        createPassword = (TextField) getRootNode().lookup("#createPassword");
        confirmPassword = (TextField) getRootNode().lookup("#confirmPassword");

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
        Assertions.assertEquals(error.getText(), "no user found with that combination");

        logInMail.clear();
        passwordField.clear();

        //log in with correct password 
        helpLogIn(password); 
        Assertions.assertEquals(eMail, logInMail.getText());
        Assertions.assertEquals(password, passwordField.getText());

        //Assertions.assertNotNull(dashboardController.getMainController());

//         final Button logIn = (Button) getRootNode().lookup("#logIn");
//         clickOn(logIn);
//         Assertions.assertEquals(Email, getRootNode().lookup("#logInEmail").getText());
//         Assertions.assertEquals(Password, getRootNode().lookup("#passwordLogIn").getText());

    //checks that mainController is empty befor the login button is pressed. 
    //checks that the same main is sent from dashboardController to mainController. 
    /**
    public void testSendMain(){
        Assertions.assertNull(dashboardController.getMainController()); 
        helpLogIn(password);
        Assertions.assertEquals(dashboardController.getMainController().getMain(), dashboardController.getMain());
    }**/

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

        confirmPassword.clear();

        clickOn(confirmPassword).write(correctPasswordConfirm);
        clickOn(createUser); 

        Assertions.assertEquals(eMail, registerMail.getText()); 
        Assertions.assertEquals(password, createPassword.getText()); 
        Assertions.assertEquals(password, confirmPassword.getText()); 

        //Assertions.assertNotNull(dashboardController.getMainController());

    }


// }