package org.jolmkbL2B.DataBaseImport;

import java.sql.*;

public class SchoolGeopositionFinder {

    /** Classe permettant de retrouver les coordonnées d'un lieu grace à son adresse et à une table de base de données
     * @author Bastien Richardeau
     * @version 1
     * @since 2.7.3*/

    public static void main(String[] args) throws SQLException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "PROJET2022poogl"); //etablissement connection
            con.setAutoCommit(false);
            /**
             Les changements sur la BD ne seront enregistrés que lorsque l'instruction con.commit() ext executée
             * Cela permet d'éviter que la transaction ne soit que partiellement realisée si le process crashe */
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            System.out.println("Connection established!");
            String findGeopositionFromAddressQuery = "SELECT latitude, longitude, idmarqueur FROM adresses, schools\n" +
                    "WHERE adresses.ville LIKE schools.city AND adresses.adresse LIKE CONCAT('%', schools.adresse ,'%');";
            ResultSet rs = stmt.executeQuery(findGeopositionFromAddressQuery);
            Statement stmt2 = con.createStatement();
            int i=1;
            while(rs.next())    {
                stmt2.executeUpdate("UPDATE marqueurs " +
                        "SET latitude = " + rs.getDouble("latitude") + ", longitude = " +
                        rs.getDouble("longitude") + " WHERE idmarqueur = " + rs.getLong("idmarqueur") + " ;");
                System.out.println(i);
                i++;
            }
            con.commit();

        } catch (SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }
}

