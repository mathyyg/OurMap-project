package org.jolmkbL2B.DataBaseImport;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class MarqueursCSVToDB   {

    /** Min pour l'export de fichier CSV
     * @author Bastien*/
    public static void main( String[] args ) throws SQLException {

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
            CsvExporter exporter = new CsvExporter();

            Path path = Paths.get("C:\\project_database\\csv\\hotels.csv");

            //boolean exportHotelSuccess = exporter.exportHotels(path.toString(), con);
            //System.out.println("Export hotels terminé : " + exportHotelSuccess);
            //con.commit();

            path = Paths.get("C:\\project_database\\csv\\arretsbus.csv");

            //boolean exportArretBusSuccess = exporter.exportArretBus(path.toString(), con);
            //System.out.println("Export arretsBus terminé : " + exportArretBusSuccess);

           //con.commit();
            con.rollback();
            path = Paths.get("C:\\Users\\lloyd\\Desktop\\projects\\l2s1 gl project\\project database\\csv\\schools.csv");
            exporter.exportSchools(path.toString(), con);
            con.commit();

            con.close();
        }

        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }
}  //comentaire