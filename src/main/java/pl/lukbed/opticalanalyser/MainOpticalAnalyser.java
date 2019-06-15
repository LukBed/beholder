package pl.lukbed.opticalanalyser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.lukbed.opticalanalyser.mvc.model.ThisApplication;


public class MainOpticalAnalyser extends Application {

//    public MainOpticalAnalyser() {
//        System.out.println("Optical Analyser");
//    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        ThisApplication.start();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle(ThisApplication.getAppName());
        stage.show();
    }

    @Override
    public void init() {
    }
//
//    @Override
//    public void stop() {
//        System.out.println("See you later");
//    }

}
