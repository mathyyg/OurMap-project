package org.jolmkbL2B;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import org.jolmkbL2B.vue.frame.AppliHubFrame;
import org.jolmkbL2B.vue.frame.LoginFrame;


public class Main
{
    public static void main( String[] args ) {
        System.out.println("Hello World!");
        AppControllers app = new AppControllers();
        FlatDarkPurpleIJTheme.setup();
        LoginFrame login = new LoginFrame(app);
        login.pack();
        login.setVisible(true);
    }
}
