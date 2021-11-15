package org.jolmkbL2B;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import org.jolmkbL2B.vue.frame.AppliHubFrame;


public class Main
{
    public static void main( String[] args ) {
        System.out.println("Hello World!");
        App app = new App();
        FlatDarkPurpleIJTheme.setup();
        AppliHubFrame hub = new AppliHubFrame("HOME");
        hub.pack();
        hub.setVisible(true);
    }
}
