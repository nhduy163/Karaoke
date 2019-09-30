/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Hainguyen
 */
public class NhanVienBUS {

    public static ArrayList<NhanVienDTO> NhanVienAll() {
        return NhanVienDAO.NhanVienAll();
    }

    public static NhanVienDTO getNhanVien(int MaNV) {
        return NhanVienDAO.getNhanVien(MaNV);
    }

    public static String getLamViec(int MaLV) {
        return NhanVienDAO.getLamViec(MaLV);
    }

    public static int getSoNV() {
        return NhanVienDAO.getSoNV();
    }

    public static boolean checkTenNV(JTextField tf) {
        return !"".equals(tf.getText());
    }

    public static boolean checkNgaySinh(JDateChooser dc) {
        try {
            java.util.Date NgaySinh = dc.getDate();
            java.sql.Date date = new java.sql.Date(NgaySinh.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkCMND(String x) {
        try {
            return Integer.parseInt(x) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkSDT(String x) {
        try {
            return Integer.parseInt(x) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkLuong(JTextField tf) {
        try {
            return Integer.parseInt(tf.getText()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
