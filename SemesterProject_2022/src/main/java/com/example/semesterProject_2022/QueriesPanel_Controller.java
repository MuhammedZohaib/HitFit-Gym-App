package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class QueriesPanel_Controller {
    @FXML
    private Text texttbchanged;

    @FXML
    void changetxt(ActionEvent event) {
        texttbchanged.setText("This is Queries Panel");

    }
}
