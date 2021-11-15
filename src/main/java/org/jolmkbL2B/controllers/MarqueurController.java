package org.jolmkbL2B.controllers;
import org.jolmkbL2B.marqueurs.*;

import java.util.HashSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MarqueurController {
    protected final Connection con;
    private HotelController hotelController;

    public MarqueurController(Connection con) {
        this.con = con;
        try {
            Statement stmt = con.createStatement();
            stmt.execute("USE ourmapdb;");
            stmt.close();
            this.hotelController = new HotelController(this.con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashSet<Marqueur> getAllMarkers()    {

    }

}
