
package org.jolmkbL2B.controllers;

import java.sql.*;
/*auteurs jalal & oualid



 */
public class UtilisateurController {

public int idutilisateur;
public String displayName;
public String password;
public int idFavList;
public boolean isAdmin;

    public UtilisateurController(int idutilisateur,String displayName,String password,int idFavList,boolean isAdmin)
    {
        this.idutilisateur=idutilisateur;
        this.displayName=displayName;
        this.password=password;
        this.idFavList=idFavList;
        this.isAdmin=isAdmin;

    }
    public UtilisateurController()
    {
    }


public void AfficherUtilisateurs()
{
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                "mathys", "projet2021GL"); //etablissement connection
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        stmt.execute("USE ourmapdb;");
        String log ="select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs ";
        //select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs ;
        //  String log ="select idutilisateur,displayName,password,idFavList,isAdmin from utilisateurs ";
        PreparedStatement ps =  con.prepareStatement(log);
        ResultSet resultat = ps.executeQuery();
        if(resultat.next()){

           // dispose();
        }

        else
        {
            //mmessage="Désolé, nous n'avons pas pu trouver votre compte.";
        }
        //message.setText(mmessage);
    }

    catch(SQLException e) {
        System.out.println("Connection failed. Aborting process." + e);
    }


}
public void UpdateNomUtilisateur(int idutilisateur,String NouveauNom)
{
    stmt.executeUpdate("UPDATE `utilisateurs`\n" +
            " SET `displayName` = " + NouveauNom + "\n" +
            "WHERE idutilisateur = " + idutilisateur + ";");
    con.commit();
}



}



