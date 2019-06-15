package pl.lukbed.opticalanalyser.mvc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ToolbarPaneController {

    @FXML
    private Button newProjectTool;

    @FXML
    private Button openProjectTool;

    @FXML
    private Button saveProjectTool;

    @FXML
    private Button newLightFunctionTool;

    @FXML
    private Button newMeasurementTool;

    @FXML
    private Button newRegulationTool;

    @FXML
    private Button importRegulationsTool;

    @FXML
    private ImageView newProjectToolImg;

    @FXML
    private ImageView openProjectToolImg;

    @FXML
    private ImageView saveProjectToolImg;

    @FXML
    private ImageView newLightFunctionToolImg;

    @FXML
    private ImageView newMeasurementToolImg;

    @FXML
    private ImageView newRegulationToolImg;

    @FXML
    private ImageView importRegulationsToolImg;

    public Button getNewProjectTool() {
        return newProjectTool;
    }

    public Button getOpenProjectTool() {
        return openProjectTool;
    }

    public Button getSaveProjectTool() {
        return saveProjectTool;
    }

    public Button getNewLightFunctionTool() {
        return newLightFunctionTool;
    }

    public Button getNewMeasurementTool() {
        return newMeasurementTool;
    }

    public Button getNewRegulationTool() {
        return newRegulationTool;
    }

    public Button getImportRegulationsTool() {
        return importRegulationsTool;
    }
}
