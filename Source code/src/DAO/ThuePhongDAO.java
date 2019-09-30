/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import BUS.GoiDichVuBUS;
import BUS.PhongBUS;
import BUS.ThuePhongBUS;
import DTO.ThuePhongDTO;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 *
 * @author Hainguyen
 */
public class ThuePhongDAO {

    public static ThuePhongDTO getThuePhong(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        ThuePhongDTO tp = new ThuePhongDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();

            tp.setMaTP(rs.getInt("MaTP"));
            tp.setMaPhong(rs.getInt("MaPhong"));
            tp.setMaKH(rs.getInt("MaKH"));

            tp.setGioBatDau(rs.getTimestamp("GioBatDau"));
            tp.setGioKetThuc(rs.getTimestamp("GioKetThuc"));

            tp.setTinhTrangThue(rs.getInt("TinhTrangThue"));
            tp.setTienTraTruoc(rs.getDouble("TienTraTruoc"));
            tp.setKhoangTG(rs.getInt("KhoangTG"));

//            DateTime startTime = new DateTime(start), endTime = new DateTime(end);
//            Period p = new Period(startTime,endTime);          
//            DateTime dt = new DateTime();
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return tp;
    }

    public static ThuePhongDTO getThuePhong_MaPhong_1(int MaPhong) {
        String sql = "select * from thuephong where MaPhong = " + MaPhong + " and TinhTrangThue = " + 1;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        ThuePhongDTO tp = new ThuePhongDTO();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            rs.next();
            tp.setMaTP(rs.getInt("MaTP"));
            tp.setMaPhong(rs.getInt("MaPhong"));
            tp.setMaKH(rs.getInt("MaKH"));

            tp.setGioBatDau(rs.getTimestamp("GioBatDau"));
            tp.setGioKetThuc(rs.getTimestamp("GioKetThuc"));

            tp.setTinhTrangThue(rs.getInt("TinhTrangThue"));
            tp.setTienTraTruoc(rs.getDouble("TienTraTruoc"));
            tp.setKhoangTG(rs.getInt("KhoangTG"));

//            DateTime startTime = new DateTime(start), endTime = new DateTime(end);
//            Period p = new Period(startTime,endTime);          
//            DateTime dt = new DateTime();
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return tp;
    }

    public static ArrayList<ThuePhongDTO> ThuePhongAll(int MaPhong) {

        ArrayList<ThuePhongDTO> arr = new ArrayList<>();
        String sql = "select * from thuephong where MaPhong = " + MaPhong;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {

                ThuePhongDTO tp = new ThuePhongDTO();
                tp.setMaTP(rs.getInt("MaTP"));
                tp.setMaPhong(rs.getInt("MaPhong"));
                tp.setMaKH(rs.getInt("MaKH"));

//                Timestamp timestamp = rs.getTimestamp("GioBatDau");
//                Date start = new Date(timestamp.getTime());
                tp.setGioBatDau(rs.getTimestamp("GioBatDau"));

//                timestamp = rs.getTimestamp("GioKetThuc");
//                Date end = new Date(timestamp.getTime());
                tp.setGioKetThuc(rs.getTimestamp("GioKetThuc"));

                tp.setTinhTrangThue(rs.getInt("TinhTrangThue"));
                tp.setTienTraTruoc(rs.getDouble("TienTraTruoc"));
                tp.setKhoangTG(rs.getInt("KhoangTG"));

                arr.add(tp);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static ArrayList<ThuePhongDTO> getThuePhong_MaPhong_2(int MaPhong) {

        ArrayList<ThuePhongDTO> arr = new ArrayList<>();
        String sql = "select * from thuephong where MaPhong = " + MaPhong + " and TinhTrangThue = " + 2;
        System.out.println("" + sql);//nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();

        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            while (rs.next()) {
                ThuePhongDTO tp = new ThuePhongDTO();
                tp.setMaTP(rs.getInt("MaTP"));
                tp.setMaPhong(rs.getInt("MaPhong"));
                tp.setMaKH(rs.getInt("MaKH"));

//                Timestamp timestamp = rs.getTimestamp("GioBatDau");
//                Date start = new Date(timestamp.getTime());
                tp.setGioBatDau(rs.getTimestamp("GioBatDau"));

//                timestamp = rs.getTimestamp("GioKetThuc");
//                Date end = new Date(timestamp.getTime());
                tp.setGioKetThuc(rs.getTimestamp("GioKetThuc"));

                tp.setTinhTrangThue(rs.getInt("TinhTrangThue"));
                tp.setTienTraTruoc(rs.getDouble("TienTraTruoc"));
                tp.setKhoangTG(rs.getInt("KhoangTG"));

                arr.add(tp);
            }
        } catch (SQLException ex) {
            my.displayError(ex);
        } finally {
            my.close();
        }
        return arr;
    }

    public static int getMaTP() {
        String sql = "select * from thuephong";  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaTP");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Error getting row count");
        }
        return 0;
    }

    public static int getKhachHang(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                return resultset.getInt("MaKH");
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Không tìm thấy khách hàng");
        }
        return 0;
    }

    public static Period getSoGio(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                ThuePhongDTO tp = ThuePhongBUS.getThuePhong(MaTP);
                Timestamp GioBatDau = tp.getGioBatDau();
                DateTime startTime = new DateTime(GioBatDau);
                Timestamp GioKetThuc = tp.getGioKetThuc();
                DateTime endTime = new DateTime(GioKetThuc);
                return new Period(startTime, endTime);
            } else {
                return null; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
            System.out.println("Không tín được số giờ");
        }
        return null;
    }

    public static float getSoGioNgay(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                ThuePhongDTO tp = ThuePhongBUS.getThuePhong(MaTP);
                if (tp.getGioBatDau().getHours() >= 18 || tp.getGioBatDau().getHours() < 6) {
                    System.out.println("tp.getGioBatDau().getHours() >= 18 || tp.getGioBatDau().getHours() < 6");
                    return 0;
                } else if (tp.getGioKetThuc().getHours() >= 18 || tp.getGioKetThuc().getHours() < 6) {
                    DateTime startTime = new DateTime(tp.getGioBatDau());
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 18);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    Timestamp GioKetThuc = new Timestamp(cal.getTimeInMillis());
                    DateTime endTime = new DateTime(GioKetThuc);
                    Period p = new Period(startTime, endTime);
                    System.out.println("tp.getGioKetThuc().getHours() >= 18 || tp.getGioKetThuc().getHours() < 6: " + p);
                    return (float) (p.getHours() + (float) (p.getMinutes()) / 60);
                } else {
                    DateTime startTime = new DateTime(tp.getGioBatDau());
                    DateTime endTime = new DateTime(tp.getGioKetThuc());
                    Period p = new Period(startTime, endTime);
                    System.out.println("else : " + p);
                    return (float) (p.getHours() + (float) (p.getMinutes()) / 60);
                }
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public static float getSoGioDem(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet resultset = my.executeQuery(sql);
            if (resultset.last()) {
                ThuePhongDTO tp = ThuePhongBUS.getThuePhong(MaTP);
                if (tp.getGioKetThuc().getHours() < 18 && tp.getGioKetThuc().getHours() >= 6) {

                    return 0;
                } else if (tp.getGioBatDau().getHours() < 18 && tp.getGioBatDau().getHours() >= 6 && tp.getGioKetThuc().getHours() >= 6) {
                    DateTime endTime = new DateTime(tp.getGioKetThuc());
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 18);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    Timestamp GioBatDau = new Timestamp(cal.getTimeInMillis());
                    DateTime startTime = new DateTime(GioBatDau);
                    Period p = new Period(startTime, endTime);
                    return (float) (p.getHours() + (float) (p.getMinutes()) / 60);
                } else if (tp.getGioBatDau().getHours() < 18 && tp.getGioBatDau().getHours() >= 6 && tp.getGioKetThuc().getHours() < 6) {
                    DateTime endTime = new DateTime(tp.getGioKetThuc());
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    Timestamp GioBatDau = new Timestamp(cal.getTimeInMillis());
                    DateTime startTime = new DateTime(GioBatDau);
                    Period p = new Period(startTime, endTime);
                    return (float) (12 + p.getHours() + (float) (p.getMinutes()) / 60);
                } else if (tp.getGioKetThuc().getHours() >= 6) {
                    DateTime startTime = new DateTime(tp.getGioBatDau());
                    DateTime endTime = new DateTime(tp.getGioKetThuc());
                    Period p = new Period(startTime, endTime);
                    return (float) (p.getHours() + (float) (p.getMinutes()) / 60);
                } else {
                    DateTime startTime = new DateTime(tp.getGioBatDau());
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 24);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    Timestamp Gioketthuc = new Timestamp(cal.getTimeInMillis());
                    DateTime endTime = new DateTime(Gioketthuc);
                    Period p = new Period(startTime, endTime);

                    Calendar cal1 = Calendar.getInstance();
                    cal1.set(Calendar.HOUR_OF_DAY, 0);
                    cal1.set(Calendar.MINUTE, 0);
                    cal1.set(Calendar.SECOND, 0);
                    Timestamp GioBatDau = new Timestamp(cal1.getTimeInMillis());
                    DateTime startTime1 = new DateTime(GioBatDau);
                    DateTime endTime1 = new DateTime(tp.getGioKetThuc());
                    Period p1 = new Period(startTime1, endTime1);
                    return (float) (p.getHours() + (float) (p.getMinutes()) / 60) + (float) (p1.getHours() + (float) (p1.getMinutes()) / 60);
                }
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public static double getTienGio(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            if (rs.last()) {
                double GiaGioNgay = PhongBUS.getPhong(rs.getInt("MaPhong")).getGiaGioNgay();
                double GiaGioDem = PhongBUS.getPhong(rs.getInt("MaPhong")).getGiaGioDem();
                float SoGioNgay = ThuePhongBUS.getSoGioNgay(MaTP);
                float SoGioDem = ThuePhongBUS.getSoGioDem(MaTP);

                return SoGioNgay * GiaGioNgay + SoGioDem * GiaGioDem;
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public static double getTienTraTruoc(int MaTP) {
        String sql = "select * from thuephong where MaTP = " + MaTP;  //nhớ đổi tên table
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            ResultSet rs = my.executeQuery(sql);
            if (rs.last()) {

                double TienGio = ThuePhongBUS.getThuePhong(MaTP).getKhoangTG() * PhongBUS.getPhong(ThuePhongBUS.getThuePhong(MaTP).getMaPhong()).getGiaGioDem();
                double TienDV = GoiDichVuBUS.getTongTien(MaTP);

                return (TienGio + TienDV) * 0.2;
            } else {
                return 0; //just cus I like to always do some kinda else statement.
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public boolean InsertThuePhong(int MaTP, int MaPhong, int MaKH, Timestamp GioBatDau, Timestamp GioKetThuc, int TinhTrangThue, double TienTraTruoc, int KhoangTG) {
        boolean rs;
        String sql_stmt = "INSERT INTO thuephong";
        sql_stmt += " VALUES ('" + MaTP + "','" + MaPhong + "','" + MaKH + "','" + GioBatDau + "','" + GioKetThuc + "','" + TinhTrangThue + "','" + TienTraTruoc + "','" + KhoangTG + "')";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateGioBatDau(int MaTP, Timestamp GioBatdau) {
        boolean rs;
        String sql_stmt = "update thuephong SET GioBatDau = '" + GioBatdau + "' WHERE MaTP = '" + MaTP + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateGioKetThuc(int MaTP, Timestamp GioKetThuc) {
        boolean rs;
        String sql_stmt = "update thuephong SET GioKetThuc = '" + GioKetThuc + "' WHERE MaTP = '" + MaTP + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateTienTraTruoc(int MaTP, double TienTraTruoc) {
        boolean rs;
        String sql_stmt = "update thuephong SET TienTraTruoc = '" + TienTraTruoc + "' WHERE MaTP = '" + MaTP + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateKhachHang(int MaTP, int MaKH) {
        boolean rs;
        String sql_stmt = "update thuephong SET MaKH = '" + MaKH + "' WHERE MaTP = '" + MaTP + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }

    public boolean UpdateTinhTrangThue(int MaTP, int MaTTT) {
        boolean rs;
        String sql_stmt = "update thuephong SET TinhTrangThue = '" + MaTTT + "' WHERE MaTP = '" + MaTP + "'";
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        my.open();
        rs = my.executeUpdate(sql_stmt);
        return rs;
    }
}
