package org.jolmkbL2B.controllers;

import java.sql.*;

public class UserContentController implements ListesEtCommentaires  {
    private Connection con;

    public UserContentController()   {
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
            System.out.println("Error establishing connection with database in class UserContentController" +
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

    public ResultSet fetchAllCommentairesByMarqueur(long idmarqueur)  {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, idutilisateur, text FROM commentaires WHERE idmarqueur = " + idmarqueur + " AND setVisible = 1;");
            stmt.close();
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
            stmt.close();
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
                    "\"" + idutilisateur + "\",\n" +
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


    public ResultSet fetchList(long idliste)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur FROM listemarqueurs WHERE idliste = " + idliste +
                    ";");
            stmt.close();
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
/** */
    public long createList(long idutilisateur, String listName)   {
        try {
            Statement stmt = con.createStatement();
            if(stmt.executeUpdate("INSERT INTO `ourmapdb`.`listeOwner`\n" +
                    "(`idutilisateur`, `listname`)\n" +
                    "VALUES\n" +
                    "(" + idutilisateur + ", \"" + listName + "\");") > 0)    {
                //Si l'insertion réussie, on continue en ajoutant le marqueur dans la table listmarqueurs
                String getLastIDQuerry = "SELECT idliste FROM listeOwner ORDER BY idliste DESC LIMIT 1;";
                ResultSet rs = stmt.executeQuery(getLastIDQuerry); //Recuperation de l'id de la liste tout juste crée
                rs.next();
                con.commit();
                return rs.getLong(1);
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
     * */
    public boolean addToList(long idmarqueur, long idliste, long requestingUser) {
        try {
            if(checkListAccessRight(idliste, requestingUser)) {
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
            }
            else System.out.println("Erreur lors de l'ajout du collaborateur.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addCollaborateurListe(long idliste, String newCollaborateurID, long requestingUser)    {
        try {
            if(checkOwnerPrivilege(idliste, requestingUser)) {
                Statement stmt = con.createStatement();
                if (stmt.executeUpdate("INSERT INTO `ourmapdb`.`collabliste`\n" +
                        "(`idliste`,\n" +
                        "`idutilisateur`)\n" +
                        "VALUES\n" +
                        "(" + idliste + ",\n" +
                        "\"" + newCollaborateurID + "\");") > 0)    {
                    con.commit();
                    return true;
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
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
     * @author Bastien
     * */
    public boolean checkListAccessRight(long idliste, long idutilisateur)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idutilisateur FROM listeOwner WHERE idliste = " + idliste +
                    ";");
            rs.next();
            if(rs.getLong(1) == idutilisateur)    {
                stmt.close();
                return true;
            }
            else {
                rs = stmt.executeQuery("SELECT idutilisateur FROM collabliste WHERE idliste = " + idliste +
                        ";");
                stmt.close();
                while (rs.next()) {
                    if (rs.getLong(1) == idutilisateur) return true;
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
                    ";");
            rs.next();
            if (rs.getLong(1) == idutilisateur) {
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
