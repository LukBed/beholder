package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import javafx.scene.control.TextInputDialog;
import pl.lukbed.opticalanalyser.mvc.model.ThisApplication;
import pl.lukbed.opticalanalyser.mvc.model.project.LightFunction;

import java.util.Optional;

public abstract class FunctionCtrl {

    public static void addLightFunction() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("New light function");
        textInputDialog.setHeaderText("New light function");
        textInputDialog.setContentText("Light function:");
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(name -> { ThisApplication.getProject().addLightFunction(new LightFunction(name));
            CommunicationCtrl.print("New light function \"" + name + "\"created");

        });
        RefreshCtrl.refreshLightFunctionsList();
    }

    //get selected light function or if it is not get first function or if it doesn't exist create new
    public static LightFunction getSelectedLightFunction() {

        if (MainCtrl.getPanelPaneController().getLightFunctionsListView().getSelectionModel().getSelectedItems().isEmpty()) {
            if (ThisApplication.getProject().getLightFunctions().size() == 0) {
                ThisApplication.getProject().addLightFunction();
                RefreshCtrl.refreshLightFunctionsList();
            }
            return ThisApplication.getProject().getLightFunctions().get(0);
        }
        return MainCtrl.getPanelPaneController().getLightFunctionsListView().getSelectionModel().getSelectedItem();
    }





}
