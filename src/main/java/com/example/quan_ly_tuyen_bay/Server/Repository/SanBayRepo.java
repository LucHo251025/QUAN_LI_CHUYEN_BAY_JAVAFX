package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Controller.SanBayController;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import com.example.quan_ly_tuyen_bay.Model.SanBay;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanBayRepo {

    public List<SanBay> findAllSanBay() {
        List<SanBay> list = new ArrayList<>();

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from SANBAY");
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {

                SanBay sanBay = new SanBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim()
                );

                list.add(sanBay);
            }
        } catch (Exception e) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, "Lỗi load Data table SANBAY");
        }
        return list;
    }


    public boolean addSayBay(SanBay sb) {
        String sqlCommand = "INSERT INTO SANBAY VALUE(?,?,?)";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps =connection.prepareStatement(sqlCommand);
        ) {


            ps.setString(1, sb.getMaSanBay());
            ps.setString(2, sb.getTenSanBay());
            ps.setString(3, sb.getDiaDiem());

            if (ps.executeUpdate() > 0) {
                System.out.println("Thêm sân bay thành công");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSanBay(SanBay sb) {
        String sqlCommand = "UPDATE SANBAY SET TENSANBAY=?,DIADIEM=? WHERE MASANBAY=?";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
        ) {


            ps.setString(1, sb.getTenSanBay());
            ps.setString(2, sb.getDiaDiem());
            ps.setString(3, sb.getMaSanBay());

            if (ps.executeUpdate() > 0) {
                System.out.println("Sửa thành công sân bay");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSanBay(String maSanBay) {
        String sqlCommand = "DELETE FROM SANBAY WHERE MASANBAY =?";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
        ) {


            ps.setString(1, maSanBay);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xóa sân bay thành công");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
