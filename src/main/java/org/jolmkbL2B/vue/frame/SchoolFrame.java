/*
 * Created by JFormDesigner on Mon Dec 06 14:36:57 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.School;

/**
 * @author Mathys Gagner
 */
public class SchoolFrame extends JFrame {
    private final School school;
    private final AppControllers app;

    public SchoolFrame(School school, AppControllers app) {
        this.school = school;
        this.app = app;
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textFNom = new JTextField();
        label2 = new JLabel();
        label6 = new JLabel();
        textFVille = new JTextField();
        textFStatut = new JTextField();
        label3 = new JLabel();
        label7 = new JLabel();
        scrollPane1 = new JScrollPane();
        textADesc = new JTextArea();
        textFType = new JTextField();
        label4 = new JLabel();
        textFAdresse = new JTextField();
        buttonSuggestion = new JButton();

        //======== this ========
        setTitle("School");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[100]" +
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
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Nom                                   ");
        contentPane.add(label1, "cell 0 0");

        //---- textFNom ----
        textFNom.setMinimumSize(new Dimension(100, 30));
        textFNom.setEditable(false);
        contentPane.add(textFNom, "cell 0 1");

        //---- label2 ----
        label2.setText("Ville");
        contentPane.add(label2, "cell 0 2");

        //---- label6 ----
        label6.setText("Statut");
        contentPane.add(label6, "cell 2 2");

        //---- textFVille ----
        textFVille.setMinimumSize(new Dimension(100, 30));
        textFVille.setEditable(false);
        contentPane.add(textFVille, "cell 0 3");

        //---- textFStatut ----
        textFStatut.setEditable(false);
        contentPane.add(textFStatut, "cell 2 3");

        //---- label3 ----
        label3.setText("Description");
        contentPane.add(label3, "cell 0 4");

        //---- label7 ----
        label7.setText("Type d'\u00e9tablissement");
        contentPane.add(label7, "cell 2 4");

        //======== scrollPane1 ========
        {
            scrollPane1.setMinimumSize(new Dimension(100, 16));

            //---- textADesc ----
            textADesc.setMinimumSize(new Dimension(100, 17));
            textADesc.setEditable(false);
            scrollPane1.setViewportView(textADesc);
        }
        contentPane.add(scrollPane1, "cell 0 5");

        //---- textFType ----
        textFType.setEditable(false);
        contentPane.add(textFType, "cell 2 5");

        //---- label4 ----
        label4.setText("Adresse");
        contentPane.add(label4, "cell 0 6");

        //---- textFAdresse ----
        textFAdresse.setMinimumSize(new Dimension(100, 30));
        textFAdresse.setEditable(false);
        contentPane.add(textFAdresse, "cell 0 7");

        //---- buttonSuggestion ----
        buttonSuggestion.setText("Faire une suggestion");
        contentPane.add(buttonSuggestion, "cell 0 8 3 1,alignx center,growx 0");
        setSize(315, 325);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textFNom.setText(school.getName());
        textFAdresse.setText(school.getAddress());
        textFVille.setText(school.getCity());
        textADesc.setText(school.getDescription());
        textFStatut.setText(school.PublicOuPrive().toString());
        textFType.setText(school.getSchoolType().toString());

        buttonSuggestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SuggestionFrame suggestionFrame = new SuggestionFrame(school.getLieuID(),  app);
                suggestionFrame.setVisible(true);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    public JTextField textFNom;
    private JLabel label2;
    private JLabel label6;
    public JTextField textFVille;
    public JTextField textFStatut;
    private JLabel label3;
    private JLabel label7;
    private JScrollPane scrollPane1;
    public JTextArea textADesc;
    public JTextField textFType;
    private JLabel label4;
    public JTextField textFAdresse;
    public JButton buttonSuggestion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
