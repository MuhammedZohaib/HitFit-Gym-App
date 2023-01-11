package com.example.semesterProject_2022;

import database.DatabaseFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardPanel_Controller implements Initializable {

/*-Buttons-*/
    @FXML
    private Button PendingButton;
    @FXML
    private Button CompletedButton;
    @FXML
    private Button dueButton;
    @FXML
    private Button expiredButton;
    @FXML
    private Button recentButton;
    @FXML
    private Button HistoryButton;
    @FXML
    private Button RecentButtonExpenses;
    @FXML
    private Button InStockButton;
    @FXML
    private Button OutofStockButton;
/*--------*/

    @FXML
    private AnchorPane duePane;

    @FXML
    private AnchorPane expiredPane;

    @FXML
    private Text membershipsDue;

    @FXML
    private StackPane memberstckpane;
    @FXML
    public ScrollPane scrollpanedashboard = new ScrollPane();
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
    private StackPane ExpensestckPane;
    @FXML
    private AnchorPane recentPane;
    @FXML
    private StackPane itemstckpane;
    @FXML
    private StackPane querystckpane;

    @FXML
    private LineChart<?,?> monthlyProfitChart;

    @FXML
    private BarChart<?,? > monthlyExpenseChart;
    @FXML
    private Text totalMembers;
    private int noOfCustomers;

    @FXML
    void CompeleteBtn(ActionEvent event) {
        System.out.println(querystckpane.getChildren());
        CompletedButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 12 0 0 0;");
        querystckpane.getChildren().get(1).setVisible(true);
        new animatefx.animation.FadeIn(querystckpane).play();
        querystckpane.getChildren().get(0).setVisible(false);
        PendingButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 0 0 0 0;");
    }

    @FXML
    void Pendingbtn(ActionEvent event) {
        PendingButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 0 0 0 0;");
        querystckpane.getChildren().get(0).setVisible(true);
        new animatefx.animation.FadeIn(querystckpane).play();
        querystckpane.getChildren().get(1).setVisible(false);
        CompletedButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 12 0 0 0;");
    }
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


    @FXML
    void RecentExpBtn(ActionEvent event) {
        RecentButtonExpenses.setStyle("-fx-background-color: #03032c; -fx-background-radius: 12 0 0 0;");
        ExpensestckPane.getChildren().get(0).setVisible(true);
        new animatefx.animation.FadeIn(ExpensestckPane).play();
        ExpensestckPane.getChildren().get(1).setVisible(false);
        HistoryButton.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 0 0 0 0;");
    }
    @FXML
    void HistoryBtn(ActionEvent event) {
        HistoryButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 0 0 0 0;");
        ExpensestckPane.getChildren().get(1).setVisible(true);
        new animatefx.animation.FadeIn(ExpensestckPane).play();
        ExpensestckPane.getChildren().get(0).setVisible(false);
        RecentButtonExpenses.setStyle("-fx-background-color: #8E9DB5; -fx-background-radius: 12 0 0 0;");

    }

    @FXML
    void InStockBtn(ActionEvent event) {
        InStockButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 12 0 0 0;");
        itemstckpane.getChildren().get(0).setVisible(true);
        new animatefx.animation.FadeIn(itemstckpane).play();
        itemstckpane.getChildren().get(1).setVisible(false);
        OutofStockButton.setStyle("-fx-background-color: #8e9db5; -fx-background-radius: 0 0 0 0;");
    }

    @FXML
    void OutofStockBtn(ActionEvent event) {
        InStockButton.setStyle("-fx-background-color: #839db5; -fx-background-radius: 12 0 0 0;");
        itemstckpane.getChildren().get(0).setVisible(false);
        new animatefx.animation.FadeIn(itemstckpane).play();
        itemstckpane.getChildren().get(1).setVisible(true);
        OutofStockButton.setStyle("-fx-background-color: #03032c; -fx-background-radius: 0 0 0 0;");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO Get sum of All packages of one month and set here
        monthlyRevenue.setText("1000");
        //TODO Get sum of All expenses of one month and set here
        monthlyExpense.setText("1000");
        //TODO Difference of monthly packages and monthly expense will be set here
        monthlyprofit.setText("20000");


        /*--Members card--*/
        for(int i =1; i<3;i++)
        {
            memberstckpane.getChildren().get(i).setVisible(false);
        }
        /*--End--*/


        XYChart.Series series = new XYChart.Series<>();
        series.setName("Monthly Profit in Rupees");
        series.getData().add(new XYChart.Data<>("1", 2000));
        series.getData().add(new XYChart.Data<>("2", 40000));
        series.getData().add(new XYChart.Data<>("3", 60000));
        series.getData().add(new XYChart.Data<>("4", 80000));
        series.getData().add(new XYChart.Data<>("5", 100000));
        series.getData().add(new XYChart.Data<>("6", 100000));
        series.getData().add(new XYChart.Data<>("7", 100000));
        series.getData().add(new XYChart.Data<>("8", 100000));
        series.getData().add(new XYChart.Data<>("9", 100000));
        series.getData().add(new XYChart.Data<>("10", 100000));
        series.getData().add(new XYChart.Data<>("11", 100000));
        series.getData().add(new XYChart.Data<>("12", 100000));

        monthlyProfitChart.getData().add(series);

        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("Monthly Expense in Rupees");
        series1.getData().add(new XYChart.Data<>("1", 2000));
        series1.getData().add(new XYChart.Data<>("2", 40000));
        series1.getData().add(new XYChart.Data<>("3", 60000));
        series1.getData().add(new XYChart.Data<>("4", 80000));
        series1.getData().add(new XYChart.Data<>("5", 100000));
        series1.getData().add(new XYChart.Data<>("6", 100000));
        series1.getData().add(new XYChart.Data<>("7", 100000));
        series1.getData().add(new XYChart.Data<>("8", 100000));
        series1.getData().add(new XYChart.Data<>("9", 100000));
        series1.getData().add(new XYChart.Data<>("10", 100000));
        series1.getData().add(new XYChart.Data<>("11", 100000));
        series1.getData().add(new XYChart.Data<>("12", 100000));
        monthlyExpenseChart.getData().add(series1);
        try{
            noOfCustomers = DatabaseFunctions.getNumberOfCustomers();
        }
        catch (Exception e){
            System.out.println(e);
        }
        totalMembers.setText(String.valueOf(noOfCustomers));


    }
}
