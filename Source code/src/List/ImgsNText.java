/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import javax.swing.Icon;

/**
 *
 * @author Hainguyen
 */
public class ImgsNText {
    public String TenPhong;
    public Icon img;
    int MaPhong;
    
    public ImgsNText(String text, Icon icon, int MaPhong){
        this.TenPhong = text;
        this.img = icon;
        this.MaPhong = MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }
    
}
