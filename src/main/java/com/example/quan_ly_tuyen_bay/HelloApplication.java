package com.example.quan_ly_tuyen_bay;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("C:\\Users\\vanth\\IdeaProjects\\DoAn\\Quan_Li_Chuyen_Bay\\src\\main\\resources\\com\\example\\quan_li_chuyen_bay\\View\\Home.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/quan_ly_tuyen_bay/View/DangNhap.fxml"));

//        Parent root = fxmlLoader.load();
//
//        fxmlLoader.setRoot(root);

        Scene scene = new Scene(fxmlLoader.load(), 937, 562);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
