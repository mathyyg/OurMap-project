package org.jolmkbL2B.controllers;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * @author Mathys Gagner
 * Classe permettant d'intéragir avec les suggestions dans la base de données
 */
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

    /**
     * @author Mathys Gagner
     * Méthode permettant l'ajout d'une suggestion à un marqueur
     * @param idmarqueur id du marqueur concerné
     * @param idutilisateur id de l'utilisateur qui envoie la suggestion
     * @param text message de la suggestion
     * @return
     */
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

    /**
     * @author Mathys Gagner
     * @param idmarqueur id du marqueur concerné
     * @return un ResultSet contenant toutes les suggestions pour le marqueur renseigné
     */
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

    /**
     * @author Mathys Gagner
     * Méthode qui permet de récupérer toutes les suggestions de la base de données, tous marqueurs confondus
     * @return un ResultSet contenant toutes les suggestions
     */
    public ResultSet fetchAllSuggestions() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM suggestions");
//            stmt.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author Mathys Gagner
     * Méthode qui permet de supprimer une suggestion selon son ID
     * @param idsuggestion id de la suggestion à supprimer
     * @return un booléen faisant état de la réalisation de la requête (succès / erreur)
     */
    public boolean deleteSuggestion(int idsuggestion) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM suggestions WHERE idsuggestion = " + idsuggestion);
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
