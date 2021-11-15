package org.jolmkbL2B.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class CsvExporter {
    private final String info = "Classe utiisée pour l'import de fichiers CSV stockés localement vers la base de données";

    public CsvExporter() {
    }

    public boolean exportArretBus(String path, Connection con)  {
        boolean success = false;
        String csvLine = "";

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(path)); //Buffer servant à lire le fichier ligne par ligne
            lineReader.readLine();

            while ((csvLine = lineReader.readLine()) != null) {
                String[] data = csvLine.split(",");
                Statement stmt = con.createStatement();
                String sql1 = "INSERT INTO `ourmapdb`.`marqueurs`\n" +
                        "(`type`,\n" +
                        "`latitude`,\n" +
                        "`longitude`,\n" +
                        "`name`,\n" +
                        "`city`,\n" +
                        "`description`)\n VALUES (\"ARRETBUS\", \"" + data[0] + "\", \"" + data[1] + "\", \"" +
                        data[2] + "\", \"" + data[3] + "\", \"No description yet\");";
                stmt.executeUpdate(sql1);

                String getLastIDQuerry = "SELECT idmarqueur FROM marqueurs ORDER BY idmarqueur DESC LIMIT 1;";
                ResultSet rs = stmt.executeQuery(getLastIDQuerry);
                rs.next();
                int rowID = rs.getInt(1);

                String sql2 = "INSERT INTO `ourmapdb`.`arretsbus`\n" +
                        "(`idarretBus`,\n" +
                        "`accesHandi`)\n" +
                        "VALUES\n( " + rowID + ", " + data[4] + ");";
                stmt.executeUpdate(sql2);
                stmt.close();
            }
            lineReader.close();
            System.out.println("Export terminé");
            success = true;
        }
        catch(IOException ioException)  {
            System.err.println(ioException);
        }
        catch(SQLException sqlException)    {
            sqlException.printStackTrace();
            try {
                con.rollback();
            }
            catch(SQLException e)   {
                e.printStackTrace();
            }
        }
        return success;
    }



    public boolean exportHotels(String path, Connection con)    {
        boolean success = false;
        String csvLine = "";

        try {
            con.setAutoCommit(false);
            BufferedReader lineReader = new BufferedReader(new FileReader(path)); //Buffer servant à lire le fichier ligne par ligne
            csvLine = lineReader.readLine();
            Statement stmt = con.createStatement();

            while ((csvLine = lineReader.readLine()) != null) {
                String[] data = csvLine.split(",");
                String sql1 = "INSERT INTO `ourmapdb`.`marqueurs`\n" +
                        "(`type`,\n" +
                        "`latitude`,\n" +
                        "`longitude`,\n" +
                        "`name`,\n" +
                        "`city`,\n" +
                        "`description`)\n VALUES (\"HOTEL\", \"" + data[0] + "\", \"" + data[1] + "\", \"" +
                        data[2] + "\", \"" + data[3] + "\", + \"No description yet\");";
                stmt.executeUpdate(sql1);

                String getLastIDQuerry = "SELECT idmarqueur FROM marqueurs ORDER BY idmarqueur DESC LIMIT 1;";
                ResultSet rs = stmt.executeQuery(getLastIDQuerry);
                rs.next();
                int rowID = rs.getInt(1);

                String sql2 = "INSERT INTO `ourmapdb`.`hotels`\n" +
                        "(`idhotels`,\n" +
                        "`adresse`,\n" +
                        "`hasRestaurant`,\n" +
                        "`numTel`,\n" +
                        "`etoiles`,\n" +
                        "`siteWeb`,\n" +
                        "`tripadvisor`,\n" +
                        "`handi_moteur`,\n" +
                        "`handi_mental`,\n" +
                        "`handi_auditif`,\n" +
                        "`handi_visuel`,\n" +
                        "`accepteAnimaux`)\n" +
                        "VALUES ( " + rowID + ", \"" + data[4] + "\", " + data[5] + ", \"" + data [6] +
                        "\", \"" + data[7] + "\", \"" + data[8] + "\", \"" + data[9] + "\", " + data[10] + ", " + data[11] +
                        ", " + data[12] + ", " + data[13] + ", " + data[14] + ");";
                stmt.executeUpdate(sql2);
           }
            lineReader.close();
            stmt.close();
            System.out.println("Export terminé.");
            con.commit();
            success = true;
        }
        catch(IOException ioException)  {
            System.err.println(ioException);
        }
        catch(SQLException sqlException)    {
            sqlException.printStackTrace();
            try {
                con.rollback();
            }
            catch(SQLException e)   {
                e.printStackTrace();
            }
        }
        return success;
    }
/** Quqntité tres grande de données. Necessite l'utilisation de batch. */

    public boolean exportAdresses(String path, Connection con)  {
        boolean success = false;
        String csvLine = "";
        System.out.println("Step 1. Init");

        try {
            con.setAutoCommit(false);
            System.out.println("Step 2. Init buffer");

            BufferedReader lineReader = new BufferedReader(new FileReader(path)); //Buffer servant à lire le fichier ligne par ligne
            int batchsize=1024;

            System.out.println("Step 3. Entering while loop.");
            int i=1;
            int batchcount =0;

            Statement stmt = con.createStatement();
            lineReader.readLine(); //lire ligne des titres
            while ((csvLine = lineReader.readLine()) != null) {
                String[] data = csvLine.split(",");
                String sql = "INSERT INTO `ourmapdb`.`adresses`\n" +
                        "(`idadresses`,\n" +
                        "`latitude`,\n" +
                        "`longitude`,\n" +
                        "`adresse`,\n" +
                        "`ville`)\n" +
                        "VALUES\n" +
                        "( \"" + data[0] + "\", \"" + data[1] + "\", \"" + data [2] +
                        "\", \"" + data[3] + "\", \"" + data[4] + "\" );" ;
                System.out.println("Adding Row " + i + " to batch. Address : " + data[3] + ", " + data[4] + ".");
                stmt.addBatch(sql);

                if(i % batchsize == 0) {
                    System.out.println("Executing batch n " + batchcount + ".");
                    long[] count = stmt.executeLargeBatch();
                    System.out.println("Batch executed.");
                    batchcount++;
                }
                i++;
            }
            lineReader.close();

            System.out.println("Executing batch n " + batchcount + "(final batch).");
            long[] count = stmt.executeLargeBatch();
            batchcount++;
            System.out.println("Batch executed. Number of batches : " + batchcount);

            stmt.close();
            System.out.println("Export terminé. Commiting transaction");
            con.commit();
            success = true;
        }
        catch(IOException ioException)  {
            System.err.println(ioException);
        }
        catch(SQLException sqlException)    {
            sqlException.printStackTrace();
            try {
                con.rollback();
                System.err.println("Connection Rollbacked;");
            }
            catch(SQLException e)   {
                e.printStackTrace();
            }
        }
        return success;
    }


}
