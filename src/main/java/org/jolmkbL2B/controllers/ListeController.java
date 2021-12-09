package org.jolmkbL2B.controllers;

import java.sql.*;
/**
 * @author Oualid Siraji
 *
 *
 */

public class ListeController implements Listes {
    private Connection con;

    /** Constructeur. Etablit la connection à la base de données sur un serveur distant
     * @throws SQLException en cas de soucis de connection.
     */
    public ListeController()    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL");
            this.con = con;
            Statement stmt = con.createStatement();
            /* Requête sur le serveur pour utiliser la base de données de dédiée à l'application */
            stmt.execute("USE ourmapdb;");
            /* Les updates et insertion sur la base de données ne sont pas enregistrées
             * automatiquement. Il est nécessaire d'executer la commande "con.commit(); pour enregistrer les changements.
             * Ceci permet d'éviter que des transactions incompletes soient enregistrés en cas de crash.*/
            con.setAutoCommit(false);
            }
        catch(SQLException sqlException) {
            this.con = null;
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database in class ListeController" +
                    ". Initialization cannot continue.");
            // Soucis : si pas internet, on peut pas utiliser l'appli
            sqlException.printStackTrace();
        }
    }

    /** Cette méthode renvoie un ResultSet contenant tous les marqueurs d'une liste, à condition que l'utilisateur qui
     *en fait le demande ait le droit de visualiser la liste demandée.
     *
     * @param idliste la liste que l'utilisateur souhaite visualiser
     * @param requestingUser l'identifiant de l'utilisateur effectuant la requête
     * @return Un ResultSet contenant les marqueurs de la liste.
     *                      Colonnes (idmarqueur, placeType, latitude, longitude, marqueurName)
     *
     * ATTENTION : acces rights check désactivé temporairement. Cause un crash au demarage
     * @version 1
     */
    public ResultSet fetchList(long idliste, long requestingUser)    {
        try {
            Statement stmt = con.createStatement();
            //if(checkListAccessRight(idliste, requestingUser)) {
                ResultSet rs = stmt.executeQuery("SELECT idmarqueur, marqueurName FROM listemarqueurs NATURAL JOIN marqueurs WHERE idliste = " + idliste +
                        ";");
                return rs;//}

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet fetchAllLists(long requestingUser) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idliste, listName FROM listeOwner WHERE idutilisateur = " + requestingUser +
                    ";");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Creation d'une entree dans la table listOwner de la base de données.
     * @param idutilisateur identifiant de l'utilisateur faisant la demande
     * @param listName le nom qu l'utilisateur souhaite donner à sa liste
     * @return l'identifiant de la liste nouvellement créée
     *
     * @since 2.0
     * @version 1*/
    public long createList(long idutilisateur, String listName)   {
        try {
            Statement stmt = con.createStatement();
            if(stmt.executeUpdate("INSERT INTO `ourmapdb`.`listeOwner`\n" +
                    "(`idutilisateur`, `listname`)\n" +
                    "VALUES\n" +
                    "(" + idutilisateur + ", \"" + listName + "\");") > 0)    {
                String getLastIDQuerry = "SELECT idliste FROM listeOwner ORDER BY idliste DESC LIMIT 1;";
                ResultSet rs = stmt.executeQuery(getLastIDQuerry); //Recuperation de l'id de la liste tout juste crée
                rs.next();
                con.commit();
                return rs.getLong(1); //renvoi l'identifiant de la liste nouvellement créée
            }
            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**Cette méthode permet d'ajouter un marqueur à une liste de marqueurs dans la bse de données. Pour cela, elle
     * verifie d'abord que l'utilisateur qui fait la demande a le droit d'ajouter le maqueur a la liste spécifiée
     * (via la methode checkAccessRight)
     *
     *
     * ATTENTION : acces rights check désactivé temporairement. Cause un crash au demarage
     *
     * @param idliste la liste que l'utilisateur souhaite modifier
     * @param requestingUser l'identifiant de l'utilisateur effectuant la requête
     * @param idmarqueur le marqueur a ajouter
     * @return true en cas de reussite, false en cas d'echec*/
    public boolean addToList(long idmarqueur, long idliste, long requestingUser) {
        try {
            //if(checkListAccessRight(idliste, requestingUser)) { //verification des droits de l'utilisateur sur la liste
                Statement stmt = con.createStatement();
                if(stmt.executeUpdate("INSERT INTO `ourmapdb`.`listemarqueurs`\n" +
                        "(`idliste`,\n" +
                        "`idmarqueur`)\n" +
                        "VALUES\n" +
                        "(" + idliste + ",\n" +
                        idmarqueur + ");\n") > 0) {
                    con.commit();
                    return true;
                }
                //}

            else System.out.println("Erreur lors de l'ajout du collaborateur.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Cette méthode permet de donner le statut de collaborateur d'une à un utilisateur.
     * Elle procède à une vérification  pour savoir si l'utilisateur qui fait la demande est bien propriétaire de la liste.
     * Seul ce propriétaire peut ajouter un un nouveau collaborateur à la liste.
     * @param idliste identifiant de la liste en question
     * @param newCollaborateurID identifiant du nouveau collaborateur
     * @param requestingUser identifiant de l'utilisateur qui fait
     * @return true si l'ajout réussi, false si il échoue.
     *
     * NON IMPLEMENTEE
     */
    public boolean addCollaborateurListe(long idliste, String newCollaborateurID, long requestingUser)    {
        try {
            if(checkOwnerPrivilege(idliste, requestingUser)) {//verifie que l'utilisateur est propriétaire de la liste
                Statement stmt = con.createStatement();
                if (stmt.executeUpdate("INSERT INTO `ourmapdb`.`collabliste`\n" +
                        "(`idliste`,\n" +
                        "`idutilisateur`)\n" +
                        "VALUES\n" +
                        "(" + idliste + ",\n" +
                        "\"" + newCollaborateurID + "\");") > 0)    {
                    con.commit();
                    return true; //réussite
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false; //echec
    }

    /** Cette méthode vérifie qu'un utilisateur a accès à une liste de marqueurs.
     * La méthode regarde d'abord si l'utilisateur est propriétaire de la liste. si oui, elle retourne true
     * Si non, elle regarde si l'utilisateur est un collaborateur de la liste. Si oui elle retourne true. Si non,
     * elle retourne false.
     * @version 1
     * @param idliste identifiant de la liste
     * @param idutilisateur identifiant de l'utilisateur
     * @return true si l''utilisateur a le droit d'acceder à la liste, non sinon
     * @exception SQLException
     *
     * */
    public boolean checkListAccessRight(long idliste, long idutilisateur)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT isAdmin FROM utilisateurs WHERE idutilisateur = " + idutilisateur +";");
            if(rs.getBoolean(1))   return true; //la colonne is admin contient soit un 0, soit un 1. 1 Si l'utilisateur est admin

            rs = stmt.executeQuery("SELECT idutilisateur FROM listeOwner WHERE idliste = " + idliste +
                    ";"); //regarde l'identifiant du propriétaire de la liste dans la base de donnée
            rs.next();
            if(rs.getLong(1) == idutilisateur)    { //comparaison  avec l'identifiant d'utilisateur donné en paramètre
                stmt.close();
                return true;
            }
            else {
                rs = stmt.executeQuery("SELECT idutilisateur FROM collabliste WHERE idliste = " + idliste +
                        ";"); //Consulte la liste des collaborateurs à la liste.
                stmt.close();
                while (rs.next()) {
                    if (rs.getLong(1) == idutilisateur) return true;
                    //Si l'identifiant de l'utilisateur donné en parametre apparait dans la liste, accès autorisé
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Accès refusé. Vous n'avez pas le droit d'acceder à cette liste.");
        return false;
    }

    private boolean checkOwnerPrivilege(long idliste, long idutilisateur) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idutilisateur FROM listeOwner WHERE idliste = " + idliste +
                    ";");//regarde l'identifiant du propriétaire de la liste dans la base de donnée
            rs.next();
            if (rs.getLong(1) == idutilisateur) {/*Comparaison  avec l'identifiant d'utilisateur donné en
            paramètrela colonne isAdmin contient soit un 0, soit un 1. 1 Si l'utilisateur est admin*/
                stmt.close();
                return true;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Accès refusé.Seul le propriétaire de la liste peut réaliser cette action.");
        return false;
    }

}
