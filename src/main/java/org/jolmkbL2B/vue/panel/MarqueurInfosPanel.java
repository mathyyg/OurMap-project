/*
 * Created by JFormDesigner on Wed Nov 17 18:52:14 CET 2021
 */

package org.jolmkbL2B.vue.panel;

import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathys Gagner
 */
public class MarqueurInfosPanel extends JPanel {
    public MarqueurInfosPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelNom = new JLabel();
        labelDescription = new JLabel();
        separator1 = new JSeparator();
        separator2 = new JSeparator();
        tfNom = new JTextField();
        scrollPaneDesc = new JScrollPane();
        taDesc = new JTextArea();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[200,fill]" +
            "[300,fill]" +
            "[73,fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));

        //---- labelNom ----
        labelNom.setText("Nom");
        add(labelNom, "cell 0 0,alignx left,growx 0");

        //---- labelDescription ----
        labelDescription.setText("Description");
        add(labelDescription, "cell 1 0,alignx left,growx 0");
        add(separator1, "cell 0 1");
        add(separator2, "cell 1 1");

        //---- tfNom ----
        tfNom.setEditable(false);
        add(tfNom, "cell 0 2");

        //======== scrollPaneDesc ========
        {

            //---- taDesc ----
            taDesc.setEditable(false);
            taDesc.setRows(1);
            taDesc.setToolTipText("Description du marqueur");
            scrollPaneDesc.setViewportView(taDesc);
        }
        add(scrollPaneDesc, "cell 1 2");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelNom;
    private JLabel labelDescription;
    private JSeparator separator1;
    private JSeparator separator2;
    private JTextField tfNom;
    private JScrollPane scrollPaneDesc;
    private JTextArea taDesc;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
