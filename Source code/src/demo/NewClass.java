/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.text.SimpleDateFormat;

/**
 *
 * @author Hainguyen
 */
public class NewClass {

    public static void main(String[] args) {
        int x = 2;
        float y = (float) 2.35;
        System.out.println("" + (x + y));
        new SimpleDateFormat("dd/MM/yyyy").format("$P{NgayBatDau}");
    }
}
