package com.example.session;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AdminRegistration {



    @FXML
    private Button regButton;

    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private RadioButton student;
    @FXML
    private RadioButton teacher;
    @FXML
    private RadioButton zamDirector;
    @FXML
    void initialize() {
        regButton.setOnAction(event2->{
            String username = login.getText().trim();
            String password1 = password.getText().trim();
            String role;
            if (student.isSelected()) {
                role = "rew";
            } else if (teacher.isSelected()) {
                role = "ew";
            } else if (zamDirector.isSelected()) {
                    role = "disp";
            }
            else role = "";
            DButils manager = new DButils();
            if (!username.equals("") && !password1.equals("") && !role.equals("")) {
                manager.registration(username, password1, role);
            }
        });

    }
}
