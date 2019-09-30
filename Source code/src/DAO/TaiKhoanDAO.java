/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class TaiKhoanDAO {

    public static ArrayList<TaiKhoanDTO> TaiKHoanAll() {

        ArrayList<TaiKhoanDTO> arr = new ArrayList<>();
        String sql = "select * from taikhoan";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

                TaiKhoanDTO dv = new TaiKhoanDTO();
                dv.setMaTK(rs.getInt("MaTK"));
                dv.setTenTK(rs.getString("TenTK"));
                dv.setMatKhau(rs.getString("MatKhau"));
                dv.setMaNV(rs.getInt("Manv"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static TaiKhoanDTO getTaiKhoan(int MaTK) {
        String sql = "select * from taikhoan where MaTK = "+MaTK;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        TaiKhoanDTO dv = new TaiKhoanDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);

            rs.next();
            dv.setMaTK(rs.getInt("MaTK"));
            dv.setTenTK(rs.getString("TenTK"));
            dv.setMatKhau(rs.getString("MatKhau"));
            dv.setMaNV(rs.getInt("Manv"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }

    public static String getNhanVien(int Manv) {
        String sql = "select * from nhanvien where MaNV = " + Manv;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getString("TenNV");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }

    public static int getMaNV(int MaTK) {
        String sql = "select * from taikhoan where MaTK = " + MaTK;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Manv");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }

    public static int getSoTK() {
        String sql = "select * from taikhoan";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaTK");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }

    public boolean InsertTaiKhoan(int MaTK, String TenTK, String MatKhau, int MaNV) {
        boolean rs;
        String sql_stmt = "INSERT INTO taikhoan";
        sql_stmt += " VALUES ('" + MaTK + "','" + TenTK + "','" + MatKhau + "','" + MaNV + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateTaiKhoan(int MaTK, String TenTK, String MatKhau, int MaNV) {
        boolean rs;
        String sql_stmt = "update taikhoan SET TenTK = '" + TenTK + "', MatKhau = '" + MatKhau + "', Manv = '" + MaNV + "' WHERE MaTK = '" + MaTK + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
    public boolean DeleteTaiKhoan(int MaTK){
        boolean rs;
        String sql_stmt = "delete from taikhoan WHERE MaTK = '" + MaTK + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
}
