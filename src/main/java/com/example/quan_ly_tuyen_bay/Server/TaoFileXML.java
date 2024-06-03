package com.example.quan_ly_tuyen_bay.Server;

import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class TaoFileXML {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            TaiKhoan tk = new TaiKhoan("luc","12345","sinhvien");
            Element cb = doc.createElement("taikhoan");

            Element nguoiDung= doc.createElement("nguoidung");

            Element tenDN = doc.createElement("tendangnhap");
            tenDN.setTextContent(tk.getTenDangNhap());

            Element mk = doc.createElement("matkhau");
            mk.setTextContent(tk.getMatKhau());

            Element loaiTaiKhoan = doc.createElement("loaiTaiKhoan");
            loaiTaiKhoan.setTextContent(tk.getLoaiTaiKhoan());

            nguoiDung.appendChild(tenDN);
            nguoiDung.appendChild(mk);
            nguoiDung.appendChild(loaiTaiKhoan);

            cb.appendChild(nguoiDung);
            doc.appendChild(cb);


            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf=tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT,"yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");

            DOMSource source=new DOMSource(doc);
            StreamResult result=new StreamResult(new File("C:\\Users\\vanth\\IdeaProjects\\HK2\\IO_Stream\\Quan_Ly_Tuyen_Bay\\src\\main\\java\\com\\example\\quan_ly_tuyen_bay\\Server\\TaiKhoan.xml"));
            tf.transform(source,result);
            System.out.println("Ghi file thành công");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
