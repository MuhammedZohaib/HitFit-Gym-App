package com.example.semesterProject_2022;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeSidePanel_Controller implements Initializable {
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

    @FXML
    private Text Username;
    DashboardPanel_Controller dashboardPanel_controller = new DashboardPanel_Controller();

    private static int Menu_Counter = 0;
    /*---ChangeFxml Class objects---*/
    Changefxml dashboardPanel = new Changefxml();
    Changefxml membersPanel = new Changefxml();
    Changefxml accountSettingsPanel = new Changefxml();
    Changefxml membershipsPanel = new Changefxml();
    Changefxml employeesPanel = new Changefxml();
    Changefxml equipmentsPanel = new Changefxml();
    Changefxml revenuePanel = new Changefxml();
    Changefxml enquiriesPanel = new Changefxml();

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

            // for stack pane
            TranslateTransition translateTransition1 = new TranslateTransition();
            translateTransition1.setDuration(Duration.millis(400));
            translateTransition1.setNode(stackPane);
            translateTransition1.setToX(-135);
            translateTransition1.play();

            stackPane.setTranslateX(-200);
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

            TranslateTransition translateTransition2 = new TranslateTransition();
            translateTransition2.setDuration(Duration.millis(400));
            translateTransition2.setNode(dashboardPanel_controller.scrollpanedashboard);
            translateTransition2.setToX(-100);

            stackPane.setTranslateX(0);
            navPanel.setTranslateX(0);
            dashboardPanel_controller.scrollpanedashboard.setTranslateX(0);

            Menu_Counter=0;
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO Get admin username or name from db to set here
        Username.setText(" ");

        // created  a class named change fxml and called its function which loads up a new fxml file and makes it the children of stack pane
        dashboardPanel.getfxml("EmployeeSideDashboard.fxml");
        membersPanel.getfxml("MembersPanel_EmployeeSide.fxml");
        membershipsPanel.getfxml("TransactionPanel.fxml");
        enquiriesPanel.getfxml("QueriesPanel.fxml");
        equipmentsPanel.getfxml("EquipmentsPanel.fxml");
        revenuePanel.getfxml("RevenuePanel.fxml");
        accountSettingsPanel.getfxml("AccountSettingsPanel.fxml");
        employeesPanel.getfxml("Employees.fxml");


        /*--Adding to Arraylist of panels--*/
        panels.add(0, accountSettingsPanel);
        panels.add(1,membersPanel);
        panels.add(2, membershipsPanel);
        panels.add(3, equipmentsPanel);
        panels.add(4, employeesPanel);
        panels.add(5, revenuePanel);
        panels.add(6, enquiriesPanel);
        panels.add(7,dashboardPanel);
        /*--Adding Ends here--*/

        /*--Adding FXML stored in panes to Children of stack pane--*/
        for(int i=0; i<8;i++)
        {
            stackPane.getChildren().add(i,panels.get(i).pane);
        }
        /*--Adding of Children end here--*/

        /*--Initially only the Dashboard will be displayed so, turning the visibility of other panes off--*/
        for(int i=0; i<7;i++)
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
            dashboardPanel_controller.scrollpanedashboard.setTranslateX(-100);
            navPanel.setTranslateX(0);
            stackPane.setTranslateX(0);
        }

    }
    /*---All the menu button actions are handled here---*/
    @FXML
    void AccountSettingsBtn() {
        for(int i= 1; i<8;i++)
        {
            stackPane.getChildren().get(i).setVisible(false);
        }
        stackPane.getChildren().get(0).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }
    @FXML
    void logoutBtn(ActionEvent e) throws IOException {
        new GeneralFunctions().close(logout);
        new GeneralFunctions().switchScene("LoginSignUp.fxml");
    }

    @FXML
    void DashboardBtn() {

        for(int i=0; i<7;i++)
        {
            stackPane.getChildren().get(i).setVisible(false);
        }
        stackPane.getChildren().get(7).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void EmployeesBtn() {

        for(int i=0; i<8;i++)
        {
            if(i!=4)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        stackPane.getChildren().get(4).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void EquipmentsBtn() {
        for(int i=0; i<8;i++)
        {
            if(i!=3)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        stackPane.getChildren().get(3).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void MembersBtn() {
        for(int i=0; i<8;i++)
        {
            if(i!=1)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        stackPane.getChildren().get(1).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void MembershipsBtn() {
        for(int i=0; i<8;i++)
        {
            if(i!=2)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        stackPane.getChildren().get(2).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void QueriesBtn() {
        for(int i=0; i<8;i++)
        {
            if(i!=6)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        stackPane.getChildren().get(6).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    @FXML
    void RevenueBtn() {
        for(int i=0; i<8;i++)
        {
            if(i!=5)
            {
                stackPane.getChildren().get(i).setVisible(false);
            }
        }
        stackPane.getChildren().get(5).setVisible(true);
        new animatefx.animation.FadeIn(stackPane).play();
    }

    /*--------------------------------------------------*/
}
