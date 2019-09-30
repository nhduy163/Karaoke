/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LoaiPhongDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class LoaiPhongDAO {

    public static ArrayList<LoaiPhongDTO> LoaiPhongAll() {
        ArrayList<LoaiPhongDTO> arr = new ArrayList<>();
        String sql = "select * from loaiphong";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                LoaiPhongDTO dv = new LoaiPhongDTO();
                dv.setMaLP(rs.getInt("MaLP"));
                dv.setLoaiPhong(rs.getString("LoaiPhong"));

                arr.add(dv);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static LoaiPhongDTO getLoaiPhong(int MaLP) {
        String sql = "select * from loaiphong where MaLP = " + MaLP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        LoaiPhongDTO dv = new LoaiPhongDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            dv.setMaLP(rs.getInt("MaLP"));
            dv.setLoaiPhong(rs.getString("LoaiPhong"));

        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return dv;
    }
}
