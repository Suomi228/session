package com.example.session;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class ListOfUsers implements Initializable {
    @FXML
    private TableView<Users> table_users;

    @FXML
    private TableColumn<Users, Integer> col_id;

    @FXML
    private TableColumn<Users, String> col_username;

    @FXML
    private TableColumn<Users, String> col_password;

    @FXML
    private TableColumn<Users, String> col_role;

    @FXML
    private TableColumn<Users, String> col_subject;
    ObservableList<Users> listM;
    ObservableList<Users> dataList;
    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private RadioButton math;
    @FXML
    private RadioButton informatika;
    @FXML
    private RadioButton history;
    @FXML
    private RadioButton noSubject;
    @FXML
    private RadioButton student;
    @FXML
    private RadioButton teacher;
    @FXML
    private RadioButton zamDirector;
    @FXML
    private Button button_logout;

    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void getSelected (MouseEvent event){
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        String role;
        String subject;
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        role = (col_role.getCellData(index).toString());
        subject = (col_subject.getCellData(index).toString());
        if (role.equals("student")){
            student.setSelected(true);
        } else if (role.equals("teacher")) {
            teacher.setSelected(true);
        }
        else if (role.equals("zamDirector")) {
            zamDirector.setSelected(true);
        }
        if (subject.equals("math")){
            math.setSelected(true);
        } else if (subject.equals("informatika")) {
            informatika.setSelected(true);
        }
        else if (subject.equals("history")) {
            history.setSelected(true);
        }
        else if (subject.equals("noSubject")) {
            noSubject.setSelected(true);
        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException { // GO BACK!!!
        Stage stage = (Stage) button_logout.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-menu.fxml")));
        stage.setTitle("Session");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void Edit (){
        try {
            conn = DButils.ConnectDb();
            String login = txt_username.getText();
            String password = txt_password.getText();
            String subject;
            if (math.isSelected()) {
                subject = "math";
            } else if (informatika.isSelected()) {
                subject = "informatika";
            } else if (history.isSelected()) {
                subject = "history";
            }
            else if (noSubject.isSelected()) {
                subject = "noSubject";
            }
            else subject = "";
            String role;
            if (student.isSelected()) {
                role = "student";
            } else if (teacher.isSelected()) {
                role = "teacher";
            } else if (zamDirector.isSelected()) {
                role = "zamDirector";
            }
            else role = "";
            String id = (col_id.getCellData(index).toString());
            String sql = "update users set username= '"+login+"',password= '"+
                    password+"',role= '"+role+"',subject= '"+subject+"' where id='"+id+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            UpdateTable();
            //search_user();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
        }

    }
    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        col_role.setCellValueFactory(new PropertyValueFactory<Users,String>("role"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Users,String>("subject"));

        listM = DButils.getDatausers();
        table_users.setItems(listM);
    }
    @FXML
    void deleteSelected() {
        if (index > -1) {
            try {
                conn = DButils.ConnectDb();
                String id = col_id.getCellData(index).toString();
                String sql = "DELETE FROM users WHERE id = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, id);
                pst.executeUpdate();
                UpdateTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<Users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<Users,String>("password"));
        col_role.setCellValueFactory(new PropertyValueFactory<Users,String>("role"));
        col_subject.setCellValueFactory(new PropertyValueFactory<Users,String>("subject"));
        listM = DButils.getDatausers();
        table_users.setItems(listM);
    }
}
