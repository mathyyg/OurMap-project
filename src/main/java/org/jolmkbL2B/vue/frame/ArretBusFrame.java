/*
 * Created by JFormDesigner on Thu Dec 02 20:03:13 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathys Gagner
 */
public class ArretBusFrame extends JFrame {
    public ArretBusFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label4 = new JLabel();
        textField3 = new JTextField();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label3 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Arr\u00eat de bus");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label4 ----
        label4.setText("Nom");
        contentPane.add(label4, "cell 1 0");
        contentPane.add(textField3, "cell 1 1");

        //---- label1 ----
        label1.setText("Ville");
        contentPane.add(label1, "cell 1 2");
        contentPane.add(textField1, "cell 1 3 2 1");

        //---- label2 ----
        label2.setText("Description");
        contentPane.add(label2, "cell 1 4");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1, "cell 1 5 2 1");

        //---- label3 ----
        label3.setText("Acc\u00e8s handicap\u00e9 ?");
        contentPane.add(label3, "cell 1 6 2 1");
        contentPane.add(textField2, "cell 1 7 2 1");

        //---- button1 ----
        button1.setText("Faire une suggestion");
        contentPane.add(button1, "cell 1 8");
        setSize(245, 310);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JLabel label4;
    public JTextField textField3;
    public JLabel label1;
    public JTextField textField1;
    public JLabel label2;
    public JScrollPane scrollPane1;
    public JTextArea textArea1;
    public JLabel label3;
    public JTextField textField2;
    public JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
