package com.example.quan_ly_tuyen_bay.Model;


import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Controller.DuongBayController;

import java.io.Serializable;

public class DuongBay implements Serializable {
    private String maDuongBay;
    private String maSanBayDi;
    private String maSanBayDen;
    private int khoangCach;


    public DuongBay() {

    }

    public DuongBay(String maDuongBay, String maSanBayDi, String maSanBayDen, int khoangCach) {
        this.maDuongBay = maDuongBay;
        this.maSanBayDi = maSanBayDi;
        this.maSanBayDen = maSanBayDen;
        this.khoangCach = khoangCach;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public String getMaSanBayDi() {
        return maSanBayDi;
    }

    public void setMaSanBayDi(String maSanBayDi) {
        this.maSanBayDi = maSanBayDi;
    }

    public String getMaSanBayDen() {
        return maSanBayDen;
    }

    public void setMaSanBayDen(String maSanBayDen) {
        this.maSanBayDen = maSanBayDen;
    }

    public int getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(int khoangCach) {
        this.khoangCach = khoangCach;
    }

    public String getTenSanBayDi() {
        for (SanBay sb : Controller.sanBayArrayList) {
            if (sb.getMaSanBay().equals(this.maSanBayDi)) {
                return sb.getTenSanBay();
            }
        }
        return null;
    }

    public String getTenSanBayDen(){
        for (SanBay sb : Controller.sanBayArrayList){
            if(sb.getMaSanBay().equals(this.maSanBayDen)){
                return sb.getTenSanBay();
            }
        }

        return null;
    }

    public int getGia(String maDuongBay){
        for (DuongBay db : Controller.duongBayArrayList){
            if(db.getMaDuongBay().equals(maDuongBay)){
                if(db.getKhoangCach() >=500 && db.getKhoangCach() < 850){
                    return 2250000;
                }else if(db.getKhoangCach() >=850 && db.getKhoangCach() < 1000){
                    return 2790000;
                }else if(db.getKhoangCach() >=1000 && db.getKhoangCach() < 1280){
                    return 3400000;
                }else if(db.getKhoangCach() >=1280){
                    return 4000000;
                }else {
                    return 1000000;
                }
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        return "DuongBay{" +
                "maDuongBay='" + maDuongBay + '\'' +
                ", maSanBayDi='" + maSanBayDi + '\'' +
                ", maSanBayDen='" + maSanBayDen + '\'' +
                ", khoangCach=" + khoangCach +
                '}';
    }
}
