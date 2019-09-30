/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ThuePhongDAO;
import DTO.ThuePhongDTO;
import com.toedter.calendar.JDateChooser;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 *
 * @author Hainguyen
 */
public class ThuePhongBUS {

    public static ThuePhongDTO getThuePhong_MaPhong_1(int MaPhong) {
        return ThuePhongDAO.getThuePhong_MaPhong_1(MaPhong);
    }

    public static ThuePhongDTO getThuePhong(int MaTP) {
        return ThuePhongDAO.getThuePhong(MaTP);
    }

    public static ArrayList<ThuePhongDTO> ThuePhongAll(int MaPhong) {
        return ThuePhongDAO.ThuePhongAll(MaPhong);
    }

    public static ArrayList<ThuePhongDTO> getThuePhong_MaPhong_2(int MaPhong) {
        return ThuePhongDAO.getThuePhong_MaPhong_2(MaPhong);
    }

    public static int getMaTP() {
        return ThuePhongDAO.getMaTP();
    }

    public static int getKhachHang(int MaTP) {
        return ThuePhongDAO.getKhachHang(MaTP);
    }

    public static Period getSoGio(int MaTP) {
        return ThuePhongDAO.getSoGio(MaTP);
    }

    public static double getTienTraTruoc(int MaTP) {
        return ThuePhongDAO.getTienTraTruoc(MaTP);
    }

    public static float getSoGioNgay(int MaTP) {
        return ThuePhongDAO.getSoGioNgay(MaTP);
    }

    public static float getSoGioDem(int MaTP) {
        return ThuePhongDAO.getSoGioDem(MaTP);
    }

    public static double getTienGio(int MaTP) {
        return ThuePhongDAO.getTienGio(MaTP);
    }

    public static boolean checkNgay(Timestamp a, Timestamp b) {
        if (a.getYear() == b.getYear()) {
            if (a.getMonth() == b.getMonth()) {
                return a.getDay() == b.getDay();
            } else {
                System.out.println("do tháng");
                return false;
            }
        } else {
            System.out.println("do năm");
            return false;
        }
    }

    public static boolean checkKhoangThoiGian(Timestamp a, Timestamp b, int x, int y) {
        if (checkNgay(a, b) == true) {
            if (a.getHours() < b.getHours()) {
                System.out.println("x = " + x);
                System.out.println("y = " + y);
                System.out.println("a = " + (a.getHours()));
                System.out.println("b = " + (b.getHours()));
                return (a.getHours() + x) < b.getHours();
            } else {
                return (b.getHours() + y) < a.getHours();
            }
        } else {
            return true;
        }
    }

    public static boolean checkGioBatDau(Timestamp x, int y, int MaPhong) {

        ArrayList<ThuePhongDTO> arr = ThuePhongBUS.getThuePhong_MaPhong_2(MaPhong);
        if (!arr.isEmpty()) {
            ThuePhongDTO tp;
            for (int i = 0; i < arr.size(); i++) {
                tp = arr.get(i);
                if (!checkKhoangThoiGian(x, tp.getGioBatDau(), y, tp.getKhoangTG())) {
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian thuê không cho phép! " + tp.getMaTP());
                    return false;
                }
            }
        }
        if (ThuePhongBUS.getThuePhong_MaPhong_1(MaPhong).getGioBatDau() != null) {
            if (!checkKhoangThoiGian(x, ThuePhongBUS.getThuePhong_MaPhong_1(MaPhong).getGioBatDau(), y, ThuePhongBUS.getThuePhong_MaPhong_1(MaPhong).getKhoangTG())) {
                JOptionPane.showMessageDialog(null, "Khoảng thời gian thuê không cho phép! "+ ThuePhongBUS.getThuePhong_MaPhong_1(MaPhong).getMaTP());
                return false;
            }
        }
        return true;
    }

    public static boolean checkNgayBatDau(JDateChooser dc) {
        try {
            java.util.Date NgaySinh = dc.getDate();
            java.sql.Date date = new java.sql.Date(NgaySinh.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkTienTraTruoc(String x) {
        try {
            return Integer.parseInt(x) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
