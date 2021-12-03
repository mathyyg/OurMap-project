/*
 * Created by JFormDesigner on Thu Dec 02 17:16:43 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathys Gagner
 */
public class CommentFrame extends JFrame {
    public CommentFrame() {

        initComponents();
        table1.getColumnModel().getColumn(0).setHeaderValue("Commentaire");
        table1.getColumnModel().getColumn(1).setHeaderValue("Auteur");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        button1 = new JButton();

        //======== this ========
        setTitle("Commentaires");
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1, BorderLayout.CENTER);

            //======== panel2 ========
            {
                panel2.setLayout(new MigLayout(
                    "hidemode 3,alignx center",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[fill]"));

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(textArea1);
                }
                panel2.add(scrollPane2, "cell 0 0 6 1");

                //---- button1 ----
                button1.setText("Ajouter un commentaire");
                panel2.add(button1, "cell 6 0");
            }
            panel1.add(panel2, BorderLayout.SOUTH);
        }
        contentPane.add(panel1);
        setSize(500, 190);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JPanel panel1;
    public JScrollPane scrollPane1;
    public JTable table1;
    public JPanel panel2;
    public JScrollPane scrollPane2;
    public JTextArea textArea1;
    public JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
