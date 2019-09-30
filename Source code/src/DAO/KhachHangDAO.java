/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhachHangDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class KhachHangDAO {

    public static ArrayList<KhachHangDTO> KhachHangAll() {

        ArrayList<KhachHangDTO> arr = new ArrayList<>();
        String sql = "select * from khachhang";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                KhachHangDTO kh = new KhachHangDTO();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setCMND(rs.getString("CMND"));
                kh.setSDT(rs.getString("SDT"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTichLuy(rs.getDouble("TichLuy"));
                kh.setLoaiKH(rs.getInt("LoaiKH"));

                arr.add(kh);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static KhachHangDTO getKhachHang(int MaKH) {
        String sql = "select * from khachhang where MaKH = " + MaKH;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        KhachHangDTO kh = new KhachHangDTO();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            kh.setMaKH(rs.getInt("MaKH"));
            kh.setTenKH(rs.getString("TenKH"));
            kh.setCMND(rs.getString("CMND"));
            kh.setSDT(rs.getString("SDT"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setTichLuy(rs.getDouble("TichLuy"));
            kh.setLoaiKH(rs.getInt("LoaiKH"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return kh;
    }

    public static String getLoaiKH(int MaLKH) {
        String sql = "select * from loaikh where MaLKH = " + MaLKH;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getString("LoaiKH");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }

    public static int getSoKH() {
        String sql = "select * from khachhang";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaKH");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }

    public static int getGiamGia(int MaKH) {
        String sql = "select * from khachhang where MaKH = " + MaKH;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            switch (rs.getInt("LoaiKH")) {
                case 1:
                    return 0;
                case 2:
                    return 5;
                case 3:
                    return 10;
            }
        } catch (SQLException e) {
            System.out.println("Không tìm thấy giảm giá");
        }
        return 0;
    }

    public boolean InsertKH(int MaKH, String TenKH, String CMND, String SDT, String DiaChi, double TichLuy, int LoaiKH) {
        boolean rs;
        String sql_stmt = "INSERT INTO khachhang";
        sql_stmt += " VALUES ('" + MaKH + "','" + TenKH + "','" + CMND + "','" + SDT + "','" + DiaChi + "','" + TichLuy + "','" + LoaiKH + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateKH(int MaKH, String TenKH, String CMND, String SDT, String DiaChi) {
        boolean rs;
        String sql_stmt = "update khachhang SET TenKH = '" + TenKH + "', CMND = '" + CMND + "', SDT = '" + SDT + "', DiaChi = '" + DiaChi + "' WHERE MaKH = '" + MaKH + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateTichLuy(int MaKH, double TichLuy) {
        boolean rs;
        String sql_stmt = "update khachhang SET TichLuy = '" + TichLuy + "' WHERE MaKH = '" + MaKH + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateLoaiKH(int MaKH, int LoaiKH) {
        boolean rs;
        String sql_stmt = "update khachhang SET LoaiKH = '" + LoaiKH + "' WHERE MaKH = '" + MaKH + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
}
