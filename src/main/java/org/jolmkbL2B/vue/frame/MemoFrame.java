/*
 * Created by JFormDesigner on Thu Dec 02 19:47:58 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.Marqueur;

/**
 * @author Mathys Gagner
 */
public class MemoFrame extends JFrame {
    private final AppControllers app;
    private final Marqueur mq;

    public MemoFrame(AppControllers app, Marqueur mq) throws SQLException {
        this.app = app;
        this.mq = mq;
        initComponents();
    }

    private void initComponents() throws SQLException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel1 = new JPanel();
        buttonAdd = new JButton();
        butotnDelete = new JButton();
        textField1 = new JTextField();

        //======== this ========
        setTitle("M\u00e9mos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(25, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3,alignx center",
                // columns
                "[fill]" +
                "[fill]",
                // rows
                "[]" +
                "[]"));

            //---- buttonAdd ----
            buttonAdd.setText("Ajouter");
            panel1.add(buttonAdd, "cell 0 0");

            //---- butotnDelete ----
            butotnDelete.setText("Supprimer");
            panel1.add(butotnDelete, "cell 1 0");
            panel1.add(textField1, "cell 0 1 2 1");
        }
        contentPane.add(panel1, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        DefaultListModel listModel = new DefaultListModel();
        ResultSet memos = this.app.memoController.fetchUserMemo(this.mq.getLieuID(), this.app.idUtilisateurConnecte);
        while(memos.next()) {
            listModel.addElement(memos.getString(1));
        }
        list1.setModel(listModel);

        this.buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.memoController.addMemoToMarqueur(app.idUtilisateurConnecte, mq.getLieuID(), textField1.getText())) {
                    listModel.addElement(textField1.getText());
                    JOptionPane.showMessageDialog(getParent(), "Votre mémo a bien été ajouté.", "Suggestion", JOptionPane.INFORMATION_MESSAGE);
                    textField1.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur avec la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.butotnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.memoController.removeMemoFromMarqueur(app.idUtilisateurConnecte, mq.getLieuID(), listModel.getElementAt(list1.getSelectedIndex()).toString())) {
                    listModel.remove(list1.getSelectedIndex());
                    JOptionPane.showMessageDialog(getParent(), "Votre mémo a bien été supprimé.", "Suggestion", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur avec la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JScrollPane scrollPane1;
    public JList list1;
    public JPanel panel1;
    public JButton buttonAdd;
    public JButton butotnDelete;
    public JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
