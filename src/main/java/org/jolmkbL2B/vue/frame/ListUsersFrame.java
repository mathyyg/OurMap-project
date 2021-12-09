/*
 * Created by JFormDesigner on Thu Dec 09 17:17:00 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mathys Gagner
 */
public class ListUsersFrame extends JFrame {
    AppControllers app;
    public ListUsersFrame(AppControllers app) throws SQLException {
        this.app = app;
        initComponents();
    }

    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("Tous les utilisateurs");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 0");

        //---- button1 ----
        button1.setText("Donner les droits administrateur");
        contentPane.add(button1, "cell 0 1");

        //---- button2 ----
        button2.setText("Supprimer les droits administrateur");
        contentPane.add(button2, "cell 0 2");
        setSize(465, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        ResultSet users = this.app.utilisateurController.getAllUtilisateurs();
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table1.setModel(tableModel);
        table1.setAutoCreateRowSorter(true);
        table1.setRowSelectionAllowed(true);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableModel.addColumn("ID Utilisateur");
        tableModel.addColumn("Nom d'utilisateur");
        tableModel.addColumn("Admin ?");

        while(users.next()) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            String isAdmin = "Non";
            if(users.getInt(5) == 1) {
                isAdmin = "Oui";
            }
            model.insertRow(0,new Object[]{users.getInt(1), users.getString(2), isAdmin});
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getValueAt(table1.getSelectedRow(),2) == "Oui") {
                    JOptionPane.showMessageDialog(getParent(), "L'utilisateur a déjà les droits admin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else if(app.utilisateurController.setAdmin((Integer)table1.getValueAt(table1.getSelectedRow(),0), true)) {
                    ((DefaultTableModel)table1.getModel()).setValueAt("Oui", table1.getSelectedRow(), 2);
                    JOptionPane.showMessageDialog(getParent(), "L'utilisateur a bien reçu les droits admin.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur lors de l'attribution des droits admin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getValueAt(table1.getSelectedRow(),2) == "Non") {
                    JOptionPane.showMessageDialog(getParent(), "L'utilisateur n'a pas les droits admin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else if(app.utilisateurController.setAdmin((Integer)table1.getValueAt(table1.getSelectedRow(),0), true)) {
                    ((DefaultTableModel)table1.getModel()).setValueAt("Non", table1.getSelectedRow(), 2);
                    JOptionPane.showMessageDialog(getParent(), "Les droits admin ont bien été retirés à l'utilisateur.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur lors de la suppression des droits admin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JScrollPane scrollPane1;
    public JTable table1;
    public JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
