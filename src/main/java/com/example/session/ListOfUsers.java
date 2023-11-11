package com.example.session;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

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
