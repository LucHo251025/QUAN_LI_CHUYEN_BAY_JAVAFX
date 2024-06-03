package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;
import javafx.util.converter.ShortStringConverter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.imageio.metadata.IIOMetadataNode;
import javax.print.Doc;
import javax.swing.plaf.nimbus.NimbusStyle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanRepo {

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private  String fileName="C:\\Users\\vanth\\IdeaProjects\\HK2\\IO_Stream\\Quan_Ly_Tuyen_Bay\\src\\main\\java\\com\\example\\quan_ly_tuyen_bay\\Server\\TaiKhoan.xml";

    private Document doc;

    public TaiKhoanRepo() {
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TaiKhoan> findAllTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
//        ResultSet rs= DataConnection.retrieveData("select * from taikhoan");
//        try {
//            while(rs.next()){
//                TaiKhoan taiKhoan=new TaiKhoan(
//                        rs.getString(1).trim(),
//                        rs.getString(2).trim(),
//                        rs.getString(3).trim()
//
//                );
//                list.add(taiKhoan);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        try {
            NodeList nodeNguoidung = doc.getElementsByTagName("nguoidung");

            for (int i = 0; i < nodeNguoidung.getLength(); i++) {
                Element element = (Element) nodeNguoidung.item(i);
                String tenDangNhap = element.getElementsByTagName("tendangnhap").item(0).getTextContent();
                String matKhau = element.getElementsByTagName("matkhau").item(0).getTextContent();
                String loaiTaiKhoan = element.getElementsByTagName("loaiTaiKhoan").item(0).getTextContent();

                TaiKhoan tk = new TaiKhoan(tenDangNhap, matKhau, loaiTaiKhoan);
                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  list;
    }

    public boolean addTaikhoan(TaiKhoan taikhoan) {
//        String sqlCommand="insert taikhoan values(?,?,?)";
//        try{
//            DataConnection.createStatement();
//            PreparedStatement ps=DataConnection.connection.prepareStatement(sqlCommand);
        String hashedPassword;
        try {
            hashedPassword = GFG2.toHexString(GFG2.getSHA(taikhoan.getMatKhau()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Element root = doc.getDocumentElement();
        Element nguoidung = doc.createElement("nguoidung");

        Element tenDn = doc.createElement("tendangnhap");
        Element mk = doc.createElement("matkhau");
        Element loaiTk = doc.createElement("loaiTaiKhoan");

        tenDn.appendChild(doc.createTextNode(taikhoan.getTenDangNhap()));
        mk.appendChild(doc.createTextNode(hashedPassword));
        loaiTk.appendChild(doc.createTextNode(taikhoan.getLoaiTaiKhoan()));

        nguoidung.appendChild(tenDn);
        nguoidung.appendChild(mk);
        nguoidung.appendChild(loaiTk);

        root.appendChild(nguoidung);

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
            System.out.println("Đăng ký thành công");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Đăng ký thất bại");
        return false;
    }

    public boolean doiMatKhau(String tenDn, String mk) {
//        String sqlCommand = "UPDATE TAIKHOAN SET MATKHAU=? WHERE TENDANGNHAP=?";
//
//        try {
//            DataConnection.createStatement();
//            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);
//
//            ps.setString(1, GFG2.toHexString(GFG2.getSHA(mk)));
//            ps.setString(2, tenDn);
//
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//        NodeList nguoiDungList = doc.getElementsByTagName("nguoidung");
//
//        for (int i = 0; i < nguoiDungList.getLength(); i++) {
//            Element nguoiDung = (Element) nguoiDungList.item(i);
//            NodeList tenDangNhapList = nguoiDung.getElementsByTagName("tendangnhap");
//            NodeList matKhauList = nguoiDung.getElementsByTagName("matkhau");
//
//            if (tenDangNhapList.getLength() > 0 && matKhauList.getLength() > 0) {
//                String currentTenDangNhap = tenDangNhapList.item(0).getTextContent();
//                if (currentTenDangNhap.equals(tenDn)) {
//                    matKhauList.item(0).setTextContent(tenDn);
//                    return true;
//                }
//            }
//        }
//
//        return  false;

        NodeList nguoiDungList = doc.getElementsByTagName("nguoidung");

        for (int i = 0; i < nguoiDungList.getLength(); i++) {
            Element nguoiDung = (Element) nguoiDungList.item(i);
            NodeList tenDangNhapList = nguoiDung.getElementsByTagName("tendangnhap");
            NodeList matKhauList = nguoiDung.getElementsByTagName("matkhau");

            if (tenDangNhapList.getLength() > 0 && matKhauList.getLength() > 0) {
                String currentTenDangNhap = tenDangNhapList.item(0).getTextContent();
                if (currentTenDangNhap.equals(tenDn)) {
                    matKhauList.item(0).setTextContent(mk);
                    try {
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File(fileName));
                        transformer.transform(source, result);
                        System.out.println("Đổi mật khẩu thành công");
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Đổi mật khẩu thất bại");
        return false;
    }

    public boolean deleteTaiKhoan(String tenDN) {
//        String sqlCommand = "DELETE FROM TAIKHOAN WHERE TENDANGNHAP=?";
//
//        try {
//            DataConnection.createStatement();
//            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);
//
//            ps.setString(1,tenDN);
//
//            if (ps.executeUpdate() > 0) {
//                System.out.println("Xóa tài khoản thành công");
//                return true;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }


//        return false;
//        try {
//            String st = "taikhoan/nguoidung[tendangnhap=" + tenDN + "]";
//            XPathFactory xf = XPathFactory.newInstance();
//            XPath p = xf.newXPath();
//
//            Node nguoidung = (Node) p.evaluate(st, doc, XPathConstants.NODE);
//            Node parent = nguoidung.getParentNode();
//            parent.removeChild(nguoidung);
//            System.out.println("Xóa thành công tài khoản");
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  false;
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            String expression = "//nguoidung[tendangnhap='" + tenDN + "']";
            Node nguoidung = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);

            if (nguoidung != null) {
                Node parent = nguoidung.getParentNode();
                parent.removeChild(nguoidung);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(fileName));
                transformer.transform(source, result);
                System.out.println("Xóa thành công tài khoản");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Xóa thất bại");
        return false;
    }
    }

