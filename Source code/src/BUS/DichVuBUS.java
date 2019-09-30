/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DichVuDAO;
import DTO.DichVuDTO;
import java.sql.Blob;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Hainguyen
 */
public class DichVuBUS {

    public static ArrayList<DichVuDTO> DichVuAll() {
        return DichVuDAO.DichVuAll();
    }

    public static ArrayList<DichVuDTO> DichVu_Loai(int MaLDV) {
        return DichVuDAO.DichVu_Loai(MaLDV);
    }

    public static DichVuDTO getDichVu(int MaDV) {
        return DichVuDAO.getDichVu(MaDV);
    }

    public static int getSoDV() {
        return DichVuDAO.getSoDV();
    }

    public static int getSoLDV() {
        return DichVuDAO.getSoLDV(); //To change body of generated methods, choose Tools | Templates.
    }

    public static Blob getHinhAnh(int MaDV) {
        return DichVuDAO.getHinhAnh(MaDV);
    }

    public static boolean checkDonGia(JTextField tf) {
        try {
            Double.valueOf(tf.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkDonVi(JTextField tf) {
        return !"".equals(tf.getText());
    }
    public static boolean checkTenDichVu(JTextField tf) {
        return !"".equals(tf.getText());
    }
}
