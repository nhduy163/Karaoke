/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhongDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class PhongDAO {

    public static ArrayList<PhongDTO> PhongAll() {

        ArrayList<PhongDTO> arr = new ArrayList<>();
        String sql = "select * from phong";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

                PhongDTO phong = new PhongDTO();
                phong.setMaPhong(rs.getInt("MaPhong"));
                phong.setTenPhong(rs.getString("TenPhong"));
                phong.setLoaiPhong(rs.getInt("MaLP"));
                phong.setTinhTrang(rs.getInt("MaTTP"));
                phong.setGiaGioNgay(rs.getDouble("GiaGioNgay"));
                phong.setGiaGioDem(rs.getDouble("GiaGioDem"));

                arr.add(phong);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static PhongDTO getPhong(int MaPhong) {
        String sql = "select * from phong where MaPhong = " + MaPhong;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        PhongDTO phong = new PhongDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            phong.setMaPhong(rs.getInt("MaPhong"));
            phong.setTenPhong(rs.getString("TenPhong"));
            phong.setLoaiPhong(rs.getInt("MaLP"));
            phong.setTinhTrang(rs.getInt("MaTTP"));
            phong.setGiaGioNgay(rs.getDouble("GiaGioNgay"));
            phong.setGiaGioDem(rs.getDouble("GiaGioDem"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return phong;
    }

    public boolean InsertPhong(int MaPhong, String TenPhong, int LoaiPhong, int TinhTrang, double GiaGioNgay, double GiaGioDem) {
        boolean rs;
        String sql_stmt = "INSERT INTO phong";
        sql_stmt += " VALUES ('" + MaPhong + "','" + TenPhong + "','" + LoaiPhong + "','" + TinhTrang + "','" + GiaGioNgay + "','" + GiaGioDem + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdatePhong(int MaPhong, String TenPhong, int LoaiPhong, int TinhTrang, double GiaGioNgay, double GiaGioDem) {
        boolean rs;
        String sql_stmt = "update phong SET TenPhong = '" + TenPhong + "', MaLP = '" + LoaiPhong + "', MaTTP = '" + TinhTrang + "', GiaGioNgay = '" + GiaGioNgay + "', GiaGioDem = '" + GiaGioDem + "' WHERE MaPhong = '" + MaPhong + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
    
    public boolean UpdateTTP(int MaPhong, int TinhTrang){
        boolean rs;
        String sql_stmt = "update phong SET MaTTP = '" + TinhTrang + "' WHERE MaPhong = '" + MaPhong + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public static int getSoPhong_loai(int x) {
        String sql = "select * from phong where MaLP = " + x;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("Ma");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }
    
    public static String getLoaiPhong(int MaLP) {
        String sql = "select * from loaiphong where MaLP = " + MaLP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getString("LoaiPhong");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }
    public static String getTinhTrang(int MaTTP) {
        String sql = "select * from tinhtrangphong where MaTTP = " + MaTTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getString("TenTTP");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }
}
