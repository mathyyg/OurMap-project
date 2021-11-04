package org.jolmkbL2B.marqueurs;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultWaypoint;
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

    private String info;

    public void loadAllMarkers()    {
        loadHotelMarkers();
    }
    // hashset de lieux, diffs algos pour les differents types de lieux
    // Select * from toutes les tables

   public void loadHotelMarkers() {
       Set<Hotel> hotelMarkers = new HashSet<Hotel>();
       String markerQuerry = "SELECT * FROM Hotel;";
       //TO DO : requetes dans la BD pour obtenir la liste des hotels avec toutes les propriétés
   }



}
