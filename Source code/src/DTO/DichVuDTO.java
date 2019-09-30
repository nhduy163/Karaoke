/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Blob;

/**
 *
 * @author Hainguyen
 */
public class DichVuDTO {
    private int MaDV;
    private String TenDV;
    private double DonGia;
    private String DonVi;
    private int LoaiDV;
    private Blob HinhAnh;

    public int getMaDV() {
        return MaDV;
    }

    public String getTenDV() {
        return TenDV;
    }

    public double getDonGia() {
        return DonGia;
    }

    public String getDonVi() {
        return DonVi;
    }

    public int getLoaiDV() {
        return LoaiDV;
    }

    public Blob getHinhAnh() {
        return HinhAnh;
    }

    public void setMaDV(int MaDV) {
        this.MaDV = MaDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
    }

    public void setLoaiDV(int LoaiDV) {
        this.LoaiDV = LoaiDV;
    }

    public void setHinhAnh(Blob HinhAnh) {
        this.HinhAnh = HinhAnh;
    }
    
}
