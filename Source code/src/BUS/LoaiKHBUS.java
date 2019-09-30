/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiKHDAO;
import DTO.LoaiKHDTO;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class LoaiKHBUS {

    public static ArrayList<LoaiKHDTO> LoaiKHAll() {
        return LoaiKHDAO.LoaiKHAll();
    }

    public static LoaiKHDTO getLoaiKH(int MaLKH) {
        return LoaiKHDAO.getLoaiKH(MaLKH);
    }
}
