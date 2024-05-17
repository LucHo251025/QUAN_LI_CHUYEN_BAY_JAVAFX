package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.NhanVien;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DangKyController {
    private Alert alert;

    @FXML
    private Button btndk;

    @FXML
    private CheckBox ckbhienmk;

    @FXML
    private CheckBox ckbnhanvien;

    @FXML
    private CheckBox ckbquanli;

    @FXML
    private TextField txt_hoten;

    @FXML
    private PasswordField txt_mk;

    @FXML
    private TextField txt_sdt;

    @FXML
    private TextField txt_tendn;

    @FXML
    private PasswordField txt_xacthucmk;
    private String loaiTaiKhoan;

    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }
    private boolean checkIP(){
        String name =txt_tendn.getText();
        if(txt_hoten.getText().length()==0){
            notification("họ tên không được để trống");
            return false;

        }
        String str=txt_sdt.getText();
        if(str.length()!=10){
            notification("số điện thoại khong hợp lệ");
            return false;
        }
        // hàm kiểm tra xem trong số đt có chữ hay không
        for (int i=0; i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))){
                notification("số điện thoại không hợp lệ");
                return false;
            }
        }
        if (name.length()==0){
            notification("ten đăng nhập không được để trống");
            return false;
        }
        new LoadData();
        for (TaiKhoan tk: Controller.taiKhoanArrayList)
            if(name.equals(tk.getTenDangNhap().trim())){
                notification("tên đăng nhập đac tồn tại");
                return false;
            }
        if(txt_mk.getText().length()==0){
            notification("Mật Khẩu không được để trống");
            return false;

        }
        if(!txt_xacthucmk.getText().equals(txt_mk.getText())){
            notification("Xác thực mật khẩu không trùng khớp");
            return false;
        }


        return true;
    }

    public void DangKy(ActionEvent event ){
        if(event.getSource()==btndk){
            if(checkIP()){

                if(ckbnhanvien.isSelected()){
                    ckbquanli.setSelected(false);
                    loaiTaiKhoan = "nhanvien";
                }else if(ckbquanli.isSelected()){
                    ckbnhanvien.setSelected(false);
                    loaiTaiKhoan = "quanli";
                }


                TaiKhoan tk =new TaiKhoan(txt_tendn.getText(),txt_mk.getText(),loaiTaiKhoan);
                NhanVien nv= new NhanVien(txt_sdt.getText(), txt_tendn.getText(),txt_hoten.getText(),0);

                InsertData.inserTaiKhoan(tk);
                InsertData.inserNhanVien(nv);

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/DangNhap.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }


            }
        }
    }
    @FXML
    void initialize() {
        ckbhienmk.setOnAction(event -> {
            if (ckbhienmk.isSelected()) {
                txt_mk.setVisible(false); // Hide PasswordField for password
                txt_xacthucmk.setVisible(false); // Hide PasswordField for password verification
                txt_mk.setManaged(false);
                txt_xacthucmk.setManaged(false);

                TextField txt_mk_visible = new TextField(txt_mk.getText()); // Create TextField for password
                txt_mk_visible.setPromptText("Mật khẩu");
                txt_mk_visible.setLayoutX(txt_mk.getLayoutX());
                txt_mk_visible.setLayoutY(txt_mk.getLayoutY());
                txt_mk_visible.setPrefWidth(txt_mk.getPrefWidth());
                txt_mk_visible.setPrefHeight(txt_mk.getPrefHeight());
                txt_mk_visible.setStyle(txt_mk.getStyle());

                TextField txt_xacthucmk_visible = new TextField(txt_xacthucmk.getText()); // Create TextField for password verification
                txt_xacthucmk_visible.setPromptText("Xác thực mật khẩu");
                txt_xacthucmk_visible.setLayoutX(txt_xacthucmk.getLayoutX());
                txt_xacthucmk_visible.setLayoutY(txt_xacthucmk.getLayoutY());
                txt_xacthucmk_visible.setPrefWidth(txt_xacthucmk.getPrefWidth());
                txt_xacthucmk_visible.setPrefHeight(txt_xacthucmk.getPrefHeight());
                txt_xacthucmk_visible.setStyle(txt_xacthucmk.getStyle());

                AnchorPane parent = (AnchorPane) txt_mk.getParent();
                parent.getChildren().addAll(txt_mk_visible, txt_xacthucmk_visible); // Add TextFields to the layout
            } else {
                // Remove TextFields and show PasswordFields again
                AnchorPane parent = (AnchorPane) txt_mk.getParent();
                parent.getChildren().removeIf(node -> node instanceof TextField);
                txt_mk.setVisible(true);
                txt_mk.setManaged(true);
                txt_xacthucmk.setVisible(true);
                txt_xacthucmk.setManaged(true);
            }
        });
    }
    @FXML
    void dangnhap(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/DangNhap.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
