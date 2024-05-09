package com.example.quan_ly_tuyen_bay.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTreeCell;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongTinVeController implements Initializable {
    @FXML
    private Button bt_tieptheo;

    @FXML
    private Label lb_gia;

    @FXML
    private Label lb_maghe;

    @FXML
    private Label lb_title;

    @FXML
    private TextField txt_sdt;

    @FXML
    private TextField txt_tenkhachhang;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lb_title.setText("Thông tin hành khách " + Controller.dsGheChon.size());

    }




}
