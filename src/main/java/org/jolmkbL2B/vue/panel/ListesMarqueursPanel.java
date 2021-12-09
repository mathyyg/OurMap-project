package org.jolmkbL2B.vue.panel;

import net.miginfocom.swing.MigLayout;
import org.jolmkbL2B.AppControllers;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathys Gagner
 * Classe qui permet d'afficher la partie "listes de favoris" visible à droite dans l'application principale (HubFrame)
 * (rare classe graphique non réalisées avec JFormDesigner ... :) )
 */
public class ListesMarqueursPanel extends JPanel {
    private final AppControllers app;
    public JScrollPane lmScrollPane;
    public JTree lmTree;
    public JButton createMarqueurButton;
    public JButton createListeButton;
    public JTextField textNewListe;

    public ListesMarqueursPanel(AppControllers app) {
        this.app = app;
        this.lmScrollPane = new JScrollPane();
        this.lmTree = new JTree();

        this.setLayout(new MigLayout(
                "insets dialog, hidemode 3", //layout constraints
                "[200,fill]", //column constraints
                "[200][50]" //row constraints
        ));

        {
            lmTree.setShowsRootHandles(true);
//            lmTree.setRootVisible(false);
            lmTree.setEditable(true);

            lmScrollPane.setViewportView(lmTree);
        }
        add(lmScrollPane, "cell 0 0, wrap");
        createMarqueurButton = new JButton("Nouveau marqueur");
        createListeButton = new JButton("Créer une liste");
        textNewListe = new JTextField();
        add(textNewListe, "wrap");
        add(createListeButton, "wrap");
        add(createMarqueurButton);


    }

    /**
     * @author Mathys Gagner
     * Méthode permettant l'initialisation des listes de favoris et des marqueurs associées à chacune
     * Affiche "(vide)" si la liste est vide
     * Cette méthode est aussi utilisée pour rafraichir le panel
     * @throws SQLException
     */
    public void initTree() throws SQLException {
        ResultSet listesBdd = this.app.listeController.fetchAllLists(app.idUtilisateurConnecte);
        HashMap<String, Long> idListes = new HashMap<String,Long>();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Listes de favoris");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        lmTree.setModel(treeModel);

        while(listesBdd.next()) {
            idListes.put(listesBdd.getString(2), Long.valueOf(listesBdd.getString(1)));
        }
        listesBdd.close();

        for(Map.Entry<String, Long> entree : idListes.entrySet()) {
            ResultSet marqueurs = this.app.listeController.fetchList(entree.getValue(), app.idUtilisateurConnecte);

            String nameNewNode = entree.getKey();
            if(!marqueurs.isBeforeFirst()) {
                nameNewNode += " (vide)";
            }
            DefaultMutableTreeNode nouveau = new DefaultMutableTreeNode(nameNewNode);
            root.add(nouveau);

            while(marqueurs.next()) {
                nouveau.add(new DefaultMutableTreeNode(marqueurs.getString(2)));
            }
            marqueurs.close();
        }

        createListeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long newid = app.listeController.createList(app.idUtilisateurConnecte,textNewListe.getText());
                if(newid >-1) {
                    JOptionPane.showMessageDialog(getParent(), "La liste a bien été créée.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(getParent(), "Erreur lors de la création de la liste.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
