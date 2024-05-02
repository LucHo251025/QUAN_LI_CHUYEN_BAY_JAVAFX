package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;

import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateData {
    public static boolean capNhatHoanTat(String maCB){
        String splCommand = "update CHUYENBAY set TRANGTHAI = ?" +" where MACHUYENBAY=?";


        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(splCommand);
            ps.setInt(1, ChuyenBay.HOANTAT);
            ps.setString(2,maCB);

            System.out.println("Chuyến bay "+maCB+" hoàn tất");

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }
}
