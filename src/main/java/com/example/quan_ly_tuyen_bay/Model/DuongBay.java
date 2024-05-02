package com.example.quan_ly_tuyen_bay.Model;


import com.example.quan_ly_tuyen_bay.Controller.Controller;

public class DuongBay {
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
