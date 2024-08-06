package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.example.quan_ly_tuyen_bay.Server.Repository.AES;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoiMatKhauController implements Initializable {
    @FXML
    private PasswordField mk_cu;

    @FXML
    private PasswordField mk_moi;

    @FXML
    private PasswordField mk_nhaplai;

    private Alert alert;
    @FXML
    private Button bt_cancel;

    @FXML
    private Button bt_save;

    private static DoiMatKhauController instance;

    // Phương thức getInstance trả về tham chiếu đến controller
    public static DoiMatKhauController getInstance() {
        return instance;
    }
    public DoiMatKhauController(){
        instance =this;
    }

    @FXML
    void CANCEL(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



    public void LoadTaiKhoan(){
        LoadData.loadTableTaiKhoan();
//        int index=-1;
//        for(TaiKhoan tk:Controller.taiKhoanArrayList){
//            if(Controller.tk.getTenDangNhap().equals(tk.getTenDangNhap().trim())){
//                index=Controller.taiKhoanArrayList.indexOf(tk);
//            }
//        }
//
//        TaiKhoan tk=Controller.taiKhoanArrayList.get(index);
//        Controller.tk=tk;

    }


    public void notification(String mes) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

    @FXML
    void SAVE(ActionEvent event) {
        try {
            String mkCu = mk_cu.getText();

            if (mkCu.equals("") || mk_nhaplai.getText().equals("") || mk_moi.getText().equals("")) {
                notification("Vui lòng nhập đầy đủ thông tin");
            } else if (mkCu.equals(Controller.tk.getMatKhau()) == false) {
                notification("Mật khẩu hiện tại không chính xác");
            } else if (!(mk_moi.getText().equals(mk_nhaplai)) == false) {
                notification("Xác thực mật khẩu không chính xác");
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Xác nhận đổi mật khẩu");

                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get() == ButtonType.OK) {

                    try {
                        SecretKey secretKey = AES.getStaticKey();
                        String hashMatkhau = AES.encrypt(mk_moi.getText(), secretKey);
                        UpdateData.doiMatKhau(Controller.tk.getTenDangNhap(), hashMatkhau);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
