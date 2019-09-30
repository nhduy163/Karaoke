/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.PhongDAO;
import DTO.PhongDTO;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Hainguyen
 */
public class PhongBUS {

    public static ArrayList<PhongDTO> PhongAll() {
        return PhongDAO.PhongAll();
    }

    public static PhongDTO getPhong(int MaPhong) {
        return PhongDAO.getPhong(MaPhong);
    }

    public static int getSoPhong_Loai(int x) {
        return PhongDAO.getSoPhong_loai(x);
    }

    public static String getLoaiPhong(int MaLP) {
        return PhongDAO.getLoaiPhong(MaLP);
    }

    public static String getTinhTrang(int MaTTP) {
        return PhongDAO.getTinhTrang(MaTTP);
    }

    public static boolean checkTenPhong(JTextField tf) {
        return !"".equals(tf.getText());
    }

    public static boolean checkGiaGio(JTextField tf) {
        try {
            return Double.valueOf(tf.getText()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
