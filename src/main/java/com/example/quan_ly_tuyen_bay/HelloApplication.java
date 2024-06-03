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

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/quan_ly_tuyen_bay/View/DangNhap.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/quan_ly_tuyen_bay/View/Loading.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Đăng Nhập");

        stage.setScene(scene);
        stage.show();

        new ClientUpdate(new Socket("localhost", 20003));
        new ClientCRUD(new Socket("localhost",20005));
    }

    public static void main(String[] args) {
        launch();
    }
}
