package com.example.quan_ly_tuyen_bay.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button bt_timkiem;

    @FXML
    private Button bt_thoat;
    @FXML
    private ComboBox<?> cb_gio;

    @FXML
    private ComboBox<?> cm_phut;

    @FXML
    private DatePicker date_ngaybay;

    @FXML
    private Label home_chucvu;

    @FXML
    private Label home_giohientai;

    @FXML
    private TableView<?> home_tbview;

    @FXML
    private Label home_tennguoidung;

    @FXML
    private TableColumn<?, ?> tb_duongbay;

    @FXML
    private TableColumn<?, ?> tb_machuyenbay;

    @FXML
    private TableColumn<?, ?> tb_shmb;

    @FXML
    private TableColumn<?, ?> tb_thoigian;

    @FXML
    private TextField txt_duongbay;

    @FXML
    private TextField txt_machuyenbay;

    @FXML
    private TextField txt_shmb;
    private volatile boolean stop = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeNow();
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
}