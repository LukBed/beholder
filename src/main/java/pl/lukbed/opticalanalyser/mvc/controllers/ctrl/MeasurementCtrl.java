package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.lukbed.opticalanalyser.mvc.model.files.IesFile;
import pl.lukbed.opticalanalyser.mvc.model.functions.OpticalFileReader;
import pl.lukbed.opticalanalyser.mvc.model.photometricGrid.Grid;
import pl.lukbed.opticalanalyser.mvc.model.project.GridMeasurement;
import pl.lukbed.opticalanalyser.mvc.model.project.Measurement;
import pl.lukbed.opticalanalyser.mvc.model.regulations.Regulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class MeasurementCtrl {

    public static void loadMeasurementFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("IES", "*.ies"));
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            IesFile iesFile = OpticalFileReader.readIes(file);
            Grid grid = iesFile.getGrid();
            FunctionCtrl.getSelectedLightFunction().addMeasurement(new GridMeasurement(file.getName(), grid));

            CommunicationCtrl.print("File " + file.getName() + " loaded");
        } catch (Exception e) {
            CommunicationCtrl.print("Can not load measurement from file " + file.getName());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("New measurement");
            alert.setHeaderText("Can not load measurement from file " + file.getName());
            alert.setContentText("Error during loading file");
            alert.showAndWait();
        }
        RefreshCtrl.refreshMeasurements();
    }

    public static Measurement getSelectedMeasurement() {
        return MainCtrl.getMeasurementPaneController().getMeasurementComboBox().getSelectionModel().getSelectedItem();
    }

    public static void createGridRaport(Regulation regulation, Grid grid) {
        String raport = regulation.checkGrid(grid);
        try (
                var fileWriter = new FileWriter(MainCtrl.getRaportUrl());
                var writer = new BufferedWriter(fileWriter);
        ) {
            writer.write(raport);
        } catch (IOException e) {
            CommunicationCtrl.print("Can not save raport file");
        }
    }

}
