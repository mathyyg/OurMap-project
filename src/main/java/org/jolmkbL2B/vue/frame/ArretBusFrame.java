/*
 * Created by JFormDesigner on Thu Dec 02 20:03:13 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.ArretBus;
import org.jolmkbL2B.marqueurs.Marqueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mathys Gagner
 * Classe permettant l'affichage des informations détaillées d'un arrêt de bus
 */
public class ArretBusFrame extends JFrame {
    private final AppControllers app;
    private final ArretBus arretBus;

    public ArretBusFrame(ArretBus arretBus, AppControllers app) {
        this.arretBus = arretBus;
        this.app = app;
        initComponents();
    }

    /**
     * @author Mathys Gagner
     * Méthode créée par JFormDesigner qui initialise tous les composants Swing
     * Le reste des instructions est personnalisé et contient toutes les autres initialisations ;
     * listeners, états/contenus de composants, ...
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label4 = new JLabel();
        textFNom = new JTextField();
        label1 = new JLabel();
        textFVille = new JTextField();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        textADesc = new JTextArea();
        label3 = new JLabel();
        textFHandi = new JTextField();
        buttonSuggestion = new JButton();

        //======== this ========
        setTitle("Arr\u00eat de bus");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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

        //---- textFNom ----
        textFNom.setEditable(false);
        contentPane.add(textFNom, "cell 1 1");

        //---- label1 ----
        label1.setText("Ville");
        contentPane.add(label1, "cell 1 2");

        //---- textFVille ----
        textFVille.setEditable(false);
        contentPane.add(textFVille, "cell 1 3 2 1");

        //---- label2 ----
        label2.setText("Description");
        contentPane.add(label2, "cell 1 4");

        //======== scrollPane1 ========
        {

            //---- textADesc ----
            textADesc.setEditable(false);
            scrollPane1.setViewportView(textADesc);
        }
        contentPane.add(scrollPane1, "cell 1 5 2 1");

        //---- label3 ----
        label3.setText("Acc\u00e8s handicap\u00e9 ?");
        contentPane.add(label3, "cell 1 6 2 1");

        //---- textFHandi ----
        textFHandi.setEditable(false);
        contentPane.add(textFHandi, "cell 1 7 2 1");

        //---- buttonSuggestion ----
        buttonSuggestion.setText("Faire une suggestion");
        contentPane.add(buttonSuggestion, "cell 1 8");
        setSize(245, 310);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textFNom.setText(arretBus.getName());
        textFVille.setText(arretBus.getCity());
        textADesc.setText(arretBus.getDescription());
        if(arretBus.isAccesHandicap() == 1) {
            textFHandi.setText("Oui");
        }
        else {
            textFHandi.setText("Non");
        }

        buttonSuggestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SuggestionFrame suggestionFrame = new SuggestionFrame(arretBus.getLieuID(),  app);
                suggestionFrame.setVisible(true);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JLabel label4;
    public JTextField textFNom;
    public JLabel label1;
    public JTextField textFVille;
    public JLabel label2;
    public JScrollPane scrollPane1;
    public JTextArea textADesc;
    public JLabel label3;
    public JTextField textFHandi;
    public JButton buttonSuggestion;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
