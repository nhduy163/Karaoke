/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author Hainguyen
 */
public class HoaDonDTO {
    private int MaHD;
    private Timestamp NgayHD;
    private int MaTP;
    private int GiamGia;
    private int MaNV;
    private double TienDichVu;
    private double TienGio;
    private double TongTien;

    public int getMaHD() {
        return MaHD;
    }

    public Timestamp getNgayHD() {
        return NgayHD;
    }

    public int getMaTP() {
        return MaTP;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public int getMaNV() {
        return MaNV;
    }

    public double getTienDichVu() {
        return TienDichVu;
    }

    public double getTienGio() {
        return TienGio;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public void setNgayHD(Timestamp NgayHD) {
        this.NgayHD = NgayHD;
    }

    public void setMaTP(int MaTP) {
        this.MaTP = MaTP;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public void setTienDichVu(double TienDichVu) {
        this.TienDichVu = TienDichVu;
    }

    public void setTienGio(double TienGio) {
        this.TienGio = TienGio;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
    
    
}
