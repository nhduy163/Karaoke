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
public class ThuePhongDTO {
    private int MaTP;
    private int MaPhong;
    private int MaKH;
    private Timestamp GioBatDau;
    private Timestamp GioKetThuc;
    private int TinhTrangThue;
    private double TienTraTruoc;
    private int KhoangTG;
    

    public int getMaTP() {
        return MaTP;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public int getMaKH() {
        return MaKH;
    }

    public Timestamp getGioBatDau() {
        return GioBatDau;
    }

    public Timestamp getGioKetThuc() {
        return GioKetThuc;
    }

    public int getTinhTrangThue() {
        return TinhTrangThue;
    }

    public double getTienTraTruoc() {
        return TienTraTruoc;
    }
    public int getKhoangTG(){
        return KhoangTG;
    }

    public void setMaTP(int MaTP) {
        this.MaTP = MaTP;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public void setGioBatDau(Timestamp GioBatDau) {
        this.GioBatDau = GioBatDau;
    }

    public void setGioKetThuc(Timestamp GioKetThuc) {
        this.GioKetThuc = GioKetThuc;
    }

    public void setTinhTrangThue(int TinhTrangThue) {
        this.TinhTrangThue = TinhTrangThue;
    }

    public void setTienTraTruoc(double TienTraTruoc) {
        this.TienTraTruoc = TienTraTruoc;
    }  
    public void setKhoangTG(int KhoangTG) {
        this.KhoangTG = KhoangTG;
    }  
}
