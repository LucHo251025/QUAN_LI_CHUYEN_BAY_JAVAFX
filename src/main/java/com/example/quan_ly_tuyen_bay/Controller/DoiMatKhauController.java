package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
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

import java.io.IOException;
import java.net.URL;
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
    @FXML
    void CANCEL(ActionEvent event) {
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
    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }
    @FXML
    void SAVE(ActionEvent event) {
        if(mk_cu.getText().equals("") || mk_nhaplai.getText().equals("") || mk_moi.getText().equals("")){
            notification("Vui lòng nhập đầy đủ thông tin");
        }else if(mk_cu.getText().equals(Controller.tk.getMatKhau())== false){
            notification("Mật khẩu hiện tại không chính xác");
        }else if(!(mk_moi.getText().equals(mk_nhaplai))== false){
            notification("Xác thực mật khẩu không chính xác");
        }else  {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Xác nhận đổi mật khẩu");

            Optional<ButtonType> optional = alert.showAndWait();

            if(optional.get() == ButtonType.OK){
                UpdateData.doiMatKhau(Controller.tk.getTenDangNhap(),mk_moi.getText());
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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
