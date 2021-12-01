/*
 * Created by JFormDesigner on Wed Dec 01 13:08:35 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import javax.swing.*;
import org.jolmkbL2B.vue.panel.*;

/**
 * @author Mathys Gagner
 */
public class HubFrame extends JFrame {
    public HubFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        mapPanel1 = new MapPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(mapPanel1, BorderLayout.CENTER);

        //---- label1 ----
        label1.setText("text");
        contentPane.add(label1, BorderLayout.EAST);

        //---- label2 ----
        label2.setText("text");
        contentPane.add(label2, BorderLayout.WEST);

        //---- label3 ----
        label3.setText("text");
        contentPane.add(label3, BorderLayout.NORTH);

        //---- label4 ----
        label4.setText("text");
        contentPane.add(label4, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private MapPanel mapPanel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
