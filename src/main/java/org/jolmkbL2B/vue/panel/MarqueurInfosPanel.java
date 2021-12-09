/*
 * Created by JFormDesigner on Wed Nov 17 18:52:14 CET 2021
 */

package org.jolmkbL2B.vue.panel;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Mathys Gagner
 * Classe permettant la création du panel avec le nom et la description d'un marqueur ainsi que les différents boutons
 * pour accéder aux autres informations liées au marqueur
 */
public class MarqueurInfosPanel extends JPanel {
    public MarqueurInfosPanel() {
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
        labelNom = new JLabel();
        labelDescription = new JLabel();
        separator6 = new JSeparator();
        separator1 = new JSeparator();
        separator2 = new JSeparator();
        tfNom = new JTextField();
        scrollPaneDesc = new JScrollPane();
        taDesc = new JTextArea();
        buttonInfos = new JButton();
        separator3 = new JSeparator();
        separator4 = new JSeparator();
        buttonMemos = new JButton();
        buttonCommentaires = new JButton();
        separator5 = new JSeparator();

        //======== this ========
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[200,fill]" +
            "[300,fill]" +
            "[200,fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- labelNom ----
        labelNom.setText("Nom");
        add(labelNom, "cell 0 0,alignx left,growx 0");

        //---- labelDescription ----
        labelDescription.setText("Description");
        add(labelDescription, "cell 1 0,alignx left,growx 0");
        add(separator6, "cell 2 0");
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

        //---- buttonInfos ----
        buttonInfos.setText("+ d'infos");
        add(buttonInfos, "cell 2 2");
        add(separator3, "cell 0 3");
        add(separator4, "cell 1 3");

        //---- buttonMemos ----
        buttonMemos.setText("M\u00e9mos");
        add(buttonMemos, "cell 0 4");

        //---- buttonCommentaires ----
        buttonCommentaires.setText("Commentaires");
        add(buttonCommentaires, "cell 1 4");
        add(separator5, "cell 2 4");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JLabel labelNom;
    public JLabel labelDescription;
    public JSeparator separator6;
    public JSeparator separator1;
    public JSeparator separator2;
    public JTextField tfNom;
    public JScrollPane scrollPaneDesc;
    public JTextArea taDesc;
    public JButton buttonInfos;
    public JSeparator separator3;
    public JSeparator separator4;
    public JButton buttonMemos;
    public JButton buttonCommentaires;
    public JSeparator separator5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
