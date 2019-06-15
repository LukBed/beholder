package pl.lukbed.opticalanalyser.mvc.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.lukbed.opticalanalyser.mvc.controllers.ctrl.*;
import pl.lukbed.opticalanalyser.mvc.model.files.IesFile;
import pl.lukbed.opticalanalyser.mvc.model.functions.OpticalFileReader;
import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Grid;
import pl.lukbed.opticalanalyser.mvc.model.project.GridMeasurement;
import pl.lukbed.opticalanalyser.mvc.model.project.LightFunction;
import pl.lukbed.opticalanalyser.mvc.model.project.Measurement;
import pl.lukbed.opticalanalyser.mvc.model.project.Project;
import pl.lukbed.opticalanalyser.mvc.model.ThisApplication;
import pl.lukbed.opticalanalyser.mvc.model.regulations.Regulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class MainPaneController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private MenuPaneController menuPaneController;

    @FXML
    private ToolbarPaneController toolbarPaneController;

    @FXML
    private PanelPaneController panelPaneController;

    @FXML
    private MeasurementPaneController measurementPaneController;

    @FXML
    private FooterPaneController footerPaneController;

    public void initialize() {

        //configuration of MainCtrl
        MainCtrl.set(mainPane, menuPaneController, toolbarPaneController, panelPaneController, measurementPaneController, footerPaneController);

        RefreshCtrl.refreshProjectName();
        RefreshCtrl.refreshLightFunctionsList();
        CommunicationCtrl.print("Welcome to Optical Analyser - application for automotive optical engineers");

        //creating of temporary html file with photometrical raport
        File file = new File(MainCtrl.getRaportUrl());
        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                fileExists = file.createNewFile();
                CommunicationCtrl.print("Raport file created");
            } catch (IOException e) {
                CommunicationCtrl.print("Raport file not created");
            }
        }
        try (
                var fileWriter = new FileWriter(MainCtrl.getRaportUrl());
                var writer = new BufferedWriter(fileWriter);
        ) {
            writer.write("");
        } catch (IOException e) {
            CommunicationCtrl.print("Can not save raport file");
        }
        WebEngine engine = measurementPaneController.getWebView().getEngine();
        engine.load(file.getAbsolutePath());
        String url = "file:///" + file.getAbsolutePath();
        engine.load(url);

        //apply of project name
        panelPaneController.getApplyProjectButton().addEventFilter(ActionEvent.ACTION, actionEvent -> {
            ProjectCtrl.setProjectName(panelPaneController.getProjectNameTextField().getText());
            panelPaneController.getProjectNameTextField().setEditable(false);
        });

        //new project
        panelPaneController.getNewProjectButton().addEventFilter(ActionEvent.ACTION, actionEvent -> ProjectCtrl.newProject());
        menuPaneController.getNewProjectMenuItem().addEventHandler(ActionEvent.ACTION, actionEvent -> ProjectCtrl.newProject());
        toolbarPaneController.getNewProjectTool().addEventHandler(ActionEvent.ACTION, actionEvent -> ProjectCtrl.newProject());

        //rename project
        menuPaneController.getRenameProjectMenuItem().addEventHandler(ActionEvent.ACTION, actionEvent -> ProjectCtrl.renameProject());

        //loading measurement file
//        panelPaneController.getNewMeasurementButton().addEventFilter(ActionEvent.ACTION, actionEvent -> loadMeasurementFile());

        //adding light function
        panelPaneController.getNewLightFunction().addEventHandler(ActionEvent.ACTION, actionEvent -> FunctionCtrl.addLightFunction());
        menuPaneController.getAddLightFunctionMenuItem().addEventHandler(ActionEvent.ACTION, actionEvent -> FunctionCtrl.addLightFunction());
        toolbarPaneController.getNewLightFunctionTool().addEventHandler(ActionEvent.ACTION, actionEvent -> FunctionCtrl.addLightFunction());

        //removing light function
        panelPaneController.getDeleteLightFunction().addEventHandler(ActionEvent.ACTION, actionEvent -> {
            LightFunction selectedFunction =  FunctionCtrl.getSelectedLightFunction();
            if (selectedFunction.getMeasurements().size()>0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Removing light function");
                alert.setHeaderText("Can not remove this light function");
                alert.setContentText("Light function musn't contain any measurements");
                alert.showAndWait();
                CommunicationCtrl.print("Can not remove \"" + selectedFunction.getName() + "\", light funciton mustn't contain any measurements");
            } else {
                ThisApplication.getProject().getLightFunctions().remove(selectedFunction);
                CommunicationCtrl.print("Light function \"" + selectedFunction.getName() + "\" deleted");
            }
            RefreshCtrl.refreshLightFunctionsList();
        });

        //information about light function
        measurementPaneController.getRefreshButton().addEventHandler(ActionEvent.ACTION, actionEvent -> RefreshCtrl.refreshLightFunctionPreview(FunctionCtrl.getSelectedLightFunction()));

        //new regulation
        measurementPaneController.getNewRegulationButton().addEventHandler(ActionEvent.ACTION, actionEvent -> RegulationCtrl.addRegulation());
        menuPaneController.getAddRegulationMenuItem().addEventHandler(ActionEvent.ACTION, actionEvent -> RegulationCtrl.addRegulation());
        toolbarPaneController.getNewRegulationTool().addEventHandler(ActionEvent.ACTION, actionEvent -> RegulationCtrl.addRegulation());

        //default regulations
        menuPaneController.getLoadDefaultRegulationsMenuItem().addEventHandler(ActionEvent.ACTION, actionEvent -> RegulationCtrl.loadDefaultRegulations());
        toolbarPaneController.getImportRegulationsTool().addEventHandler(ActionEvent.ACTION, actionEvent -> RegulationCtrl.loadDefaultRegulations());

        //removing regulation
        measurementPaneController.getDeleteRegulationButton().addEventHandler(ActionEvent.ACTION, actionEvent -> {
            ThisApplication.getProject().deleteRegulation(RegulationCtrl.getSelectedRegulation());
            CommunicationCtrl.print("Regulation \"" + RegulationCtrl.getSelectedRegulation().getName() + "\" deleted");
            RefreshCtrl.refreshRegulations();
        });

        //new measurement from IES file
        measurementPaneController.getNewMeasurementButton().addEventHandler(ActionEvent.ACTION, actionEvent -> MeasurementCtrl.loadMeasurementFile());
        menuPaneController.getAddMeasurementMenuItem().addEventHandler(ActionEvent.ACTION, actionEvent -> MeasurementCtrl.loadMeasurementFile());
        toolbarPaneController.getNewMeasurementTool().addEventHandler(ActionEvent.ACTION, actionEvent -> MeasurementCtrl.loadMeasurementFile());

        //removing measurement
        measurementPaneController.getDeleteMeasurementButton().addEventHandler(ActionEvent.ACTION, actionEvent -> {
            Measurement measurement = MeasurementCtrl.getSelectedMeasurement();
            FunctionCtrl.getSelectedLightFunction().deleteMeasurement(MeasurementCtrl.getSelectedMeasurement());
            CommunicationCtrl.print("Measurement \"" + measurement.getName() + "\" deleted");
            RefreshCtrl.refreshMeasurements();
        });

        //creating raport
        measurementPaneController.getOkButton().addEventHandler(ActionEvent.ACTION, actionEvent -> {
            MeasurementCtrl.createGridRaport(RegulationCtrl.getSelectedRegulation(), MeasurementCtrl.getSelectedMeasurement().getGrid());
            RefreshCtrl.refreshRaport();
        });
    }





}