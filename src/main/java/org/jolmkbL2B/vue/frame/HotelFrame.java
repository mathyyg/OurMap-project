/*
 * Created by JFormDesigner on Fri Dec 03 17:36:17 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathys Gagner
 */
public class HotelFrame extends JFrame {
    public HotelFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        setTitle("H\u00f4tel");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
