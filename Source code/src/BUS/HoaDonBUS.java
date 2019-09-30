/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import com.toedter.calendar.JDateChooser;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hainguyen
 */
public class HoaDonBUS {
    
    public static HoaDonDTO getHoaDon(int MaHD) {
        return HoaDonDAO.getHoaDon(MaHD);
    }
    
    public static ArrayList<HoaDonDTO> getHoaDon(Timestamp start, Timestamp end) {
        return HoaDonDAO.getHoaDon(start, end);
    }

    public static ArrayList<HoaDonDTO> getHoaDon(Date NgayHD) {
        return HoaDonDAO.getHoaDon(NgayHD);
    }
    
    public static ArrayList<HoaDonDTO> HoaDonALL() {
        return HoaDonDAO.HoaDonALL();
    }
    
    public static int getSoHoaDon() {
        return HoaDonDAO.getSoHoaDon();
    }
    
    public static boolean checkNgay(JDateChooser dc) {
        try {
            java.util.Date NgaySinh = dc.getDate();
            java.sql.Date date = new java.sql.Date(NgaySinh.getTime());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
