/*
 * Created by JFormDesigner on Mon Dec 06 17:39:01 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import javax.swing.*;
import net.miginfocom.swing.*;
import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.Marqueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Mathys Gagner
 * Classe permettant l'affichage de la fenêtre concernant les suggestions associées à un marqueur
 */
public class SuggestionFrame extends JFrame {
    private final long id;
    private final AppControllers app;

    public SuggestionFrame(long id, AppControllers app) {
        this.id = id;
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
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button1 = new JButton();

        //======== this ========
        setTitle("Suggestion");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,alignx center",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Entrez votre suggestion");
        contentPane.add(label1, "cell 0 0");

        //======== scrollPane1 ========
        {

            //---- textArea1 ----
            textArea1.setRows(6);
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1, "cell 0 1 2 1");

        //---- button1 ----
        button1.setText("Envoyer");
        contentPane.add(button1, "cell 0 2 2 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        this.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.suggestionController.addSuggestion(id, app.idUtilisateurConnecte, textArea1.getText())) {
                    JOptionPane.showMessageDialog(getParent(), "Votre suggestion a bien été envoyée.", "Suggestion", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur avec la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
