
package org.jolmkbL2B.controllers;

import org.jolmkbL2B.Utilisateurs.UtilisateurAuthentifié;
import org.jolmkbL2B.Utilisateurs.Utilisateurs;

import java.sql.*;
/*auteurs jalal & oualid*/
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void getAllUtilisateurs() {
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
               System.out.println(resultat);
            }
            else {
                System.out.println("Erreur");
            }
        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }

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

    public void setAdmin(int iduser,boolean status) {
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
        }
        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }
}



