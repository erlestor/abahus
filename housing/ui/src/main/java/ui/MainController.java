package ui;

import core.Main;//husk å importere logikk

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

public class MainController {

	@FXML public TextField email, listHouse;
	private Main main;
	
	public void sendMain(Main main) {
		this.main = main;
	}
	

}
