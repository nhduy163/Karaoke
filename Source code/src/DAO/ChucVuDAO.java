/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChucVuDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class ChucVuDAO {

    public static ArrayList<ChucVuDTO> ChucVuAll() {
        ArrayList<ChucVuDTO> arr = new ArrayList<>();
        String sql = "select * from chucvu";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                ChucVuDTO cv = new ChucVuDTO();
                cv.setTenCV(rs.getString("TenCV"));
                cv.setMaCV(rs.getInt("MaCV"));
                arr.add(cv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static ChucVuDTO getChucVu(int MaCV) {
        String sql = "select TenCV from chucvu where MaCV = " + MaCV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        ChucVuDTO cv = new ChucVuDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            cv.setTenCV(rs.getString("TenCV"));
            cv.setMaCV(rs.getInt("MaCV"));
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return cv;
    }

    public static int getMaCV_last() {
        String sql = "select * from chucvu";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaCV");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }    

    public static boolean InsertChucVu(int MaCV, String TenCV) {
        boolean rs;
        String sql_stmt = "INSERT INTO chucvu";
        sql_stmt += " VALUES ('" + MaCV + "','" + TenCV + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

}
