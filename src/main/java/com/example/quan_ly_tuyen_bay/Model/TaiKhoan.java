package com.example.quan_ly_tuyen_bay.Model;

import java.io.Serializable;

/**
 *
 * @author conghau
 */
public class TaiKhoan implements Serializable {

    private String tenDangNhap;
    private String matKhau;
    private String loaiTaiKhoan;


    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;

    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }


    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", loaiTaiKhoan='" + loaiTaiKhoan + '\'' +
                '}';
    }
}