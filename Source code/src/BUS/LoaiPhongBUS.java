/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiPhongDAO;
import DTO.LoaiPhongDTO;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class LoaiPhongBUS {

    public static ArrayList<LoaiPhongDTO> LoaiPhongAll() {
        return LoaiPhongDAO.LoaiPhongAll();
    }

    public static LoaiPhongDTO getLoaiPhong(int MaLP) {
        return LoaiPhongDAO.getLoaiPhong(MaLP);
    }
}
