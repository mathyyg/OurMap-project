package org.jolmkbL2B.controllers;
import org.jolmkbL2B.marqueurs.*;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;

/** Cette classe contient toutes les méthodes pour les interactions avec la base de données concernant les marqueurs et
 * objets dérivés.
 * @author Bastien*/
public class MarqueurController  {
    protected Connection con;

    public MarqueurController() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "projet2021GL");
            this.con = con;
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            con.setAutoCommit(false);
        }
        catch(SQLException sqlException) {
            this.con = null;
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database in class MarqueurController" +
                    ". Initialization cannot continue.");
            // Soucis : si pas internet, on peut pas utiliser l'appli
            sqlException.printStackTrace();
            try {
                con.close();
            }
            catch(SQLException e)   {
                e.printStackTrace();
                System.err.println("Connection cannot be closed or already is.");
            }
        }
    }



    /** Recupere tous les marqueurs de la base de données.
     * @since 2.0 ~
     * @version 1
     * @retyrn ResultSet aux colonnes suivantes : (`type`, `latitude`, `longitude`, `name`, `city`, `description`)
     * @author Bastien
     */
    public ResultSet fetchAll() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, type, latitude, longitude, name, city, description" +
                    " FROM marqueurs;");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /** Recupere tous les marqueurs d'un certain type (seulement les hotels, les arrets de bus etc...)
     * @since 2.4 ~
     * @version 1
     * @retyrn ResultSet aux colonnes suivantes : (`type`, `latitude`, `longitude`, `name`, `city`, `description`)
     * @author Bastien
     */
    public ResultSet fetchAllByType(PlaceType placeType)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, type, latitude, longitude, name, city, description" +
                    " FROM marqueurs WHERE type = \"" + placeType.toString() + "\";");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /** Recupere tous les marqueurs de la base de données.
     * @since 2.0 ~
     * @param id identifiant d'un marqueur
     * @version 1
     * @author Bastien
     */
    public ResultSet fetchMarqueurBasicInfo(long id)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs WHERE idmarqueur = " + id + ";");
            checkResultSetSize(rs);
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Recupere toutes les infos d'un unique marqueur
     * @version 1
     * @retyrn ResultSet aux colonnes suivantes : (`type`, `latitude`, `longitude`, `name`, `city`, `description`) + colonnes specifiques aux tables
     * @author Bastien*/
    public ResultSet fetchAllInfo(long id, PlaceType placeType) {
        String table = selectTable(placeType);

        try {
            if (table.equals("none"))    {
                System.err.println("Erreur lors de la selection de la table de BD.");
                return null;
            }
            else {
                Statement stmt = con.createStatement();
                ResultSet infoMarqueur = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN " + table + " WHERE " +
                        "idmarqueur = " + id + ";");
                checkResultSetSize(infoMarqueur);
                return infoMarqueur;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /** Verifie qu'un ResultSet a au plus 1 resultat. Affiche un message d'avertissement si ce n'est pas le cas
     * @author Bastien*/
    private void checkResultSetSize(ResultSet rs)   {
        try {
            int rsSize = rs.getFetchSize();
            if (rsSize > 1)
            {
                System.out.println(" Warning : Unexpected ResultSet size. " +
                        "Exepected : 1. Actual size : " + rsSize + ".\nStill returning all results.");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /** Selection d'une table de base de donnees en fonction d'un parametre Enum PlaceType donné */
    protected String selectTable(PlaceType placeType) {
        switch(placeType)   {
            case SCHOOL:
                return "schools";
            case HOTEL:
                return "hotels";
            case ARRETBUS:
                return "arretsbus";
            default:
                System.err.println("Argument PlaceType non reconnu;");
                return "none";
        }
    }

    /** Insertion des données de base d'un nouveau marqueur dans la table marqueurs
     * @author Bastien
     * @since 2.5
     * @param marqueur : un objet Marqueur
     * @return identifiant de la nouvelle entree, -1 en cas d'erreur
     * @version 1*/
    private int insertMarqueur(Marqueur marqueur)    {
        boolean success = false;
        int lastID = -1;

        try {
            Statement stmt = con.createStatement();
            String sql1 = "INSERT INTO `ourmapdb`.`marqueurs`\n" +
                    "(`type`,\n" +
                    "`latitude`,\n" +
                    "`longitude`,\n" +
                    "`name`,\n" +
                    "`city`,\n" +
                    "`description`)\n VALUES (\"" + marqueur.getPlaceType().toString() + "\", " +
                    marqueur.getPosition().getLatitude() + ", " + marqueur.getPosition().getLongitude() + ", \"" + marqueur.getName() + "\", \""
                    + marqueur.getCity() + "\", \"" + marqueur.getDescription() + "\");";
            stmt.executeUpdate(sql1);

            String getLastIDQuerry = "SELECT idmarqueur FROM marqueurs ORDER BY idmarqueur DESC LIMIT 1;"; //Recuperation de l'identifiant de la nouvelle entree
            ResultSet rs = stmt.executeQuery(getLastIDQuerry);
            rs.next();
            lastID = rs.getInt(1);
            con.commit();
            stmt.close();
        }
        catch(SQLException e)
            {e.printStackTrace();}

        return lastID;

    }
    /** Insertion des données de base d'un nouvel Hotel dans la tables marqueurs et hotels
     * @author Bastien
     * @since 2.5
     * @param hotel : un objet Hotel (etendu de marqueur)
     * @return identifiant de la nouvelle entree
     * @version 1*/
    public boolean insertMarqueur(Hotel hotel) {
        boolean success = false;
        try {
            int id = insertMarqueur((Marqueur) hotel); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(id> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`hotels`\n" +
                        "(`idmarqueur`,\n" +
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
                        "VALUES ( " + id + ", \"" + hotel.getAddress() + "\", " + hotel.isHasRestaurant() + ", \"" + hotel.getNumTelephone() +
                        "\", " + hotel.getCategorieEtoiles() + ", \"" + hotel.getSiteWeb() + "\", \"" + hotel.getTripAdvisor() +
                        "\", " + hotel.getLabelHandicap()[0] + ", " + hotel.getLabelHandicap()[1] + ", " + hotel.getLabelHandicap()[1] +
                        ", " + hotel.getLabelHandicap()[2] + ", " + hotel.getLabelHandicap()[3] + ", " + hotel.isAnimauxAcceptes() + ");";

                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return success;
    }

    /** Insertion des données de base d'un nouvel Arret de Bus dans les tables marqueurs et arretsbus
     * @author Bastien
     * @since 2.5
     * @param arret : un objet ArretBus (etendu de marqueur)
     * @return identifiant de la nouvelle entree
     * @version 1*/
    public boolean insertMarqueur(ArretBus arret)   {
        boolean success = false;
        try {
            int id = insertMarqueur((Marqueur) arret); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(id> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`arretsbus`\n" +
                        "(`idmarqueur`,\n" +
                        "`accesHandi`)\n" +
                        "VALUES\n" +
                        "( " + id + ", " + arret.isAccesHandicap() + ");\n";
                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return success;
    }

    public boolean insertMarqueur(School school)   {
        boolean success = false;
        try {
            int id = insertMarqueur((Marqueur) school); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(id> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`schools`\n" +
                        "(`idschools`,\n" +
                        "`type`,\n" +
                        "`statut`,\n" +
                        "`adresse`)\n" +
                        "VALUES\n" +
                        "(" + id + ",\n\"" +
                        school.getSchoolType().toString() + "\",\n\"" +
                        school.PublicOuPrive().toString() + "\",\n\"" +
                        school.getAddress() + ");\n";
                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return success;
    }


    public boolean updateHotel(long idmarqueur, HashMap<TableHotel, String> changes) {
        try {
            Statement stmt = con.createStatement();
            String operations = "";
            String formattedValue = "";


            for(Map.Entry e : changes.entrySet()) {
                TableHotel colonne = (TableHotel) e.getKey();
                if (colonne.equals(TableHotel.description)) {
                    stmt.executeUpdate("UPDATE `ourmapdb`.`marqueurs` (\n" +
                            "SET `description` = \"" + e.getValue().toString() + "\"\n" +
                            "WHERE idmarqueur = " + idmarqueur +";");
                }
                else {
                    switch(colonne) {
                        case etoiles:
                            formattedValue = e.getValue().toString();
                            break;
                        case adresse, numTel, siteWeb, tripadvisor:
                            formattedValue = "\"" + e.getValue().toString() + "\"";
                            break;
                        case hasRestaurant, handi_moteur, handi_mental, handi_auditif, handi_visuel, accepteAnimaux:
                            String bool = e.getValue().toString();
                            if(bool.equals("true") || bool.equals("True") || bool.equals("TRUE") || bool.equals("1"))   {
                                formattedValue = "1";
                            }
                            else formattedValue = "0";
                            break;
                        default :
                            throw new RuntimeException("Vous n'avez pas la permission de modifier cette" +
                                    "information (" + colonne + ")");
                    }
                    operations += "`" + colonne + "` = " + formattedValue +", ";
                }
            }
            operations = operations.substring(0, operations.length()-2);

            String sqlUpdate = "UPDATE `ourmapdb`.`hotels` (SET " + operations + "WHERE idmarqueur = " + idmarqueur +";";
            stmt.executeUpdate(sqlUpdate);
            stmt.close();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            }
            catch (SQLException ex)    {
                ex.printStackTrace();
            }
        }
        catch (RuntimeException e)    {
            System.err.println(e);
        }
        return false;
    }

    public boolean updateArretBus(long idmarqueur, HashMap<TableArretBus, String> changes) {
        try {
            Statement stmt = con.createStatement();
            String operations = "";
            String formattedValue = "";


            for (Map.Entry e : changes.entrySet()) {
                TableArretBus colonne = (TableArretBus) e.getKey();
                if (colonne.equals(TableArretBus.description)) {
                    stmt.executeUpdate("UPDATE `ourmapdb`.`marqueurs` (\n" +
                            "SET `description` = \"" + e.getValue().toString() + "\"\n" +
                            "WHERE idmarqueur = " + idmarqueur + ";");
                } else {
                    if (colonne.equals(TableArretBus.accesHandi)) {
                        String bool = e.getValue().toString();
                        if (bool.equals("true") || bool.equals("True") || bool.equals("TRUE") || bool.equals("1")) {
                            formattedValue = "1";
                        } else formattedValue = "0";
                        operations += "`accesHandi` = " + formattedValue + ", ";
                    }
                    else throw new IllegalArgumentException("Vous n'avez pas la permission de modifier cette" +
                            "information (" + colonne + ")");
                }
            }
            operations = operations.substring(0, operations.length() - 2);

            String sqlUpdate = "UPDATE `ourmapdb`.`arretsbus` (SET " + operations + "WHERE idmarqueur = " + idmarqueur + ";";
            stmt.executeUpdate(sqlUpdate);
            stmt.close();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (RuntimeException e)    {
            System.err.println(e);
        }
        return false;
    }
}