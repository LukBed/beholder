package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

import javafx.scene.layout.AnchorPane;
import pl.lukbed.opticalanalyser.mvc.controllers.*;

public abstract class MainCtrl {
    private static AnchorPane mainPane;
    private static MenuPaneController menuPaneController;
    private static ToolbarPaneController toolbarPaneController;
    private static PanelPaneController panelPaneController;
    private static MeasurementPaneController measurementPaneController;
    private static FooterPaneController footerPaneController;
    private static String raportUrl = "temp.html";

    public static void set(AnchorPane mainPane, MenuPaneController menuPaneController, ToolbarPaneController toolbarPaneController,
                            PanelPaneController panelPaneController, MeasurementPaneController measurementPaneController, FooterPaneController footerPaneController) {
        MainCtrl.mainPane = mainPane;
        MainCtrl.menuPaneController = menuPaneController;
        MainCtrl.toolbarPaneController = toolbarPaneController;
        MainCtrl.panelPaneController = panelPaneController;
        MainCtrl.measurementPaneController = measurementPaneController;
        MainCtrl.footerPaneController = footerPaneController;
    }

    public static AnchorPane getMainPane() {
        return mainPane;
    }

    public static MenuPaneController getMenuPaneController() {
        return menuPaneController;
    }

    public static ToolbarPaneController getToolbarPaneController() {
        return toolbarPaneController;
    }

    public static PanelPaneController getPanelPaneController() {
        return panelPaneController;
    }

    public static MeasurementPaneController getMeasurementPaneController() {
        return measurementPaneController;
    }

    public static FooterPaneController getFooterPaneController() {
        return footerPaneController;
    }

    public static String getRaportUrl() {
        return raportUrl;
    }
}
