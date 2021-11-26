package org.jolmkbL2B.controllers;

import java.sql.*;

public class MemoController {
    private Connection con;

    public MemoController()    {
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
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Connection cannot be closed or already is.");
            }
        }
    }

    public boolean addMemoToMarqueur(long idutilisateur, long idmarqueur, String text)    {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `ourmapdb`.`userMemo`\n" +
                    "(`idmarqueur`,\n" +
                    "`idutilisateur`,\n" +
                    "`text`)\n" +
                    "VALUES\n" +
                    "(" + idmarqueur + ",\n" +
                    + idutilisateur + ",\n" +
                    "\"" + text + "\",\n" +
                    " 1);\n");

            stmt.close();
            con.commit();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet fetchUserMemo(long idmarqueur, long idutilisateur) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT text FROM userMemo WHERE idmarqueur = " + idmarqueur + " AND " +
                    "idutilisateur = " + idutilisateur + " ;");
            stmt.close();
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
