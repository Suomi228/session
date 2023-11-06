package com.example.session;

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
    public String registration(String username, String password, String role){
        try {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
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
}

