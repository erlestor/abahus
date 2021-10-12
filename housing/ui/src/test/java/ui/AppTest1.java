package ui; 

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;


public class AppTest1 extends ApplicationTest {

    private DashboardController dashboardController; 
    private Parent root; 

    @Override 
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Dashboard.fxml"));
        root = fxmlLoader.load();
        dashboardController = fxmlLoader.getController();
        stage.setScene(new Scene (root));
        stage.show();

    }

    public Parent getRootNode(){
        return root; 

    }

    @Test 
    public void testHandleLogIn() {
        Label eMail = (Label) GuiTest.find("#logInEmail");
        
        eMail.write("hei@gmail.com");

        Label password = (Label) GuiTest.find("#passwordLogIn");
        password.write("password");

        //clickOn("#logIn");

    }


}