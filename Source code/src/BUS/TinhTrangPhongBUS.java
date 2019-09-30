/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TinhTrangPhongDAO;
import DTO.TinhTrangPhongDTO;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class TinhTrangPhongBUS {

    public static ArrayList<TinhTrangPhongDTO> TinhTrangPhong_All() {
        return TinhTrangPhongDAO.TinhTrangPhong_All();
    }

    public static TinhTrangPhongDTO getTinhTrangPhong(int MaTTP) {
        return TinhTrangPhongDAO.getTinhTrangPhong(MaTTP);
    }
}
