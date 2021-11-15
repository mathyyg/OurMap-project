package org.jolmkbL2B.vue;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import org.jolmkbL2B.App;
import org.jolmkbL2B.Main;

import javax.swing.*;
import java.awt.*;

public class AppliHub extends JFrame {
    JPanel principal;

    public AppliHub() {
        super("HOME");
//        principal.add();
        this.setContentPane(principal);
        this.setPreferredSize(new Dimension(400, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        FlatDarkPurpleIJTheme.setup();
        AppliHub ah = new AppliHub();
        ah.pack();
        ah.setVisible(true);
    }
}
