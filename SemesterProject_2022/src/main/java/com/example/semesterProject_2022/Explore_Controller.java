package com.example.semesterProject_2022;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Explore_Controller {
    @FXML
    private Button closeBtn;
    @FXML
    private Button maxBtn;
    @FXML
    private Button restoreBtn;

    public void close() {
        new GeneralFunctions().close(closeBtn);
    }
    public void max () {
        new GeneralFunctions().maxmin(maxBtn);
    }

    public void restore () {
        new GeneralFunctions().restoring(restoreBtn);
    }
}
