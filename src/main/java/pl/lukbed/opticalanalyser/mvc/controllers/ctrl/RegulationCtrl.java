package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.lukbed.opticalanalyser.mvc.model.ThisApplication;
import pl.lukbed.opticalanalyser.mvc.model.functions.OpticalFileReader;
import pl.lukbed.opticalanalyser.mvc.model.regulations.Regulation;

import java.io.File;

public abstract class RegulationCtrl {

    public static Regulation getSelectedRegulation() {
        return MainCtrl.getMeasurementPaneController().getRegulationComboBox().getSelectionModel().getSelectedItem();
    }



    public static void addRegulation() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT text files", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files", "*"));
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            ThisApplication.getProject().addRegulation(OpticalFileReader.readRegulation(file));
            CommunicationCtrl.print("Regulation from file " + file.getName() + " loaded");
        } catch (Exception e) {
            CommunicationCtrl.print("Can not load regulation from file " + file.getName());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("New regulation");
            alert.setHeaderText("Can not load regulation from file " + file.getName());
            alert.setContentText("Error during loading file");
            alert.showAndWait();
        }
        RefreshCtrl.refreshRegulations();
    }

    public static void loadDefaultRegulations() {
        int i = 0;
        if (loadRegulation("Tail ECE LH")) {
            i++;
        }
        if (loadRegulation("Turn ECE LH")) {
            i++;
        }
        if (loadRegulation("Stop ECE")) {
            i++;
        }
        if (loadRegulation("Fog ECE cross")) {
            i++;
        }
        if (loadRegulation("Reverse ECE LH single")) {
            i++;
        }
        if (loadRegulation("Reverse ECE LH double")) {
            i++;
        }
        if (i ==6) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Default regulations");
            alert.setHeaderText(null);
            alert.setContentText("Default regulations loaded succesfully");
            alert.showAndWait();
        }
        RefreshCtrl.refreshRegulations();
    }

    private static boolean loadRegulation(String regulationName) {
        File file;
        String path = "regulations/default/";
        file = new File(path + regulationName + ".txt");
        try {
            ThisApplication.getProject().addRegulation(OpticalFileReader.readRegulation(file));
            CommunicationCtrl.print("Regulation from file " + file.getName() + " loaded");
            return true;
        } catch (Exception e) {
            CommunicationCtrl.print("Can not load regulation from file " + file.getName());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("New regulation");
            alert.setHeaderText("Can not load default regulations");
            alert.setContentText("Error during loading file");
            alert.showAndWait();
        }
        return false;
    }



}
