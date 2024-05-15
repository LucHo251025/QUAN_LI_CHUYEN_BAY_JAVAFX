package com.example.quan_ly_tuyen_bay.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {
    @FXML
    private Button bt_doanhthu;

    @FXML
    private Button bt_khachhang;

    @FXML
    private Button bt_nhanvien;

    @FXML
    private BarChart<?, ?> chart_dt;

    @FXML
    private BarChart<?, ?> chart_kh;

    @FXML
    private BarChart<?, ?> chart_nv;

    @FXML
    private AnchorPane pane_doanhthu;

    @FXML
    private AnchorPane pane_khachhang;

    @FXML
    private AnchorPane pane_nhanvien;

    @FXML
    private Label sl_dt;

    @FXML
    private Label sl_kh;

    @FXML
    private Label sl_nv;

    @FXML
    void ClickNV(ActionEvent event) {

    }

    @FXML
    void clickKH(ActionEvent event) {

    }

    @FXML
    void click_DT(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
