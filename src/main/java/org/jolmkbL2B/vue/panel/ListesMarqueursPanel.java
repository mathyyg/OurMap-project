package org.jolmkbL2B.vue.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class ListesMarqueursPanel extends JPanel {
    public JScrollPane lmScrollPane;
    public JTree lmTree;
    public JButton createMarqueurButton;

    public ListesMarqueursPanel() {

        this.lmScrollPane = new JScrollPane();
        this.lmTree = new JTree();

        this.setLayout(new MigLayout(
                "insets dialog, hidemode 3", //layout constraints
                "[200,fill]", //column constraints
                "[200][50]" //row constraints
        ));

        {
            lmTree.setShowsRootHandles(true);
            lmTree.setRootVisible(false);
            lmTree.setEditable(true);
            lmTree.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("Root") {
                        {
                            DefaultMutableTreeNode favoris = new DefaultMutableTreeNode("Favoris");
                                favoris.add(new DefaultMutableTreeNode("Fac blois"));
                                favoris.add(new DefaultMutableTreeNode("Gare SNCF"));
                                favoris.add(new DefaultMutableTreeNode("Leclerc Drive Blois"));
                                favoris.add(new DefaultMutableTreeNode("Lidl Vineuil"));
                                favoris.add(new DefaultMutableTreeNode("Mcdonald's Blois"));
                                favoris.add(new DefaultMutableTreeNode("Le marqueur de tes morts"));
                            add(favoris);
                            DefaultMutableTreeNode listeFav1 = new DefaultMutableTreeNode("Liste de marqueurs 1");
                                listeFav1.add(new DefaultMutableTreeNode("Marqueur personnalisé"));
                                listeFav1.add(new DefaultMutableTreeNode("Marqueur personnalisé"));
                                listeFav1.add(new DefaultMutableTreeNode("Marqueur personnalisé"));
                            add(listeFav1);
                            DefaultMutableTreeNode listeFav2 = new DefaultMutableTreeNode("Liste de marqueurs 2");
                                listeFav2.add(new DefaultMutableTreeNode("ta grand mère"));
                            add(listeFav2);
                        }
                    }));
            lmScrollPane.setViewportView(lmTree);
        }
        add(lmScrollPane, "cell 0 0, wrap");
        createMarqueurButton = new JButton("Nouveau marqueur");
        add(createMarqueurButton);

    }
}
