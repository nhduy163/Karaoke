/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiKHDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class LoaiKHDAO {

    public static ArrayList<LoaiKHDTO> LoaiKHAll() {
        ArrayList<LoaiKHDTO> arr = new ArrayList<>();
        String sql = "select * from loaiKH";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                LoaiKHDTO dv = new LoaiKHDTO();
                dv.setMaLKH(rs.getInt("MaLKH"));
                dv.setLoaiKH(rs.getString("LoaiKH"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static LoaiKHDTO getLoaiKH(int MaLKH) {
        String sql = "select * from loaiKH where MaLKH = " + MaLKH;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        LoaiKHDTO dv = new LoaiKHDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            dv.setMaLKH(rs.getInt("MaLKH"));
            dv.setLoaiKH(rs.getString("LoaiKH"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }
}
