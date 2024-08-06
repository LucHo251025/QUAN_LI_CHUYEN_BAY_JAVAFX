package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.example.quan_ly_tuyen_bay.Server.Repository.AES;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;

import javax.swing.plaf.TableUI;
import java.net.URL;

import java.security.NoSuchAlgorithmException;
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
    private PasswordField textmk;
    @FXML
    private TextField textten;
    private Alert alert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoadData.loadTableTaiKhoan();

        for(TaiKhoan tk : Controller.taiKhoanArrayList){
            System.out.println(tk.getTenDangNhap()+"  "+tk.getMatKhau());
        }

    }
    @FXML
    void dangky(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/DangKy.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleLogin(ActionEvent event) throws NoSuchAlgorithmException {
    String name= textten.getText();
    String pass= textmk.getText();

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

            String mhMK=tk.getMatKhau();
            if(mhMK.equals(pass)){
                Controller.tk=tk;

                System.out.println(Controller.tk.toString());
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));

                        stage.setScene(scene);
                        stage.show();

                    }  catch (Exception e) {
                        e.printStackTrace();
                    }

            }else {
                notification("Sai mật khẩu");
            }
        }
    }
    @FXML
    void handleHienMK(ActionEvent event) {
        if (hienmk.isSelected()) {
            String password = textmk.getText();
            // Change PasswordField to TextField
            TextField textField = new TextField(password);
            textField.setLayoutX(textmk.getLayoutX());
            textField.setLayoutY(textmk.getLayoutY());
            textField.setPrefWidth(textmk.getPrefWidth());
            textField.setPrefHeight(textmk.getPrefHeight());
            textField.setStyle(textmk.getStyle());
            // Replace PasswordField with TextField
            AnchorPane parent = (AnchorPane) textmk.getParent();
            parent.getChildren().remove(textmk);
            parent.getChildren().add(textField);
        } else {
            String password = textmk.getText();
            // Change TextField back to PasswordField
            PasswordField passwordField = new PasswordField();
            passwordField.setText(password);
            passwordField.setLayoutX(textmk.getLayoutX());
            passwordField.setLayoutY(textmk.getLayoutY());
            passwordField.setPrefWidth(textmk.getPrefWidth());
            passwordField.setPrefHeight(textmk.getPrefHeight());
            passwordField.setStyle(textmk.getStyle());
            // Replace TextField with PasswordField
            AnchorPane parent = (AnchorPane) textmk.getParent();
            if (parent == null) {
                System.out.println("Parent is null");
                // Initialize parent or handle the null case appropriately
                return;
            }
            parent.getChildren().remove(textmk);
            parent.getChildren().add(passwordField);
            parent.getChildren().clear();
        }
    }




    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

}
