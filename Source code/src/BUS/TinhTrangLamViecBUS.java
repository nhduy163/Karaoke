/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.TinhTrangLamViecDAO;
import DTO.TinhTrangLamViecDTO;
import java.util.ArrayList;

/**
 *
 * @author Hainguyen
 */
public class TinhTrangLamViecBUS {

    public static ArrayList<TinhTrangLamViecDTO> TTLV_All() {
        return TinhTrangLamViecDAO.TTLV_All();
    }

    public static TinhTrangLamViecDTO getTTLV(int MaTTLV) {
        return TinhTrangLamViecDAO.getTTLV(MaTTLV);
    }
}
