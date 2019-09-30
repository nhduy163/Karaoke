/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChucVuDAO;
import DTO.ChucVuDTO;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class ChucVuBUS {

    public static ArrayList<ChucVuDTO> ChucVuAll() {
        return ChucVuDAO.ChucVuAll();
    }

    public static ChucVuDTO getChucVu(int MaCV) {
        return ChucVuDAO.getChucVu(MaCV);
    }

    public static int getMaCV_last() {
        return ChucVuDAO.getMaCV_last();
    }
  
}
