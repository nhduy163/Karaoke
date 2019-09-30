/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hainguyen
 */
public class HoaDonDAO {

    public static HoaDonDTO getHoaDon(int MaHD) {
        String sql = "select * from hoadon where MaHD = " + MaHD;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        HoaDonDTO hd = new HoaDonDTO();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            hd.setMaHD(rs.getInt("MaHD"));
            hd.setNgayHD(rs.getTimestamp("NgayHD"));
            hd.setMaTP(rs.getInt("MaTP"));
            hd.setGiamGia(rs.getInt("GiamGia"));
            hd.setMaNV(rs.getInt("MaNV"));
            hd.setTienDichVu(rs.getDouble("TienDichVu"));
            hd.setTienGio(rs.getDouble("TienGio"));
            hd.setTongTien(rs.getDouble("TongTien"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return hd;
    }

    public static ArrayList<HoaDonDTO> HoaDonALL() {

        ArrayList<HoaDonDTO> arr = new ArrayList<>();
        String sql = "select * from hoadon";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setNgayHD(rs.getTimestamp("NgayHD"));
                hd.setMaTP(rs.getInt("MaTP"));
                hd.setGiamGia(rs.getInt("GiamGia"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setTienDichVu(rs.getDouble("TienDichVu"));
                hd.setTienGio(rs.getDouble("TienGio"));
                hd.setTongTien(rs.getDouble("TongTien"));

                arr.add(hd);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static ArrayList<HoaDonDTO> getHoaDon(Timestamp start, Timestamp end) {
        ArrayList<HoaDonDTO> arr = new ArrayList<>();
        String sql = "select * from hoadon where NgayHD >= '" + start + "' and NgayHD <= '" + end + "';";  //nhớ đổi tên table
        System.out.println(sql);
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setNgayHD(rs.getTimestamp("NgayHD"));
                hd.setMaTP(rs.getInt("MaTP"));
                hd.setGiamGia(rs.getInt("GiamGia"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setTienDichVu(rs.getDouble("TienDichVu"));
                hd.setTienGio(rs.getDouble("TienGio"));
                hd.setTongTien(rs.getDouble("TongTien"));

                arr.add(hd);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static ArrayList<HoaDonDTO> getHoaDon(Date NgayHD) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(NgayHD);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        ArrayList<HoaDonDTO> arr = new ArrayList<>();
        String sql = "SELECT * FROM hoadon where day(NgayHD) = " + day + " and month(NgayHD) = " + month + " and year(NgayHD) = " + year;  //nhớ đổi tên table
        System.out.println(sql);
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO();
                hd.setMaHD(rs.getInt("MaHD"));
                hd.setNgayHD(rs.getTimestamp("NgayHD"));
                hd.setMaTP(rs.getInt("MaTP"));
                hd.setGiamGia(rs.getInt("GiamGia"));
                hd.setMaNV(rs.getInt("MaNV"));
                hd.setTienDichVu(rs.getDouble("TienDichVu"));
                hd.setTienGio(rs.getDouble("TienGio"));
                hd.setTongTien(rs.getDouble("TongTien"));

                arr.add(hd);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static int getSoHoaDon() {
        String sql = "select * from hoadon";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaHD");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Không tìm thấy hóa đơn");
        }
        return 0;
    }

    public boolean InsertHoaDon(int MaHD, Timestamp NgayHD, int MaTP, int GiamGia, int MaDV, double TienDichVu, double TienGio, double TongTien) {
        boolean rs;
        String sql_stmt = "INSERT INTO hoadon";
        sql_stmt += " VALUES ('" + MaHD + "','" + NgayHD + "','" + MaTP + "','" + GiamGia + "','" + MaDV + "','" + TienDichVu + "','" + TienGio + "','" + TongTien + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

}
