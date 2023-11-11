package com.example.session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DButils {

    private Connection connection;

    public DButils() {
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "123456789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    Connection conn = null;
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "123456789");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            return null;
        }
    }


    public String authenticate(String username, String password) {
        try {
            String query = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }

        return "not found";
    }
    public String registration(String username, String password, String role, String subject){
        try {
            String query = "INSERT INTO users (username, password, role, subject) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.setString(4, subject);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0){
                return "Пользователь успешно зарегистрирован";

            }
            else {
                return "Ошибка при регистрации пользователя";
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при регистрации пользователя";

        }
        finally {
            closeConnection();
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Users> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Users(Integer.parseInt(rs.getString("id")), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("subject")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}


