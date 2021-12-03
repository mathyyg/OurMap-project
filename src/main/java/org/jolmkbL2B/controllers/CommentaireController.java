package org.jolmkbL2B.controllers;

import java.sql.*;

public class CommentaireController implements Commentaires  {
    private Connection con;

    public CommentaireController()    {
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
            System.out.println("Error establishing connection with database in class CommentaireController" +
                    ". Initialization cannot continue.");
            // Soucis : si pas internet, on peut pas utiliser l'appli
            sqlException.printStackTrace();
        }
    }

    public ResultSet fetchAllCommentairesByMarqueur(long idmarqueur)  {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, idutilisateur, text FROM commentaires WHERE idmarqueur = " + idmarqueur + " AND setVisible = 1;");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet fetchAllCommentairesByUser(long idutilisateur)  {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM commentaires WHERE idutilisateur = " + idutilisateur +
                    " AND setVisible = 1;");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setCommentaireVisible(long idutilisateur, long idmarqueur, boolean visible) {
        //On s'assure que le booleen sera bien transmis sous lq forme 0 ou 1 (la BD etant configuree pour que les booleens soient codés sur 1 bit
        int isVisible = 0;
        if(visible == true) isVisible = 1;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE `ourmapdb`.`commentaires`\n" +
                    "(SET `setVisible`= " + isVisible + ")\n" +
                    "WHERE idmarqueur = " + idmarqueur + "AND idutlisateur = " + idutilisateur + ";\n");
            stmt.close();
            con.commit();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean postCommentaire(long idutilisateur, long idmarqueur, String text)  {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO `ourmapdb`.`commentaires`\n" +
                    "(`idmarqueur`,\n" +
                    "`idutilisateur`,\n" +
                    "`text`,\n" +
                    "`setVisible`)\n" +
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
}
