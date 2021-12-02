/*
 * Created by JFormDesigner on Thu Dec 02 19:47:58 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathys Gagner
 */
public class MemoFrame extends JFrame {
    public MemoFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel1 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        textField1 = new JTextField();

        //======== this ========
        setTitle("M\u00e9mos");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(25, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3,alignx center",
                // columns
                "[fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]"));

            //---- button2 ----
            button2.setText("Ajouter");
            panel1.add(button2, "cell 0 0");

            //---- button3 ----
            button3.setText("Supprimer");
            panel1.add(button3, "cell 1 0");
            panel1.add(textField1, "cell 0 1 2 1");
        }
        contentPane.add(panel1, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList list1;
    private JPanel panel1;
    private JButton button2;
    private JButton button3;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
