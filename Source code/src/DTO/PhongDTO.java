/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Hainguyen
 */
public class PhongDTO {
    private int MaPhong;
    private String TenPhong;
    private int LoaiPhong;
    private int TinhTrang;
    private double GiaGioNgay;
    private double GiaGioDem;

    public int getMaPhong() {
        return MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public int getLoaiPhong() {
        return LoaiPhong;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public double getGiaGioNgay() {
        return GiaGioNgay;
    }

    public double getGiaGioDem() {
        return GiaGioDem;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public void setLoaiPhong(int LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public void setGiaGioNgay(double GiaGioNgay) {
        this.GiaGioNgay = GiaGioNgay;
    }

    public void setGiaGioDem(double GiaGioDem) {
        this.GiaGioDem = GiaGioDem;
    }
    
}
