package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Model.NhanVien;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienRepo {
    public List<NhanVien> findAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();


        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM NHANVIEN");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        rs.getInt(4)
                );

                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addNhanVien(NhanVien nv) {
        String sqlCommand = "insert into nhanvien values(?,?,?,?)";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps =connection.prepareStatement(sqlCommand);
        ) {

            ps.setString(1, nv.getSdt());
            ps.setString(2, nv.getTendn());
            ps.setString(3, nv.getTenNhanVien());
            ps.setInt(4, nv.getLuong());
            if (ps.executeUpdate() > 0) {
                System.out.println("thêm nhân viên thành công");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("thêm khách hàng thất bại ");
        return false;
    }

    public boolean uadateNhanvien(NhanVien nhanvien) {
        String sqlCommand = "update NHANVIEN set  LUONG=? " + " where SDT=?";
//        DataConnection.createStatement();

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
        ) {

            ps.setInt(1, nhanvien.getLuong());
            ps.setString(2, nhanvien.getSdt());
            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sửa nhân viên thất bại");
        return false;
    }

    public boolean deleteNhanvien(String sdt) {
        String sqlCommand = "DELETE FROM NHANVIEN WHERE SDT=?";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
        ) {


            ps.setString(1, sdt);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xóa nhân viên thành công");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
