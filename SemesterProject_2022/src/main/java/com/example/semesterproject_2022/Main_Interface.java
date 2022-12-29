package com.example.semesterproject_2022;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model_class.Customer;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class Main_Interface implements Initializable {
    @FXML
    private StackPane stckPane;
    @FXML
    private Button closeBtn;

    @FXML
    private Button maxBtn;

    @FXML
    private Button menuBarBtn;

    @FXML
    private AnchorPane navPanel;

    @FXML
    private Button restoreBtn;


    @FXML
    private AnchorPane stageBorderPane;

    private static int Menu_Counter = 0;
    /*---ChangeFxml Class objects---*/
    Changefxml dashboardPanel = new Changefxml();
    Changefxml membersPanel = new Changefxml();
    Changefxml accountSettingspanel = new Changefxml();
    Changefxml membershipspanel = new Changefxml();
    Changefxml employeespanel = new Changefxml();
    Changefxml equipmentspanel = new Changefxml();
    Changefxml revenuepanel = new Changefxml();
    Changefxml enquiriespanel = new Changefxml();

    ArrayList<Changefxml> panels = new ArrayList<>();

    /*--ChangeFXML class objects bock ends here--*/

    /*--Windows Border Logic--*/

    @FXML
    void close(ActionEvent event) {
        new GeneralFunctions().close(closeBtn);
    }

    @FXML
    void max(ActionEvent event) {
        new GeneralFunctions().maxmin(maxBtn);
    }

    @FXML
    void restore(ActionEvent event) {
        new GeneralFunctions().restoring(restoreBtn);
    }

    /*--End--*/
    @FXML
    void menuBar(ActionEvent event)  {


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
            translateTransition1.setNode(stckPane);
            translateTransition1.setToX(-135);
            translateTransition1.play();

            stckPane.setTranslateX(-200);
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
            translateTransition1.setNode(stckPane);
            translateTransition1.setToX(-186);
            translateTransition1.play();

            stckPane.setTranslateX(0);
            navPanel.setTranslateX(0);
            Menu_Counter=0;
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // created  a class named changefxml and called its function which loads up a new fxml file and makes it the children of stack pane
        dashboardPanel.getfxml("DasboardPanel.fxml");
        membersPanel.getfxml("MembersPanel.fxml");
        membershipspanel.getfxml("MembershipsPanel.fxml");
        enquiriespanel.getfxml("QueriesPanel.fxml");
        equipmentspanel.getfxml("EquipmentsPanel.fxml");
        revenuepanel.getfxml("Revenuepanel.fxml");
        accountSettingspanel.getfxml("AccountSettingsPanel.fxml");
        employeespanel.getfxml("Employees.fxml");


        /*--Adding to Arraylist of panels--*/
        panels.add(0,accountSettingspanel);
        panels.add(1,membersPanel);
        panels.add(2,membershipspanel);
        panels.add(3,equipmentspanel);
        panels.add(4,employeespanel);
        panels.add(5,revenuepanel);
        panels.add(6,enquiriespanel);
        panels.add(7,dashboardPanel);
        /*--Adding Ends here--*/

        /*--Adding FXML stored in panes to Children of stack pane--*/
        for(int i=0; i<8;i++)
        {
            stckPane.getChildren().add(i,panels.get(i).pane);
        }
        /*--Adding of Children end here--*/

        /*--Initially only the Dashboard will be displayed so, turning the visibility of other panes off--*/
        for(int i=0; i<7;i++)
        {
            stckPane.getChildren().get(i).setVisible(false);
        }
        /*-Visibility Block End here-*/
        /*-- added a library called "aniamtefx" ver 1.2.0 from Maven library ( -Saad S. )--*/
        new animatefx.animation.FadeIn(stckPane).play();



        /*---nav pane animation code starts here--*/
        if(Menu_Counter==0) {
            navPanel.setTranslateX(-186);
            stckPane.setTranslateX(-186);
        }
        if(Menu_Counter==1)
        {
            navPanel.setTranslateX(0);
            stckPane.setTranslateX(0);
        }

    }
    /*---All the menu button actions are handled here---*/
    @FXML
    void AccountSettingsBtn(ActionEvent event) {
        for(int i= 1; i<8;i++)
        {
            stckPane.getChildren().get(i).setVisible(false);
        }
        stckPane.getChildren().get(0).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void DashboardBtn(ActionEvent event) {

        for(int i=0; i<7;i++)
        {
            stckPane.getChildren().get(i).setVisible(false);
        }
        stckPane.getChildren().get(7).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void EmployeesBtn(ActionEvent event) {

        for(int i=0; i<8;i++)
        {
            if(i!=4)
            {
                stckPane.getChildren().get(i).setVisible(false);
            }
        }
        stckPane.getChildren().get(4).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void EquipmentsBtn(ActionEvent event) {
        for(int i=0; i<8;i++)
        {
            if(i!=3)
            {
                stckPane.getChildren().get(i).setVisible(false);
            }
        }
        stckPane.getChildren().get(3).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void MembersBtn(ActionEvent event) throws SQLException {
        for(int i=0; i<8;i++)
        {
            if(i!=1)
            {
                stckPane.getChildren().get(i).setVisible(false);
            }
        }
        stckPane.getChildren().get(1).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void MembershipsBtn(ActionEvent event) {
        for(int i=0; i<8;i++)
        {
            if(i!=2)
            {
                stckPane.getChildren().get(i).setVisible(false);
            }
        }
        stckPane.getChildren().get(2).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void QueriesBtn(ActionEvent event) {
        for(int i=0; i<8;i++)
        {
            if(i!=6)
            {
                stckPane.getChildren().get(i).setVisible(false);
            }
        }
        stckPane.getChildren().get(6).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    @FXML
    void RevenueBtn(ActionEvent event) {
        for(int i=0; i<8;i++)
        {
            if(i!=5)
            {
                stckPane.getChildren().get(i).setVisible(false);
            }
        }
        stckPane.getChildren().get(5).setVisible(true);
        new animatefx.animation.FadeIn(stckPane).play();
    }

    /*--------------------------------------------------*/
}
