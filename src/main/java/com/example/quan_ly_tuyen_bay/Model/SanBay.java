package com.example.quan_ly_tuyen_bay.Model;

import java.io.Serializable;

public class SanBay implements Serializable {
    private String maSanBay;
    private String tenSanBay;
    private String diaDiem;

    public SanBay(){

    }


    public SanBay(String maSanBay, String tenSanBay, String diaDiem) {
        this.maSanBay = maSanBay;
        this.tenSanBay = tenSanBay;
        this.diaDiem = diaDiem;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    public String getTenSanBay() {
        return tenSanBay;
    }

    public void setTenSanBay(String tenSanBay) {
        this.tenSanBay = tenSanBay;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
