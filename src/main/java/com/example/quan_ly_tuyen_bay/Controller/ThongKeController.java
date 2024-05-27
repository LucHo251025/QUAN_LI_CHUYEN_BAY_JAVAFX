package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter; // Import PdfWriter
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {
    @FXML
    private Button bt_doanhthu;

    @FXML
    private Button bt_khachhang;

    @FXML
    private Button bt_nhanvien;

    @FXML
    private Button btn_thongke;

    @FXML
    private Label sl_dt;

    @FXML
    private Label sl_kh;

    @FXML
    private Label sl_nv;

    @FXML
    private TableView<ChuyenBay> tb_View;

    @FXML
    private TableColumn<ChuyenBay, String> tb_mcb;

    @FXML
    private TableColumn<ChuyenBay, String> tb_ngaybay;

    @FXML
    private TableColumn<ChuyenBay, String> tb_shmb;

    @FXML
    private TableColumn<ChuyenBay, Integer> tb_tongtien;
    private ObservableList<ChuyenBay> observableList;
    private int doanhThu;
    private int khachhang;
    private int nhanvien=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadData.loadTableChuyenBay();
        showData();

        for (ChuyenBay cb: Controller.chuyenBayArrayList){
            if(ChuyenBay.HOANTAT==cb.getTrangThai()){
                doanhThu+=cb.getTongTien();
            }
            khachhang+=cb.getVeArrayList().size();
        }
        sl_dt.setText(String.valueOf(doanhThu));
        sl_kh.setText(String.valueOf(khachhang));
        for (TaiKhoan tk:Controller.taiKhoanArrayList){
            if(tk.getLoaiTaiKhoan().equals("nhanvien")){
                nhanvien++;
            }
        }
        sl_nv.setText(String.valueOf(nhanvien));
    }


    public void showData(){

            new LoadData();
            ArrayList<ChuyenBay> list =new ArrayList<>();
            for (ChuyenBay cb : Controller.chuyenBayArrayList){
                if(cb.getTrangThai() == ChuyenBay.HOANTAT){
                    list.add(cb);
                }
            }

        tb_mcb.setCellValueFactory(new PropertyValueFactory<>("maChuyenBay"));
        tb_shmb.setCellValueFactory(new PropertyValueFactory<>("SHMB"));
        tb_ngaybay.setCellValueFactory(new PropertyValueFactory<>("ngayBay"));
        tb_tongtien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));

        observableList=FXCollections.observableArrayList(list);
        tb_View.setItems(observableList);

    }

    @FXML
    void Print(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FDF Files","*.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                BaseFont baseFont = BaseFont.createFont("C:\\Users\\vanth\\IdeaProjects\\HK2\\IO_Stream\\Quan_Ly_Tuyen_Bay\\file\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                Font font = new Font(baseFont, 12);
                Paragraph title = new Paragraph("Thống kê", new Font(baseFont, 18, Font.BOLD));

                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);


                for (ChuyenBay cb : Controller.chuyenBayArrayList) {
                    if (cb.getTrangThai() == ChuyenBay.HOANTAT) {
                        Paragraph paragraph = new Paragraph();
                        paragraph.setFont(font);

                        paragraph.add(new Chunk("Mã Chuyến bay: "));
                        paragraph.add(new Chunk(cb.getMaChuyenBay() + "       ", font));

                        paragraph.add(new Chunk("MB: "));
                        paragraph.add(new Chunk(cb.getSHMB() + "       ", font));

                        paragraph.add(new Chunk("Ngày bay: "));
                        paragraph.add(new Chunk(String.valueOf(cb.getNgayBay()) + "       ", font));

                        paragraph.add(new Chunk("Tổng tiền: "));
                        paragraph.add(new Chunk(String.valueOf(cb.getTongTien()) + "       ", font));

                        document.add(paragraph);
                        document.add(new Paragraph("\n")); // Add a blank line between each record for better readability
                    }
                }
                document.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

}
