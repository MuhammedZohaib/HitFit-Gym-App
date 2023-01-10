package model_class;

import com.example.semesterProject_2022.*;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class Queries {
    private int id;
    private String username;
    private String email;
    private String heading;
    private String description;

    private QueryMenuButton actionBtn;
    private MenuItem item1 = new MenuItem("View");
    private MenuItem item2 = new MenuItem("Remove");

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

    public Queries(int id, String username, String email, String heading, String description, QueryMenuButton actionBtn) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.heading = heading;
        this.description = description;
        this.actionBtn = actionBtn;

        actionBtn.getItems().addAll(item1,item2,item3);
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

    }

    private MenuItem item3 = new MenuItem("Reply");
    public String getLowerUserName()
    {
        return getUsername().toLowerCase();
    }

}
