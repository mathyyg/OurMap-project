/*
 * Created by JFormDesigner on Thu Dec 02 17:16:43 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.Marqueur;

/**
 * @author Mathys Gagner
 */
public class CommentFrame extends JFrame {
    public final Marqueur mq;
    private final AppControllers app;

    public CommentFrame(AppControllers app, Marqueur mq) throws SQLException {
        this.app = app;
        this.mq = mq;
        initComponents();
    }

    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        textAComment = new JTextArea();
        ButtonAddComment = new JButton();

        //======== this ========
        setTitle("Commentaires");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setFillsViewportHeight(true);
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
                    scrollPane2.setViewportView(textAComment);
                }
                panel2.add(scrollPane2, "cell 0 0 6 1");

                //---- ButtonAddComment ----
                ButtonAddComment.setText("Ajouter un commentaire");
                panel2.add(ButtonAddComment, "cell 6 0");
            }
            panel1.add(panel2, BorderLayout.SOUTH);
        }
        contentPane.add(panel1);
        setSize(500, 190);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        ResultSet comments = app.commentaireController.fetchAllCommentairesByMarqueur(mq.getLieuID());
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table1.setModel(tableModel);
        table1.setAutoCreateRowSorter(true);
        tableModel.addColumn("Commentaire");
        tableModel.addColumn("Auteur");

        while(comments.next()) {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.insertRow(0,new Object[]{comments.getString(3), app.utilisateurController.getUtilisateurById(comments.getInt(2)).getDisplayName()});
        }

        ButtonAddComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.commentaireController.postCommentaire(app.idUtilisateurConnecte, mq.getLieuID(), textAComment.getText())) {
                    tableModel.insertRow(0, new Object[]{textAComment.getText(), app.utilisateurController.getUtilisateurById((int) app.idUtilisateurConnecte).getDisplayName()});
                    JOptionPane.showMessageDialog(getParent(), "Votre commentaire a bien été ajouté.", "Suggestion", JOptionPane.INFORMATION_MESSAGE);
                    textAComment.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur avec la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JPanel panel1;
    public JScrollPane scrollPane1;
    public JTable table1;
    public JPanel panel2;
    public JScrollPane scrollPane2;
    public JTextArea textAComment;
    public JButton ButtonAddComment;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
