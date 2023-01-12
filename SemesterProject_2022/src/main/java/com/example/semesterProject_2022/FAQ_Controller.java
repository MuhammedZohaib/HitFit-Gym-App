package com.example.semesterProject_2022;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class FAQ_Controller {
    @FXML
    private AnchorPane P1;

    @FXML
    private AnchorPane P2;

    @FXML
    private AnchorPane P3;

    @FXML
    private AnchorPane P4;

    @FXML
    private AnchorPane P5;

    @FXML
    private AnchorPane P6;

    public void q1(){
        P2.setVisible(true);
        P1.setVisible(false);
    }
    public void q1Ans(){
        P2.setVisible(false);
        P1.setVisible(true);
    }
    public void q2(){
        P4.setVisible(true);
        P3.setVisible(false);
    }
    public void q2Ans(){
        P4.setVisible(false);
        P3.setVisible(true);

    }
    public void q3(){
        P6.setVisible(true);
        P5.setVisible(false);
    }
    public void q3Ans(){
        P6.setVisible(false);
        P5.setVisible(true);

    }
}

