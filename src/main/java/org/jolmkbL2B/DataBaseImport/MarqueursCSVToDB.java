package org.jolmkbL2B.DataBaseImport;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class MarqueursCSVToDB   {

    public static void main( String[] args ) throws SQLException {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL"); //etablissement connection
            con.setAutoCommit(false);
            /**
             Les changements sur la BD ne seront enregistrés que lorsque l'instruction con.commit() ext executée
             * Cela permet d'éviter que la transaction ne soit que partiellement realisée si le process crashe */
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            System.out.println("Connection established!");
            CsvExporter exporter = new CsvExporter();

            Path path = Paths.get("C:\\project_database\\csv\\hotels.csv");

            boolean exportHotelSuccess = exporter.exportHotels(path.toString(), con);
            System.out.println("Export hotels terminé : " + exportHotelSuccess);
            con.commit();

            path = Paths.get("C:\\project_database\\csv\\arretsbus.csv");

            boolean exportArretBusSuccess = exporter.exportArretBus(path.toString(), con);
            System.out.println("Export arretsBus terminé : " + exportArretBusSuccess);

            con.commit();
            con.close();
        }

        catch(SQLException e) {
            System.out.println("Connection failed. Aborting process." + e);
        }
    }
}  //comentaire