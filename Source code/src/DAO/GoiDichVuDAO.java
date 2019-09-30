/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.GoiDichVuDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class GoiDichVuDAO {

    public static ArrayList<GoiDichVuDTO> GoiDichVuAll(int MaTP) {

        ArrayList<GoiDichVuDTO> arr = new ArrayList<>();
        String sql = "select * from goidichvu where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

               GoiDichVuDTO tp = new GoiDichVuDTO();
                tp.setMaGDV(rs.getInt("MaGDV"));
                tp.setMaTP(rs.getInt("MaTP"));
                tp.setMaDV(rs.getInt("MaDV"));
                tp.setSL(rs.getInt("SL"));

                arr.add(tp);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }
    
    public static String getDichVu(int MaDV) {
        String sql = "select * from dichvu where MaDV = " + MaDV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getString("TenDV");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }
    
    public static double getTongTien(int MaTP){
        String sql = "select * from goidichvu where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        double TongTien = 0;
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                double DonGia = DichVuDAO.getDichVu(rs.getInt("MaDV")).getDonGia();
                int SL = rs.getInt("SL");
                TongTien += DonGia*SL;
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return TongTien;
    }
    
    public static GoiDichVuDTO getGoiDichVu(int MaGDV) {
        String sql = "select * from goidichvu where MaGDV = " + MaGDV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        GoiDichVuDTO kh = new GoiDichVuDTO();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            kh.setMaGDV(rs.getInt("MaGDV"));
            kh.setMaTP(rs.getInt("MaTP"));
            kh.setMaDV(rs.getInt("MaDV"));
            kh.setSL(rs.getInt("SL"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return kh;
    }

    public boolean InsertGoiDV(int MaGDV, int MaTP, int MaDV, int SL) {
        boolean rs;
        String sql_stmt = "INSERT INTO goidichvu";
        sql_stmt += " VALUES ('" + MaGDV + "','" + MaTP + "','" + MaDV + "','" + SL + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
    
    public boolean UpdateSoLuong(int MaGDV, int SL){
        boolean rs;
        String sql_stmt = "update goidichvu SET SL = '" + SL + "' WHERE MaGDV = '" + MaGDV + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
    public boolean DeleteGDV(int MaGDV){
        boolean rs;
        String sql_stmt = "delete from goidichvu WHERE MaGDV = '" + MaGDV + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public static int getSoGoiDV() {
        String sql = "select * from goidichvu";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaGDV");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }
}
