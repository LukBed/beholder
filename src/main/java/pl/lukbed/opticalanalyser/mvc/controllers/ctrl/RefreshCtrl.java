package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import pl.lukbed.opticalanalyser.mvc.model.ThisApplication;
import pl.lukbed.opticalanalyser.mvc.model.project.LightFunction;

public abstract class RefreshCtrl {

    public static void refreshAll() {
        refreshProjectName();
        refreshLightFunctionsList();
        refreshRegulations();
        refreshMeasurements();
        refreshRaport();
    }

    public static void refreshRaport() {
        MainCtrl.getMeasurementPaneController().getWebView().getEngine().reload();
    }

    public static void refreshProjectName() {
        String name = ThisApplication.getProject().getName();
        MainCtrl.getPanelPaneController().getProjectNameTextField().setText(name);
    }

    public static void refreshLightFunctionPreview(LightFunction lightFunction) {
        MainCtrl.getMeasurementPaneController().getLightFunctionLabel().setText("Light function: " + lightFunction.getName());
        refreshMeasurements();
    }

    public static void refreshMeasurements() {
        MainCtrl.getMeasurementPaneController().getMeasurementComboBox().getItems().clear();
        MainCtrl.getMeasurementPaneController().getMeasurementComboBox().getItems().addAll(FunctionCtrl.getSelectedLightFunction().getMeasurements());
    }

    //refresh light function preview
    public static void refreshLightFunctionsList() {
        MainCtrl.getPanelPaneController().getLightFunctionsListView().getItems().clear();
        MainCtrl.getPanelPaneController().getLightFunctionsListView().getItems().addAll(ThisApplication.getProject().getLightFunctions());
    }

    public static void refreshRegulations() {
        MainCtrl.getMeasurementPaneController().getRegulationComboBox().getItems().clear();
        MainCtrl.getMeasurementPaneController().getRegulationComboBox().getItems().addAll(ThisApplication.getProject().getRegulations());
    }

}
