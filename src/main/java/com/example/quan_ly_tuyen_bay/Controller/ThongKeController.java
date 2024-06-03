package com.example.quan_ly_tuyen_bay.Controller;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; // Import PdfWriter
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private Button btn_Thoat;
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


        LoadData.loadTableDuongBay();
        LoadData.loadTableSanBay();
        LoadData.loadTableChuyenBay();

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
        showData();
    }



    @FXML
    void click(MouseEvent event) {
//        ChuyenBay cb = tb_View.getSelectionModel().getSelectedItem();
//        int num = tb_View.getSelectionModel().getSelectedIndex();
//
//        if(num < 0){
//            return;
//        }
//
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
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                BaseFont baseFont = BaseFont.createFont("C:\\Users\\vanth\\IdeaProjects\\HK2\\IO_Stream\\Quan_Ly_Tuyen_Bay\\file\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontTitle = new Font(baseFont, 18, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD);
                Font font = new Font(baseFont, 12);

                Paragraph nv = new Paragraph(Controller.tk.getTenDangNhap() + " (" + Controller.tk.getLoaiTaiKhoan() + ")", new Font(baseFont, 15, Font.ITALIC));
                nv.setAlignment(Element.ALIGN_RIGHT);
                document.add(nv);

                Paragraph title = new Paragraph("Thống kê hóa đơn", fontTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                document.add(new Paragraph("\n")); // Add a blank line

                PdfPTable table = new PdfPTable(4); // 4 columns
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Set Column widths
                float[] columnWidths = {1f, 1f, 1f, 1f};
                table.setWidths(columnWidths);

                // Add table header
                PdfPCell cell;

                cell = new PdfPCell(new Paragraph("Mã chuyến bay", fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Máy bay", fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Ngày bay", fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("Tổng tiền", fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                // Add table rows
                if (tb_View.getSelectionModel().getSelectedIndex() == -1) {
                    for (ChuyenBay cb : Controller.chuyenBayArrayList) {
                        if (cb.getTrangThai() == ChuyenBay.HOANTAT) {
                            table.addCell(new PdfPCell(new Paragraph(cb.getMaChuyenBay(), font)));
                            table.addCell(new PdfPCell(new Paragraph(cb.getSHMB(), font)));
                            table.addCell(new PdfPCell(new Paragraph(cb.getNgayBay().toString(), font)));
                            table.addCell(new PdfPCell(new Paragraph(String.valueOf(cb.getTongTien()), font)));
                        }
                    }
                } else {
                    ChuyenBay cbB = tb_View.getSelectionModel().getSelectedItem();
                    table.addCell(new PdfPCell(new Paragraph(cbB.getMaChuyenBay(), font)));
                    table.addCell(new PdfPCell(new Paragraph(cbB.getSHMB(), font)));
                    table.addCell(new PdfPCell(new Paragraph(cbB.getNgayBay().toString(), font)));
                    table.addCell(new PdfPCell(new Paragraph(String.valueOf(cbB.getTongTien()), font)));
                }

                document.add(table);
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    void Exit(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }


}
