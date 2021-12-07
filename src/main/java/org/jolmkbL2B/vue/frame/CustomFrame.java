/*
 * Created by JFormDesigner on Mon Dec 06 14:40:12 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.controllers.databaseTablesAndColumns.ModifiableCustomsColumns;
import org.jolmkbL2B.marqueurs.CustomMarqueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Mathys Gagner
 */
public class CustomFrame extends JFrame {
    private final CustomMarqueur customMarqueur;
    private final AppControllers app;

    public CustomFrame(CustomMarqueur customMarqueur, AppControllers app) {
        this.customMarqueur = customMarqueur;
        this.app = app;
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textFNom = new JTextField();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        textADesc = new JTextArea();
        buttonSauvegarder = new JButton();
        button1 = new JButton();

        //======== this ========
        setTitle("Marqueur personnalis\u00e9");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
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
            "[]"));

        //---- label1 ----
        label1.setText("Nom");
        contentPane.add(label1, "cell 0 0 4 1");
        contentPane.add(textFNom, "cell 0 1 4 1");

        //---- label2 ----
        label2.setText("Description");
        contentPane.add(label2, "cell 0 2");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textADesc);
        }
        contentPane.add(scrollPane1, "cell 0 3 4 1");

        //---- buttonSauvegarder ----
        buttonSauvegarder.setText("Sauvegarder");
        contentPane.add(buttonSauvegarder, "cell 1 4");

        //---- button1 ----
        button1.setText("Ajouter \u00e0 une liste");
        contentPane.add(button1, "cell 1 5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        textFNom.setText(customMarqueur.getName());
        textADesc.setText(customMarqueur.getDescription());

        buttonSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap updates = new HashMap<ModifiableCustomsColumns, String>();
                updates.put(ModifiableCustomsColumns.marqueurName, textFNom.getText());
                updates.put(ModifiableCustomsColumns.marqueurDescription, textADesc.getText());
                if(app.marqueurController.updateCustom(customMarqueur.getLieuID(),updates, app.idUtilisateurConnecte)) {
                    JOptionPane.showMessageDialog(getParent(), "Les modifications ont bien été enregistrées.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur lors de la sauvegarde.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ChoixListeFrame choixListeFrame = new ChoixListeFrame(customMarqueur,app);
                    choixListeFrame.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JLabel label1;
    public JTextField textFNom;
    public JLabel label2;
    public JScrollPane scrollPane1;
    public JTextArea textADesc;
    public JButton buttonSauvegarder;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
