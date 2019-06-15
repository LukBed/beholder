package pl.lukbed.opticalanalyser.mvc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import pl.lukbed.opticalanalyser.mvc.model.project.Measurement;
import pl.lukbed.opticalanalyser.mvc.model.regulations.Regulation;


public class MeasurementPaneController {

    @FXML
    private Label lightFunctionLabel;

    @FXML
    private ComboBox<Measurement> measurementComboBox;

    @FXML
    private ComboBox<Regulation> regulationComboBox;

    @FXML
    private Button refreshButton;

    @FXML
    private Button okButton;

    @FXML
    private Button newRegulationButton;

    @FXML
    private Button deleteRegulationButton;

    @FXML
    private Button newMeasurementButton;

    @FXML
    private Button deleteMeasurementButton;

    @FXML
    private WebView webView;

    public Label getLightFunctionLabel() {
        return lightFunctionLabel;
    }

    public ComboBox<Measurement> getMeasurementComboBox() {
        return measurementComboBox;
    }

    public ComboBox<Regulation> getRegulationComboBox() {
        return regulationComboBox;
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    public Button getOkButton() {
        return okButton;
    }

    public Button getNewRegulationButton() {
        return newRegulationButton;
    }

    public Button getDeleteRegulationButton() {
        return deleteRegulationButton;
    }

    public Button getNewMeasurementButton() {
        return newMeasurementButton;
    }

    public Button getDeleteMeasurementButton() {
        return deleteMeasurementButton;
    }

    public WebView getWebView() {
        return webView;
    }
}