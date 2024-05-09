package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DataConnection;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;



public class DangNhapController implements Initializable {


    @FXML
    private Button buttondk;

    @FXML
    private Button buttondn;

    @FXML
    private CheckBox hienmk;

    @FXML
    private Label lbl;

    @FXML
    private TextField textmk;

    @FXML
    private TextField textten;

    private Alert alert;

    @FXML
    void dangky(ActionEvent event) {

    }

    @FXML
    void handleLogin(ActionEvent event) {
    String name= textten.getText();
    String pass= textmk.getText();
        LoadData.loadTableTaiKhoan();
        int index=-1;
        for(TaiKhoan tk:Controller.taiKhoanArrayList){
            if(name.equals(tk.getTenDangNhap().trim())){
                index=Controller.taiKhoanArrayList.indexOf(tk);

            }

        }
        if(index==-1){
            notification("Tài Khoản Không tồn Tại");
        }else {
            TaiKhoan tk=Controller.taiKhoanArrayList.get(index);
            if(tk.getMatKhau().equals(pass)){
                Controller.tk=tk;

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }

            }else {
                notification("Sai mật khẩu");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

}
