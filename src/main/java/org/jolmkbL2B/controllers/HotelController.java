package org.jolmkbL2B.controllers;
import java.sql.*;
import org.jolmkbL2B.marqueurs.Hotel;
import org.jxmapviewer.viewer.GeoPosition;

public class HotelController {

    protected final Connection con;

    public HotelController(Connection con) {
        this.con = con;
        try {
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Hotel getHotel(int id)   {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN hotels WHERE idmarqueur = " + id + ";");
            stmt.close();
            /**Vérifie que la requête ne renvoie qu'une seule ligne. Dans tous les cas, le premier est renvoyé.
             * S'il y en a plus, affiche un message d'erreur indiquant l'erreur */
            if(rs != null)  {
                return(importHotel(rs));
            }
            else System.out.println("Aucun hotel ne correspond à cet identifiant.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Hotel getHotel(GeoPosition position)   {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN hotels WHERE latitude = " + position.getLatitude() +
                    " AND longitude = " + position.getLongitude() + ";");
            stmt.close();
            /**Vérifie que la requête ne renvoie qu'une seule ligne. Dans tous les cas, le premier est renvoyé.
             * S'il y en a plus, affiche un message d'erreur indiquant l'erreur */
            if(rs != null)  {
                return(importHotel(rs));
            }
            else System.out.println("Aucun hotel ne correspond à ces coordonnées.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Hotel getHotelByName(String name)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN hotels WHERE name = \"" + name + "\";");
            stmt.close();
            /**Vérifie que la requête ne renvoie qu'une seule ligne. Dans tous les cas, le premier est renvoyé.
             * S'il y en a plus, affiche un message d'erreur indiquant l'erreur */
            if(rs != null)  {
                return(importHotel(rs));
            }
            else System.out.println("Aucun hotel ne correspond à ces coordonnées.");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Hotel importHotel(ResultSet rs)   {
        try {
        rs.next(); //Lecture de la première ligne
        boolean[] labelhandi = {rs.getBoolean(15), rs.getBoolean(16),
                rs.getBoolean(17), rs.getBoolean(18)};

                Hotel hotel = new Hotel(rs.getLong(1), rs.getDouble(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getBoolean(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), labelhandi, rs.getBoolean(14));
                rs.last();
                if (rs.getRow() > 1)    {
                    System.err.println("Unexpected result count. Expected : 1. Actual : " + rs.getRow() +". Returning first result only.\n");
                }
                return hotel;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
