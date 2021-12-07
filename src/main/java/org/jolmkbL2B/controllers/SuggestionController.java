package org.jolmkbL2B.controllers;

import java.sql.*;

// @author MATHYS
public class SuggestionController {
    private Connection con;

    public SuggestionController()    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL");
            this.con = con;
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            con.setAutoCommit(false);
            stmt.close();
        }
        catch(SQLException sqlException) {
            this.con = null;
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database in class MemoController" +
                    ". Initialization cannot continue.");
            // Soucis : si pas internet, on peut pas utiliser l'appli
            sqlException.printStackTrace();

        }
    }

    public boolean addSuggestion(long idmarqueur, long idutilisateur, String text) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `ourmapdb`.`suggestions`\n" +
                    "(`idmarqueur`,\n" +
                    "`idutilisateur`,\n" +
                    "`text`)\n" +
                    "VALUES\n" +
                    "(" + idmarqueur + ",\n" +
                    + idutilisateur + ",\n" +
                    "\"" + text + "\")\n" + ";\n");

            stmt.close();
            con.commit();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public ResultSet fetchMarqueurSuggestions(long idmarqueur) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM suggestions WHERE idmarqueur = " + idmarqueur + " ;");
            stmt.close();
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
