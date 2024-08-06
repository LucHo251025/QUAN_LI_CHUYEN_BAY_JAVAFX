package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import org.w3c.dom.*;

import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanRepo {

    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private String fileName = "C:\\Users\\vanth\\IdeaProjects\\HK2\\IO_Stream\\Quan_Ly_Tuyen_Bay\\src\\main\\java\\com\\example\\quan_ly_tuyen_bay\\Server\\TaiKhoan.xml";

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
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            SecretKey secretKey = AES.getStaticKey();


            NodeList nodeNguoidung = doc.getElementsByTagName("nguoidung");

            for (int i = 0; i < nodeNguoidung.getLength(); i++) {
                Element element = (Element) nodeNguoidung.item(i);
                String tenDangNhap = element.getElementsByTagName("tendangnhap").item(0).getTextContent();
                String matKhau = element.getElementsByTagName("matkhau").item(0).getTextContent();
                String loaiTaiKhoan = element.getElementsByTagName("loaiTaiKhoan").item(0).getTextContent();

                String maHoaMK = AES.encrypt(matKhau, secretKey);
                System.out.println("Tài khoanRepo "+matKhau);
                TaiKhoan tk = new TaiKhoan(tenDangNhap, maHoaMK, loaiTaiKhoan);
                System.out.println("Tài Khoản Repo:"+ tk.toString());
                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addTaikhoan(TaiKhoan taikhoan) {

        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            SecretKey secretKey = AES.getStaticKey();

            String giaiMaMK = AES.decrypt(taikhoan.getMatKhau(), secretKey);

            Element root = doc.getDocumentElement();
            Element nguoidung = doc.createElement("nguoidung");

            Element tenDn = doc.createElement("tendangnhap");
            Element mk = doc.createElement("matkhau");
            Element loaiTk = doc.createElement("loaiTaiKhoan");

            tenDn.appendChild(doc.createTextNode(taikhoan.getTenDangNhap()));
            mk.appendChild(doc.createTextNode(giaiMaMK));
            loaiTk.appendChild(doc.createTextNode(taikhoan.getLoaiTaiKhoan()));

            nguoidung.appendChild(tenDn);
            nguoidung.appendChild(mk);
            nguoidung.appendChild(loaiTk);

            root.appendChild(nguoidung);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(fileName));
            doc.getDocumentElement().normalize();

            SecretKey secretKey = AES.getStaticKey();
            String maMK = AES.decrypt(mk, secretKey);


            NodeList nguoiDungList = doc.getElementsByTagName("nguoidung");

            for (int i = 0; i < nguoiDungList.getLength(); i++) {
                Element nguoiDung = (Element) nguoiDungList.item(i);
                NodeList tenDangNhapList = nguoiDung.getElementsByTagName("tendangnhap");
                NodeList matKhauList = nguoiDung.getElementsByTagName("matkhau");

                if (tenDangNhapList.getLength() > 0 && matKhauList.getLength() > 0) {
                    String currentTenDangNhap = tenDangNhapList.item(0).getTextContent();
                    if (currentTenDangNhap.equals(tenDn)) {
                        matKhauList.item(0).setTextContent(maMK);

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource source = new DOMSource(doc);
                        StreamResult result = new StreamResult(new File(fileName));
                        transformer.transform(source, result);
                        System.out.println("Đổi mật khẩu thành công");
                        return true;

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Đổi mật khẩu thất bại");
        return false;
    }

    public boolean deleteTaiKhoan(String tenDN) {

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

