/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TinhTrangLamViecDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class TinhTrangLamViecDAO {

    public static ArrayList<TinhTrangLamViecDTO> TTLV_All() {
        ArrayList<TinhTrangLamViecDTO> arr = new ArrayList<>();
        String sql = "select * from tinhtranglamviec";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                TinhTrangLamViecDTO dv = new TinhTrangLamViecDTO();
                dv.setMaTTLV(rs.getInt("MaTTLV"));
                dv.setTenTTLV(rs.getString("TenTTLV"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static TinhTrangLamViecDTO getTTLV(int MaTTLV) {
        String sql = "select * from tinhtranglamviec where MaTTLV = " + MaTTLV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        TinhTrangLamViecDTO dv = new TinhTrangLamViecDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();

            dv.setMaTTLV(rs.getInt("MaTTLV"));
            dv.setTenTTLV(rs.getString("TenTTLV"));
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }
}
