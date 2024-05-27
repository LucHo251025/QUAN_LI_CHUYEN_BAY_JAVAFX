package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.NhanVien;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NhanVienController implements Initializable {
    private static NhanVienController instance;
    public static NhanVienController getInstance(){
         return instance;
    }

    public NhanVienController(){
        instance = this;
    }
    @FXML
    private Button btn_huy;

    @FXML
    private Button btn_luu;

    @FXML
    private Button btn_sua;

    @FXML
    private Button btn_them;

    @FXML
    private Button btn_xoa;

    @FXML
    private TableView<NhanVien> tb_View;

    @FXML
    private TableColumn<NhanVien, Integer> tb_luong;

    @FXML
    private TableColumn<NhanVien, String> tb_sdt;

    @FXML
    private TableColumn<NhanVien, String> tb_tenDangNhap;

    @FXML
    private TableColumn<NhanVien, String> tb_tenNhanVien;

    @FXML
    private TextField txt_luong;

    @FXML
    private TextField txt_sdt;

    @FXML
    private TextField txt_tenDN;

    @FXML
    private TextField txt_tenNV;
    private ObservableList<NhanVien> observableList;
    private Alert alert;

    public void showData(){
        LoadData.loadTableNhanVien();
        tb_luong.setCellValueFactory(new PropertyValueFactory<>("luong"));
        tb_sdt.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        tb_tenDangNhap.setCellValueFactory(new PropertyValueFactory<>("tendn"));
        tb_tenNhanVien.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));

        observableList = FXCollections.observableArrayList(Controller.nhanVienArrayList);
        tb_View.setItems(observableList);
    }

    private void clearText(){
        txt_tenNV.setText("");
        txt_tenDN.setText("");
        txt_sdt.setText("");
        txt_luong.setText("");
    }
    @FXML
    void ADD(ActionEvent event) {

        btn_sua.setDisable(true);
        btn_xoa.setDisable(true);
        btn_huy.setDisable(false);
        btn_luu.setDisable(false);
        txt_luong.setDisable(false);
        txt_sdt.setDisable(false);
        txt_tenDN.setDisable(false);
        txt_tenNV.setDisable(false);
    }

    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }
    @FXML
    void CANCEL(ActionEvent event) {
        btn_them.setDisable(false);
        btn_sua.setDisable(false);
        btn_xoa.setDisable(false);
        btn_huy.setDisable(true);
        btn_luu.setDisable(true);
        txt_luong.setDisable(true);
        txt_sdt.setDisable(true);
        txt_tenDN.setDisable(true);
        txt_tenNV.setDisable(true);
        clearText();
    }

    @FXML
    void DELETE(ActionEvent event) {
        if(tb_View.getSelectionModel().getSelectedIndex() == -1){
            notification("Vui long chọn nhân viên");
        }else {
            alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn có chắc muốn xóa nhân viên "+txt_tenNV.getText()+" này không ?");

            Optional<ButtonType> optional = alert.showAndWait();

            if(optional.get() == ButtonType.OK){
                DeleteData.deleteNhanVien(txt_sdt.getText());
                DeleteData.deleteTaiKhoan(txt_tenDN.getText());
                clearText();
            }else if(optional.get() == ButtonType.CANCEL){
                clearText();
            }

        }

    }

    @FXML
    void EDIT(ActionEvent event) {
        btn_them.setDisable(true);
        btn_xoa.setDisable(true);
        btn_huy.setDisable(false);
        btn_luu.setDisable(false);
        txt_luong.setDisable(false);

    }

    @FXML
    void EXIT(ActionEvent event) {
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

    @FXML
    void SAVE(ActionEvent event) {
        for (int i = 0 ; i < txt_luong.getText().length(); i++){
            if(Character.isDigit(txt_luong.getText().charAt(i))){
                continue;
            }else {
                notification("Thông tin về lương không đúng");
                return;
            }
        }

        if(txt_sdt.getText().length() != 10){
            notification("Thông tin về điện thoại không hợp lệ");
            return;
        }else {
            for (int i = 0;i < txt_sdt.getText().length() ; i++){
                if(Character.isDigit(txt_sdt.getText().charAt(i))){
                    continue;
                }else {
                    notification("Thông tin về số điện thoại không hợp lệ");
                    return;
                }
            }
        }

        if(txt_sdt.getText().equals("") ||txt_tenDN.getText().equals("") ||txt_luong.getText().equals("") ||txt_tenNV.getText().equals("") ){
            notification("Vui lòng nhập đầy đủ thông tin");
        }else {
            NhanVien nv = new NhanVien(txt_sdt.getText(),txt_tenDN.getText(),txt_tenNV.getText(),Integer.parseInt(txt_luong.getText()));
            TaiKhoan tk = new TaiKhoan(txt_tenDN.getText(),txt_sdt.getText(),"nhanvien");

            if(tb_View.getSelectionModel().getSelectedIndex() == -1){

                for (NhanVien nvv : Controller.nhanVienArrayList){
                    if(nvv.getSdt().equals(txt_sdt.getText())){
                        notification("Nhân viên đã tồn tại");
                        return;
                    }
                }

                for (TaiKhoan tkk : Controller.taiKhoanArrayList){
                    if(tkk.getTenDangNhap().equals(txt_tenDN.getText())){
                        notification("Tài khoản đã tồn tại");
                        return;
                    }
                }

                InsertData.inserTaiKhoan(tk);
                InsertData.inserNhanVien(nv);

            }else {
                UpdateData.updateNhanvien(nv);
            }

            btn_them.setDisable(false);
            btn_sua.setDisable(false);
            btn_xoa.setDisable(false);
            btn_huy.setDisable(true);
            btn_luu.setDisable(true);
            txt_luong.setDisable(true);
            txt_sdt.setDisable(true);
            txt_tenDN.setDisable(true);
            txt_tenNV.setDisable(true);
            clearText();
        }
    }

    @FXML
    void clickTable(MouseEvent event) {
        NhanVien nv = tb_View.getSelectionModel().getSelectedItem();
        int num = tb_View.getSelectionModel().getSelectedIndex();
        if((num) < 0){
            return;
        }

        txt_tenDN.setText(nv.getTendn());
        txt_luong.setText(String.valueOf(nv.getLuong()));
        txt_sdt.setText(nv.getSdt());
        txt_tenNV.setText(nv.getTenNhanVien());

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_them.setDisable(false);
        btn_sua.setDisable(false);
        btn_xoa.setDisable(false);
        btn_huy.setDisable(true);
        btn_luu.setDisable(true);
        txt_luong.setDisable(true);
        txt_sdt.setDisable(true);
        txt_tenDN.setDisable(true);
        txt_tenNV.setDisable(true);

        showData();
    }
}
