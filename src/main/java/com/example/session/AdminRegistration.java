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
                role = "student";
            } else if (teacher.isSelected()) {
                role = "teacher";
            } else if (zamDirector.isSelected()) {
                    role = "zamDirector";
            }
            else role = "";
            DButils manager = new DButils();
            if (!username.isEmpty() && !password1.isEmpty() && !role.isEmpty()) {
                manager.registration(username, password1, role);
            }
        });

    }
}
