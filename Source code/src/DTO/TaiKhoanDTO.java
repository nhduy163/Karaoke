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
public class TaiKhoanDTO {
    private int MaTK;
    private String TenTK;
    private String MatKhau;
    private int MaNV;

    public int getMaTK() {
        return MaTK;
    }

    public String getTenTK() {
        return TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaTK(int MaTK) {
        this.MaTK = MaTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
}
