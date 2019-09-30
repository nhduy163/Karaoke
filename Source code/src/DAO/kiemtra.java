/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Scanner;

/**
 *
 * @author Hainguyen
 */
public class kiemtra {

    public static void main(String[] args) {
        int start  =1;
        int end  =2;
        String sql = "select * from hoadon where NgayHD >= '" + start + "' and NgayHD <= '" + end + "';";
        System.out.println(""+sql);
    }
}
