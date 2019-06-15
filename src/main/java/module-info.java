module opticalanalyser {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    exports pl.lukbed.opticalanalyser to javafx.graphics;
    opens pl.lukbed.opticalanalyser.mvc.controllers to javafx.fxml;
    opens pl.lukbed.opticalanalyser.mvc.model to javafx.fxml;
}