package com.example.quan_ly_tuyen_bay.Server.Database;

import java.sql.*;
import java.util.DoubleSummaryStatistics;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/qlcb";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection connection;
    public static Statement statement;

    public static ResultSet resultSet;


    public static ResultSet retrieveData(String sqlCommand) {

        try {
            createStatement();
//            connection = DriverManager.getConnection(URL, USER, PASS);
//            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            return resultSet;
        } catch (Exception e) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, "Lỗi kết nối database ", e);
            return null;
        }

    }

    public static void createStatement() {

        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
        } catch (Exception e) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, "Lỗi kết nối database ", e);

        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null && !resultSet.isClosed()) resultSet.close();
            if (statement != null && !statement.isClosed()) statement.close();
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, "Failed to close resources", e);
        }
    }

    public static void closeResources(Connection connection, Statement statement) {
        closeResources(connection, statement, null);
    }
    public static void main(String[] args) {
        createStatement();
    }

}
