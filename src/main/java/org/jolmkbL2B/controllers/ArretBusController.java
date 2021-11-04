package org.jolmkbL2B.controllers;
import java.sql.*;
import org.jolmkbL2B.marqueurs.ArretBus;
import org.jolmkbL2B.marqueurs.Hotel;
import org.jxmapviewer.viewer.GeoPosition;

public class ArretBusController {
    private final Connection con;

    public ArretBusController(Connection con)   {
        this.con = con;
        try {
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArretBus getArretBus(int id)   {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN arretsbus WHERE idmarqueur = " + id + ";");
            stmt.close();
            /**Vérifie que la requête ne renvoie qu'une seule ligne. Dans tous les cas, le premier est renvoyé.
             * S'il y en a plus, affiche un message d'erreur indiquant l'erreur */
            if(rs != null)  {
                return(importArretBus(rs));
            }
            else System.out.println("Aucun arret de bus ne correspond à cet identifiant.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArretBus getArretBusByName(String name)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN arretsbus WHERE name = \"" + name + "\";");
            stmt.close();
            /**Vérifie que la requête ne renvoie qu'une seule ligne. Dans tous les cas, le premier est renvoyé.
             * S'il y en a plus, affiche un message d'erreur indiquant l'erreur */
            if(rs != null)  {
                return(importArretBus(rs));
            }
            else System.out.println("Aucun arret de bus ne correspond à ces coordonnées.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArretBus getArretBus(GeoPosition position)   {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN arretsbus WHERE latitude = " + position.getLatitude() +
                    " AND longitude = " + position.getLongitude() + ";");
            stmt.close();
            /**Vérifie que la requête ne renvoie qu'une seule ligne. Dans tous les cas, le premier est renvoyé.
             * S'il y en a plus, affiche un message d'erreur indiquant l'erreur */
            if(rs != null)  {
                return(importArretBus(rs));
            }
            else System.out.println("Aucun arret de bus ne correspond à ces coordonnées.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArretBus importArretBus(ResultSet rs)   {
        try {
            rs.next(); //Lecture de la première ligne

            ArretBus arret = new ArretBus (rs.getLong(1), rs.getDouble(3), rs.getDouble(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8));
            rs.last();
            if (rs.getRow() > 1)    {
                System.err.println("Unexpected result count. Expected : 1. Actual : " + rs.getRow() +". Returning first result only.\n");
            }
            return arret;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
