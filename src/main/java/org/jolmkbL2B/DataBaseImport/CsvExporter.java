package org.jolmkbL2B.DataBaseImport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class CsvExporter {
    private final String info = "Classe utiisée pour l'import de fichiers CSV stockés localement vers la base de données";

    public CsvExporter() {
    }

    /** @obsolete N'est plus compatible avec la BD
     * @author Bastien*/
    private boolean exportArretBus(String path, Connection con)  {
        boolean success = false;

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(path)); //Buffer servant à lire le fichier ligne par ligne
            String csvLine = "";
            lineReader.readLine(); //la ligne actuellement lue

            while ((csvLine = lineReader.readLine()) != null) {
                String[] data = csvLine.split(","); //la virgule sépare les colonnes
                Statement stmt = con.createStatement();

                //insertion dans la table marqueurs
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
                //récupération de l'identifiant du dernier marqueur
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


    /** @obsolete N'est plus compatible avec la BD
     * @author Bastien*/
    private boolean exportHotels(String path, Connection con)    {
        boolean success = false;
        String csvLine = "";

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(path)); //Buffer servant à lire le fichier ligne par ligne

            csvLine = lineReader.readLine();//la ligne actuellement lue

            while ((csvLine = lineReader.readLine()) != null) {
                String[] data = csvLine.split(","); //la virgule sépare les colonnes
                Statement stmt = con.createStatement();

                //insertion dans la table marqueurs
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
                //récupération de l'identifiant du dernier marqueur
                ResultSet rs = stmt.executeQuery(getLastIDQuerry);
                rs.next();
                int rowID = rs.getInt(1);

                //insertion du reste des infos

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


    public boolean exportSchools(String path, Connection con)   {
        boolean success = false;

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(path)); //Buffer servant à lire le fichier ligne par ligne
            String csvLine = "";//la ligne actuellement lue
            lineReader.readLine();

            while ((csvLine = lineReader.readLine()) != null) {
                String[] data = csvLine.split(",");//la virgule sépare les colonnes
                Statement stmt = con.createStatement();

                //insertion dans la table marqueurs
                String sql1 = "INSERT INTO `ourmapdb`.`marqueurs`\n" +
                        "(`placeType`,\n" +
                        "`latitude`, \n" +
                        "`longitude`, \n" +
                        "`marqueurName`)\n VALUES (\"SCHOOL\", 0, 0, \"" + data[0] + "\");";
                stmt.executeUpdate(sql1);

                String getLastIDQuerry = "SELECT idmarqueur FROM marqueurs ORDER BY idmarqueur DESC LIMIT 1;";
                //récupération de l'identifiant du dernier marqueur
                ResultSet rs = stmt.executeQuery(getLastIDQuerry);
                rs.next();
                int rowID = rs.getInt(1);

                //insertion du reste des infos
                String sql2 = "INSERT INTO `ourmapdb`.`schools`\n" +
                        "(`idmarqueur`,\n" +
                        "`city`,\n" +
                        "`marqueurDescription`," +
                        "`schoolType`," +
                        "`statut`," +
                        "`adresse`)" +
                        "VALUES\n( " + rowID + ", \"" + data[1] + "\", \"NO DESCRIPTION YET\", \"" + data[3] + "\", \"" +
                data[4] + "\", \"" + data[2] + "\");";
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
}
