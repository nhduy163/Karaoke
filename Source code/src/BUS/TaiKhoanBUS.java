/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Hainguyen
 */
public class TaiKhoanBUS {

    public static ArrayList<TaiKhoanDTO> TaiKhoanAll() {
        return TaiKhoanDAO.TaiKHoanAll();
    }

    public static TaiKhoanDTO getTaiKhoan(int MaTK) {
        return TaiKhoanDAO.getTaiKhoan(MaTK);
    }

    public static int getMaNV(String TenTK) {
        ArrayList<TaiKhoanDTO> arr = TaiKhoanBUS.TaiKhoanAll();
        TaiKhoanDTO tk;
        int i = 0;
        while (i < arr.size()) {
            tk = arr.get(i);
            if (TenTK.equals(tk.getTenTK())) {
                return tk.getMaNV();
            } else {
                i++;
            }
        }
        return 0;
    }

    public static int getSoTK() {
        return TaiKhoanDAO.getSoTK();
    }

    public static boolean checkTenTaikhoan(JTextField tf) {
        ArrayList<TaiKhoanDTO> arr = TaiKhoanBUS.TaiKhoanAll();
        TaiKhoanDTO tk;
        String taikhoan = tf.getText();
        int i = 0;
        while (i < arr.size()) {
            tk = arr.get(i);
            if (tk.getTenTK().equals(taikhoan)) {
                return false;
            } else {
                i++;
            }
        }
        return true;
    }

    public static boolean checkMatKhau(JTextField tf) {

        return !"".equals(tf.getText());
    }

    public static boolean checkTaikhoan(JTextField tf) {

        return !"".equals(tf.getText());
    }

    public static boolean checkDangNhap(JTextField tf1, JTextField tf2) {
        ArrayList<TaiKhoanDTO> arr = TaiKhoanBUS.TaiKhoanAll();
        TaiKhoanDTO tk;
        String taikhoan = tf1.getText();
        String matkhau = tf2.getText();
        int i = 0;
        while (i < arr.size()) {
            tk = arr.get(i);
            if (taikhoan.equals(tk.getTenTK()) && matkhau.equals(tk.getMatKhau())) {
                return true;
            } else {
                i++;
            }
        }
        return false;
    }

    public static boolean checkTinhTrang(NhanVienDTO nv) {
        return nv.getTinhTrang() == 1;
    }
}
