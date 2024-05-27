package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.plaf.TableUI;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChonChuyenBayController implements Initializable {
    @FXML
    private Button btn_thoat;

    @FXML
    private TableView<ChuyenBay> tb_View;

    @FXML
    private TableColumn<ChuyenBay, String> tb_duongbay;

    @FXML
    private TableColumn<ChuyenBay, String> tb_machuyenbay;

    @FXML
    private TableColumn<ChuyenBay, String> tb_maybay;

    @FXML
    private TableColumn<ChuyenBay, String> tb_thoigian;
    private ObservableList<ChuyenBay> observableList;

    @FXML
    void EXIT(ActionEvent event) {
        for (TaiKhoan taiKhoan : Controller.taiKhoanArrayList){
            if(taiKhoan.getTenDangNhap().equals(Controller.tk.getTenDangNhap())){
                Controller.tk=taiKhoan;
                break;
            }
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
            stage.setScene(scene);
            stage.show();

        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchChuyenBay();
    }

    public void searchChuyenBay(){
        new LoadData();
        ArrayList<ChuyenBay> list =new ArrayList<>();
        for (ChuyenBay cb : Controller.timchuyenBayArrayList){
                list.add(cb);
        }
        tb_machuyenbay.setCellValueFactory(new PropertyValueFactory<>("maChuyenBay"));
        tb_maybay.setCellValueFactory(new PropertyValueFactory<>("SHMB"));
        tb_duongbay.setCellValueFactory(cellData -> {
            ChuyenBay chuyenBay = cellData.getValue();
            DuongBay duongBay = chuyenBay.getDuongBay(); // Lấy đối tượng DuongBay từ ChuyenBay
            return new SimpleStringProperty(duongBay.getMaSanBayDi() + "->" + duongBay.getMaSanBayDen());
        });
        tb_thoigian.setCellValueFactory(cellData-> {
            ChuyenBay chuyenBay=cellData.getValue();
            return new SimpleStringProperty(chuyenBay.getNgayBay()+" "+chuyenBay.getGioBay());
        });

        observableList= FXCollections.observableArrayList(list);
        tb_View.setItems(observableList);

    }

    @FXML
    void clickTable(MouseEvent event) {
        Controller.cb = Controller.timchuyenBayArrayList.get(tb_View.getSelectionModel().getSelectedIndex());
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
