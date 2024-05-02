package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MayBayController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData.loadTableMayBay();
        showDataMayBay();
    }


    public void showDataMayBay(){

        tb_shmb.setCellValueFactory(new PropertyValueFactory<>("SHMB"));
        tb_hangbay.setCellValueFactory(new PropertyValueFactory<>("hangBay"));
        tb_soghe.setCellValueFactory(new PropertyValueFactory<>("soGhe"));

        observableList = FXCollections.observableArrayList(Controller.mayBayArrayList);
        tb_View.setItems(observableList);

    }
}
