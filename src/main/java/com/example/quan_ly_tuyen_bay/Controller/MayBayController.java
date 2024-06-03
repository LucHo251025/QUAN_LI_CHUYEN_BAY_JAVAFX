package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.HelloApplication;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MayBayController implements Initializable {
    private static MayBayController instance;

    // Phương thức getInstance trả về tham chiếu đến controller
    public static MayBayController getInstance() {
        return instance;
    }

    // Hàm khởi tạo của controller
    public MayBayController() {
        instance = this; // Gán tham chiếu đến chính bản thân controller
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
    private TableColumn<MayBay, String> tb_hangbay;

    @FXML
    private TableColumn<MayBay, String> tb_shmb;

    @FXML
    private TableColumn<MayBay, Integer> tb_soghe;

    @FXML
    private TextField txt_hangbay;

    @FXML
    private TextField txt_shmb;
    @FXML
    private TableView<MayBay> tb_View;

    @FXML
    private TextField txt_soghe;
    private ObservableList<MayBay> observableList;
    private Alert alert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showDataMayBay();
        txt_shmb.setDisable(true);
        txt_hangbay.setDisable(true);
        txt_soghe.setDisable(true);
        btn_luu.setDisable(true);
        btn_huy.setDisable(true);
    }
    public void showDataMayBay(){
//        LoadData.loadTableChuyenBay();
        LoadData.loadTableMayBay();
        tb_shmb.setCellValueFactory(new PropertyValueFactory<>("SHMB"));
        tb_hangbay.setCellValueFactory(new PropertyValueFactory<>("hangBay"));
        tb_soghe.setCellValueFactory(new PropertyValueFactory<>("soGhe"));

        observableList = FXCollections.observableArrayList(Controller.mayBayArrayList);
        tb_View.setItems(observableList);
        tb_View.refresh();

    }

    public void clickTable(){
        MayBay mb = tb_View.getSelectionModel().getSelectedItem();
        int num = tb_View.getSelectionModel().getFocusedIndex();

        if((num) < 0){
          return;
        }

        txt_shmb.setText(mb.getSHMB());
        txt_hangbay.setText(mb.getHangBay());
        txt_soghe.setText(String.valueOf(mb.getSoGhe()));

    }
    public void notification(String mes){
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

    public void clearText(){
        txt_shmb.setText("");
        txt_hangbay.setText("");
        txt_soghe.setText("");
    }

    public void ADD(ActionEvent event){
        if(event.getSource() == btn_them){
            clearText();
            txt_shmb.setDisable(false);
            txt_soghe.setDisable(false);
            txt_hangbay.setDisable(false);
            btn_xoa.setDisable(true);
            btn_sua.setDisable(true);
            btn_luu.setDisable(false);
            btn_huy.setDisable(false);

        }
    }

    public void EDIT(ActionEvent event){
        if(event.getSource() == btn_sua){
            if(tb_View.getSelectionModel().getFocusedIndex() == -1){
                notification("Vui long chọn máy bay");
            }else {
                for (ChuyenBay cb : Controller.chuyenBayArrayList){
                    if(cb.getSHMB().equals(txt_shmb.getText())){
                        notification("Không sửa được máy bay này");
                        return;
                    }
                }
                btn_luu.setDisable(false);
                btn_huy.setDisable(false);
                btn_them.setDisable(true);
                btn_xoa.setDisable(true);
                txt_shmb.setDisable(true);
                txt_hangbay.setDisable(true);
                txt_soghe.setDisable(false);
            }
        }
    }

    public void DELETE(ActionEvent event){
        if(event.getSource() == btn_xoa){
            txt_shmb.setDisable(true);
            txt_soghe.setDisable(true);
            txt_hangbay.setDisable(true);


            if(txt_shmb.getText() == null || txt_hangbay.getText() == null ||txt_soghe.getText() == null ){
                notification("Vui lòng chọn máy bay");
            }

            for (ChuyenBay cb : Controller.chuyenBayArrayList){
                if(cb.getSHMB().equals(txt_shmb.getText())){
                    notification("Không sửa được máy bay này");
                    return;
                }
            }

            alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn có chắc muốn xóa máy bay "+txt_shmb.getText()+" này không ?");

            Optional<ButtonType> optional = alert.showAndWait();

            if(optional.get() == ButtonType.OK){
                DeleteData.deleteMayBay(txt_shmb.getText());
                clearText();
            }else if(optional.get() == ButtonType.CANCEL){
                clearText();
            }

//            showDataMayBay();

        }
    }

    public void SAVE(ActionEvent event){
        if(event.getSource() == btn_luu){

            for (int i = 0; i < txt_soghe.getText().length();i++){
                if (Character.isDigit(txt_soghe.getText().charAt(i))){
                    continue;
                }else  {
                    notification("Thông tin máy bay không hợp lệ!");
                    return;
                }
            }

            txt_shmb.setText(txt_shmb.getText().toUpperCase());

            if(txt_shmb.getText() == null || txt_hangbay.getText() == null ||txt_soghe.getText() == null ){
                notification("Vui lòng nhập đầy đủ thông tin máy bay");
            }else {
                MayBay mb = new MayBay(txt_shmb.getText(),txt_hangbay.getText(),Integer.parseInt(txt_soghe.getText()));
                if(tb_View.getSelectionModel().getSelectedIndex() == -1){
                    for (MayBay mbb : Controller.mayBayArrayList){
                        if(mbb.getSHMB().equals(txt_shmb.getText())){
                            notification("Máy bay đã tồn tại");
                            return;
                        }
                    }
                    InsertData.insertMayBay(mb);
                }else {
                    UpdateData.updateMayBay(mb);
                }

//                showDataMayBay();
                clearText();
                txt_hangbay.setDisable(true);
                txt_soghe.setDisable(true);
                txt_shmb.setDisable(true);
                btn_them.setDisable(false);
                btn_sua.setDisable(false);
                btn_xoa.setDisable(false);
            }
        }
    }

    public void CANCEL(ActionEvent event){
        if(event.getSource() == btn_huy){
            txt_hangbay.setDisable(true);
            txt_soghe.setDisable(true);
            txt_shmb.setDisable(true);
            btn_them.setDisable(false);
            btn_sua.setDisable(false);
            btn_xoa.setDisable(false);
        }
    }

    
    public void EXIT(ActionEvent event){
        if(event.getSource() == bt_quayve){
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
                stage.setScene(scene);
                stage.setTitle("Home");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
