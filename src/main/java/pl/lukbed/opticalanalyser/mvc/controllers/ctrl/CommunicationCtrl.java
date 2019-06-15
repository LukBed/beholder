package pl.lukbed.opticalanalyser.mvc.controllers.ctrl;

public abstract class CommunicationCtrl {

    public static void print(String message) {
        String result = message +
                "\n" +
                MainCtrl.getFooterPaneController().getMessageTextArea().getText();
        MainCtrl.getFooterPaneController().getMessageTextArea().setText(result);
    }
}
