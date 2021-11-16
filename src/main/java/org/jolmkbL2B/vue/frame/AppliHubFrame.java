package org.jolmkbL2B.vue.frame;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import org.jolmkbL2B.vue.panel.MapPanel;

import javax.swing.*;
import java.awt.*;

public class AppliHubFrame extends JFrame {
    private JPanel contentPanel;

    public AppliHubFrame(String titre) {
        super(titre);
//        try {
//            UIManager.setLookAndFeel( new FlatDarkPurpleIJTheme() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }
        this.contentPanel = new JPanel();
        this.contentPanel.setLayout(new BorderLayout());
        this.contentPanel.add(new MapPanel());

        JPanel listesetmarqueurs = new JPanel();
        this.contentPanel.add(listesetmarqueurs, BorderLayout.EAST);

        //principal.add ^^^
        this.setContentPane(contentPanel);
        this.setPreferredSize(new Dimension(1200, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // METHODE MAIN DESTINEE U N I Q U E M E N T AU DEBUG
//    public static void main(String[] args) {
//        FlatDarkPurpleIJTheme.setup();
//        AppliHubFrame ah = new AppliHubFrame("HOME");
//        ah.pack();
//        ah.setVisible(true);
//    }
}
