/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author Hainguyen
 */
public class MysqlDataAccessHelper {

    public Connection conn = null;
    // xu ly ngoai le khi tuong tac voi csdl 

    public Connection getConn() {
        return conn;
    }

    
    
    public void displayError(SQLException ex) {
        System.out.println(" Error Message:" + ex.getMessage());
        System.out.println(" SQL State:" + ex.getSQLState());
        System.out.println(" Error Code:" + ex.getErrorCode());
    }

    public void open() {// mo ket noi den csdl 
        try {
            Driver driver = new org.gjt.mm.mysql.Driver();// nap driver 
            DriverManager.registerDriver(driver);// dang ky driver 

            String url = "jdbc:mysql://localhost:3306/karaoke?verifyServerCertificate=false&useSSL=true";
            conn = DriverManager.getConnection(url, "root", "admin123456");//tao ket noi den co so du lieu 
            System.out.println("ok");
        } catch (SQLException ex) {// xu ly ngoai le 
            displayError(ex);
        }
    }

    public void close() {// dong ket noi co so du lieu 
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            displayError(ex);
        }
    }
    //tao va thuc thi cac cau lenh sql 
    // cung cap thong tin trich rut tu csdl va cho phep truy xuat tung dong du lieu 

    public ResultSet executeQuery(String sql) {// danh cho cau lenh secect 
        ResultSet rs = null;
        try {
            Statement stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            displayError(ex);
        }
        return rs;
    }
    public boolean executeUpdate(String sql) {// danh cho cau lenh update
        boolean rs = true;
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            displayError(ex);
            rs = false;
        }
        return rs;
    }
//    public static void main(String[] args) {
//        MysqlDatAccsessHelper conn = new MysqlDatAccsessHelper();
//        conn.open();
//    }
}
