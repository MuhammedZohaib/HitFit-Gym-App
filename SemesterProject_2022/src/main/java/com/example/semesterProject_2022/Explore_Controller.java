package com.example.semesterProject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Explore_Controller {
    @FXML
    private Button closeBtn;
    @FXML
    private Button maxBtn;
    @FXML
    private Button restoreBtn;
    @FXML
    private Button beginnerButton;
    @FXML
    private Button proButton;

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
    private Button starterButton;
    @FXML
    private Button back;
    @FXML
    private AnchorPane starterPane2;
    @FXML
    private Button exploreToLogin;

    @FXML
    private Button exploreToSignUp;
    public void close() {
        new GeneralFunctions().close(closeBtn);
    }
    public void max () {
        new GeneralFunctions().maxmin(maxBtn);
    }
    public void buyBeginner() throws IOException {
        new GeneralFunctions().close(beginnerButton);
        new GeneralFunctions().switchScene("SignUp.fxml");
    }
    public void buyPro() throws IOException {
        new GeneralFunctions().close(proButton);
        new GeneralFunctions().switchScene("SignUp.fxml");
    }
    public void buyStarter() throws IOException {
        new GeneralFunctions().close(starterButton);
        new GeneralFunctions().switchScene("SignUp.fxml");
    }
    public void signUp() throws IOException {
        new GeneralFunctions().close(exploreToSignUp);
        new GeneralFunctions().switchScene("SignUp.fxml");
    }
    public void customerLogin() throws IOException {
        new GeneralFunctions().close(exploreToLogin);
        new GeneralFunctions().switchScene("MemberLogin_Form.fxml");
    }
    public void GoBackLogIn() throws IOException {
        new GeneralFunctions().close(back);
        new GeneralFunctions().switchScene("LoginSignUp.fxml");

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
