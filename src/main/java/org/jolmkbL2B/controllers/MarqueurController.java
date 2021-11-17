package org.jolmkbL2B.controllers;
import org.jolmkbL2B.marqueurs.*;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/** Cette classe contient toutes les méthodes pour les interactions avec la base de données concernant les marqueurs et
 * objets dérivés.
 * @author Bastien*/
public class MarqueurController  {

    protected final Connection con;

    public MarqueurController(Connection con) {
        this.con = con;
        try {
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            con.setAutoCommit(false);
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet fetchAll() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, type, latitude, longitude, name FROM marqueurs;");
            stmt.close();
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet fetchMarqueurBasicInfo(long id)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs WHERE idmarqueur = " + id + ";");
            checkResultSetSize(rs);
            stmt.close();
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
                stmt.close();
                return infoMarqueur;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
                    else throw new RuntimeException("Vous n'avez pas la permission de modifier cette" +
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