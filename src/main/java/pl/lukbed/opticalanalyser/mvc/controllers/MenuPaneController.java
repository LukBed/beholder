package pl.lukbed.opticalanalyser.mvc.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

import java.util.Optional;

public class MenuPaneController {

    //-------------------------------------------------------------FILE---------------------------------------

    @FXML
    private MenuItem newProjectMenuItem;

    @FXML
    private MenuItem openProjectMenuItem;

    @FXML
    private MenuItem saveProjectMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    public MenuItem getNewProjectMenuItem() {
        return newProjectMenuItem;
    }

    public MenuItem getOpenProjectMenuItem() {
        return openProjectMenuItem;
    }

    public MenuItem getSaveProjectMenuItem() {
        return saveProjectMenuItem;
    }

    //-------------------------------------------------------------PROJECT---------------------------------------

    @FXML
    private MenuItem renameProjectMenuItem;

    @FXML
    private MenuItem addLightFunctionMenuItem;

    @FXML
    private MenuItem addMeasurementMenuItem;

    @FXML
    private MenuItem addRegulationMenuItem;

    @FXML
    private MenuItem loadDefaultRegulationsMenuItem;

    public MenuItem getRenameProjectMenuItem() {
        return renameProjectMenuItem;
    }

    public MenuItem getAddLightFunctionMenuItem() {
        return addLightFunctionMenuItem;
    }

    public MenuItem getAddMeasurementMenuItem() {
        return addMeasurementMenuItem;
    }

    public MenuItem getAddRegulationMenuItem() {
        return addRegulationMenuItem;
    }

    public MenuItem getLoadDefaultRegulationsMenuItem() {
        return loadDefaultRegulationsMenuItem;
    }

    //-------------------------------------------------------------HELP---------------------------------------
    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem webpageMenuItem;

    @FXML
    private MenuItem contactMenuItem;

    public void initialize() {

        //-------------------------------------------------------------FILE---------------------------------------

        //close application
        closeMenuItem.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Close");
            alert.setHeaderText("Closing application");
            alert.setContentText("Are you sure you want to close this application? Unsaved changes will be lost.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        });

        //-------------------------------------------------------------HELP---------------------------------------
        helpMenuItem.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("Help section will be updated");
            alert.showAndWait();
        });

        aboutMenuItem.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.setContentText("About section will be updated\n\n" +
                    "Used Feather icons made by @colebemis - MIT License");
            alert.showAndWait();
        });

        webpageMenuItem.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("Webpage section will be updated");
            alert.showAndWait();
        });

        contactMenuItem.addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Help");
            alert.setHeaderText(null);
            alert.setContentText("Contact section will be updated");
            alert.showAndWait();
        });

    }



}