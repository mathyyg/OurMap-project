
package org.jolmkbL2B.controllers;

import java.sql.*;

public class UtilisateurController {
public int idutilisateur;
public String displayName;
public String password;
public int idFavList;
public boolean isAdmin;

public void AfficherUtilisateurs()
{
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                "mathys", "projet2021GL"); //etablissement connection
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        stmt.execute("USE ourmapdb;");
        String log ="select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs ;";
        //select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs ;
        PreparedStatement ps =  con.prepareStatement(log);
        ResultSet resultat = ps.executeQuery();
        if(resultat.next()){

            dispose();
        }

        else
        {
            mmessage="Désolé, nous n'avons pas pu trouver votre compte.";
        }
        message.setText(mmessage);
    }

    catch(SQLException e) {
        System.out.println("Connection failed. Aborting process." + e);
    }


}




}



