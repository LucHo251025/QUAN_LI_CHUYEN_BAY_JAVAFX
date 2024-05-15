package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.example.quan_ly_tuyen_bay.Model.Ve;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
    private Alert alert;
    @FXML
    private TextField txt_cccd;

    @FXML
    private TextField txt_email;

    @FXML
    private DatePicker date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lb_title.setText("Thông tin hành khách " + Controller.dsGheChon.size());
        lb_maghe.setText(Controller.dsGheChon.get(Controller.dsVeChon.size()));
        System.out.println(Controller.dsGheChon.size());
        lb_gia.setText(String.valueOf(Controller.cb.getGia()));
        if((Controller.dsVeChon.size()) == Controller.dsGheChon.size()){
            bt_tieptheo.setText("Đặt vé");
        }
    }

    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

    private boolean checKIP(){
        if(txt_tenkhachhang.getText().equals("")){
            notification("Họ tên không được để trống");
            return false;
        }
        String str = txt_sdt.getText();
        if(str.length() != 10){
            notification("Số điện thoại không hợp lệ");
            return false;
        }

        for (int i=0;i<str.length() ;i++){
            if(!Character.isDigit(str.charAt(i))){
                notification("Số điện thoại không hợp lệ");
                return  false;
            }
        }
        return  true;
    }


    @FXML
    void Next(ActionEvent event) {
        if(checKIP()){
            java.sql.Date myDate = java.sql.Date.valueOf(date.getValue());

            Controller.dsVeChon.add(new Ve(Controller.cb.getMaChuyenBay(),txt_tenkhachhang.getText(),txt_sdt.getText(),Controller.dsGheChon.get(Controller.dsVeChon.size()),Integer.parseInt(lb_gia.getText()),myDate,txt_email.getText(),txt_cccd.getText()));
            if((Controller.dsVeChon.size()) < Controller.dsGheChon.size()){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienThongTinVe.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }else {
                InsertData.insertVe(Controller.dsVeChon);
                if(Controller.cb.getSoGheTrong() == Controller.soLuongVeChon){
                    UpdateData.capNhatHetVe(Controller.cb.getMaChuyenBay());
                }
                Controller.dsVeChon.clear();
                for (TaiKhoan taiKhoan : Controller.taiKhoanArrayList){
                    if(Controller.tk.getTenDangNhap().equals(taiKhoan.getTenDangNhap())){
                        Controller.tk=taiKhoan;
                        break;
                    }
                }
                Controller.soLuongVeChon=-1;
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
            }




    }


}
