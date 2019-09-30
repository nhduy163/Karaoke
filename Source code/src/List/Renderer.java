/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Hainguyen
 */
public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> jlist, Object e, int i, boolean bln, boolean bln1) {
        ImgsNText is = (ImgsNText) e;//thay doi 
        setIcon(is.getImg());
        setText(is.getTenPhong());
        setVerticalTextPosition(BOTTOM);
        setHorizontalTextPosition(CENTER);
        if (bln) {
            setBackground(jlist.getSelectionBackground());
            setForeground(jlist.getSelectionForeground());
        } else {
            setBackground(jlist.getBackground());
            setForeground(jlist.getForeground());
        }
        setEnabled(true);
        setFont(jlist.getFont());
        return this;
    }
//    public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
//        ImgsNText is = (ImgsNText) o;
//        setText(is.getTenPhong());
//        setIcon(is.getImg());
//
//        if (bln) {
//            setBackground(jlist.getSelectionBackground());
//            setForeground(jlist.getSelectionForeground());
//        } else {
//            setBackground(jlist.getBackground());
//            setForeground(jlist.getForeground());
//        }
//
//        setEnabled(true);
//        setFont(jlist.getFont());
//        return this;
//    }

}
