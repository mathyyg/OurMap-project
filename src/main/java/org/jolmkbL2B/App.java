package org.jolmkbL2B;

import org.jolmkbL2B.controllers.MarqueurController;
import org.jolmkbL2B.controllers.UserContentController;
//import org.jolmkbL2B.controllers.UserContentController;

import java.sql.*;


public class App     {
    public static Connection con;
    public static MarqueurController marqueurController;
    private final UserContentController userContentController;
//    public static UserContentController userContentController;

    public App()    {
        this.marqueurController = new MarqueurController();
        this.userContentController = new UserContentController();
    }

}
