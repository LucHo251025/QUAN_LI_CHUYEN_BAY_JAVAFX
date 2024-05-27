package com.example.quan_ly_tuyen_bay;

import com.example.quan_ly_tuyen_bay.Client.ClientCRUD;
import com.example.quan_ly_tuyen_bay.Client.ClientListenUpdate;
import com.example.quan_ly_tuyen_bay.Client.ClientUpdate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("C:\\Users\\vanth\\IdeaProjects\\DoAn\\Quan_Li_Chuyen_Bay\\src\\main\\resources\\com\\example\\quan_li_chuyen_bay\\View\\Home.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/quan_ly_tuyen_bay/View/DangNhap.fxml"));

//        Parent root = fxmlLoader.load();

//        fxmlLoader.setRoot(root);

        Scene scene = new Scene(fxmlLoader.load(), 659, 479);
        stage.setTitle("Đăng Nhập");

        stage.setScene(scene);
        stage.show();

        new ClientUpdate(new Socket("192.168.1.123", 20003));
        new ClientCRUD(new Socket("192.168.1.123",20005));
//        new ClientListenUpdate(new ServerSocket(20003));
    }

    public static void main(String[] args) {
        launch();
    }
}
