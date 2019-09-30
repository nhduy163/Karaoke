/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiDVDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class LoaiDVDAO {

    public static ArrayList<LoaiDVDTO> LoaiDVAll() {

        ArrayList<LoaiDVDTO> arr = new ArrayList<>();
        String sql = "select * from loaidv";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

                LoaiDVDTO dv = new LoaiDVDTO();
                dv.setMaLDV(rs.getInt("MaLDV"));
                dv.setTenLDV(rs.getString("TenLDV"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static LoaiDVDTO getLoaiDV(int MaLDV) {
        String sql = "select * from loaidv where MaLDV = " + MaLDV;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        LoaiDVDTO dv = new LoaiDVDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();

            dv.setMaLDV(rs.getInt("MaLDV"));
            dv.setTenLDV(rs.getString("TenLDV"));
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }

}
