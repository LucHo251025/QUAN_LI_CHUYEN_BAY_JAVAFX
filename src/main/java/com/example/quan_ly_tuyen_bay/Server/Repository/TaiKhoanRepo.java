package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Model.TaiKhoan;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;
import javafx.util.converter.ShortStringConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanRepo {
    public List<TaiKhoan> findAllTaiKhoan(){
        List<TaiKhoan> list = new ArrayList<>();
        ResultSet rs= DataConnection.retrieveData("select * from taikhoan");
        try {
            while(rs.next()){
                TaiKhoan taiKhoan=new TaiKhoan(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim()

                );
                list.add(taiKhoan);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public boolean addTaikhoan(TaiKhoan taikhoan){
        String sqlCommand="insert taikhoan values(?,?,?)";
        try{
            DataConnection.createStatement();
            PreparedStatement ps=DataConnection.connection.prepareStatement(sqlCommand);
            ps.setString(1,taikhoan.getTenDangNhap());
            ps.setString(2,taikhoan.getMatKhau());
            ps.setString(3,taikhoan.getLoaiTaiKhoan());
            ps.executeUpdate();
            System.out.println("thanh cong");
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean doiMatKhau(String tenDn, String mk){
        String sqlCommand = "UPDATE TAIKHOAN SET MATKHAU=? WHERE TENDANGNHAP=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,mk);
            ps.setString(2,tenDn);

            return ps.executeUpdate() >0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteTaiKhoan(String tenDN){
        String sqlCommand = "DELETE FROM TAIKHOAN WHERE TENDANGNHAP=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,tenDN);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xóa tài khoản thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
