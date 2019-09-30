/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.LoaiDVDAO;
import DTO.LoaiDVDTO;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class LoaiDVBUS {

    public static ArrayList<LoaiDVDTO> LoaiDVAll() {
        return LoaiDVDAO.LoaiDVAll();
    }

    public static LoaiDVDTO getLoaiDV(int MaLDV) {
        return LoaiDVDAO.getLoaiDV(MaLDV);
    }
}
