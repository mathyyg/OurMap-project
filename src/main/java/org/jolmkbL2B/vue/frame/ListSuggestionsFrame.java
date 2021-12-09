/*
 * Created by JFormDesigner on Thu Dec 09 13:27:46 CET 2021
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
public class ListSuggestionsFrame extends JFrame {
    AppControllers app;
    public ListSuggestionsFrame(AppControllers app) throws SQLException {
        this.app = app;
        initComponents();
    }

    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();

        //======== this ========
        setTitle("Toutes les suggestions");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 0");

        //---- button1 ----
        button1.setText("Supprimer la suggestion");
        contentPane.add(button1, "cell 0 1");
        setSize(465, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        ResultSet suggestions = this.app.suggestionController.fetchAllSuggestions();
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
        tableModel.addColumn("ID Suggestion");
        tableModel.addColumn("ID Utilisateur");
        tableModel.addColumn("Nom marqueur");
        tableModel.addColumn("Suggestion");

        while(suggestions.next()) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.insertRow(0,new Object[]{suggestions.getInt(1), suggestions.getInt(2), suggestions.getInt(4), suggestions.getString(3)});
        }

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.suggestionController.deleteSuggestion((Integer) table1.getValueAt(table1.getSelectedRow(),0))) {
                    ((DefaultTableModel)table1.getModel()).removeRow(table1.getSelectedRow());
                    JOptionPane.showMessageDialog(getParent(), "La suggestion a bien été supprimée.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur lors de la suppression de la suggestion", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


//                if(app.commentaireController.setCertainCommVisible((long)app.utilisateurController.getUtilisateurByName(commentFrame.table1.getValueAt(commentFrame.table1.getSelectedRow(), 1).toString()).getIdutilisateur(),
//                        commentFrame.mq.getLieuID(),commentFrame.table1.getValueAt(commentFrame.table1.getSelectedRow(), 0).toString(), false )) {
//                    ((DefaultTableModel)commentFrame.table1.getModel()).removeRow(commentFrame.table1.getSelectedRow());
//                    JOptionPane.showMessageDialog(commentFrame.getParent(), "Le commentaire a bien été caché.", "Succès", JOptionPane.INFORMATION_MESSAGE);
//                }
//                else {
//                    JOptionPane.showMessageDialog(commentFrame.getParent(), "Erreur lors de la sélection du commentaire", "Erreur", JOptionPane.ERROR_MESSAGE);
//                }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JScrollPane scrollPane1;
    public JTable table1;
    public JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
