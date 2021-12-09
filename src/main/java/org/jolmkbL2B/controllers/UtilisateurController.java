
package org.jolmkbL2B.controllers;

import org.jolmkbL2B.Utilisateurs.UtilisateurAuthentifié;
import org.jolmkbL2B.Utilisateurs.Utilisateurs;

import java.sql.*;
/** Cette classe permet de gérer les intéractions avec la table utilisateurs de la base de données.
 * @author Mohammed Jalal El Hani */
public class UtilisateurController {

public int idutilisateur;
public String displayName;
public String password;
public int idFavList;
public boolean isAdmin;

    public UtilisateurController(int idutilisateur,String displayName,String password,int idFavList,boolean isAdmin) {
        this.idutilisateur=idutilisateur;
        this.displayName=displayName;
        this.password=password;
        this.idFavList=idFavList;
        this.isAdmin=isAdmin;

    }
    public UtilisateurController() {
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdFavList() {
        return idFavList;
    }

    public void setIdFavList(int idFavList) {
        this.idFavList = idFavList;
    }

//    public void setAdmin(boolean admin) {
//        isAdmin = admin;
//    }

    /** Cette méthode renvoit un ResultSet contenant tous les utilisateurs authentifiés de la base de données
     * @author Mohammed Jalal El Hani
     * @return  un ResultSet
     * @throws SQLException*/
    public ResultSet getAllUtilisateurs() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            String log ="select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs ";
            PreparedStatement ps =  con.prepareStatement(log);
            ResultSet resultat = ps.executeQuery();
            if(resultat.next()){
                return resultat;
            }

        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
        return null;
    }

    /** Cette méthode renvoit un ResultSet contenant un utilisateur recherché par son ID
     * @author Mohammed Jalal El Hani
     * @param identr l'ID de l'utilisateur que l'on cherche
     * @return  un ResultSet contenant l'utilisateur
     * @throws SQLException*/
    public UtilisateurAuthentifié getUtilisateurById(int identr) {
        UtilisateurAuthentifié res = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            String log ="select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs where idutilisateur = ?";
            PreparedStatement ps =  con.prepareStatement(log);
            ps.setInt(1, identr);
            ResultSet resultat = ps.executeQuery();
            if(resultat.next()){
                res = new UtilisateurAuthentifié(resultat.getInt("idutilisateur"), resultat.getString("displayName"),
                                                resultat.getString("password"), resultat.getInt("idFavList"),
                                                resultat.getBoolean("isAdmin"));
            }

        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
        return res;
    }

    /** Cette méthode renvoit un objet de type UtilisateurAuthentifié (pour tester) contenant un utilisateur recherché par son pseudo
     * @author Mohammed Jalal El Hani
     * @param name le nom de l'utilisateur que l'on cherche
     * @return  objet UtilisateurAuthentifié
     * @throws SQLException*/
    public UtilisateurAuthentifié getUtilisateurByName(String name) {
        UtilisateurAuthentifié res = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            String log ="select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs where displayName = ?";
            PreparedStatement ps =  con.prepareStatement(log);
            ps.setString(1, name);
            ResultSet resultat = ps.executeQuery();
            if(resultat.next()){
                res = new UtilisateurAuthentifié(resultat.getInt("idutilisateur"), resultat.getString("displayName"),
                        resultat.getString("password"), resultat.getInt("idFavList"),
                        resultat.getBoolean("isAdmin"));
            }

        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
        return res;
    }

    /** Cette méthode modifie le nom d'un utilisateur avec un ID et un nouveau nom en entrée
     * @author Mohammed Jalal El Hani
     * @param iduser l'ID de l'utilisateur dont on veut modifier le nom
     * @param NouveauNom nouveau nom que l'on veut attribuer
     * @throws SQLException*/
    public void setNom(int iduser,String NouveauNom) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            stmt.executeUpdate("UPDATE `utilisateurs`\n" +
                    " SET `displayName` = " + NouveauNom + "\n" +
                    "WHERE idutilisateur = " + iduser + ";");
            con.commit();
        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }

    /** Cette méthode modifie le champ isAdmin de la base de données pour donner ou non les droits admin à un user avec son ID
     * @author Mohammed Jalal El Hani
     * @param iduser l'ID de l'utilisateur dont on veut changer les droits
     * @param status true ou false selon si on veut donner ou retirer les droits admin à l'utilisateur
     * @return true ou false si la fonction s'est exécutée correctement ou non
     * @throws SQLException*/
    public boolean setAdmin(int iduser,boolean status) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            stmt.executeUpdate("UPDATE `utilisateurs`\n" +
                    " SET `isAdmin` = " + status + "\n" +
                    "WHERE idutilisateur = " + iduser + ";");
            con.commit();
            return true;
        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
        return false;
    }

    /** Cette méthode renvoit le champ booléen isAdmin
     * @author Mohammed Jalal El Hani
     * @return un booléen true ou false */
    public boolean isAdmin(int iduser) {
        return getUtilisateurById(iduser).isAdmin;
    }
}



