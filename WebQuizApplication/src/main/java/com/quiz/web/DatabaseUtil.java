package com.quiz.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    public static Connection getConnection() throws SQLException {
        try {
            // Ensure the MySQL driver is registered
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Database connection URL, username, and password
            String url = "jdbc:mysql://localhost:3306/quizdb";
            String username = "root"; // Replace with your MySQL username
            String password = "yugala@10"; // Replace with your MySQL password

            // Return the connection
            Connection con=DriverManager.getConnection(url, username, password);
            return con;
        } catch (ClassNotFoundException e) {
            // If the driver class is not found, throw an SQLException
            throw new SQLException("MySQL Driver not found", e);
        }
    }
}
