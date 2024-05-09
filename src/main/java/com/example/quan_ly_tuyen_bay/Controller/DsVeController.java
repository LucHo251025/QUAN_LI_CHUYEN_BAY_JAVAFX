package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.Ve;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DsVeController implements Initializable {
    @FXML
    private Button bt_huyve;

    @FXML
    private Label lb_title;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txt_sdt;

    @FXML
    private TextField txt_tenkhackhang;

    private ArrayList<Button> listGhe = new ArrayList<Button>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Controller.dsGheChon.clear();
        ArrayList<String> mapGhe = new ArrayList<String>();
        lb_title.setText("Sơ đồ chuyến bay "+Controller.cb.getMaChuyenBay());
        if(Controller.cb.getTrangThai() == ChuyenBay.HUYCHUYEN || Controller.cb.getTrangThai() == ChuyenBay.HOANTAT){
            bt_huyve.setVisible(false);
        }

        int soGhe = Controller.cb.getSoGhe();
        System.out.println(soGhe);
        mapGhe.add("");

        for (int i = 0; i < soGhe; i++){
            mapGhe.add("0");
        }

        for (Ve ve : Controller.cb.getVeArrayList()){
            mapGhe.set(Integer.parseInt(ve.getMaGhe()),ve.getMaGhe());
        }
        int chiso=1;
        int kichthuoc=80;

        for (int i=0; ;i=i+kichthuoc+10){
            for (int j=1;j<=6;j++){
                if (chiso >= mapGhe.size()) {
                    return; // Nếu chiso vượt quá kích thước của mapGhe, thoát khỏi vòng lặp
                }
                Button button = new Button();
                button.setLayoutX((j-0.6)*(kichthuoc+10)+50*(j>3?1:0));
                button.setLayoutY(i+10);
                button.setPrefSize(kichthuoc,kichthuoc);

                pane.getChildren().add(button);
                button.setText(String.valueOf(chiso));

                if( ! mapGhe.get(chiso).equals("0")){
                    button.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)));
//                    if(Controller.tk.getLoaiTaiKhoan().equals("guest")){
//                        button.setEnabled(false);
//                    }
                }
//                else{
//                        if(!Controller.tk.getLoaiTaiKhoan().equals("guest"))
//                            jButton1.setEnabled(false);
//                    }

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                    }
                });
                listGhe.add(button);
                chiso++;
            }
        }
    }

//    private void chonGhe(ActionEvent event,Button btn){
//        if
//    }
}
