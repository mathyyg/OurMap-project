/*
 * Created by JFormDesigner on Tue Dec 07 20:42:04 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.Marqueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mathys Gagner
 * Classe permetttant l'affichage du choix de la liste de favoris à laquelle on veut ajouter un marqueur
 */
public class ChoixListeFrame extends JFrame {
    private final Marqueur mq;
    private final AppControllers app;

    public ChoixListeFrame(Marqueur mq, AppControllers app) throws SQLException {
        this.mq = mq;
        this.app = app;
        initComponents();
    }
    /**
     * @author Mathys Gagner
     * Méthode créée par JFormDesigner qui initialise tous les composants Swing
     * Le reste des instructions est personnalisé et contient toutes les autres initialisations ;
     * listeners, états de composants, ...
     */
    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();

        //======== this ========
        setTitle("Ajouter \u00e0 une liste");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Veuillez choisir une liste de favoris :");
        contentPane.add(label1, "cell 0 0");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 1");

        //---- button1 ----
        button1.setText("Valider");
        contentPane.add(button1, "cell 0 2");
        setSize(290, 270);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ResultSet listes = app.listeController.fetchAllLists(app.idUtilisateurConnecte);
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table1.setModel(tableModel);
        table1.setAutoCreateRowSorter(true);
        tableModel.addColumn("Nom de la liste");
        tableModel.addColumn("ID de la liste");

        while(listes.next()) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.insertRow(0,new Object[]{listes.getString(2), listes.getString(1)});
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (app.listeController.addToList(mq.getLieuID(), Long.parseLong(table1.getValueAt(table1.getSelectedRow(), 1).toString()), app.idUtilisateurConnecte)) {
                    JOptionPane.showMessageDialog(getParent(), "Le marqueur a bien été ajouté à la liste.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur lors de l'ajout à la liste.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
