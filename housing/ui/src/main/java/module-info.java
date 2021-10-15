module ui {
    requires core;
    requires jsonworker;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
    requires com.fasterxml.jackson.databind;


    opens ui to javafx.graphics, javafx.fxml;

    exports ui;
}
