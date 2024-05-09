package com.example.quan_ly_tuyen_bay.Connection;


import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadData {
    public static void loadTableMayBay(){
        ResultSet rs = DataConnection.retrieveData("select * from MAYBAY");

        try {
            while (rs.next()){
                MayBay mayBay = new MayBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getInt(3)
                );

                Controller.mayBayArrayList.add(mayBay);
            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi loadData table MAYBAY",e);
        }

    }

    public static void loadTableSanBay() {
        ResultSet rs = DataConnection.retrieveData("select * from SANBAY");

        try {
            while (rs.next()){

                SanBay sanBay =new SanBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim()
                );

                Controller.sanBayArrayList.add(sanBay);
            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi load Data table SANBAY");
        }
    }
    public static void loadTableDuongBay() {
        ResultSet rs = DataConnection.retrieveData("select * from DUONGBAY");

        try {
            while (rs.next()){
                DuongBay duongBay = new DuongBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        rs.getInt(4)
                );


                Controller.duongBayArrayList.add(duongBay);
            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi load table DUONGBAY",e);
        }
    }

    public static void loadTableChuyenBay() {
        ResultSet rs = DataConnection.retrieveData("select * from CHUYENBAY");

        Date datenow = Calendar.getInstance().getTime();

        try {
            while (rs.next()){
                ChuyenBay cb = new ChuyenBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        ChuyenBay.timDuongBay(rs.getString(3).trim()),
                        rs.getDate(4),
                        rs.getTime(5).toLocalTime(),
                        rs.getInt(6)
                );


                cb.setVeArrayList(loadTableVe(cb.getMaChuyenBay()));

                if(cb.getTrangThai() == ChuyenBay.CONVE || cb.getTrangThai() == ChuyenBay.HETVE){
                    if(datenow.after(cb.getCBTime())){
                        UpdateData.capNhatHoanTat(cb.getMaChuyenBay());
                        cb.setTrangThai(ChuyenBay.HOANTAT);
                    }
                }

                Controller.chuyenBayArrayList.add(cb);

            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi load table CHUYENBAY",e);
        }
    }


    public static ArrayList<Ve> loadTableVe(String maChuyenBay){

        ArrayList<Ve> veArrayList = new ArrayList<Ve>();
        ResultSet rs = DataConnection.retrieveData("select * from ve where MaCB like '"+ maChuyenBay+"'");

        try {
            while (rs.next()){
                Ve ve = new Ve(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        rs.getString(4).trim()
                );

                veArrayList.add(ve);
            }

        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,null,e);
        }
        return veArrayList;
    }

    public static void loadTableTaiKhoan(){

        ResultSet rs= DataConnection.retrieveData("select * from taikhoan");
        try {
            while(rs.next()){
                TaiKhoan taiKhoan=new TaiKhoan(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim()

                );
                Controller.taiKhoanArrayList.add(taiKhoan);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public LoadData() {
        Controller.duongBayArrayList.removeAll(Controller.duongBayArrayList);
        Controller.sanBayArrayList.removeAll(Controller.sanBayArrayList);
        Controller.mayBayArrayList.removeAll(Controller.mayBayArrayList);
        Controller.chuyenBayArrayList.removeAll(Controller.chuyenBayArrayList);


        loadTableDuongBay();
        loadTableSanBay();
        loadTableMayBay();
        loadTableChuyenBay();
    }
}
