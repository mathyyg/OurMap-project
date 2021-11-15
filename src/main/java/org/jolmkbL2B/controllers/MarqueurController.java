package org.jolmkbL2B.controllers;
import org.jolmkbL2B.marqueurs.*;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/** Cette classe contient toutes les méthodes pour les interactions avec la base de données concernant les marqueurs et
 * objets dérivés.
 * @author Bastien*/
public class MarqueurController {
    private Connection con;

    public MarqueurController(Connection con) {
        this.con = con;
    }

    
    public ResultSet fetchAll() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs;");
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

    public ResultSet[] fetchAllInfo(long id, PlaceType placeType) {
        String table = selectTable(placeType);

        try {
            if (table == "none")    return null;
            else {
                Statement stmt = con.createStatement();
                ResultSet infoMarqueur = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN " + table + " WHERE " +
                        "idmarqueur = " + id + ";");
                checkResultSetSize(infoMarqueur);
                ResultSet commentaires = stmt.executeQuery("SELECT idmarqueur, idutilisateur, text, FROM commentaires " +
                        "WHERE idmarqueur = " + id + " AND setVisible = 0;");
                stmt.close();
                ResultSet[] rsTab = {infoMarqueur, commentaires};
                return rsTab;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateInfo(PlaceType placeType){
        boolean success = false;
        String table = selectTable(placeType);
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
    private String selectTable(PlaceType placeType) {
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
