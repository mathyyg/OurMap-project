package org.jolmkbL2B.controllers;

import java.sql.*;
/** Cette Classe gere les interactions avec la table userMemo de la BDD
 * @auhtor Bastien*/
public class MemoController {
    private Connection con;

    /** Constructeur. Etablit la connection à la base de données sur un serveur distant
     * @throws SQLException en cas de soucis de connection.
     * @author Bastien
     */
    public MemoController()    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL");
            this.con = con;
            Statement stmt = con.createStatement();

            /** Requête sur le serveur pour utiliser la base de données de
             dédiée à l'application */
            stmt.execute("USE ourmapdb;");


            /** Les updates et insertion sur la base de données ne sont pas enregistrées
             * automatiquement. Il est nécessaire d'executer la commande "con.commit(); pour enregistrer les changements.
             * Ceci permet d'éviter que des transactions incompletes soient enregistrés en cas de crash.*/
            con.setAutoCommit(false);
        }
        catch(SQLException sqlException) {
            this.con = null;
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database in class MarqueurController" +
                    ". Initialization cannot continue.");
            // Soucis : si pas internet, on peut pas utiliser l'appli
            sqlException.printStackTrace();
        }
    }

    /** Ajout d'un Memo sur un marqueur. Ajoute une entree dans la table userMemo
     * @author Bastien
     * @param idmarqueur
     * @param idutilisateur
     * @return true en cas de succes, false en cas d'echec
     * */
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
                    "\"" + text + "\"\n" + " );\n");

            stmt.close();
            con.commit();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /** Supprime l'entree un memo de la base de données
     * @author Bastien
     * @param idutilisateur
     * @param idmarqueur
     * @param text
     * @return true en cas de succes, false en cas d'echec
     */
    public boolean removeMemoFromMarqueur(long idutilisateur, long idmarqueur, String text) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM userMemo WHERE idutilisateur = " + idutilisateur + " AND idmarqueur = " + idmarqueur + " AND text = " + "\"" + text + "\"");
            stmt.close();
            con.commit();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**Recuperation d'un memo ajouté par l'utilisateur connecté sur le marqueur selectionné
     * @author Bastien
     * @param idmarqueur
     * @param idutilisateur
     * @return Un resultSet contenant le texte d'un memo
     */
    public ResultSet fetchUserMemo(long idmarqueur, long idutilisateur) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT text FROM userMemo WHERE idmarqueur = " + idmarqueur + " AND " +
                    "idutilisateur = " + idutilisateur + " ;");
//            stmt.close();
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
