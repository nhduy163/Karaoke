/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class NhanVienDAO {

    public static ArrayList<NhanVienDTO> NhanVienAll() {

        ArrayList<NhanVienDTO> arr = new ArrayList<>();
        String sql = "select * from nhanvien";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setManv(rs.getInt("MaNV"));
                nv.setTennv(rs.getString("TenNV"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setGioiTinh(rs.getInt("GioiTinh"));
                nv.setCMND(rs.getString("CMND"));
                nv.setSDT(rs.getString("SDT"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setLuong(rs.getInt("Luong"));
                nv.setChucVu(rs.getInt("ChucVu"));
                nv.setTinhTrang(rs.getInt("TinhTrang"));
                arr.add(nv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static NhanVienDTO getNhanVien(int MaNV) {
        String sql = "select * from nhanvien where MaNV = " + MaNV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        NhanVienDTO nv = new NhanVienDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);

            rs.next();
            nv.setManv(rs.getInt("MaNV"));
            nv.setTennv(rs.getString("TenNV"));
            nv.setNgaySinh(rs.getDate("NgaySinh"));
            nv.setGioiTinh(rs.getInt("GioiTinh"));
            nv.setCMND(rs.getString("CMND"));
            nv.setSDT(rs.getString("SDT"));
            nv.setDiaChi(rs.getString("DiaChi"));
            nv.setLuong(rs.getInt("Luong"));
            nv.setChucVu(rs.getInt("ChucVu"));
            nv.setTinhTrang(rs.getInt("TinhTrang"));
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return nv;
    }

    public boolean InsertNhanVien(int MaNV, String TenNV, Date NgaySinh, int GioiTinh, String CMND, String SDT,
            String DiaChi, int Luong, int ChucVu, int TinhTrang) {
        boolean rs;
        String sql_stmt = "INSERT INTO nhanvien";
        sql_stmt += " VALUES ('" + MaNV + "','" + TenNV + "','" + NgaySinh + "','" + GioiTinh + "','" + CMND + "','" + SDT + "','"
                + DiaChi + "','" + Luong + "','" + ChucVu + "','" + TinhTrang + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateNhanVien(int MaNV, String TenNV, java.sql.Date NgaySinh, int GioiTinh, String CMND, String SDT,
            String DiaChi, int Luong, int ChucVu, int TinhTrang) {
        boolean rs;
        String sql_stmt = "UPDATE nhanvien SET TenNV = '" + TenNV + "', NgaySinh = '" + NgaySinh + "', GioiTinh = '" + GioiTinh + "', CMND = '" + CMND + "', SDT = '" + SDT + "', DiaChi = '" + DiaChi + "', Luong = '" + Luong + "', ChucVu = '" + ChucVu + "', TinhTrang = '" + TinhTrang + "' WHERE Manv = '" + MaNV + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public static String getLamViec(int MaTTLV) {
        String sql = "select * from tinhtranglamviec where MaTTLV = " + MaTTLV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getString("TenTTLV");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }

    public static int getSoNV() {
        String sql = "select * from nhanvien";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaNV");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }
}
