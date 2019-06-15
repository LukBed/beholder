package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import pl.lukbed.opticalanalyser.mvc.controllers.*;
import pl.lukbed.opticalanalyser.mvc.model.ThisApplication;
import pl.lukbed.opticalanalyser.mvc.model.project.Project;

import java.util.Optional;

public abstract class ProjectCtrl {

    public static void newProject() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New project");
        alert.setHeaderText("Creating of new project");
        alert.setContentText("Are you sure you want to create a new project? All data will be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ThisApplication.setProject(new Project());
            MeasurementPanelCtrl.clearRaport();
            RefreshCtrl.refreshAll();
            CommunicationCtrl.print("New project created");
            Stage stage = (Stage) MainCtrl.getMainPane().getScene().getWindow();
            stage.setTitle(ThisApplication.getAppName());
        }
    }

    public static void renameProject() {
        TextInputDialog dialog = new TextInputDialog(ThisApplication.getProject().getName());
        dialog.setTitle("Rename project");
        dialog.setHeaderText("Insert new project name");
        dialog.setContentText("Project:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ProjectCtrl::setProjectName);
    }

    public static void setProjectName(String name){
        ThisApplication.getProject().setName(name);
        RefreshCtrl.refreshProjectName();
        CommunicationCtrl.print("Project name updated to " + name);
        Stage stage = (Stage) MainCtrl.getMainPane().getScene().getWindow();
        stage.setTitle(ThisApplication.getAppName() + " - [" + name + "]");
    }


}
