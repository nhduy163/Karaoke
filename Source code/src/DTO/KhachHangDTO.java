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
public class KhachHangDTO {
    private int MaKH;
    private String TenKH;
    private String CMND;
    private String SDT;
    private String DiaChi;
    private double TichLuy;
    private int LoaiKH;

    public int getMaKH() {
        return MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public String getCMND() {
        return CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public double getTichLuy() {
        return TichLuy;
    }

    public int getLoaiKH() {
        return LoaiKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setTichLuy(double TichLuy) {
        this.TichLuy = TichLuy;
    }

    public void setLoaiKH(int LoaiKH) {
        this.LoaiKH = LoaiKH;
    }
    
}
