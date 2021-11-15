package org.jolmkbL2B.vue;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import javax.swing.*;
import java.awt.*;

public class AppliHubFrame extends JFrame {
    JPanel principal;

    public AppliHubFrame() {
        super("HOME");
//        principal.add();
        this.setContentPane(principal);
        this.setPreferredSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        FlatDarkPurpleIJTheme.setup();
        AppliHubFrame ah = new AppliHubFrame();
        ah.pack();
        ah.setVisible(true);
    }
}
