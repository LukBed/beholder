package pl.lukbed.opticalanalyser.mvc.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class FooterPaneController {

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button clearMessageButtonn;

    public TextArea getMessageTextArea() {
        return messageTextArea;
    }

    public void initialize() {
        clearMessageButtonn.addEventFilter(ActionEvent.ACTION, actionEvent -> clearMessage());
    }

    public void clearMessage() {
        messageTextArea.clear();
    }
}