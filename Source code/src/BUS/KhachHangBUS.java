/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Hainguyen
 */
public class KhachHangBUS {

    public static ArrayList<KhachHangDTO> KhachHangAll() {
        return KhachHangDAO.KhachHangAll();
    }

    public static KhachHangDTO getKhachHang(int MaKH) {
        return KhachHangDAO.getKhachHang(MaKH);
    }

    public static String getLoaiKH(int MaLKH) {
        return KhachHangDAO.getLoaiKH(MaLKH);
    }

    public static int getSoKH() {
        return KhachHangDAO.getSoKH();
    }

    public static int getGiamGia(int MaKH) {
        return KhachHangDAO.getGiamGia(MaKH);
    }

    public static double getTichLuy(int MaKH, double TichLuy) {
        return KhachHangBUS.getKhachHang(MaKH).getTichLuy() + TichLuy;
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

    public static boolean checkTenKH(JTextField tf) {
        return !"".equals(tf.getText());
    }

    public static boolean checkkTichLuy_UuDai(KhachHangDTO kh) {
        return kh.getTichLuy() >= 3000000;
    }

    public static boolean checkkTichLuy_Vip(KhachHangDTO kh) {
        return kh.getTichLuy() >= 6000000;
    }

}
