package com.example.semesterproject_2022;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardPanel_Controller implements Initializable {

    @FXML
    private Button dueButton;

    @FXML
    private AnchorPane duePane;

    @FXML
    private Button expiredButton;

    @FXML
    private AnchorPane expiredPane;

    @FXML
    private Text membershipsDue;

    @FXML
    private StackPane memberstckpane;

    @FXML
    private Text monthlyExpense;

    @FXML
    private Text monthlyMembers;

    @FXML
    private Text monthlyRevenue;

    @FXML
    private Text monthlyprofit;

    @FXML
    private Text pendingPayments;

    @FXML
    private Button recentButton;

    @FXML
    private AnchorPane recentPane;

    @FXML
    private Text totalMembers;

    @FXML
    void duebtn(ActionEvent event) {
        dueButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 0 0 0 0;");
        memberstckpane.getChildren().get(1).setVisible(true);
        new animatefx.animation.FadeIn(memberstckpane).play();
        for(int i=0;i<2;i++)
        {
            if(i!=1){
                        memberstckpane.getChildren().get(i).setVisible(false);
                    }
        }
        expiredButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 0 0 0 0;");
        recentButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 12 0 0 0;");

    }

    @FXML
    void expiredbtn(ActionEvent event) {
        expiredButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 0 0 0 0;");
        memberstckpane.getChildren().get(2).setVisible(true);
        new animatefx.animation.FadeIn(memberstckpane).play();
        for(int i=0;i<1;i++)
        {
            memberstckpane.getChildren().get(i).setVisible(false);
        }
        dueButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 0 0 0 0;");
        recentButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 12 0 0 0;");

    }

    @FXML
    void recentBtn(ActionEvent event) {
        System.out.println(memberstckpane.getChildren());
        recentButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 12 0 0 0;");
        memberstckpane.getChildren().get(0).setVisible(true);
        new animatefx.animation.FadeIn(memberstckpane).play();

        for(int i=1;i<3;i++)
        {
            memberstckpane.getChildren().get(i).setVisible(false);
        }
        dueButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 0 0 0 0;");
        expiredButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 0 0 0 0;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*--Members card--*/
        for(int i =1; i<3;i++)
        {
            memberstckpane.getChildren().get(i).setVisible(false);
        }
        /*--End--*/

    }
}
