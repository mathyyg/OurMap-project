package org.jolmkbL2B;

import org.jolmkbL2B.controllers.MarqueurController;
//import org.jolmkbL2B.controllers.UserContentController;

import java.sql.*;


public class App     {
    public static Connection con;
    public static MarqueurController marqueurController;
//    public static UserContentController userContentController;

    public App()    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL");
            this.con = con;
        }
        catch(SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database. Initialization cannot contiue"); // Soucis : si pas internet, on peut pas utiliser l'appli
        }
        this.marqueurController = new MarqueurController(con);
//        this.userContentController = new UserContentController(con);


        try {
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
