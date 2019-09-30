/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.GoiDichVuDAO;
import DTO.GoiDichVuDTO;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Hainguyen
 */
public class GoiDichVuBUS {

    public static String getDichVu(int MaDV) {
        return GoiDichVuDAO.getDichVu(MaDV);
    }

    public static int getSoGoiDV() {
        return GoiDichVuDAO.getSoGoiDV();
    }

    public static ArrayList<GoiDichVuDTO> GoiDichVuAll(int MaTP) {
        return GoiDichVuDAO.GoiDichVuAll(MaTP);
    }

    public static double getTongTien(int MaTP) {
        return GoiDichVuDAO.getTongTien(MaTP);
    }

    public static GoiDichVuDTO getGoiDichVu(int MaGDV) {
        return GoiDichVuDAO.getGoiDichVu(MaGDV);
    }

    public static boolean checkSoLuong(JTextField tf) {
        try {
            return Integer.parseInt(tf.getText()) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkSoLuong(String x) {
        try {
            return Integer.parseInt(x)>0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
