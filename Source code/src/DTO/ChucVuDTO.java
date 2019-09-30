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
public class ChucVuDTO {
    private int MaCV;
    private String TenCV;

    public int getMaCV() {
        return MaCV;
    }

    public String getTenCV(){
        return TenCV;
    }

    public void setMaCV(int MaCV) {
        this.MaCV = MaCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }
    
}
