package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Logger;

public class InsertData {

    public static boolean insertMayBay(MayBay mb){
        String sqlCommand = "INSERT INTO MAYBAY VALUE(?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps =DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,mb.getSHMB());
            ps.setString(2,mb.getHangBay());
            ps.setInt(3,mb.getSoGhe());

            if(ps.executeUpdate() > 0){
                System.out.println("Thêm thành công máy bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertSanBay(SanBay sb){
        String sqlCommand = "INSERT INTO SANBAY VALUE(?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps =DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,sb.getMaSanBay());
            ps.setString(2,sb.getTenSanBay());
            ps.setString(3,sb.getDiaDiem());

            if(ps.executeUpdate() > 0){
                System.out.println("Thêm sân bay thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertDuongBay(DuongBay db){
        String sqlCommand ="INSERT INTO DUONGBAY VALUE(?,?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,db.getMaDuongBay());
            ps.setString(2,db.getMaSanBayDi());
            ps.setString(3,db.getMaSanBayDen());
            ps.setInt(4,db.getKhoangCach());

            if(ps.executeUpdate() > 0){
                System.out.println("Thêm thành công đường bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean insertChuyenBay(ChuyenBay cb){
        String sqlCommand = "INSERT INTO CHUYENBAY VALUES(?,?,?,?,?,?)";
        System.out.println(cb.toString());
        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);
            ps.setString(1,cb.getMaChuyenBay());
            ps.setString(2,cb.getSHMB());
            ps.setString(3,cb.getDuongBay().getMaDuongBay());
            ps.setDate(4, (Date) cb.getNgayBay());
            ps.setTime(5, Time.valueOf(cb.getGioBay()));
            ps.setInt(6,cb.getTrangThai());

            if (ps.executeUpdate() > 0) {
                System.out.println("thêm chuyến bay thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Thêm thất bại chuyến bay");
        return false;
    }

        public static void inserTaiKhoan(TaiKhoan taikhoan){
            String sqlCommand="insert taikhoan values(?,?,?)";
            try{
                DataConnection.createStatement();
                PreparedStatement ps=DataConnection.connection.prepareStatement(sqlCommand);
                ps.setString(1,taikhoan.getTenDangNhap());
                ps.setString(2,taikhoan.getMatKhau());
                ps.setString(3,taikhoan.getLoaiTaiKhoan());
                ps.executeUpdate();
                System.out.println("thanh cong");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public static boolean inserNhanVien(NhanVien nv){

            String sqlCommand ="insert into nhanvien values(?,?,?,?)";

            try {
                DataConnection.createStatement();
                PreparedStatement ps=DataConnection.connection.prepareStatement(sqlCommand);
                ps.setString(1,nv.getSdt());
                ps.setString(2, nv.getTendn());
                ps.setString(3, nv.getTenNhanVien());
                ps.setInt(4,nv.getLuong());
                if(ps.executeUpdate()>0){
                    System.out.println("thêm nhân viên thành công");
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("thêm khách hàng thất bại ");
            return false;
        }

        public static void  insertVe(ArrayList<Ve> dsVe){
            String sqlCommand ="INSERT INTO VE VALUES(?,?,?,?,?,?,?,?)";

            try {
                for (Ve ve : dsVe){
                    DataConnection.createStatement();
                    PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

                    ps.setString(1,ve.getMaChuyenBay());
                    ps.setString(2,ve.getTenHanhKhach());
                    ps.setString(3,ve.getsDT());
                    ps.setString(4,ve.getMaGhe());
                    ps.setInt(5,ve.getGia());
                    ps.setDate(6, (Date) ve.getNgaysinh());
                    ps.setString(7,ve.getEmail());
                    ps.setString(8,ve.getCccd());

                    if(ps.executeUpdate() > 0){
                        System.out.println("Thêm vé thành công");
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
