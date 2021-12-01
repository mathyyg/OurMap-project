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
        label2 = new JLabel();
        label3 = new JLabel();
        mapPanel1 = new MapPanel();
        listesMarqueursPanel1 = new ListesMarqueursPanel();
        marqueurInfosPanel1 = new MarqueurInfosPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label2 ----
        label2.setText("text");
        contentPane.add(label2, BorderLayout.WEST);

        //---- label3 ----
        label3.setText("text");
        contentPane.add(label3, BorderLayout.NORTH);
        contentPane.add(mapPanel1, BorderLayout.CENTER);
        contentPane.add(listesMarqueursPanel1, BorderLayout.EAST);
        contentPane.add(marqueurInfosPanel1, BorderLayout.SOUTH);
        setSize(685, 375);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label2;
    private JLabel label3;
    private MapPanel mapPanel1;
    private ListesMarqueursPanel listesMarqueursPanel1;
    private MarqueurInfosPanel marqueurInfosPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
