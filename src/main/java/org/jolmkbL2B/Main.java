package org.jolmkbL2B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.jolmkbL2B.locations.*;
import org.jolmkbL2B.markers.*;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

public class Main
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //Generator generator = new Generator();

        JXMapViewer OurMap = new JXMapViewer();



        /** Creer une TileFactoryInfo pour importer OpenStreetMap La Tile factory genere grosso modo topus les points de la carte */
        TileFactoryInfo info= new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        OurMap.setTileFactory(tileFactory);

        boolean[] tab = {true, true, false, false};
        Hotel almostAnHotel = new Hotel(47.590313485235505, 1.3355955057336704, "41000prkrndhjsns", "Je c c pas 1 hotel",
                "Blois", "41000", "Jean jaures", true, "zzzzzzzzzzt", "www.google.com",
                "mdr", 5, tab, true);

        HotelMarker monBeauMarqueur = new HotelMarker(almostAnHotel);

        /** Affichage de la carte */
        JFrame mapFrame = new JFrame("Welcome to our map");
        mapFrame.getContentPane().add(OurMap);
        mapFrame.setSize(800, 600);
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setVisible(true);
        OurMap.setAddressLocation(almostAnHotel.getLocation());
        OurMap.setZoom(7);

        System.out.println(almostAnHotel.toString());

    }
}
