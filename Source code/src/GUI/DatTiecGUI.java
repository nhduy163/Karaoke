/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.KhachHangBUS;
import BUS.PhongBUS;
import BUS.ThuePhongBUS;
import DAO.PhongDAO;
import DAO.ThuePhongDAO;
import DTO.KhachHangDTO;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Hainguyen
 */
public class DatTiecGUI extends javax.swing.JDialog {

    java.awt.Frame parent;

    /**
     * Creates new form DatTiecGUI
     *
     * @param parent
     * @param modal
     */
    public DatTiecGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadcbKhachHang();
        dcNgayDatTiec.setDate(new Date());

    }

    @Override
    public Frame getParent() {
        return parent;
    }

    public void setParent(Frame parent) {
        this.parent = parent;
    }

    public void loadcbKhachHang() {
        ArrayList<KhachHangDTO> arr = KhachHangBUS.KhachHangAll();
        KhachHangDTO dv;
        cbKhachHang.removeAllItems();
        for (int i = 0; i < arr.size(); i++) {
            dv = arr.get(i);
            String TenLDV = dv.getTenKH();
            int MaLDV = dv.getMaKH();

            cbKhachHang.addItem(new ComboItem(TenLDV, MaLDV));

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btXacNhan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbKhachHang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dcNgayDatTiec = new com.toedter.calendar.JDateChooser();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        spGioBatDau = new javax.swing.JSpinner(sm);
        btThemKhachHang = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfKhoangTG = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đặt tiệc");
        setBackground(new java.awt.Color(234, 234, 195));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Image/rsz_karaoke.png")));
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(234, 234, 195));

        jPanel2.setBackground(new java.awt.Color(234, 234, 195));

        btXacNhan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btXacNhan.setText("Xác nhận");
        btXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXacNhanActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btXacNhan)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btXacNhan)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Khách hàng:");

        cbKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhachHangActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Giờ tổ chức:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Ngày đặt :");

        dcNgayDatTiec.setDateFormatString("yyyy-MM-dd");

        JSpinner.DateEditor de = new JSpinner.DateEditor(spGioBatDau,"HH:mm:ss");
        spGioBatDau.setEditor(de);

        btThemKhachHang.setText("Thêm khách hàng");
        btThemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemKhachHangActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Khoảng t/g:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("h");

        tfKhoangTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKhoangTGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btThemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbKhachHang, 0, 210, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcNgayDatTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(tfKhoangTG, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcNgayDatTiec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfKhoangTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btThemKhachHang)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXacNhanActionPerformed
        if (ThuePhongBUS.checkNgayBatDau(dcNgayDatTiec)) {
            ManHinhChinh mhc = (ManHinhChinh) this.getParent();
            int MaTP = ThuePhongBUS.getMaTP() + 1;
            int MaPhong = mhc.getMaPhong();
            Object item = cbKhachHang.getSelectedItem();
            int MaKH = ((ComboItem) item).getValue();
            DateFormat only_day = new SimpleDateFormat("dd");
            DateFormat only_month = new SimpleDateFormat("MM");
            DateFormat only_year = new SimpleDateFormat("yyyy");
            int day = Integer.parseInt(only_day.format(dcNgayDatTiec.getDate()));
            int mont = Integer.parseInt(only_month.format(dcNgayDatTiec.getDate()));
            int year = Integer.parseInt(only_year.format(dcNgayDatTiec.getDate()));
            String sp = spGioBatDau.getValue().toString();
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            try {
                cal.setTime(sdf.parse(sp));// all done
            } catch (ParseException ex) {
                Logger.getLogger(DatTiecGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            int hour = cal.getTime().getHours();
            int min = cal.getTime().getMinutes();
            int sec = cal.getTime().getSeconds();

            cal.set(year, mont - 1, day, hour, min, sec);
            Timestamp GioBatDau = new Timestamp(cal.getTimeInMillis());
            System.out.println("GioBatDau = " + GioBatDau);
            Timestamp GioKetThuc = new Timestamp(new Date().getTime());
            int TinhTrangThue = 2;
            double TienTraTruoc = ThuePhongBUS.getTienTraTruoc(MaTP);
            int KhoangTG = Integer.parseInt(tfKhoangTG.getText());
            if (ThuePhongBUS.checkGioBatDau(GioBatDau, KhoangTG, MaPhong)) {
                ThuePhongDAO tp = new ThuePhongDAO();
                boolean chk = tp.InsertThuePhong(MaTP, MaPhong, MaKH, GioBatDau, GioKetThuc, TinhTrangThue, TienTraTruoc, KhoangTG);
                if (chk == true) {
                    new ThuePhongDAO().UpdateTienTraTruoc(MaTP, ThuePhongBUS.getTienTraTruoc(MaTP));
                    mhc.loadListPhong();
                    if (PhongBUS.getPhong(MaPhong).getTinhTrang() == 1) {
                        new PhongDAO().UpdateTTP(MaPhong, 2);
                    }
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    this.setVisible(false);
                    mhc.loadPhong(MaPhong);
                    mhc.loadThuePhong(MaPhong);
                    mhc.loadButton(PhongBUS.getPhong(MaPhong).getTinhTrang());
                    mhc.loadtbThuePhong(MaPhong);
                    new QuanLyDatTiecGUI(mhc, true, MaTP).setVisible(true);
                    
                } else {

                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ngày đặt tiệc không đúng!");
        }
    }//GEN-LAST:event_btXacNhanActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKhachHangActionPerformed

    private void btThemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemKhachHangActionPerformed
        KhachHangGUI_Them them = new KhachHangGUI_Them(this, true);
        them.setVisible(true);
        loadcbKhachHang();
    }//GEN-LAST:event_btThemKhachHangActionPerformed

    private void tfKhoangTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKhoangTGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKhoangTGActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatTiecGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatTiecGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatTiecGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatTiecGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatTiecGUI dialog = new DatTiecGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btThemKhachHang;
    private javax.swing.JButton btXacNhan;
    private javax.swing.JComboBox<ComboItem> cbKhachHang;
    private com.toedter.calendar.JDateChooser dcNgayDatTiec;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSpinner spGioBatDau;
    private javax.swing.JTextField tfKhoangTG;
    // End of variables declaration//GEN-END:variables
}