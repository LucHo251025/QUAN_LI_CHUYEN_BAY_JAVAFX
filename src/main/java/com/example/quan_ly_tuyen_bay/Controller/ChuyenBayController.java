package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.DeleteData;
import com.example.quan_ly_tuyen_bay.Connection.InsertData;
import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import com.example.quan_ly_tuyen_bay.Model.Ve;
import com.example.quan_ly_tuyen_bay.Server.Repository.DuongBayRepo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChuyenBayController implements Initializable {

    private static ChuyenBayController instance;

    public static ChuyenBayController getInstance() {
        return instance;
    }

    public ChuyenBayController() {
        instance = this;
    }

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
    private ComboBox<String> cb_gio;

    @FXML
    private ComboBox<String> cm_phut;

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
    private Alert alert;
    private List<ChuyenBay> list = new ArrayList<ChuyenBay>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
//        LoadData.loadTableDuongBay();
        for (DuongBay db : Controller.duongBayArrayList) {
            cb_duongbay.getItems().add(db.getMaSanBayDi() + "->" + db.getMaSanBayDen());
        }

        cheDo = 0;

        for (MayBay mb : Controller.mayBayArrayList) {
            cb_shmb.getItems().add(mb.getSHMB().trim());
        }

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


        bt_huychuyen.setDisable(false);
        bt_them.setDisable(false);
        bt_sua.setDisable(false);
        bt_sua.setDisable(false);
        tb_dsve.setDisable(false);
        bt_luu.setDisable(true);
        bt_huy.setDisable(true);
        txt_machuyenbay.setDisable(true);
        cb_shmb.setDisable(true);
        cb_duongbay.setDisable(true);
        date_ngaybay.setDisable(true);
        cb_gio.setDisable(true);
        cm_phut.setDisable(true);

        radio_huy.setSelected(false);
        radio_hoantat.setSelected(false);
        radio_het.setSelected(false);
        radio_con.setSelected(false);


    }
    public void showData() {
        LoadData.loadTableDuongBay();
        LoadData.loadTableChuyenBay();
        LoadData.loadTableMayBay();
        LoadData.loadTableSanBay();

        list.clear();
        for (ChuyenBay cb : Controller.chuyenBayArrayList) {
            if (cb.getTrangThai() == this.cheDo) {
                list.add(cb);
            }
        }
        tb_machuyenbay.setCellValueFactory(new PropertyValueFactory<>("maChuyenBay"));
        tb_shmb.setCellValueFactory(new PropertyValueFactory<>("SHMB"));

        tb_duongbay.setCellValueFactory(cellData -> {
            ChuyenBay chuyenBay = cellData.getValue();
            DuongBay duongBay = chuyenBay.getDuongBay();
            if (duongBay != null) {
                return new SimpleStringProperty(duongBay.getMaSanBayDi() + "->" + duongBay.getMaSanBayDen());
            } else {
                return new SimpleStringProperty("Unknown");
            }
        });
        tb_thoigian.setCellValueFactory(cellData -> {
            ChuyenBay chuyenBay = cellData.getValue();
            return new SimpleStringProperty(chuyenBay.getNgayBay() + " " + chuyenBay.getGioBay());
        });

        observableList = FXCollections.observableArrayList(list);
        home_tbview.setItems(observableList);
        home_tbview.refresh();

    }


    public void notification(String mes) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(mes);
        alert.showAndWait();
    }

    public void radioButton(ActionEvent event) {
        if (radio_con.isSelected()) {
            this.cheDo = ChuyenBay.CONVE;
            showData();
            radio_huy.setSelected(false);
            radio_hoantat.setSelected(false);
            radio_het.setSelected(false);
        } else if (radio_het.isSelected()) {
            this.cheDo = ChuyenBay.HETVE;
            radio_huy.setSelected(false);
            radio_hoantat.setSelected(false);
            radio_con.setSelected(false);
            showData();
        } else if (radio_hoantat.isSelected()) {
            this.cheDo = ChuyenBay.HOANTAT;
            radio_huy.setSelected(false);
            radio_con.setSelected(false);
            radio_het.setSelected(false);
            showData();
        } else if (radio_huy.isSelected()) {
            this.cheDo = ChuyenBay.HUYCHUYEN;
            radio_con.setSelected(false);
            radio_hoantat.setSelected(false);
            radio_het.setSelected(false);
            showData();
        }
    }

    @FXML
    void ADD(ActionEvent event) {
        bt_huychuyen.setDisable(true);
        bt_them.setDisable(false);
        bt_sua.setDisable(true);
        bt_sua.setDisable(true);
        tb_dsve.setDisable(true);
        bt_luu.setDisable(false);
        bt_huy.setDisable(false);
        txt_machuyenbay.setDisable(false);
        cb_shmb.setDisable(false);
        cb_duongbay.setDisable(false);
        date_ngaybay.setDisable(false);
        cb_gio.setDisable(false);
        cm_phut.setDisable(false);

        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

        try {
            java.util.Date dateNow = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearText() {
        txt_machuyenbay.setText("");
        cb_shmb.setValue("");
        cb_duongbay.setValue("");
        date_ngaybay.setValue(null);
        cb_gio.setValue(null);
        cm_phut.setValue(null);
        bt_huychuyen.setDisable(false);
        bt_them.setDisable(false);
        bt_sua.setDisable(false);
        bt_sua.setDisable(false);
        tb_dsve.setDisable(false);
        bt_luu.setDisable(true);
        bt_huy.setDisable(true);
        txt_machuyenbay.setDisable(true);
        cb_shmb.setDisable(true);
        cb_duongbay.setDisable(true);
        date_ngaybay.setDisable(true);
        cb_gio.setDisable(true);
        cm_phut.setDisable(true);

        radio_huy.setSelected(false);
        radio_hoantat.setSelected(false);
        radio_het.setSelected(false);
        radio_con.setSelected(false);
    }

    public void click() {
        ChuyenBay cb = home_tbview.getSelectionModel().getSelectedItem();
        int num = home_tbview.getSelectionModel().getFocusedIndex();

        if (num < 0) {
            return;
        }

        txt_machuyenbay.setText(cb.getMaChuyenBay());
        cb_shmb.setValue(cb.getSHMB());
        cb_duongbay.setValue(cb.getDuongBay().getMaSanBayDi() + "->" + cb.getDuongBay().getMaSanBayDen());
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
    void CANCEL(ActionEvent event) {
        clearText();
        bt_huychuyen.setDisable(false);
        bt_them.setDisable(false);
        bt_sua.setDisable(false);
        bt_sua.setDisable(false);
        tb_dsve.setDisable(false);
        bt_luu.setDisable(true);
        bt_huy.setDisable(true);
        txt_machuyenbay.setDisable(true);
        cb_shmb.setDisable(true);
        cb_duongbay.setDisable(true);
        date_ngaybay.setDisable(true);
        cb_gio.setDisable(true);
        cm_phut.setDisable(true);
    }

    @FXML
    void DSve(ActionEvent event) {
        if (home_tbview.getSelectionModel().getSelectedIndex() == -1) {
            notification("Vui lòng chọn chuyến bay");
            return;
        }

        for (ChuyenBay cbb : Controller.chuyenBayArrayList) {
            if (cbb.getMaChuyenBay().equals(home_tbview.getSelectionModel().getSelectedItem().getMaChuyenBay())) {
                Controller.cb = cbb;
                System.out.println("Controller Chuuyen BAy "+Controller.cb.getMaChuyenBay());
                for (Ve ve : Controller.cb.getVeArrayList()){
                    System.out.println(ve.toString());
                }
                break;
            }
        }

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/GiaoDienChonSoLuongVe.fxml")));
            stage.setScene(scene);
            stage.setTitle("Chọn số lượng vé");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void EDIT(ActionEvent event) {
        date_ngaybay.setDisable(false);
        cb_gio.setDisable(false);
        cm_phut.setDisable(false);
        bt_luu.setDisable(false);
        bt_huy.setDisable(false);
    }

    @FXML
    void SAVE(ActionEvent event) {
        txt_machuyenbay.setText(txt_machuyenbay.getText().toUpperCase());

        if (txt_machuyenbay.getText().equals("")) {
            notification("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        LocalDate datenow = LocalDate.now();
        LocalTime myTime = LocalTime.of(
                Integer.parseInt(cb_gio.getValue()), // Giờ được chọn từ ComboBox
                Integer.parseInt(cm_phut.getValue()), // Phút được chọn từ ComboBox
                0
        );

        // Tạo LocalDateTime từ ngày và thời gian
        LocalDateTime dateTime = LocalDateTime.of(date_ngaybay.getValue(), myTime);

        // Tính thời giân cách thời điểm hiêện tại
        long timeDiff = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - System.currentTimeMillis();

        if ((timeDiff < 24 * 3600000)) {
            notification("Chuyến bay phải mở trước một ngày");
            return;
        }
        java.sql.Date myDate = java.sql.Date.valueOf(date_ngaybay.getValue());
        LoadData.loadTableDuongBay();
        DuongBay myDuongBay = null;
        String str = cb_duongbay.getValue();
        System.out.println(str);
        String[] splip = str.split("->");
        String maduongbay = splip[0].trim() + splip[1].trim();
        System.out.println(maduongbay);

        for (DuongBay db : Controller.duongBayArrayList) {
            if (db.getMaDuongBay().equals(maduongbay)) {
                myDuongBay = db;
                System.out.println("Đường Bay " + myDuongBay.getMaDuongBay());
                break;
            }
        }

        // Check if myDuongBay is still null after the loop
        if (myDuongBay == null) {
            notification("Đường bay không hợp lệ");
            return;
        }

        ChuyenBay cb = new ChuyenBay(txt_machuyenbay.getText(), cb_shmb.getValue(), myDuongBay, myDate, myTime, ChuyenBay.CONVE);
        if (home_tbview.getSelectionModel().getSelectedIndex() == -1) {

            LoadData.loadTableChuyenBay();
            for (ChuyenBay chuyenBay : Controller.chuyenBayArrayList) {
                if (chuyenBay.getMaChuyenBay().equals(cb.getMaChuyenBay())) {
                    notification("Chuyến bay đã tồn tại");
                    return;
                }

                //kiểm tra chuyến bay mới có cách chuyến bay trong hệ thống 30 phút không
                if ((chuyenBay.getSHMB().equals(cb.getSHMB()) || chuyenBay.getDuongBay().equals(cb.getDuongBay()))
                        && ((Math.abs(chuyenBay.getCBTime().getTime() - cb.getCBTime().getTime()) < 1800000))) {

                    notification("Không thể thêm chuyến bay vào thời gian này");
                    return;
                }

            }
            System.out.println("Chuẩn bị thêm");
            InsertData.insertChuyenBay(cb);
        } else {
            for (ChuyenBay chuyenBay : Controller.chuyenBayArrayList) {
                //kiểm tra chuyến bay mới có cách chuyến bay trong hệ thống 30 phút không
                if ((chuyenBay.getSHMB().equals(cb.getSHMB()) || chuyenBay.getDuongBay().equals(cb.getDuongBay()))
                        && ((Math.abs(chuyenBay.getCBTime().getTime() - cb.getCBTime().getTime()) < 1800000))
                ) {
                    notification("Không thể thêm chuyến bay vào thời gian này");
                    return;
                }
            }

            UpdateData.updateChuyenBay(cb);
        }
        clearText();
//        showData();
    }

    @FXML
    void Thoat(ActionEvent event) {
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

    @FXML
    void cancelTrip(ActionEvent event) {
        if (home_tbview.getSelectionModel().getSelectedIndex() == -1) {
            notification("Vui lòng chọn chuyến bay");
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn có chắc muốn xóa đường bay " + txt_machuyenbay.getText() + " này không ?");

            Optional<ButtonType> optional = alert.showAndWait();

            if (optional.get() == ButtonType.OK) {
                UpdateData.cancelTrip(txt_machuyenbay.getText());
                showData();
                clearText();
            } else if (optional.get() == ButtonType.CANCEL) {
                clearText();
            }
        }
    }

}
