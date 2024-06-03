package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.SanBay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import java.awt.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


public class TimKiemController implements Initializable {
    @FXML
    private ComboBox<String> bt_tre;

    @FXML
    private ComboBox<String> cb_den;

    @FXML
    private ComboBox<String> cb_khoihanh;

    @FXML
    private ComboBox<String> cb_lon;

    @FXML
    private DatePicker date_ngay;

    @FXML
    public Button bt_thoat;

    @FXML
    private Button bt_timkiem;

    private Alert alert;

    private LocalDate dateNow;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData.loadTableChuyenBay();
        dateNow = LocalDate.now();
        new LoadData();

        for (SanBay sb : Controller.sanBayArrayList){
            cb_khoihanh.getItems().add(sb.getMaSanBay().trim()+ "-"+sb.getTenSanBay());
            cb_den.getItems().add(sb.getMaSanBay().trim()+ "-"+sb.getTenSanBay());
        }
        for (int i=1;i<50;i++){
            cb_lon.getItems().add(String.valueOf(i));
        }

        for (int i=1;i<50;i++){
            bt_tre.getItems().add(String.valueOf(i));
        }
    }

    public void EXIT(ActionEvent event){
        if(event.getSource() == bt_thoat){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }
    public void SEARCH(ActionEvent event){
        if(event.getSource() == bt_timkiem){
            String sanBayDi = cb_khoihanh.getValue().split("-")[0];
            String sanBayDen = cb_den.getValue().split("-")[0];

            if(sanBayDi.equals(sanBayDen)){
                notification("Sân bây đi và sân bây đến không được trùng nhau");
                return;
            }

            LocalDate date = date_ngay.getValue();
            String dateString =String.valueOf(date);
            if(date.isBefore(dateNow)){
                notification("Ngày đi không hợp lệ");
                return;
            }

            String maDuongBay =sanBayDi+sanBayDen;
            System.out.println(maDuongBay);
            int soGheNguoiLon = 0;
            int soGheTreEm= 0;
            if(cb_lon.getValue() != null && bt_tre.getValue() != null){
                soGheNguoiLon = Integer.parseInt(cb_lon.getValue());
                soGheTreEm = Integer.parseInt(bt_tre.getValue());
            }else if(cb_lon.getValue() != null && bt_tre.getValue() == null){
                soGheNguoiLon = Integer.parseInt(cb_lon.getValue());
                soGheTreEm = 0;
            }else if(cb_lon.getValue() == null && bt_tre.getValue() != null){
                soGheNguoiLon = 0;
                soGheTreEm = Integer.parseInt(bt_tre.getValue());
            }else if(cb_lon.getValue() == null && bt_tre.getValue() == null){
                soGheNguoiLon = 0;
                soGheTreEm = 0;
            }




            Controller.soLuongVeChon = soGheNguoiLon+soGheTreEm;
            Controller.timchuyenBayArrayList.clear();

            for (ChuyenBay cb : Controller.chuyenBayArrayList){
                System.out.println(cb.getTrangThai()+"="+ChuyenBay.CONVE);
                System.out.println(cb.getDuongBay().getMaDuongBay()+"="+maDuongBay);
                System.out.println(cb.getNgayBay()+"="+date);
                if(cb.getTrangThai() == ChuyenBay.CONVE && cb.getDuongBay().getMaDuongBay().equals(maDuongBay) && String.valueOf(cb.getNgayBay()).equals(dateString) ){
                    System.out.println("phù hợp");
                    if(cb.getSoGheTrong() < (soGheNguoiLon+ soGheTreEm)){
                        notification("Không đủ ghế");
                    }else {
                        Controller.timchuyenBayArrayList.add(cb);
                    }
                }
            }

            for (ChuyenBay cb : Controller.timchuyenBayArrayList){
                System.out.println(cb.toString());
            }

            if(!Controller.timchuyenBayArrayList.isEmpty()){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienChonChuyenBay.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }else {
                notification("Không có chuyến bay phù hợp");
            }
        }
    }

}
