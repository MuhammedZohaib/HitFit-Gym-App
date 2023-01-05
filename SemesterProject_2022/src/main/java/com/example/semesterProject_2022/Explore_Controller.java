package com.example.semesterProject_2022;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Explore_Controller {
    @FXML
    private Button closeBtn;
    @FXML
    private Button maxBtn;
    @FXML
    private Button restoreBtn;

    @FXML
    private AnchorPane beginnerP1;
    @FXML
    private AnchorPane beginnerP2;
    @FXML
    private AnchorPane proPane1;

    @FXML
    private AnchorPane proPane2;
    @FXML
    private AnchorPane starterPane1;

    @FXML
    private AnchorPane starterPane2;
    public void close() {
        new GeneralFunctions().close(closeBtn);
    }
    public void max () {
        new GeneralFunctions().maxmin(maxBtn);
    }

    public void restore () {
        new GeneralFunctions().restoring(restoreBtn);
    }
    public void beginnerPlan(){
        beginnerP2.toFront();
        new animatefx.animation.FadeIn(beginnerP1).play();
    }
    public void beginnerPlanDetail(){
        new animatefx.animation.FadeIn(beginnerP1).play();
        beginnerP2.toBack();
    }
    public void starterPlan(){
        starterPane2.toFront();
        new animatefx.animation.FadeIn(starterPane1).play();
    }
    public void starterPlanDetail(){
        new animatefx.animation.FadeIn(starterPane1).play();
        starterPane2.toBack();
    }
    public void proPlan(){
        proPane2.toFront();
        new animatefx.animation.FadeIn(proPane1).play();
    }
    public void proPlanDetail(){
        new animatefx.animation.FadeIn(proPane1).play();
        proPane2.toBack();
    }

}
