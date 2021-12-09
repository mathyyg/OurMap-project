/*
 * Created by JFormDesigner on Fri Dec 03 17:36:17 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.Hotel;

/**
 * @author Mathys Gagner
 * Classe permettant l'affichage des informations détaillées d'un Hotel
 */
public class HotelFrame extends JFrame {
    private final Hotel hotel;
    private final AppControllers app;

    public HotelFrame(Hotel hotel, AppControllers app) {
        this.hotel = hotel;
        this.app = app;
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
        label1 = new JLabel();
        label5 = new JLabel();
        textFNom = new JTextField();
        textFNumTel = new JTextField();
        label2 = new JLabel();
        label6 = new JLabel();
        textFVille = new JTextField();
        textFEtoiles = new JTextField();
        label3 = new JLabel();
        label7 = new JLabel();
        scrollPane1 = new JScrollPane();
        textADesc = new JTextArea();
        textFSite = new JTextField();
        label4 = new JLabel();
        label8 = new JLabel();
        textFAdresse = new JTextField();
        textFTripAdvisor = new JTextField();
        label9 = new JLabel();
        label10 = new JLabel();
        checkBoxResto = new JCheckBox();
        checkBoxAnimaux = new JCheckBox();
        label11 = new JLabel();
        label12 = new JLabel();
        checkBoxMoteur = new JCheckBox();
        checkBoxMental = new JCheckBox();
        label13 = new JLabel();
        label14 = new JLabel();
        checkBoxAuditif = new JCheckBox();
        checkBoxVisuel = new JCheckBox();
        buttonSuggestion = new JButton();

        //======== this ========
        setTitle("H\u00f4tel");
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
        label1.setText("Nom");
        contentPane.add(label1, "cell 0 0");

        //---- label5 ----
        label5.setText("Num\u00e9ro de t\u00e9l\u00e9phone");
        contentPane.add(label5, "cell 2 0");

        //---- textFNom ----
        textFNom.setMinimumSize(new Dimension(100, 30));
        textFNom.setEditable(false);
        contentPane.add(textFNom, "cell 0 1");

        //---- textFNumTel ----
        textFNumTel.setEditable(false);
        contentPane.add(textFNumTel, "cell 2 1");

        //---- label2 ----
        label2.setText("Ville");
        contentPane.add(label2, "cell 0 2");

        //---- label6 ----
        label6.setText("\u00c9toiles");
        contentPane.add(label6, "cell 2 2");

        //---- textFVille ----
        textFVille.setMinimumSize(new Dimension(100, 30));
        textFVille.setEditable(false);
        contentPane.add(textFVille, "cell 0 3");

        //---- textFEtoiles ----
        textFEtoiles.setEditable(false);
        contentPane.add(textFEtoiles, "cell 2 3");

        //---- label3 ----
        label3.setText("Description");
        contentPane.add(label3, "cell 0 4");

        //---- label7 ----
        label7.setText("Site web");
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

        //---- textFSite ----
        textFSite.setEditable(false);
        contentPane.add(textFSite, "cell 2 5");

        //---- label4 ----
        label4.setText("Adresse");
        contentPane.add(label4, "cell 0 6");

        //---- label8 ----
        label8.setText("Trip Advisor");
        contentPane.add(label8, "cell 2 6");

        //---- textFAdresse ----
        textFAdresse.setMinimumSize(new Dimension(100, 30));
        textFAdresse.setEditable(false);
        contentPane.add(textFAdresse, "cell 0 7");

        //---- textFTripAdvisor ----
        textFTripAdvisor.setEditable(false);
        contentPane.add(textFTripAdvisor, "cell 2 7");

        //---- label9 ----
        label9.setText("Restaurant ?");
        contentPane.add(label9, "cell 0 8,alignx center,growx 0");

        //---- label10 ----
        label10.setText("Accepte les animaux?");
        contentPane.add(label10, "cell 2 8");
        contentPane.add(checkBoxResto, "cell 0 9,alignx center,growx 0");
        contentPane.add(checkBoxAnimaux, "cell 2 9,alignx center,growx 0");

        //---- label11 ----
        label11.setText("Acc\u00e8s handicap. moteur?");
        contentPane.add(label11, "cell 0 10,alignx center,growx 0");

        //---- label12 ----
        label12.setText("Acc\u00e8s handicap mental?");
        contentPane.add(label12, "cell 2 10,alignx center,growx 0");
        contentPane.add(checkBoxMoteur, "cell 0 11,alignx center,growx 0");
        contentPane.add(checkBoxMental, "cell 2 11,alignx center,growx 0");

        //---- label13 ----
        label13.setText("Acc\u00e8s handicap auditif?");
        contentPane.add(label13, "cell 0 12,alignx center,growx 0");

        //---- label14 ----
        label14.setText("Acc\u00e8s handicap visuel?");
        contentPane.add(label14, "cell 2 12,alignx center,growx 0");
        contentPane.add(checkBoxAuditif, "cell 0 13,alignx center,growx 0");
        contentPane.add(checkBoxVisuel, "cell 2 13,alignx center,growx 0");

        //---- buttonSuggestion ----
        buttonSuggestion.setText("Faire une suggestion");
        contentPane.add(buttonSuggestion, "cell 0 14 3 1,alignx center,growx 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textFNom.setText(hotel.getName());
        textFVille.setText(hotel.getCity());
        textADesc.setText(hotel.getDescription());
        textFAdresse.setText(hotel.getAddress());
        textFNumTel.setText(hotel.getNumTelephone());
        textFSite.setText(hotel.getSiteWeb());
        textFTripAdvisor.setText(hotel.getTripAdvisor());
        textFEtoiles.setText(String.valueOf(hotel.getCategorieEtoiles()));
        checkBoxResto.setSelected(hotel.isHasRestaurant());
        for(EventListener el : checkBoxResto.getListeners(MouseListener.class)) {
            checkBoxResto.removeMouseListener((MouseListener) el);
        }
        if(hotel.isAnimauxAcceptes() == 1) {
            checkBoxAnimaux.setSelected(true);
        }
        if(hotel.getLabelHandicap()[0] == 1) {
            checkBoxMoteur.setSelected(true);
        }
        if(hotel.getLabelHandicap()[1] == 1) {
            checkBoxMental.setSelected(true);
        }
        if(hotel.getLabelHandicap()[2] == 1) {
            checkBoxAuditif.setSelected(true);
        }
        if(hotel.getLabelHandicap()[3] == 1) {
            checkBoxVisuel.setSelected(true);
        }

        for(EventListener el : checkBoxAnimaux.getListeners(MouseListener.class)) {
            checkBoxAnimaux.removeMouseListener((MouseListener) el);
        }

        for(EventListener el : checkBoxMoteur.getListeners(MouseListener.class)) {
            checkBoxMoteur.removeMouseListener((MouseListener) el);
        }

        for(EventListener el : checkBoxMental.getListeners(MouseListener.class)) {
            checkBoxMental.removeMouseListener((MouseListener) el);
        }

        for(EventListener el : checkBoxAuditif.getListeners(MouseListener.class)) {
            checkBoxAuditif.removeMouseListener((MouseListener) el);
        }

        for(EventListener el : checkBoxVisuel.getListeners(MouseListener.class)) {
            checkBoxVisuel.removeMouseListener((MouseListener) el);
        }

        buttonSuggestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SuggestionFrame suggestionFrame = new SuggestionFrame(hotel.getLieuID(),  app);
                suggestionFrame.setVisible(true);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label5;
    public JTextField textFNom;
    public JTextField textFNumTel;
    private JLabel label2;
    private JLabel label6;
    public JTextField textFVille;
    public JTextField textFEtoiles;
    private JLabel label3;
    private JLabel label7;
    private JScrollPane scrollPane1;
    public JTextArea textADesc;
    public JTextField textFSite;
    private JLabel label4;
    private JLabel label8;
    public JTextField textFAdresse;
    public JTextField textFTripAdvisor;
    private JLabel label9;
    private JLabel label10;
    public JCheckBox checkBoxResto;
    public JCheckBox checkBoxAnimaux;
    private JLabel label11;
    private JLabel label12;
    public JCheckBox checkBoxMoteur;
    public JCheckBox checkBoxMental;
    private JLabel label13;
    private JLabel label14;
    public JCheckBox checkBoxAuditif;
    public JCheckBox checkBoxVisuel;
    public JButton buttonSuggestion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
