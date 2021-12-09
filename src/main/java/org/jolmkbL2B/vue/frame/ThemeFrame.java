/*
 * Created by JFormDesigner on Thu Dec 09 02:44:21 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import net.miginfocom.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Mathys Gagner
 * Classe affichant la liste des thèmes de couleur disponibles pour l'application
 */
public class ThemeFrame extends JFrame {
    public ThemeFrame() {
        initComponents();
    }

    /**
     * @author Mathys Gagner
     * Méthode créée par JFormDesigner qui initialise tous les composants Swing
     * Le reste des instructions est personnalisé et contient toutes les autres initialisations ;
     * listeners, états de composants, ...
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        jlistThemes = new JList();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]"));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(jlistThemes);
        }
        contentPane.add(scrollPane1, "cell 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    public JList jlistThemes;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
