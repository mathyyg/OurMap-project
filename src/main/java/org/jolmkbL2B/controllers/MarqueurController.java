package org.jolmkbL2B.controllers;
import org.jolmkbL2B.marqueurs.*;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

    public boolean updateInfo(PlaceType placeType, String[][] setValues, String[][] condition)  {
        boolean success = false;
        String table = selectTable(placeType);
        try {
            con.setAutoCommit(false);

            con.commit();
        }
        catch(SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
                System.err.println("Connection rollbacked.");
            }
            catch(SQLException ex)  {
                ex.printStackTrace();
            }
        }
        return success;
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
}
