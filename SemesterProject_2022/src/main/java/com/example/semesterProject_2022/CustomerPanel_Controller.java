package com.example.semesterProject_2022;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model_class.Customer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerPanel_Controller implements Initializable {

    public static Customer Customer = new Customer();

    @FXML
    private StackPane stackPane;
    @FXML
    private Button closeBtn;

    @FXML
    private Button maxBtn;
    @FXML
    private Button logout;

    @FXML
    private AnchorPane navPanel;

    @FXML
    private Button restoreBtn;
    DashboardPanel_Controller dashboardPanel_controller = new DashboardPanel_Controller();

    private static int Menu_Counter = 0;
    /*---ChangeFxml Class objects---*/
    Changefxml dashboardPanel = new Changefxml();
    Changefxml BMIViewPanel = new Changefxml();
    Changefxml accountSettingsPanel = new Changefxml();
    Changefxml QueriesFormPanel = new Changefxml();
    Changefxml equipmentsPanel = new Changefxml();
    Changefxml FAQPanel = new Changefxml();


    ArrayList<Changefxml> panels = new ArrayList<>();

    /*--ChangeFXML class objects bock ends here--*/

    /*--Windows Border Logic--*/

    @FXML
    void close() {
        new GeneralFunctions().close(closeBtn);
    }

    @FXML
    void max() {
        new GeneralFunctions().maxmin(maxBtn);
    }

    @FXML
    void restore() {
        new GeneralFunctions().restoring(restoreBtn);
    }

    /*--End--*/
    @FXML
    void menuBar()  {


        if(Menu_Counter==0) {

            // for nav pane
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(400));
            translateTransition.setNode(navPanel);
            translateTransition.setToX(0);
            translateTransition.play();

//            // for stack pane
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setDuration(Duration.millis(400));
            translateTransition1.setNode(stackPane);
            translateTransition1.setToX(-80);
            translateTransition1.play();

            stackPane.setTranslateX(-80);
            navPanel.setTranslateX(-186);
            Menu_Counter=1;
        } else if(Menu_Counter==1)
        {
            TranslateTransition translateTransition = new TranslateTransition();
            translateTransition.setDuration(Duration.millis(400));
            translateTransition.setNode(navPanel);
            translateTransition.setToX(-186);
            translateTransition.play();

            // for stack pane
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setDuration(Duration.millis(400));
            translateTransition1.setNode(stackPane);
            translateTransition1.setToX(-186);
            translateTransition1.play();
//
//            TranslateTransition translateTransition2 = new TranslateTransition();
//            translateTransition2.setDuration(Duration.millis(400));
//            translateTransition2.setNode(dashboardPanel_controller.scrollpanedashboard);
//            translateTransition2.setToX(-100);
//
//            stackPane.setTranslateX(0);
//            navPanel.setTranslateX(0);
//            dashboardPanel_controller.scrollpanedashboard.setTranslateX(0);

            Menu_Counter=0;
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // created  a class named change fxml and called its function which loads up a new fxml file and makes it the children of stack pane
        dashboardPanel.getfxml("CustomerDashboardPanel.fxml");
        BMIViewPanel.getfxml("BMIViewPanel.fxml");
        QueriesFormPanel.getfxml("CustomerQueriesFormPanel.fxml");
        equipmentsPanel.getfxml("EquipmentsPanel.fxml");
        accountSettingsPanel.getfxml("AccountSettingsPanel.fxml");
        FAQPanel.getfxml("FAQPanel.fxml");


        /*--Adding to Arraylist of panels--*/
        panels.add(0, accountSettingsPanel);
        panels.add(1,BMIViewPanel);
        panels.add(2, QueriesFormPanel);
        panels.add(3, equipmentsPanel);
        panels.add(4, FAQPanel);
        panels.add(5,dashboardPanel);
        /*--Adding Ends here--*/

        /*--Adding FXML stored in panes to Children of stack pane--*/
        for(int i=0; i<6;i++)
        {
            stackPane.getChildren().add(i,panels.get(i).pane);
        }
        /*--Adding of Children end here--*/

        /*--Initially only the Dashboard will be displayed so, turning the visibility of other panes off--*/
        for(int i=0; i<5;i++)
        {
            stackPane.getChildren().get(i).setVisible(false);
        }
        /*-Visibility Block End here-*/
        /*-- added a library called "aniamtefx" ver 1.2.0 from Maven library ( -Saad S. )--*/
        new animatefx.animation.FadeIn(stackPane).play();




        /*---nav pane animation code starts here--*/
        if(Menu_Counter==0) {
            navPanel.setTranslateX(-186);
           stackPane.setTranslateX(-186);
        }
        if(Menu_Counter==1)
        {

            navPanel.setTranslateX(0);
            // stackPane.setTranslateX(0);
        }

    }
    /*---All the menu button actions are handled here---*/

    @FXML
    void logoutBtn(ActionEvent e) throws IOException {
        new GeneralFunctions().close(logout);
        new GeneralFunctions().switchScene("LoginSignUp.fxml");
        File file = new File("credentials.properties");
        if(file.exists()){
            file.delete();
        }
    }
    @FXML
    void AccountSettingsBtn() {
        stackPane.getChildren().get(0).setVisible(true);
        for(int i=1;i<6;i++)
        {
            stackPane.getChildren().get(i).setVisible(false);
        }
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void DashboardBtn() {

        stackPane.getChildren().get(5).setVisible(true);
        for(int i=0;i<5;i++)
        {
                stackPane.getChildren().get(i).setVisible(false);
        }
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void BMIBtn() {

        stackPane.getChildren().get(1).setVisible(true);
        for(int i=0;i<6;i++)
        {
            if(i!=1)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void EquipmentsBtn() {
        stackPane.getChildren().get(3).setVisible(true);

        for(int i=0; i<6;i++)
        {
            if(i!=3)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void FaqBtn() {
        stackPane.getChildren().get(4).setVisible(true);

        for(int i=0; i<6;i++)
        {
            if(i!=4)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void QueriesBtn() {
        stackPane.getChildren().get(2).setVisible(true);
        for(int i = 0; i<6; i++)
        {
            if(i!=2)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        new animatefx.animation.FadeIn(stackPane).play();
    }


    /*--------------------------------------------------*/
}
