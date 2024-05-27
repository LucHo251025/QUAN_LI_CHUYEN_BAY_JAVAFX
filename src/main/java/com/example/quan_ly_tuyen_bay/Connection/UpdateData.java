package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Client.ClientCRUD;
import com.example.quan_ly_tuyen_bay.Model.*;

public class UpdateData {

    public static void updateMayBay(MayBay mb) {
        ClientCRUD.sendRequestObject("updateMayBay", mb);
    }

    public static void updateSanBay(SanBay sb) {
        ClientCRUD.sendRequestObject("updateSanBay", sb);
    }

    public static void updateDuongBay(DuongBay db) {
        ClientCRUD.sendRequestObject("updateDuongBay", db);
    }

    public static void cancelTrip(String mcb) {
        ClientCRUD.sendRequestObject("cancelTrip", mcb);
    }

    public static void updateChuyenBay(ChuyenBay cb) {
        ClientCRUD.sendRequestObject("updateChuyenBay", cb);
    }

    public static void capNhatHetVe(String maCb) {
        ClientCRUD.sendRequestid("capNhatHetVe", maCb);
    }

    public static void capNhatConVe(String maCb) {
        ClientCRUD.sendRequestid("capNhatConVe", maCb);
    }


    public static void doiMatKhau(String tendn, String mk) {
        ClientCRUD.sendRequestid("doiMatKhau", tendn + "-" + mk);
    }

    public static void updateNhanvien(NhanVien nhanvien) {
        ClientCRUD.sendRequestObject("updateNhanvien", nhanvien);
    }

}
