package com.example.quan_ly_tuyen_bay.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataConnection {
    public static Connection connection;
    public static Statement statement;


    public static ResultSet retrieveData(String sqlCommand){

        try {
            createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);

            return resultSet;
        }catch (Exception e){
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE,"Lỗi kết nối database ",e);
            return null;
        }
    }

    public static void createStatement() {
        String url = "jdbc:mysql://localhost:3306/qlcb3";

        String user = "root";
        String pass = "";
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);
            statement = connection.createStatement();
        }catch (Exception e){
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE,"Lỗi kết nối database ",e);

        }
    }

    public static void main(String[] args) {
        createStatement();
    }

}
