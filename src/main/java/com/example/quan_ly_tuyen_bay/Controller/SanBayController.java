package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import com.example.quan_ly_tuyen_bay.Model.SanBay;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SanBayController implements Initializable {
    private static SanBayController instance;

    // Phương thức getInstance trả về tham chiếu đến controller
    public static SanBayController getInstance() {
        return instance;
    }
    public SanBayController(){
        instance=this;
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
    private Button bt_quayve;
    @FXML
    private TableView<SanBay> tb_View;

    @FXML
    private TableColumn<SanBay, String> tb_diaDiem;

    @FXML
    private TableColumn<SanBay, String> tb_maSanBay;

    @FXML
    private TableColumn<SanBay, String> tb_tenSanBay;

    @FXML
    private TextField txt_diaDiem;

    @FXML
    private TextField txt_tensanbay;

    @FXML
    private TextField txt_maSanBay;
    private Alert alert;


    private ObservableList<SanBay> observableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       showData();

       txt_diaDiem.setDisable(true);
       txt_maSanBay.setDisable(true);
       txt_tensanbay.setDisable(true);
       btn_huy.setDisable(true);
       btn_luu.setDisable(true);
       btn_them.setDisable(false);
       btn_xoa.setDisable(false);
       btn_sua.setDisable(false);
    }
    public void showData(){
        LoadData.loadTableSanBay();
        tb_maSanBay.setCellValueFactory(new PropertyValueFactory<>("maSanBay"));
        tb_tenSanBay.setCellValueFactory(new PropertyValueFactory<>("tenSanBay"));
        tb_diaDiem.setCellValueFactory(new PropertyValueFactory<>("diaDiem"));

        observableList = FXCollections.observableArrayList(Controller.sanBayArrayList);
        tb_View.setItems(observableList);
        tb_View.refresh();
    }

    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

    public void click(){
        SanBay sb=tb_View.getSelectionModel().getSelectedItem();
        int num=tb_View.getSelectionModel().getSelectedIndex();

        if((num) < 0){
            return;
        }

        txt_maSanBay.setText(sb.getMaSanBay());
        txt_tensanbay.setText(sb.getTenSanBay());
        txt_diaDiem.setText(sb.getDiaDiem());
    }

    public void ADD(ActionEvent event){
        if(event.getSource() == btn_them){
            txt_diaDiem.setDisable(false);
            txt_maSanBay.setDisable(false);
            txt_tensanbay.setDisable(false);
            btn_huy.setDisable(false);
            btn_luu.setDisable(false);
            btn_them.setDisable(false);
            btn_xoa.setDisable(true);
            btn_sua.setDisable(true);
        }
    }

    public void EDIT(ActionEvent event){
        if(event.getSource() == btn_sua){


            if(tb_View.getSelectionModel().getSelectedIndex() == -1){
                notification("Vui lòng chọn sân bây");
            }else {
                txt_diaDiem.setDisable(false);
                txt_maSanBay.setDisable(true);
                txt_tensanbay.setDisable(false);
                btn_huy.setDisable(false);
                btn_luu.setDisable(false);
                btn_them.setDisable(true);
                btn_xoa.setDisable(true);
                btn_sua.setDisable(false);
            }
        }
    }

    public void DELETE(ActionEvent event){
        if(event.getSource() == btn_xoa){
            if(tb_View.getSelectionModel().getSelectedIndex() == -1){
                notification("Vui lòng chọn sân bây");
                return;
            }

            for (DuongBay db : Controller.duongBayArrayList){
                if(db.getMaSanBayDi().equals(txt_maSanBay.getText()) || db.getMaSanBayDen().equals(txt_maSanBay.getText())){
                    notification("Không thể xóa sân bay này");
                    return;
                }
            }

            alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn có chắc muốn xóa máy bay "+txt_maSanBay.getText()+" này không ?");

            Optional<ButtonType> optional = alert.showAndWait();

            if(optional.get() == ButtonType.OK){
                DeleteData.deleteSanBay(txt_maSanBay.getText());
                clearText();
                txt_diaDiem.setDisable(true);
                txt_maSanBay.setDisable(true);
                txt_tensanbay.setDisable(true);
                btn_huy.setDisable(true);
                btn_luu.setDisable(true);
                btn_them.setDisable(false);
                btn_xoa.setDisable(false);
                btn_sua.setDisable(false);
            }else if(optional.get() == ButtonType.CANCEL){
                clearText();
            }
        }
    }

    public void SAVE(ActionEvent event){
        if(txt_maSanBay.getText().equals("") || txt_tensanbay.getText().equals("") || txt_diaDiem.getText().equals("")){
            notification("Thông tin không được để trống");
            return;
        }
        if(txt_maSanBay.getText().length() != 3){
            notification("Mã sân bây không hợp lệ");
            return;
        }

        txt_maSanBay.setText(txt_maSanBay.getText().toUpperCase());
        SanBay sb = new SanBay(txt_maSanBay.getText(),txt_tensanbay.getText(),txt_diaDiem.getText());

        if(tb_View.getSelectionModel().getSelectedIndex() == -1){

            for(SanBay sbb :Controller.sanBayArrayList){
                if(sbb.getMaSanBay().equals(sb.getMaSanBay())){
                    notification("Mã sân bay đã tồn tại");
                    return;
                }
            }
            InsertData.insertSanBay(sb);
        }else {
            UpdateData.updateSanBay(sb);
        }
        clearText();

        txt_diaDiem.setDisable(true);
        txt_maSanBay.setDisable(true);
        txt_tensanbay.setDisable(true);
        btn_huy.setDisable(true);
        btn_luu.setDisable(true);
        btn_them.setDisable(false);
        btn_xoa.setDisable(false);
        btn_sua.setDisable(false);
    }

    public void CANCEL(ActionEvent event){
        if (event.getSource() == btn_huy){
            txt_diaDiem.setDisable(true);
            txt_maSanBay.setDisable(true);
            txt_tensanbay.setDisable(true);
            btn_huy.setDisable(true);
            btn_luu.setDisable(true);
            btn_them.setDisable(false);
            btn_xoa.setDisable(false);
            btn_sua.setDisable(false);
        }
    }

    private void clearText() {
        txt_maSanBay.setText("");
        txt_tensanbay.setText("");
        txt_diaDiem.setText("");
    }
    public void EXIT(ActionEvent event){
        if(event.getSource() == bt_quayve){
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
