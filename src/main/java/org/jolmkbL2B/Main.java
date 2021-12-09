package org.jolmkbL2B;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.*;
import org.jolmkbL2B.vue.frame.LoginFrame;

import javax.swing.*;

/**
 * @author Khaled Mahdi, Bastien Richardeau, Oualid Siraji, Mohammed Jalal El Hani, Lucas Carrilho Gomes
 * (avec tout notre amour et notre bonne volonté)
 * Classe permettant de lancer l'application
 */
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
