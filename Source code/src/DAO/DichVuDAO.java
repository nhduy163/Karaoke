/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DichVuDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class DichVuDAO {

    public static ArrayList<DichVuDTO> DichVuAll() {

        ArrayList<DichVuDTO> arr = new ArrayList<>();
        String sql = "select * from dichvu";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

                DichVuDTO dv = new DichVuDTO();
                dv.setMaDV(rs.getInt("MaDV"));
                dv.setTenDV(rs.getString("TenDV"));
                dv.setDonGia(rs.getDouble("DonGia"));
                dv.setDonVi(rs.getString("DonVi"));
                dv.setLoaiDV(rs.getInt("LoaiDV"));
                dv.setHinhAnh(rs.getBlob("HinhAnh"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static ArrayList<DichVuDTO> DichVu_Loai(int MaLDV) {

        ArrayList<DichVuDTO> arr = new ArrayList<>();
        String sql = "select * from dichvu where LoaiDV = " + MaLDV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

                DichVuDTO dv = new DichVuDTO();
                dv.setMaDV(rs.getInt("MaDV"));
                dv.setTenDV(rs.getString("TenDV"));
                dv.setDonGia(rs.getDouble("DonGia"));
                dv.setDonVi(rs.getString("DonVi"));
                dv.setLoaiDV(rs.getInt("LoaiDV"));
                dv.setHinhAnh(rs.getBlob("HinhAnh"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static DichVuDTO getDichVu(int MaDV) {

        String sql = "select * from dichvu where MaDV = " + MaDV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        DichVuDTO dv = new DichVuDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            dv.setMaDV(rs.getInt("MaDV"));
            dv.setTenDV(rs.getString("TenDV"));
            dv.setDonGia(rs.getDouble("DonGia"));
            dv.setDonVi(rs.getString("DonVi"));
            dv.setLoaiDV(rs.getInt("LoaiDV"));
            dv.setHinhAnh(rs.getBlob("HinhAnh"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }


    public static int getSoDV() {
        String sql = "select * from dichvu";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaDV");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }

    public static int getSoLDV() {
        String sql = "select * from loaidv";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaLDV");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }

    public boolean InsertLoaiDV(int MaLDV, String TenLDV) {
        boolean rs;
        String sql_stmt = "INSERT INTO loaidv";
        sql_stmt += " VALUES ('" + MaLDV + "','" + TenLDV + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public static Blob getHinhAnh(int MaDV) {
        String sql = "select HinhAnh from dichvu where MaDV = " + MaDV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getBlob("HinhAnh");
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return null;
    }
    
    public boolean DeleteDichVu(int MaDV){
        boolean rs;
        String sql_stmt = "delete from dichvu WHERE MaDV = '" + MaDV + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean InsertDichVu(int MaDV, String TenDV, double DonGia, String DonVi, int LoaiDV, String HinhAnh) throws SQLException, FileNotFoundException {
        if (HinhAnh != null) {
            File image = new File(HinhAnh);
            FileInputStream fis = new FileInputStream(image);
            MysqlDataAccessHelper my = new MysqlDataAccessHelper();
            my.open();
            PreparedStatement pre = my.getConn().prepareStatement("INSERT INTO dichvu VALUES(?,?,?,?,?,?)");
            pre.setInt(1, MaDV);
            pre.setString(2, TenDV);
            pre.setDouble(3, DonGia);
            pre.setString(4, DonVi);
            pre.setInt(5, LoaiDV);
            pre.setBlob(6, fis);
            //pre.setBinaryStream(6, (InputStream) fis, (int) image.length());
            int x = pre.executeUpdate();
            pre.close();
            my.close();
            return x >= 0;
        } else {
            MysqlDataAccessHelper my = new MysqlDataAccessHelper();
            my.open();
            PreparedStatement pre = my.getConn().prepareStatement("INSERT INTO dichvu VALUES(?,?,?,?,?,?)");
            pre.setInt(1, MaDV);
            pre.setString(2, TenDV);
            pre.setDouble(3, DonGia);
            pre.setString(4, DonVi);
            pre.setInt(5, LoaiDV);
            Blob blob = null;
            pre.setBlob(6, blob);
            //pre.setBinaryStream(6, (InputStream) fis, (int) image.length());
            int x = pre.executeUpdate();
            pre.close();
            my.close();
            return x >= 0;
        }
    }

    public boolean UpdateDichVu(int MaDV, String TenDV, double DonGia, String DonVi, int LoaiDV, String HinhAnh) throws FileNotFoundException, SQLException {
        if (HinhAnh != null) {
            File image = new File(HinhAnh);
            FileInputStream fis = new FileInputStream(image);
            MysqlDataAccessHelper my = new MysqlDataAccessHelper();
            my.open();
            PreparedStatement pre = my.getConn().prepareStatement("UPDATE dichvu SET TenDV = ?, DonGia = ?, DonVi = ?, LoaiDV = ?, HinhAnh = ? WHERE MaDV= ?");
            pre.setInt(6, MaDV);
            pre.setString(1, TenDV);
            pre.setDouble(2, DonGia);
            pre.setString(3, DonVi);
            pre.setInt(4, LoaiDV);
            pre.setBlob(5, fis);
            //pre.setBinaryStream(6, (InputStream) fis, (int) image.length());
            int x = pre.executeUpdate();
            pre.close();
            my.close();
            return x >= 0;
        } else {

            MysqlDataAccessHelper my = new MysqlDataAccessHelper();
            my.open();
            PreparedStatement pre = my.getConn().prepareStatement("UPDATE dichvu SET TenDV = ?, DonGia = ?, DonVi = ?, LoaiDV = ? WHERE MaDV= ?");
            pre.setInt(5, MaDV);
            pre.setString(1, TenDV);
            pre.setDouble(2, DonGia);
            pre.setString(3, DonVi);
            pre.setInt(4, LoaiDV);
            //pre.setBinaryStream(6, (InputStream) fis, (int) image.length());
            int x = pre.executeUpdate();
            pre.close();
            my.close();
            return x >= 0;
        }
    }
}
