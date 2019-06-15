package pl.lukbed.opticalanalyser.mvc.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import pl.lukbed.opticalanalyser.mvc.model.project.LightFunction;

public class PanelPaneController {
    @FXML
    private TextField projectNameTextField;

    @FXML
    private Button newProjectButton;

    @FXML
    private Button editProjectButton;

    @FXML
    private Button applyProjectButton;

    @FXML
    private ListView<LightFunction> lightFunctionsListView;

    @FXML
    private Button newLightFunction;

    @FXML
    private Button deleteLightFunction;

    public void initialize() {

        //editable project name
        editProjectButton.addEventFilter(ActionEvent.ACTION, actionEvent -> projectNameTextField.setEditable(true));

    }

    public TextField getProjectNameTextField() {
        return projectNameTextField;
    }

    public Button getNewProjectButton() {
        return newProjectButton;
    }

    public Button getEditProjectButton() {
        return editProjectButton;
    }

    public Button getApplyProjectButton() {
        return applyProjectButton;
    }

    public ListView<LightFunction> getLightFunctionsListView() {
        return lightFunctionsListView;
    }

    public Button getNewLightFunction() {
        return newLightFunction;
    }

    public Button getDeleteLightFunction() {
        return deleteLightFunction;
    }


}