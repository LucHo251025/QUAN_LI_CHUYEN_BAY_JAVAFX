package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class HomeController implements Initializable {


    @FXML
    private Button bt_chuyenbay;

    @FXML
    private Button bt_duongbay;

    @FXML
    private Button bt_maybay;

    @FXML
    private Button bt_nhanvien;

    @FXML
    private Button bt_sanbay;

    @FXML
    private Button bt_thoat;

    @FXML
    private Button bt_thongke;

    @FXML
    private Button bt_timkiem;

    @FXML
    private ComboBox<String> cb_gio;

    @FXML
    private ComboBox<String> cm_phut;

    @FXML
    private DatePicker date_ngaybay;

    @FXML
    private Label home_chucvu;

    @FXML
    private Label home_giohientai;

    @FXML
    private TableView<ChuyenBay> home_tbview;

    @FXML
    private Label home_tennguoidung;

    @FXML
    private TableColumn<ChuyenBay, String> tb_duongbay;

    @FXML
    private TableColumn<ChuyenBay, String> tb_machuyenbay;

    @FXML
    private Hyperlink link_doimatkhau;
    @FXML
    private TableColumn<ChuyenBay, String> tb_shmb;

    @FXML
    private TableColumn<ChuyenBay, String> tb_thoigian;

    @FXML
    private TextField txt_duongbay;

    @FXML
    private TextField txt_machuyenbay;

    @FXML
    private TextField txt_shmb;

    private List<ChuyenBay> chuyenBayHomNayList = new ArrayList<>();
    private String dayNow;
    private ObservableList<ChuyenBay> observableList;
    private volatile boolean stop = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData.loadTableChuyenBay();
        dayNow = String.valueOf(LocalDate.now());
//        dayNow="2024-05-15";
        if(Controller.tk.getLoaiTaiKhoan().equals("nhanvien")){
            bt_duongbay.setDisable(true);
            bt_maybay.setDisable(true);
            bt_nhanvien.setDisable(true);
            bt_sanbay.setDisable(true);
        }

        txt_machuyenbay.setDisable(true);
        txt_shmb.setDisable(true);
        txt_duongbay.setDisable(true);
        cb_gio.setDisable(true);
        cm_phut.setDisable(true);
        date_ngaybay.setDisable(true);
        timeNow();
        home_tennguoidung.setText(Controller.tk.getTenDangNhap());
        home_chucvu.setText(Controller.tk.getLoaiTaiKhoan());

        for (ChuyenBay cb : Controller.chuyenBayArrayList){
            System.out.println(cb.getNgayBay());
            if(( (ChuyenBay.CONVE == cb.getTrangThai()) || (ChuyenBay.HETVE == cb.getTrangThai()) )&& (String.valueOf(cb.getNgayBay()).equals(dayNow))){
                chuyenBayHomNayList.add(cb);
            }
        }
        showData();

        for (int i = 0; i <= 23; i++) {
            String hour;
            if (i < 10) {
                hour = "0" + i;
            } else {
                hour = String.valueOf(i);
            }
            cb_gio.getItems().add(hour);
        }

        for (int i = 0; i <= 59; i++) {
            String minute;
            if (i < 10) {
                minute = "0" + i;
            } else {
                minute = String.valueOf(i);
            }
            cm_phut.getItems().add(minute);
        }

    }

    public void showData(){
        tb_machuyenbay.setCellValueFactory(new PropertyValueFactory<>("maChuyenBay"));
        tb_shmb.setCellValueFactory(new PropertyValueFactory<>("SHMB"));
        tb_duongbay.setCellValueFactory(cellData -> {
            ChuyenBay chuyenBay = cellData.getValue();
            DuongBay duongBay = chuyenBay.getDuongBay(); // Lấy đối tượng DuongBay từ ChuyenBay
            return new SimpleStringProperty(duongBay.getMaSanBayDi() + "->" + duongBay.getMaSanBayDen());
        });
        tb_thoigian.setCellValueFactory(cellData-> {
            ChuyenBay chuyenBay=cellData.getValue();
            return new SimpleStringProperty(chuyenBay.getNgayBay()+" "+chuyenBay.getGioBay());
        });

        observableList= FXCollections.observableArrayList(chuyenBayHomNayList);
        home_tbview.setItems(observableList);

    }
    public void close(ActionEvent event){
        if(event.getSource() == bt_thoat){
            stop = true;
            javafx.application.Platform.exit();
        }
    }
    public void timeNow(){
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while(!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }

                final String timenow = sdf.format(new Date());
                Platform.runLater(()->{
                    home_giohientai.setText(timenow);
                });
            }
        });
        thread.start();
    }
    @FXML
    void ChuyenBay(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienChuyenBay.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void duongBay(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienDuongBay.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void mayBay(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienMayBay.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void nhanVien(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienNhanVien.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    @FXML
    void clickTable(MouseEvent event) {

        ChuyenBay cb = home_tbview.getSelectionModel().getSelectedItem();
        int num =home_tbview.getSelectionModel().getFocusedIndex();

        if(num < 0){
            return;
        }

        txt_machuyenbay.setText(cb.getMaChuyenBay());
        txt_shmb.setText(cb.getSHMB());
        txt_duongbay.setText(cb.getDuongBay().getMaSanBayDi()+"->"+cb.getDuongBay().getMaSanBayDen());
        date_ngaybay.setValue(LocalDate.parse(String.valueOf(cb.getNgayBay())));
        int gio = cb.getGioBay().getHour();
        int phut = cb.getGioBay().getMinute();

        // Chuyển đổi giờ và phút sang chuỗi, thêm số 0 phía trước nếu cần
        String gioStr = gio < 10 ? "0" + gio : String.valueOf(gio);
        String phutStr = phut < 10 ? "0" + phut : String.valueOf(phut);

        // Đặt giá trị cho ComboBox giờ và phút
        cb_gio.setValue(gioStr);
        cm_phut.setValue(phutStr);
    }

    @FXML
    void SEARCH(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienTimKiem.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void sanBay(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienSanBay.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void thongKe(ActionEvent event) {

    }

    @FXML
    void changeMatKhau(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienDoiMatKhau.fxml")));
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}