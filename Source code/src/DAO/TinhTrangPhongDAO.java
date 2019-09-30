/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TinhTrangPhongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class TinhTrangPhongDAO {

    public static ArrayList<TinhTrangPhongDTO> TinhTrangPhong_All() {
        ArrayList<TinhTrangPhongDTO> arr = new ArrayList<>();
        String sql = "select * from tinhtrangphong";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                TinhTrangPhongDTO dv = new TinhTrangPhongDTO();
                dv.setMaTTP(rs.getInt("MaTTP"));
                dv.setTenTTP(rs.getString("TenTTP"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static TinhTrangPhongDTO getTinhTrangPhong(int MaTTP) {
        String sql = "select * from tinhtrangphong where MaTTP = " + MaTTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        TinhTrangPhongDTO dv = new TinhTrangPhongDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();

            dv.setMaTTP(rs.getInt("MaTTP"));
            dv.setTenTTP(rs.getString("TenTTP"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }
}
