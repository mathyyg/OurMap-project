package org.jolmkbL2B.controllers;

import java.sql.*;

/** Cette classe permet de gérer les interactions entre l'application et la base de données pour la gestion des
 * commentaires.
 * @author Bastien Richardeau*/
public class CommentaireController implements Commentaires  {
    private Connection con;

    /** Constructeur. Etablit la connection à la base de données sur un serveur distant
     * @throws SQLException en cas de soucis de connection.
     */
    public CommentaireController()    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "PROJET2022poogl");
            this.con = con;
            /** Requête sur le serveur pour utiliser la base de données de
             dédiée à l'application */
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            /** Les updates et insertion sur la base de données ne sont pas enregistrées
             * automatiquement. Il est nécessaire d'executer la commande "con.commit(); pour enregistrer les changements.
             * Ceci permet d'éviter que des transactions incompletes soient enregistrés en cas de crash.*/
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

    /** Cette méthode renvoit un ResultSet contenant les commentaires qui ont été déposés sur un marqueur, pourvu que la
     * valeur de la colonne "setVisible" soit à 1
     * @author Bastien Richardeau
     * @param idmarqueur (identifiant du marqueur pour lequel on souhaite voir les commentaires.
     * @return  un ResultSet avec les collones suivantes (idmarqueur, idutilisateur, text)
     * @throws SQLException*/
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

    /** Cette méthode renvoit un ResultSet contenant les commentaires qui ont été déposés par un utilisateur donné
     * @author Bastien Richardeau
     * @param idutilisateur (identifiant d'un utilisateur).
     * @return  un ResultSet avec les collones suivantes (idmarqueur, idutilisateur, text)
     * @throws SQLException*/
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


    /** Cette méthode permet de montrer ou cacher les commentaires d'un d'utilisateur sur un marqueur Pour cela, elle traduit
     * un booleen java en 0 ou 1 afin de pouvir l'entrer dans la base de données. Elle effectue ensuite une requete de
     * modification. Si celle ci réussi, la méthode renvoie true, sinon elle renvoie false
     * @author Bastien Richardeau
     * @param idutilisateur identifiant de l'utilisateur ayant posté le commentaire à masquer / montrer
     * @param idmarqueur identifiant du marqueur sur lequel se trouvent les commentaires à masquer / montrer
     * @param visible true si on souhaite montrer un commentaire, false si on se souhaite le cacher*/
    public boolean setCommentairesVisible(long idutilisateur, long idmarqueur, boolean visible) {
        /**On s'assure que le booleen sera bien transmis sous lq forme 0 ou 1 (la BD etant configuree
         * pour que les booleens soient sous forne de 0 ou 1)
         */
        int isVisible = 0;
        if(visible == true) isVisible = 1;
        try {
            Statement stmt = con.createStatement();
            int success = stmt.executeUpdate("UPDATE `ourmapdb`.`commentaires`\n" +
                    "(SET `setVisible`= " + isVisible + ")\n" +
                    "WHERE idmarqueur = " + idmarqueur + "AND idutilisateur = " + idutilisateur + ";\n");
            stmt.close();
            con.commit(); /** Enregistrement de la modification, le setAutoCommit étant sur false. */
            if(success == 0) { /** Si aucune ligne de la BD n'a été changée, retourner false */
                System.err.println("Aucun commentaire n'a pu être modifié.");
                return false;
            }
            else return true; /** Si une ou plusieurs lignes de la BD ont été changées, retourner true)*/
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setCertainCommVisible(long idutilisateur, long idmarqueur, String text, boolean visible) {
        int isVisible = 0;
        if(visible == true) isVisible = 1;
        try {
            Statement stmt = con.createStatement();
            int success = stmt.executeUpdate("UPDATE commentaires SET setVisible = " + isVisible + " WHERE idmarqueur = " + idmarqueur + " AND idutilisateur = " + idutilisateur
                                                + " AND text = \"" + text + "\";");
            stmt.close();
            con.commit(); /** Enregistrement de la modification, le setAutoCommit étant sur false. */
            if(success == 0) { /** Si aucune ligne de la BD n'a été changée, retourner false */
                System.err.println("Aucun commentaire n'a pu être modifié.");
                return false;
            }
            else return true; /** Si une ou plusieurs lignes de la BD ont été changées, retourner true)*/
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /** Cette méthode permet de poster un commentaire sur un marqueur avec une requête SQL
     * @author Bastien Richardeau
     * @param idmarqueur Marqueur sur lequel on poste le commentaire
     * @param idutilisateur identifiant de l'utilisateur postant le commentaire
     * @param text Contenu du commentaire
     * @return true si le commentaire a été ajouté, false sinon*/
    public boolean postCommentaire(long idutilisateur, long idmarqueur, String text)  {
        try {
            Statement stmt = con.createStatement();
            int success = stmt.executeUpdate("INSERT INTO `ourmapdb`.`commentaires`\n" +
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
            con.commit(); /** Enregistrement de la modification, le setAutoCommit étant sur false. */
            if(success > 0) return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.err.println("Aucun commentaire n'a été ajouté.");
        return false;
    }
}