package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.Ve;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DsVeController implements Initializable {
    @FXML
    private Button bt_huyve;

    @FXML
    private Label lb_title;

    @FXML
    private Label lb_maghe;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txt_sdt;
    @FXML
    private TextField txt_gia;
    @FXML
    private AnchorPane pane_ve;

    @FXML
    private TextField txt_tenkhackhang;

    private Alert alert;

    @FXML
    private TextField txt_cccd;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_ngaysinh;


    private ArrayList<Button> listGhe = new ArrayList<Button>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_tenkhackhang.setDisable(true);
        txt_sdt.setDisable(true);
        txt_gia.setDisable(true);
        pane_ve.setVisible(false);
        txt_email.setDisable(true);
        txt_cccd.setDisable(true);
        txt_ngaysinh.setDisable(true);

        Controller.dsGheChon.clear();
        ArrayList<String> mapGhe = new ArrayList<String>();
        lb_title.setText("Sơ đồ chuyến bay " + Controller.cb.getMaChuyenBay());
        if (Controller.cb.getTrangThai() == ChuyenBay.HUYCHUYEN || Controller.cb.getTrangThai() == ChuyenBay.HOANTAT) {
            bt_huyve.setVisible(false);
        }

        int soGhe = Controller.cb.getSoGhe();
        System.out.println(soGhe);
        mapGhe.add("");

        for (int i = 0; i < soGhe; i++) {
            mapGhe.add("0");
        }

        for (Ve ve : Controller.cb.getVeArrayList()) {
            mapGhe.set(Integer.parseInt(ve.getMaGhe()), ve.getMaGhe());
        }
        int chiso = 1;
        int kichthuoc = 80;

        for (int i = 0; ; i = i + kichthuoc + 10) {
            for (int j = 1; j <= 6; j++) {
                if (chiso >= mapGhe.size()) {
                    return; // Nếu chiso vượt quá kích thước của mapGhe, thoát khỏi vòng lặp
                }
                Button button = new Button();
                button.setLayoutX((j - 0.6) * (kichthuoc + 10) + 50 * (j > 3 ? 1 : 0));
                button.setLayoutY(i + 10);
                button.setPrefSize(kichthuoc, kichthuoc);

                pane.getChildren().add(button);
                button.setText(String.valueOf(chiso));

                if (!mapGhe.get(chiso).equals("0")) {
                    button.setStyle("-fx-background-color: #ec0101;");
                    if (Controller.tk.getLoaiTaiKhoan().equals("nhanvien")) {
                        button.setDisable(true);
                    }
                } else {
                    if (Controller.tk.getLoaiTaiKhoan().equals("quanli"))
                        button.setDisable(false);
                }

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        chonGhe(event,button);
                    }
                });
                listGhe.add(button);
                chiso++;
                int soHang = (int) Math.ceil(listGhe.size() / 6.0);
                int kichThuocButton = 80; // Kích thước của mỗi button
                int padding = 10; // Khoảng cách giữa các button
                int chieuDaiPane = soHang * (kichThuocButton + padding) + padding;

                pane.setPrefHeight(chieuDaiPane);
            }
        }


    }
    private  void  chonGhe(ActionEvent event,Button bt){
        if(Controller.tk.getLoaiTaiKhoan().equals("nhanvien")){

            for (String st : Controller.dsGheChon) {
                if(bt.getText().equals(st)){
                    bt.setStyle("-fx-background-color: #f0f0f0;");
                    Controller.dsGheChon.remove(st);
                    return;
                }
            }
            if(Controller.dsGheChon.size() +1 < Controller.soLuongVeChon){
                bt.setStyle("-fx-background-color: #29e11b");
                Controller.dsGheChon.add(bt.getText());
            }else {
                Controller.dsGheChon.add(bt.getText());
                Controller.dsVeChon.clear();

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienThongTinVe.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

        }else {
            pane_ve.setVisible(true);
            for (Ve ve : Controller.cb.getVeArrayList()){
                if(bt.getText().equals(ve.getMaGhe())){
                    lb_maghe.setText(ve.getMaGhe());
                    txt_tenkhackhang.setText(ve.getTenHanhKhach());
                    txt_sdt.setText(ve.getsDT());
                    txt_gia.setText(String.valueOf(ve.getGia()));
                    txt_cccd.setText(ve.getCccd());
                    txt_email.setText(ve.getEmail());
                    txt_ngaysinh.setText(String.valueOf(ve.getNgaysinh()));

                    return;
                }
            }
        }
    }



    @FXML
    void Thoat(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienChuyenBay.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void huyVe(ActionEvent event) {
        alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Xác nhận hủy vé");

        Optional<ButtonType> optional = alert.showAndWait();

        if(optional.get() == ButtonType.OK){
            DeleteData.deleteVe(Controller.cb.getMaChuyenBay(),lb_maghe.getText());
            listGhe.get(Integer.parseInt(lb_maghe.getText())-1).setDisable(true);
            listGhe.get(Integer.parseInt(lb_maghe.getText())-1).setStyle("-fx-background-color: #f0f0f0;");

            if(Controller.cb.getTrangThai() == ChuyenBay.HETVE){
                UpdateData.capNhatConVe(Controller.cb.getMaChuyenBay());
            }
        }
    }


    }

