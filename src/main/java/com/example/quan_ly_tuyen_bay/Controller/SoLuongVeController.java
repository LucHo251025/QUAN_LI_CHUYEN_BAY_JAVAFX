package com.example.quan_ly_tuyen_bay.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SoLuongVeController implements Initializable {

    @FXML
    private Button bt_boqua;

    @FXML
    private Button bt_chon;

    @FXML
    private ComboBox<String> cb_nguoilon;

    @FXML
    private ComboBox<String> cb_treem;

    @FXML
    void NEXT(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienDSVe.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void SELECT(ActionEvent event) {
        String nguoiLonValue = cb_nguoilon.getValue();
        String treemValue = cb_treem.getValue();
        if (nguoiLonValue != null && treemValue != null) {
            int soNguoiLon = Integer.parseInt(nguoiLonValue);
            int soTreEm = Integer.parseInt(treemValue);
            int sove = soNguoiLon + soTreEm;
            Controller.soLuongVeChon = sove;
        }



        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienDSVe.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i=1;i<50;i++){
            cb_nguoilon.getItems().add(String.valueOf(i));
        }

        for (int i=1;i<50;i++){
            cb_treem.getItems().add(String.valueOf(i));
        }
    }


}
