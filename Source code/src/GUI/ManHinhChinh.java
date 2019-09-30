/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.DichVuBUS;
import BUS.GoiDichVuBUS;
import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhongBUS;
import BUS.ThuePhongBUS;
import DAO.GoiDichVuDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.MysqlDataAccessHelper;
import DAO.PhongDAO;
import DAO.ThuePhongDAO;
import DTO.GoiDichVuDTO;
import DTO.PhongDTO;
import DTO.ThuePhongDTO;
import List.ImgsNText;
import List.Renderer;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Hainguyen
 */
public class ManHinhChinh extends javax.swing.JFrame {

    java.awt.Frame parent;
    int MaNV;

    /**
     * Creates new form ManHinhChinh2
     *
     * @param MaNV
     */
    public ManHinhChinh(int MaNV) {
        initComponents();
        loadListPhong();
        this.setMaNV(MaNV);
        loadNhanVien();
        loadButtonQL(MaNV);
        btTimKH.setVisible(false);
        btDatTiec.setVisible(false);
        btHuyTiec.setVisible(false);
        btNhanPhong.setVisible(false);
        btThanhToan.setVisible(false);
        btThuePhong.setVisible(false);
        btThemDichVu.setVisible(false);
        btDatCoc.setVisible(false);
        btXoaDichVu.setVisible(false);
        btDoiSoLuong.setVisible(false);
        //currentTime();
    }
//    public ManHinhChinh() {
//        initComponents();
//        loadListPhong();
//        btTimKH.setVisible(false);
//        btDatTiec.setVisible(false);
//        btHuyTiec.setVisible(false);
//        btNhanPhong.setVisible(false);
//        btThanhToan.setVisible(false);
//        btThemDichVu.setVisible(false);
//        btDatCoc.setVisible(false);
//        btXoaDichVu.setVisible(false);
//        btDoiSoLuong.setVisible(false);
//        currentTime();
//    }

    private ManHinhChinh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void XuatHoaDon(int MaHD) {
        MysqlDataAccessHelper my = new MysqlDataAccessHelper();
        try {
            my.open();
            String sql = "SELECT *\n"
                    + "from hoadon hd\n"
                    + "inner join nhanvien nv on hd.MaNV = nv.MaNV\n"
                    + "inner join thuephong tp on tp.MaTP = hd.MaTP\n"
                    + "inner join phong p on p.MaPhong = tp.MaPhong\n"
                    + "inner join khachhang kh on kh.MaKH = tp.MaKH\n"
                    + "left join goidichvu gdv on gdv.MaTP = tp.MaTP\n"
                    + "left join dichvu dv on gdv.MaDV = dv.MaDV\n"
                    + "where hd.MaHD = ";
            Map parametersMap = new HashMap();
            InputStream is = getClass().getResourceAsStream("rpHoaDon.jrxml");

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(sql + MaHD);

            JasperDesign jasperDesign = JRXmlLoader.load(is);
            jasperDesign.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, my.getConn());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
        }
    }

    public void currentTime() {
        int timeRun = 0;
        new Thread() {
            @Override
            public void run() {
                while (timeRun == 0) {
                    Calendar cal = new GregorianCalendar();
                    int sec = cal.get(Calendar.SECOND);
                    int min = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int AM_PM = cal.get(Calendar.AM_PM);

                    String day;
                    if (AM_PM == 1) {
                        day = "PM";
                    } else {
                        day = "AM";
                    }
                    //lbhour.setText(hour + ":" + min + ":" + sec + " " + day);
                }
            }
        }.start();
    }

    public ImageIcon getIcon(int MaTTP) {
        ImageIcon icon = null;
        switch (MaTTP) {
            case 1:
                icon = new ImageIcon(getClass().getResource("/Image/phonghat.png"));
                break;
            case 2:
                icon = new ImageIcon(getClass().getResource("/Image/dadat.png"));
                break;
            case 3:
                icon = new ImageIcon(getClass().getResource("/Image/dangthue.png"));
                break;
            case 4:
                icon = new ImageIcon(getClass().getResource("/Image/khonghoatdong.png"));
                break;
        }
        return icon;
    }

    public void loadListPhong() {
        ArrayList<PhongDTO> arr = PhongBUS.PhongAll();
        PhongDTO p;
        DefaultListModel dm1 = new DefaultListModel();
        DefaultListModel dm2 = new DefaultListModel();
        DefaultListModel dm3 = new DefaultListModel();
        for (int i = 0; i < arr.size(); i++) {
            p = arr.get(i);
            switch (p.getLoaiPhong()) {
                case 1:
                    dm1.addElement(new ImgsNText(p.getTenPhong(), this.getIcon(p.getTinhTrang()), p.getMaPhong()));
                    listPhongHat5.setCellRenderer(new Renderer());
                    listPhongHat5.setModel(dm1);
                    break;
                case 2:
                    dm2.addElement(new ImgsNText(p.getTenPhong(), this.getIcon(p.getTinhTrang()), p.getMaPhong()));
                    listPhongHat10.setCellRenderer(new Renderer());
                    listPhongHat10.setModel(dm2);
                    break;
                default:
                    dm3.addElement(new ImgsNText(p.getTenPhong(), this.getIcon(p.getTinhTrang()), p.getMaPhong()));
                    listPhongHat15.setCellRenderer(new Renderer());
                    listPhongHat15.setModel(dm3);
                    break;
            }
        }
    }

    public void loadThuePhong(int MaPhong) {
        int TinhTrangPhong = PhongBUS.getPhong(MaPhong).getTinhTrang();
        if (TinhTrangPhong != 3) {
            lbMa.setText(null);
            lbGioBatDau.setText(null);
            lbDatCoc.setText(null);
            lbKhachHang.setText(null);
            lbTienDV.setText(null);
            lbKhoangTG.setText(null);
        } else {
            ThuePhongDTO tp = ThuePhongBUS.getThuePhong_MaPhong_1(MaPhong);
            lbMa.setText("" + tp.getMaTP());
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss  dd/MM/yyyy");
            lbGioBatDau.setText(dateFormat.format(tp.getGioBatDau()));
            lbDatCoc.setText("" + tp.getTienTraTruoc());
            lbKhachHang.setText(KhachHangBUS.getKhachHang(tp.getMaKH()).getTenKH());
            lbTienDV.setText("" + GoiDichVuBUS.getTongTien(tp.getMaTP()));
            lbKhoangTG.setText(""+tp.getKhoangTG());
        }
    }

    public void loadPhong(int MaPhong) {
        PhongDTO phong = PhongBUS.getPhong(MaPhong);
        lbMaPhong.setText("" + phong.getMaPhong());
        lbTenPhong.setText(phong.getTenPhong());
        lbLoaiPhong.setText(PhongBUS.getLoaiPhong(phong.getLoaiPhong()));
        lbTinhTrang.setText(PhongBUS.getTinhTrang(phong.getTinhTrang()));
        lbGioNgay.setText(""+PhongBUS.getPhong(MaPhong).getGiaGioNgay());
        lbGioDem.setText(""+PhongBUS.getPhong(MaPhong).getGiaGioDem());
    }

    public void loadNhanVien() {
        lbTenNV.setText(NhanVienBUS.getNhanVien(MaNV).getTennv());
    }

    public void loadButtonQL(int MaNV) {
        int ChucVu = NhanVienBUS.getNhanVien(MaNV).getChucVu();
        System.out.println("chuc vu:" + ChucVu);
        if (ChucVu == 1) {
            btDichVu.setVisible(true);
            btThongKe.setVisible(true);
            btHoaDon.setVisible(true);
            btKhachHang.setVisible(true);
            btNhanVien.setVisible(true);
            btPhong.setVisible(true);
            btTaiKhoan.setVisible(true);
        } else {
            btDichVu.setVisible(true);
            btThongKe.setVisible(true);
            btHoaDon.setVisible(true);
            btKhachHang.setVisible(true);
            btNhanVien.setVisible(false);
            btPhong.setVisible(false);
            btTaiKhoan.setVisible(false);
        }
    }

    public void loadButton_2() {
        btTimKH.setVisible(true);
        btDatTiec.setVisible(true);
        btHuyTiec.setVisible(true);
        btNhanPhong.setVisible(true);
        btThuePhong.setVisible(true);
        btThanhToan.setVisible(false);
        btThemDichVu.setVisible(true);
        btDatCoc.setVisible(true);
        btXoaDichVu.setVisible(false);
        btDoiSoLuong.setVisible(false);
    }

    public void loadButton(int MaTTP) {
        switch (MaTTP) {
            case 1:
                btTimKH.setVisible(false);
                btDatTiec.setVisible(true);
                btHuyTiec.setVisible(false);
                btNhanPhong.setVisible(false);
                btThuePhong.setVisible(true);
                btThanhToan.setVisible(false);
                btThemDichVu.setVisible(false);
                btDatCoc.setVisible(false);
                btXoaDichVu.setVisible(false);
                btDoiSoLuong.setVisible(false);
                break;
            case 2:
                btTimKH.setVisible(false);
                btDatTiec.setVisible(true);
                btHuyTiec.setVisible(false);
                btNhanPhong.setVisible(false);
                btThuePhong.setVisible(true);
                btThanhToan.setVisible(false);
                btThemDichVu.setVisible(false);
                btDatCoc.setVisible(false);
                btXoaDichVu.setVisible(false);
                btDoiSoLuong.setVisible(false);
                break;
            case 3:
                btTimKH.setVisible(true);
                btDatTiec.setVisible(true);
                btHuyTiec.setVisible(false);
                btNhanPhong.setVisible(false);
                btThuePhong.setVisible(false);
                btThanhToan.setVisible(true);
                btThemDichVu.setVisible(true);
                btDatCoc.setVisible(false);
                btXoaDichVu.setVisible(false);
                btDoiSoLuong.setVisible(false);
                break;
            case 4:
                btTimKH.setVisible(false);
                btDatTiec.setVisible(false);
                btHuyTiec.setVisible(false);
                btNhanPhong.setVisible(false);
                btThuePhong.setVisible(false);
                btThanhToan.setVisible(false);
                btThemDichVu.setVisible(false);
                btDatCoc.setVisible(false);
                btXoaDichVu.setVisible(false);
                btDoiSoLuong.setVisible(false);
                break;
        }
    }

    public void loadtbDichVu(int MaTP) {
        String[] header = {"Mã", "Tên dịch vụ", "Số lượng", "Đơn Giá"};
        DefaultTableModel dtm = new DefaultTableModel(header, 0);
        ArrayList<GoiDichVuDTO> arr = GoiDichVuBUS.GoiDichVuAll(MaTP);
        GoiDichVuDTO kh;
        for (int i = 0; i < arr.size(); i++) {
            kh = arr.get(i);
            String TenDV = DichVuBUS.getDichVu(kh.getMaDV()).getTenDV();
            int SL = kh.getSL();
            double DonGia = DichVuBUS.getDichVu(kh.getMaDV()).getDonGia();
            int MaGDV = kh.getMaGDV();
            Object[] row = {MaGDV, TenDV, SL, DonGia};
            dtm.addRow(row);
        }
        tbDichVu.setModel(dtm);
    }

    public void loadtbThuePhong(int MaPhong) {
        String[] header = {"Mã TP", "Mã Phòng", "Giờ bắt đầu", "Khách hàng"};
        DefaultTableModel dtm = new DefaultTableModel(header, 0);
        ArrayList<ThuePhongDTO> arr = ThuePhongBUS.getThuePhong_MaPhong_2(MaPhong);
        ThuePhongDTO kh;
        for (int i = 0; i < arr.size(); i++) {
            kh = arr.get(i);
            int MaTP = kh.getMaTP();
            String KH = KhachHangBUS.getKhachHang(kh.getMaKH()).getTenKH();
            Timestamp GioBatDau = kh.getGioBatDau();
            Object[] row = {MaTP, MaPhong, GioBatDau, KH};
            dtm.addRow(row);
        }
        tbThuePhong.setModel(dtm);
    }

    public void setKhachHang(int MaKH) {
        lbKhachHang.setText(KhachHangBUS.getKhachHang(MaKH).getTenKH());
    }

    public int getMaTP() {
        return Integer.parseInt(lbMa.getText());
    }

    public int getMaPhong() {
        return Integer.parseInt(lbMaPhong.getText());
    }

    public int getMaNV() {
        return this.MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPhongHat5 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPhongHat10 = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listPhongHat15 = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        btTaiKhoan = new javax.swing.JButton();
        btDichVu = new javax.swing.JButton();
        btPhong = new javax.swing.JButton();
        btNhanVien = new javax.swing.JButton();
        btHoaDon = new javax.swing.JButton();
        btKhachHang = new javax.swing.JButton();
        btThongKe = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lbTenNV = new javax.swing.JLabel();
        pn15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        btTimKH = new javax.swing.JButton();
        btDatTiec = new javax.swing.JButton();
        btHuyTiec = new javax.swing.JButton();
        btNhanPhong = new javax.swing.JButton();
        btThanhToan = new javax.swing.JButton();
        btThuePhong = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbDichVu = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lbTenPhong = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lbMa = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbMaPhong = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbLoaiPhong = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbTinhTrang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbKhachHang = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbGioBatDau = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lbGioDem = new javax.swing.JLabel();
        lbGioNgay = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lbTienDV = new javax.swing.JLabel();
        lbDatCoc = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btThemDichVu = new javax.swing.JButton();
        btDatCoc = new javax.swing.JButton();
        btXoaDichVu = new javax.swing.JButton();
        btDoiSoLuong = new javax.swing.JButton();
        btDangXuat = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbThuePhong = new javax.swing.JTable();
        lbKhoangTG = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý karaoke");
        setBackground(new java.awt.Color(255, 0, 0));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Image/rsz_karaoke.png")));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(173, 217, 236));
        jPanel1.setToolTipText("");

        jLabel1.setBackground(new java.awt.Color(224, 233, 230));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Loại phòng 5 người");

        listPhongHat5.setBackground(new java.awt.Color(254, 240, 254));
        listPhongHat5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listPhongHat5.setForeground(new java.awt.Color(153, 0, 0));
        listPhongHat5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listPhongHat5.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        listPhongHat5.setVisibleRowCount(-1);
        listPhongHat5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPhongHat5MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listPhongHat5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(267, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2.setBackground(new java.awt.Color(173, 217, 236));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Loại phòng 10 người");

        listPhongHat10.setBackground(new java.awt.Color(254, 240, 254));
        listPhongHat10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listPhongHat10.setForeground(new java.awt.Color(153, 0, 0));
        listPhongHat10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listPhongHat10.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        listPhongHat10.setVisibleRowCount(-1);
        listPhongHat10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPhongHat10MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listPhongHat10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(173, 217, 236));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Loại phòng 15 người");

        listPhongHat15.setBackground(new java.awt.Color(254, 240, 254));
        listPhongHat15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listPhongHat15.setForeground(new java.awt.Color(153, 0, 0));
        listPhongHat15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listPhongHat15.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        listPhongHat15.setVisibleRowCount(-1);
        listPhongHat15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listPhongHat15MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listPhongHat15);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel3)
                .addContainerGap(559, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 287, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addGap(28, 28, 28)))
        );

        jPanel4.setBackground(new java.awt.Color(173, 217, 236));

        btTaiKhoan.setBackground(new java.awt.Color(5, 106, 139));
        btTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/TaiKhoang.png"))); // NOI18N
        btTaiKhoan.setToolTipText("Tài khoản");
        btTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTaiKhoanActionPerformed(evt);
            }
        });

        btDichVu.setBackground(new java.awt.Color(5, 106, 139));
        btDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dichvu.png"))); // NOI18N
        btDichVu.setToolTipText("Dịch vụ");
        btDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDichVuActionPerformed(evt);
            }
        });

        btPhong.setBackground(new java.awt.Color(5, 106, 139));
        btPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Phong.png"))); // NOI18N
        btPhong.setToolTipText("Quản lý phòng");
        btPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPhongActionPerformed(evt);
            }
        });

        btNhanVien.setBackground(new java.awt.Color(5, 106, 139));
        btNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/NhanVien.png"))); // NOI18N
        btNhanVien.setToolTipText("Nhân viên");
        btNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhanVienActionPerformed(evt);
            }
        });

        btHoaDon.setBackground(new java.awt.Color(5, 106, 139));
        btHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/BaoCao.png"))); // NOI18N
        btHoaDon.setToolTipText("Báo cáo");
        btHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHoaDonActionPerformed(evt);
            }
        });

        btKhachHang.setBackground(new java.awt.Color(5, 106, 139));
        btKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/KhachHang.png"))); // NOI18N
        btKhachHang.setToolTipText("Khách hàng");
        btKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKhachHangActionPerformed(evt);
            }
        });

        btThongKe.setBackground(new java.awt.Color(5, 106, 139));
        btThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/ThongKe.png"))); // NOI18N
        btThongKe.setToolTipText("Thống kê");
        btThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThongKeActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Karaoke.png"))); // NOI18N

        jLabel6.setText(" ");

        jPanel9.setBackground(new java.awt.Color(173, 217, 236));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Wide Latin", 1, 24)); // NOI18N
        jLabel14.setText("Karaoke UIT");

        lbTenNV.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lbTenNV.setForeground(new java.awt.Color(136, 24, 50));
        lbTenNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTenNV.setText("Tên nhân viên");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(lbTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTenNV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))))
        );

        pn15.setBackground(new java.awt.Color(173, 217, 236));

        jPanel14.setBackground(new java.awt.Color(173, 217, 236));

        btTimKH.setBackground(new java.awt.Color(37, 61, 244));
        btTimKH.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btTimKH.setText("Khách hàng ");
        btTimKH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKHActionPerformed(evt);
            }
        });

        btDatTiec.setBackground(new java.awt.Color(0, 153, 204));
        btDatTiec.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btDatTiec.setText("Đặt phòng");
        btDatTiec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDatTiec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDatTiecbtDatTiecActionPerformed(evt);
            }
        });

        btHuyTiec.setBackground(new java.awt.Color(253, 74, 74));
        btHuyTiec.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btHuyTiec.setText(" Hủy tiệc ");
        btHuyTiec.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHuyTiec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHuyTiecbtHuyTiecActionPerformed(evt);
            }
        });

        btNhanPhong.setBackground(new java.awt.Color(0, 153, 204));
        btNhanPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btNhanPhong.setText("Nhận phòng");
        btNhanPhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btNhanPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhanPhongbtNhanPhongActionPerformed(evt);
            }
        });

        btThanhToan.setBackground(new java.awt.Color(0, 153, 204));
        btThanhToan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btThanhToan.setText("Thanh toán");
        btThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThanhToanbtThanhToanActionPerformed(evt);
            }
        });

        btThuePhong.setBackground(new java.awt.Color(0, 153, 204));
        btThuePhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btThuePhong.setText("Thuê phòng");
        btThuePhong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btThuePhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThuePhongbtNhanPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btTimKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btHuyTiec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNhanPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDatTiec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btThuePhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(btTimKH, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDatTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btHuyTiec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btThanhToan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(173, 217, 236));

        tbDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbDichVu.setGridColor(new java.awt.Color(204, 204, 255));
        tbDichVu.setSelectionBackground(new java.awt.Color(51, 51, 255));
        tbDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDichVuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbDichVu);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(173, 217, 236));

        lbTenPhong.setFont(new java.awt.Font("Goudy Stout", 0, 24)); // NOI18N
        lbTenPhong.setForeground(new java.awt.Color(102, 37, 29));
        lbTenPhong.setText("Tên phòng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTenPhong)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTenPhong, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel6.setBackground(new java.awt.Color(173, 217, 236));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Mã :");

        lbMa.setBackground(new java.awt.Color(0, 153, 0));
        lbMa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbMa.setForeground(new java.awt.Color(136, 24, 50));
        lbMa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Mã phòng:");

        lbMaPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbMaPhong.setForeground(new java.awt.Color(136, 24, 50));
        lbMaPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Loại phòng:");

        lbLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLoaiPhong.setForeground(new java.awt.Color(136, 24, 50));
        lbLoaiPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Tình trạng:");

        lbTinhTrang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTinhTrang.setForeground(new java.awt.Color(136, 24, 50));
        lbTinhTrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Khách hàng:");

        lbKhachHang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbKhachHang.setForeground(new java.awt.Color(136, 24, 50));
        lbKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Giờ bắt đầu:");

        lbGioBatDau.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbGioBatDau.setForeground(new java.awt.Color(136, 24, 50));
        lbGioBatDau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel11.setBackground(new java.awt.Color(29, 93, 21));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(29, 93, 21));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(29, 93, 21));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Giá giờ:");

        lbGioDem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbGioNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbGioNgay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("-");

        jPanel26.setBackground(new java.awt.Color(29, 93, 21));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(30, 30, 30)
                                        .addComponent(lbMaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel24))
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbTinhTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel23))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbGioBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbMa, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(lbGioNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(lbGioDem, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbMaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(lbLoaiPhong))
                .addGap(3, 3, 3)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lbTinhTrang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lbGioBatDau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lbMa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbGioDem, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGioNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12))
                        .addGap(4, 4, 4))
                    .addComponent(jLabel27))
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel7.setBackground(new java.awt.Color(173, 217, 236));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Tổng tiền DV:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Trả trước:");

        lbTienDV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTienDV.setForeground(new java.awt.Color(136, 24, 50));

        lbDatCoc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbDatCoc.setForeground(new java.awt.Color(136, 24, 50));

        jPanel19.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(29, 93, 21));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(29, 93, 21));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDatCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTienDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTienDV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lbDatCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(173, 217, 236));

        btThemDichVu.setBackground(new java.awt.Color(0, 153, 102));
        btThemDichVu.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btThemDichVu.setText("Thêm dịch vụ");
        btThemDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemDichVuActionPerformed(evt);
            }
        });

        btDatCoc.setBackground(new java.awt.Color(7, 38, 183));
        btDatCoc.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btDatCoc.setText("Trả trước");
        btDatCoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDatCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDatCocActionPerformed(evt);
            }
        });

        btXoaDichVu.setBackground(new java.awt.Color(253, 74, 74));
        btXoaDichVu.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btXoaDichVu.setText("Xóa dịch vụ");
        btXoaDichVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btXoaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaDichVuActionPerformed(evt);
            }
        });

        btDoiSoLuong.setBackground(new java.awt.Color(0, 153, 102));
        btDoiSoLuong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btDoiSoLuong.setText("Đổi số lượng");
        btDoiSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDoiSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDoiSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btDoiSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btXoaDichVu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDatCoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btThemDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(btThemDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDatCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btXoaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDoiSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btDangXuat.setBackground(new java.awt.Color(5, 106, 139));
        btDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Logout.png"))); // NOI18N
        btDangXuat.setToolTipText("Đăng xuất");
        btDangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(173, 217, 236));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Danh sách đặt phòng");

        tbThuePhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbThuePhong.setGridColor(new java.awt.Color(204, 204, 255));
        tbThuePhong.setSelectionBackground(new java.awt.Color(51, 51, 255));
        tbThuePhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuePhongMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbThuePhong);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lbKhoangTG.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbKhoangTG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Khoảng t/g:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("h");

        javax.swing.GroupLayout pn15Layout = new javax.swing.GroupLayout(pn15);
        pn15.setLayout(pn15Layout);
        pn15Layout.setHorizontalGroup(
            pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn15Layout.createSequentialGroup()
                .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn15Layout.createSequentialGroup()
                        .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn15Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn15Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn15Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(52, 52, 52)
                                .addComponent(lbKhoangTG, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(pn15Layout.createSequentialGroup()
                                .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn15Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pn15Layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(btDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(382, 382, 382))
        );
        pn15Layout.setVerticalGroup(
            pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn15Layout.createSequentialGroup()
                .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn15Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn15Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn15Layout.createSequentialGroup()
                                .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel9)
                                    .addComponent(lbKhoangTG, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn15Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn15, javax.swing.GroupLayout.PREFERRED_SIZE, 885, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pn15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void listPhongHat5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPhongHat5MouseClicked
        //        JOptionPane.showMessageDialog(null,((ImgsNText)listPhongHat5.getSelectedValue()).getMaPhong());
        lbMa.setText(null);
        int MaPhong = ((ImgsNText) listPhongHat5.getSelectedValue()).getMaPhong();
        loadPhong(MaPhong);
        loadtbThuePhong(MaPhong);
        loadThuePhong(MaPhong);
        loadButton(PhongBUS.getPhong(MaPhong).getTinhTrang());
        if (lbMa.getText() != null) {
            loadtbDichVu(Integer.parseInt(lbMa.getText()));
        } else {
            loadtbDichVu(0);
        }
        loadListPhong();
//        loadListPhong();
    }//GEN-LAST:event_listPhongHat5MouseClicked

    private void listPhongHat10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPhongHat10MouseClicked
        // JOptionPane.showMessageDialog(null,((ImgsNText)listPhongHat10.getSelectedValue()).getMaPhong());
        lbMa.setText(null);
        int MaPhong = ((ImgsNText) listPhongHat10.getSelectedValue()).getMaPhong();
        loadPhong(MaPhong);
        loadtbThuePhong(MaPhong);
        loadThuePhong(MaPhong);
        loadButton(PhongBUS.getPhong(MaPhong).getTinhTrang());
        if (lbMa.getText() != null) {
            loadtbDichVu(Integer.parseInt(lbMa.getText()));
        } else {
            loadtbDichVu(0);
        }
        loadListPhong();
    }//GEN-LAST:event_listPhongHat10MouseClicked

    private void listPhongHat15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listPhongHat15MouseClicked
        //JOptionPane.showMessageDialog(null,((ImgsNText)listPhongHat15.getSelectedValue()).getMaPhong());
        lbMa.setText(null);
        int MaPhong = ((ImgsNText) listPhongHat15.getSelectedValue()).getMaPhong();
        loadPhong(MaPhong);
        loadtbThuePhong(MaPhong);
        loadThuePhong(MaPhong);
        loadButton(PhongBUS.getPhong(MaPhong).getTinhTrang());
        if (lbMa.getText() != null) {
            loadtbDichVu(Integer.parseInt(lbMa.getText()));
        } else {
            loadtbDichVu(0);
        }
        loadListPhong();
    }//GEN-LAST:event_listPhongHat15MouseClicked

    private void btTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTaiKhoanActionPerformed
        this.dispose();
        new TaiKhoanGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btTaiKhoanActionPerformed

    private void btDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDichVuActionPerformed
        this.setVisible(false);
        new DichVuGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btDichVuActionPerformed

    private void btPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPhongActionPerformed
        this.setVisible(false);
        new PhongGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btPhongActionPerformed

    private void btNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhanVienActionPerformed
        this.setVisible(false);
        new NhanVienGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btNhanVienActionPerformed

    private void btKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKhachHangActionPerformed
        this.setVisible(false);
        new KhachHangGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btKhachHangActionPerformed

    private void btThanhToanbtThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThanhToanbtThanhToanActionPerformed
        int MaHD = HoaDonBUS.getSoHoaDon() + 1;
        Timestamp NgayHD = new Timestamp(new Date().getTime());
        int MaTP = Integer.parseInt(lbMa.getText());
        int MaKH = ThuePhongBUS.getThuePhong(MaTP).getMaKH();
        new ThuePhongDAO().UpdateGioKetThuc(MaTP, new Timestamp(new Date().getTime()));
        int GiamGia = KhachHangBUS.getGiamGia(ThuePhongBUS.getKhachHang(MaTP));
        double TienDichVu = Math.floor(GoiDichVuBUS.getTongTien(MaTP));
        double TienGio = Math.floor(ThuePhongBUS.getTienGio(MaTP));
        double TongTien = Math.floor((TienDichVu + TienGio) - ((TienDichVu + TienGio) * ((float) GiamGia / 100)));
        boolean chk = new HoaDonDAO().InsertHoaDon(MaHD, NgayHD, MaTP, GiamGia, this.getMaNV(), TienDichVu, TienGio, TongTien);
        if (chk == true) {
            ArrayList<ThuePhongDTO> arr = ThuePhongBUS.getThuePhong_MaPhong_2(ThuePhongBUS.getThuePhong(MaTP).getMaPhong());
            if (arr.isEmpty()) {
                new PhongDAO().UpdateTTP(ThuePhongBUS.getThuePhong(MaTP).getMaPhong(), 1);
            } else {
                new PhongDAO().UpdateTTP(ThuePhongBUS.getThuePhong(MaTP).getMaPhong(), 2);
            }
            new ThuePhongDAO().UpdateTinhTrangThue(MaTP, 3);
            if (MaKH != 1) {
                new KhachHangDAO().UpdateTichLuy(MaKH, KhachHangBUS.getTichLuy(MaKH, TongTien));
                if (KhachHangBUS.checkkTichLuy_Vip(KhachHangBUS.getKhachHang(MaKH))) {
                    new KhachHangDAO().UpdateLoaiKH(MaKH, 3);
                } else if (KhachHangBUS.checkkTichLuy_UuDai(KhachHangBUS.getKhachHang(MaKH))) {
                    new KhachHangDAO().UpdateLoaiKH(MaKH, 2);
                }
            } else {

            }
            loadListPhong();
            loadPhong(Integer.parseInt(lbMaPhong.getText()));
            btDoiSoLuong.setVisible(false);
            loadThuePhong(Integer.parseInt(lbMaPhong.getText()));
            loadButton(PhongBUS.getPhong(Integer.parseInt(lbMaPhong.getText())).getTinhTrang());
            if (lbMa.getText() != null) {
                loadtbDichVu(Integer.parseInt(lbMa.getText()));
            } else {
                loadtbDichVu(0);
            }
            XuatHoaDon(MaHD);
        } else {

        }
    }//GEN-LAST:event_btThanhToanbtThanhToanActionPerformed

    private void btNhanPhongbtNhanPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhanPhongbtNhanPhongActionPerformed

    }//GEN-LAST:event_btNhanPhongbtNhanPhongActionPerformed

    private void btDatTiecbtDatTiecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDatTiecbtDatTiecActionPerformed
        DatTiecGUI kh = new DatTiecGUI(this, true);
        kh.setParent(this);
        kh.setVisible(true);
        loadListPhong();
    }//GEN-LAST:event_btDatTiecbtDatTiecActionPerformed

    private void btHuyTiecbtHuyTiecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHuyTiecbtHuyTiecActionPerformed
        int input = JOptionPane.showConfirmDialog(null, "Xác nhận hủy đặt tiệc?", "Xác nhận",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == JOptionPane.YES_OPTION) {
            boolean chk = new ThuePhongDAO().UpdateTinhTrangThue(Integer.parseInt(lbMa.getText()), 3);
            if (chk == true) {
                new PhongDAO().UpdateTTP(Integer.parseInt(lbMaPhong.getText()), 1);
                loadListPhong();
                loadPhong(this.getMaPhong());
                loadThuePhong(this.getMaPhong());
                loadButton(PhongBUS.getPhong(this.getMaPhong()).getTinhTrang());
                JOptionPane.showMessageDialog(this, "Hủy thành công");
            } else {

            }
        }
    }//GEN-LAST:event_btHuyTiecbtHuyTiecActionPerformed

    private void btDatCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDatCocActionPerformed
        int MaTP = Integer.parseInt(lbMa.getText());
        String tien = JOptionPane.showInputDialog(null, "Nhập số tiền trả trước");
        if (tien != null) {
            if (ThuePhongBUS.checkTienTraTruoc(tien)) {
                int TienTraTruoc = Integer.parseInt(tien);
                ThuePhongDAO tp = new ThuePhongDAO();
                boolean chk = tp.UpdateTienTraTruoc(MaTP, TienTraTruoc);
                if (chk == true) {
                    lbDatCoc.setText("" + TienTraTruoc);
                    JOptionPane.showMessageDialog(this, tien);
                } else {

                }
            } else {
                JOptionPane.showMessageDialog(null, "Tiền nhập vào không đúng!");
            }
        } else {

        }

    }//GEN-LAST:event_btDatCocActionPerformed

    private void btThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemDichVuActionPerformed
        GoiDichVuGUI Them = new GoiDichVuGUI(this, true);
        Them.setParent(this);
        Them.setVisible(true);
        btDoiSoLuong.setVisible(false);
        btXoaDichVu.setVisible(false);
        loadtbDichVu(Integer.parseInt(lbMa.getText()));
        loadThuePhong(this.getMaPhong());
    }//GEN-LAST:event_btThemDichVuActionPerformed

    private void btTimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKHActionPerformed
        TimKhachHangGUI kh = new TimKhachHangGUI(this, true);
        kh.setParent(this);
        kh.setVisible(true);
    }//GEN-LAST:event_btTimKHActionPerformed

    private void tbDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDichVuMouseClicked
        btDoiSoLuong.setVisible(true);
        btXoaDichVu.setVisible(true);
    }//GEN-LAST:event_tbDichVuMouseClicked

    private void btDoiSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDoiSoLuongActionPerformed
        int is = tbDichVu.getSelectedRow();
        TableModel model = tbDichVu.getModel();
        int MaGDV = Integer.parseInt(model.getValueAt(is, 0).toString());
        String SL_str = JOptionPane.showInputDialog(null, "Nhập số lượng");

        if (GoiDichVuBUS.checkSoLuong(SL_str)) {
            int SL = Integer.parseInt(SL_str);
            new GoiDichVuDAO().UpdateSoLuong(MaGDV, SL);
            loadtbDichVu(Integer.parseInt(lbMa.getText()));
            loadThuePhong(this.getMaPhong());
            btDoiSoLuong.setVisible(false);
            btXoaDichVu.setVisible(false);
        } else {

        }
    }//GEN-LAST:event_btDoiSoLuongActionPerformed

    private void btHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHoaDonActionPerformed
        this.setVisible(false);
        new HoaDonGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btHoaDonActionPerformed

    private void btThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThongKeActionPerformed
        this.setVisible(false);
        new ThongKeGUI(this.getMaNV()).setVisible(true);
    }//GEN-LAST:event_btThongKeActionPerformed

    private void btDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDangXuatActionPerformed
        this.dispose();
        new DangNhapGUI().setVisible(true);
    }//GEN-LAST:event_btDangXuatActionPerformed

    private void btXoaDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaDichVuActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete Record?", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            int is = tbDichVu.getSelectedRow();
            TableModel model = tbDichVu.getModel();
            int MaGDV = Integer.parseInt(model.getValueAt(is, 0).toString());
            new GoiDichVuDAO().DeleteGDV(MaGDV);
            loadtbDichVu(Integer.parseInt(lbMa.getText()));
            loadThuePhong(this.getMaPhong());
            btXoaDichVu.setVisible(false);
            btDoiSoLuong.setVisible(false);
        }

    }//GEN-LAST:event_btXoaDichVuActionPerformed

    private void tbThuePhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuePhongMouseClicked
        int is = tbThuePhong.getSelectedRow();
        TableModel model = tbThuePhong.getModel();
        int MaTP = Integer.parseInt(model.getValueAt(is, 0).toString());
        System.out.println("MaTP = " + MaTP);
        QuanLyDatTiecGUI dt = new QuanLyDatTiecGUI(this, true, MaTP);
        dt.setVisible(true);
        loadtbThuePhong(this.getMaPhong());
        loadListPhong();
        loadPhong(this.getMaPhong());
        loadThuePhong(this.getMaPhong());
        loadButton(PhongBUS.getPhong(this.getMaPhong()).getTinhTrang());
        if (lbMa.getText() != null) {
            loadtbDichVu(Integer.parseInt(lbMa.getText()));
        } else {
            loadtbDichVu(0);
        }
    }//GEN-LAST:event_tbThuePhongMouseClicked

    private void btThuePhongbtNhanPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThuePhongbtNhanPhongActionPerformed
        String KhoangTG_str = JOptionPane.showInputDialog(null, "Nhập khoảng thời gian sử dụng!");
        if (KhoangTG_str != null) {
            int KhoangTG = Integer.parseInt(KhoangTG_str);
            int MaTP = ThuePhongBUS.getMaTP() + 1;
            int MaPhong = Integer.parseInt(lbMaPhong.getText());
            Timestamp GioBatDau = new Timestamp(new Date().getTime());
            Timestamp GioKetThuc = new Timestamp(new Date().getTime());
            int TinhTrangThue = 1;
            double TienTraTruoc = 0;
            if (ThuePhongBUS.checkGioBatDau(GioBatDau, KhoangTG, MaPhong)) {
                ThuePhongDAO tp = new ThuePhongDAO();
                boolean chk = tp.InsertThuePhong(MaTP, MaPhong, 1, GioBatDau, GioKetThuc, TinhTrangThue, TienTraTruoc, KhoangTG);
                if (chk == true) {
                    new PhongDAO().UpdateTTP(MaPhong, 3);
                    loadListPhong();
                    loadThuePhong(this.getMaPhong());
                    loadButton(PhongBUS.getPhong(MaPhong).getTinhTrang());
                    loadPhong(this.getMaPhong());
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {

                }
            }
        }
    }//GEN-LAST:event_btThuePhongbtNhanPhongActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManHinhChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDangXuat;
    private javax.swing.JButton btDatCoc;
    private javax.swing.JButton btDatTiec;
    private javax.swing.JButton btDichVu;
    private javax.swing.JButton btDoiSoLuong;
    private javax.swing.JButton btHoaDon;
    private javax.swing.JButton btHuyTiec;
    private javax.swing.JButton btKhachHang;
    private javax.swing.JButton btNhanPhong;
    private javax.swing.JButton btNhanVien;
    private javax.swing.JButton btPhong;
    private javax.swing.JButton btTaiKhoan;
    private javax.swing.JButton btThanhToan;
    private javax.swing.JButton btThemDichVu;
    private javax.swing.JButton btThongKe;
    private javax.swing.JButton btThuePhong;
    private javax.swing.JButton btTimKH;
    private javax.swing.JButton btXoaDichVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbDatCoc;
    private javax.swing.JLabel lbGioBatDau;
    private javax.swing.JLabel lbGioDem;
    private javax.swing.JLabel lbGioNgay;
    private javax.swing.JLabel lbKhachHang;
    private javax.swing.JLabel lbKhoangTG;
    private javax.swing.JLabel lbLoaiPhong;
    private javax.swing.JLabel lbMa;
    private javax.swing.JLabel lbMaPhong;
    private javax.swing.JLabel lbTenNV;
    private javax.swing.JLabel lbTenPhong;
    private javax.swing.JLabel lbTienDV;
    private javax.swing.JLabel lbTinhTrang;
    private javax.swing.JList listPhongHat10;
    private javax.swing.JList listPhongHat15;
    private javax.swing.JList listPhongHat5;
    private javax.swing.JPanel pn15;
    private javax.swing.JTable tbDichVu;
    private javax.swing.JTable tbThuePhong;
    // End of variables declaration//GEN-END:variables
}
