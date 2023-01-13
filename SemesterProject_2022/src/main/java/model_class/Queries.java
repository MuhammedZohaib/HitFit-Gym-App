package model_class;

import backend_functions.CustomDate;
import com.example.semesterProject_2022.*;
import javafx.css.StyleConverter;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.sql.Date;

public class Queries {
    private int id;
    private String username;
    private String email;
    private String heading;
    private String description;
    private Boolean status;
    private String StatusString;
    private Date current_date;

    private QueryMenuButton actionBtn;
    private MenuItem item1 = new MenuItem("View");

    public String getStatusString() {
        return StatusString;
    }

    public void setStatusString(String statusString) {
        StatusString = statusString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QueryMenuButton getActionBtn() {
        return actionBtn;
    }

    public void setActionBtn(QueryMenuButton actionBtn) {
        this.actionBtn = actionBtn;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }

    public Queries(Boolean status, int id, String username, String email, String heading, String description, QueryMenuButton actionBtn) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.heading = heading;
        this.description = description;
        this.status = status;
        if(status==true)
        {
            StatusString="Completed";
        } else
        {
            StatusString= "Pending";
        }
        this.actionBtn = actionBtn;
        this.actionBtn.setStyle("-fx-background-color: #00C2FF; -fx-background-radius: 12px;");
        this.actionBtn.setTextFill(Paint.valueOf("White"));

        actionBtn.getItems().addAll(item1,item3);
        item1.setOnAction(event ->
        {
            QueryView.username = actionBtn.getUsername();
            QueryView.email = actionBtn.getEmail();
            QueryView.heading = actionBtn.getHeading();
            QueryView.description = actionBtn.getDescription();
            try {
                QueriesPanel_Controller.view();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        item3.setOnAction(event ->
        {
            QueriesReply_Controller.Id=actionBtn.getButtonId();
            try {
                new GeneralFunctions().switchSceneModality("QueryReplyForm.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public Queries(int id, String username, String email, String heading, String description, Boolean status){
        this.id = id;
        this.description = description;
        this.email = email;
        this.username = username;
        this.heading = heading;
        this.status = status;
        this.current_date = CustomDate.getCurrentDate();

    }

    private MenuItem item3 = new MenuItem("Reply");
    public String getLowerUserName()
    {
        return getUsername().toLowerCase();
    }

}
