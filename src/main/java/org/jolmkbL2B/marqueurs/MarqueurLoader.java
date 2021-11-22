package org.jolmkbL2B.marqueurs;

import org.jolmkbL2B.controllers.MarqueurController;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoBounds;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.*;
/** Classe servant a importer les marqueurs depuis la base de données
 * Les methodes d'import sont ecrites separement pour chaque type de marqueur, d'une part pour la clarté, d'autre part
 * pour pouvoir recharger seulement un set de marqueurs si besoin
 * @author Bastien, Lucas */

public class MarqueurLoader {
    private MarqueurController marqueurController;
    private WaypointPainter<Waypoint> waypointPainter;
    private final String info = "Classe qui charge ls marqueurs.";


    public MarqueurLoader() {
        this.waypointPainter = new WaypointPainter<Waypoint>();
        this.marqueurController = new MarqueurController();
    }


    /** Methode executee a l'initialisation pour creer le set initial de marqueur.
     * @author Bastien
     * @version 1*/
    public HashSet loadAllMarkers() {
        HashSet<DefaultWaypoint> waypoints = new HashSet<DefaultWaypoint>();

        ResultSet rs = marqueurController.fetchAll();
        try {
            while (rs.next()) {
                DefaultWaypoint wp = new DefaultWaypoint(rs.getDouble(3), rs.getDouble(4));
                waypoints.add(wp);
            }
        }
        catch(SQLException sqlException)   {
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database. Initialization cannot contiue");
            // Soucis : si pas internet, on peut pas utiliser l'appli
        }
        return waypoints;
    }

    public CompoundPainter<JXMapViewer> updateDisplay(HashSet<Waypoint> waypoints)  {

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(waypoints);

        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(waypointPainter);

        return new CompoundPainter<JXMapViewer>(painters);
    }



}
