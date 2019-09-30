/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author Hainguyen
 */
public class NhanVienDTO {

    private int Manv;
    private String Tennv;
    private Date NgaySinh;
    private int GioiTinh;
    private String CMND;
    private String SDT;
    private String DiaChi;
    private int Luong;
    private int ChucVu;
    private int TinhTrang;

    public int getGioiTinh() {
        return GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public int getManv() {
        return Manv;
    }

    public String getTennv() {
        return Tennv;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getCMND() {
        return CMND;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public int getLuong() {
        return Luong;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setManv(int Manv) {
        this.Manv = Manv;
    }

    public void setTennv(String Tennv) {
        this.Tennv = Tennv;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }

    public void setChucVu(int ChucVu) {
        this.ChucVu = ChucVu;
    }    
}
