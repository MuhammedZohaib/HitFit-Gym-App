package com.example.semesterProject_2022;

import javafx.scene.control.MenuButton;

public class QueryMenuButton extends MenuButton {
    private int ButtonId;
    private String username, email, heading, description;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getButtonId() {
        return ButtonId;
    }

    public void setButtonId(int buttonId) {
        ButtonId = buttonId;
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

    public QueryMenuButton(String s, int buttonId, String username, String email, String heading, String description) {
        super(s);
        ButtonId = buttonId;
        this.username = username;
        this.email = email;
        this.heading = heading;
        this.description = description;
    }

}
