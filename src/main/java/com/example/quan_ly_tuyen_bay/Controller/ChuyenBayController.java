package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChuyenBayController implements Initializable {

    @FXML
    private Button bt_huy;

    @FXML
    private Button bt_huychuyen;

    @FXML
    private Button bt_luu;

    @FXML
    private Button bt_quayve;

    @FXML
    private Button bt_sua;

    @FXML
    private Button bt_them;

    @FXML
    private ComboBox<?> cb_gio;

    @FXML
    private ComboBox<?> cm_phut;

    @FXML
    private DatePicker date_ngaybay;


    @FXML
    private RadioButton radio_con;

    @FXML
    private RadioButton radio_het;

    @FXML
    private RadioButton radio_hoantat;

    @FXML
    private RadioButton radio_huy;

    @FXML
    private Button tb_dsve;
    @FXML
    private TableView<ChuyenBay> home_tbview;

    @FXML
    private TableColumn<ChuyenBay, String> tb_duongbay;
    @FXML
    private TableColumn<ChuyenBay, String> tb_giobay;
    @FXML
    private TableColumn<ChuyenBay, String> tb_machuyenbay;

    @FXML
    private TableColumn<ChuyenBay, String> tb_shmb;

    @FXML
    private TableColumn<ChuyenBay, String> tb_thoigian;

    @FXML
    private ComboBox<String> cb_duongbay;

    @FXML
    private TextField txt_machuyenbay;

    @FXML
    private ComboBox<String> cb_shmb;
    private int cheDo;
    private ObservableList<ChuyenBay> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData.loadTableMayBay();
        LoadData.loadTableDuongBay();
        LoadData.loadTableChuyenBay();

        cheDo=0;

        showData();

        for(MayBay mb : Controller.mayBayArrayList){
            cb_shmb.getItems().add(mb.getSHMB().trim());
        }

        for(DuongBay db : Controller.duongBayArrayList){
            cb_duongbay.getItems().add(db.getMaSanBayDi()+" -> "+db.getMaSanBayDen());
        }

    }
    public void showData(){
        new LoadData();
        ArrayList<ChuyenBay> list =new ArrayList<>();
        for (ChuyenBay cb : Controller.chuyenBayArrayList){
            if(cb.getTrangThai() == this.cheDo){
                list.add(cb);
            }
        }
        tb_machuyenbay.setCellValueFactory(new PropertyValueFactory<>("maChuyenBay"));
        tb_shmb.setCellValueFactory(new PropertyValueFactory<>("SHMB"));
//        tb_duongbay.setCellValueFactory(new PropertyValueFactory<>("duongBay"));
        tb_duongbay.setCellValueFactory(cellData -> {
            ChuyenBay chuyenBay = cellData.getValue();
            DuongBay duongBay = chuyenBay.getDuongBay(); // Lấy đối tượng DuongBay từ ChuyenBay
            return new SimpleStringProperty(duongBay.getMaSanBayDi() + " -> " + duongBay.getMaSanBayDen()); // Trả về mã sân bay đi và mã sân bay đến
        });
        tb_thoigian.setCellValueFactory(new PropertyValueFactory<>("ngayBay"));
        tb_giobay.setCellValueFactory(new PropertyValueFactory<>("gioBay"));

        observableList= FXCollections.observableArrayList(list);
        home_tbview.setItems(observableList);

    }

}
