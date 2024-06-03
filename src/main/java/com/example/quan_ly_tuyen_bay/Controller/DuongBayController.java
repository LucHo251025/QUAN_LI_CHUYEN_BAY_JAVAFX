package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.SanBay;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DuongBayController implements Initializable {
    private static DuongBayController instance;

    // Phương thức getInstance trả về tham chiếu đến controller
    public static DuongBayController getInstance() {
        return instance;
    }
    public DuongBayController(){
        instance =this;
    }
    @FXML
    private Button bt_quayve;

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
    private ComboBox<String> cb_sanbayden;

    @FXML
    private ComboBox<String> cb_sanbaydi;

    @FXML
    private TableView<DuongBay> tb_View;

    @FXML
    private TableColumn<DuongBay,Integer> tb_khoangcach;

    @FXML
    private TableColumn<DuongBay,String> tb_maduongbay;

    @FXML
    private TableColumn<DuongBay, String> tb_sanbayden;

    @FXML
    private TableColumn<DuongBay,String> tb_sanbaydi;

    @FXML
    private TextField txt_khoangcach;
    private ObservableList<DuongBay> observableList;
    private Alert alert;

    private String maduongbay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
//        LoadData.loadTableSanBay();
        btn_them.setDisable(false);
        btn_xoa.setDisable(false);
        btn_sua.setDisable(false);
        btn_luu.setDisable(true);
        btn_huy.setDisable(true);
        cb_sanbayden.setDisable(true);
        cb_sanbaydi.setDisable(true);
        txt_khoangcach.setDisable(true);

            for (SanBay sb : Controller.sanBayArrayList) {
                cb_sanbaydi.getItems().add(sb.getMaSanBay() + "-" + sb.getTenSanBay());
                cb_sanbayden.getItems().add(sb.getMaSanBay() + "-" + sb.getTenSanBay());
            }


    }

    public void showData() {
        LoadData.loadTableSanBay();
        LoadData.loadTableDuongBay();

        tb_maduongbay.setCellValueFactory(new PropertyValueFactory<>("maDuongBay"));
        tb_sanbaydi.setCellValueFactory(new PropertyValueFactory<>("maSanBayDi"));
        tb_sanbayden.setCellValueFactory(new PropertyValueFactory<>("maSanBayDen"));
        tb_khoangcach.setCellValueFactory(new PropertyValueFactory<>("khoangCach"));

        observableList= FXCollections.observableArrayList(Controller.duongBayArrayList);
        tb_View.setItems(observableList);
        tb_View.refresh();
    }

    public void clearText(){
        txt_khoangcach.setText("");
        cb_sanbayden.setValue("");
        cb_sanbaydi.setValue("");

    }

    public void click(){
        DuongBay duongBay =tb_View.getSelectionModel().getSelectedItem();
        int num = tb_View.getSelectionModel().getFocusedIndex();

        if(num < 0){
            return;
        }
        cb_sanbaydi.setValue(duongBay.getMaSanBayDi());
        cb_sanbayden.setValue(duongBay.getMaSanBayDen());
        txt_khoangcach.setText(String.valueOf(duongBay.getKhoangCach()));
        this.maduongbay=duongBay.getMaDuongBay();
    }
    @FXML
    void ADD(ActionEvent event) {
        btn_them.setDisable(false);
        btn_xoa.setDisable(true);
        btn_sua.setDisable(true);
        btn_luu.setDisable(false);
        btn_huy.setDisable(false);
        cb_sanbayden.setDisable(false);
        cb_sanbaydi.setDisable(false);
        txt_khoangcach.setDisable(false);
    }

    @FXML
    void CANCEL(ActionEvent event) {
        btn_them.setDisable(false);
        btn_xoa.setDisable(false);
        btn_sua.setDisable(false);
        btn_luu.setDisable(true);
        btn_huy.setDisable(true);
        cb_sanbayden.setDisable(true);
        cb_sanbaydi.setDisable(true);
        txt_khoangcach.setDisable(true);

        clearText();

    }

    @FXML
    void DELETE(ActionEvent event) {
        if(tb_View.getSelectionModel().getFocusedIndex() == -1){
            notification("Vui lòng chọn đường bay");
            return;
        }

        for (ChuyenBay cb : Controller.chuyenBayArrayList){
            if(cb.getDuongBay().getMaDuongBay().equals(maduongbay)){
                notification("Đường bay không thể xóa");
            }
        }

        alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn có chắc muốn xóa đường bay "+maduongbay+" này không ?");

        Optional<ButtonType> optional = alert.showAndWait();

        if(optional.get() == ButtonType.OK){
            DeleteData.deleteDuongBay(maduongbay);
            showData();
            clearText();
        }else if(optional.get() == ButtonType.CANCEL){
            clearText();
        }
    }

    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }
    @FXML
    void EDIT(ActionEvent event) {
        if(tb_View.getSelectionModel().getFocusedIndex() == -1){
            notification("Vui lòng chọn đường bay");
        }else {
            btn_them.setDisable(true);
            btn_xoa.setDisable(true);
            btn_sua.setDisable(false);
            btn_luu.setDisable(false);
            btn_huy.setDisable(false);
            cb_sanbayden.setDisable(true);
            cb_sanbaydi.setDisable(true);
            txt_khoangcach.setDisable(false);
        }
    }

    @FXML
    void EXIT(ActionEvent event) {
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

    @FXML
    void SAVE(ActionEvent event) {

        for (int i = 0;i < txt_khoangcach.getText().length();i++){
            if(Character.isDigit(txt_khoangcach.getText().charAt(i))){
                continue;
            }else {
                notification("Thông tinn đường bay không hợp lệ");
                return;
            }
        }

        if(txt_khoangcach.getText().equals("")){
            notification("Vui lòng nhập đầy đủ thông tin");
        }else {
            String sb1 =cb_sanbaydi.getValue().split("-")[0];
            String sb2 =cb_sanbayden.getValue().split("-")[0];
            System.out.println(sb1+sb2);
            DuongBay db = new DuongBay(sb1.concat(sb2),sb1,sb2,Integer.parseInt(txt_khoangcach.getText()));
            System.out.println(db.toString());
            if(tb_View.getSelectionModel().getSelectedIndex() == -1){

                for (DuongBay dbb : Controller.duongBayArrayList){
                    if (dbb.getMaDuongBay().equals(sb1+sb2)){
                        notification("Đường bay đã tồn tại");
                        return;
                    }

                }

                if(sb1.equals(sb2)){
                    notification("Đường bay không hợp lệ");
                }else {
                    InsertData.insertDuongBay(db);
                }
            }else {
                UpdateData.updateDuongBay(db);
            }

//            showData();

        }
    }


}
