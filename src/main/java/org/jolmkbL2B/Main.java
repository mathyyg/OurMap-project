package org.jolmkbL2B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import javax.swing.event.MouseInputListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.input.CenterMapListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;

import org.jxmapviewer.painter.*;

import org.jxmapviewer.viewer.*;

import org.jolmkbL2B.locations.*;
import org.jolmkbL2B.markers.*;


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

        DefaultWaypoint jaures = new DefaultWaypoint(new GeoPosition(47.590313485235505, 1.3355955057336704));
/** AFFICHARGE MARQUEUR */
        Set<Waypoint> markers = new HashSet<Waypoint>();
            markers.add(jaures);

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
            waypointPainter.setWaypoints(markers);

        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
            painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);

        OurMap.setOverlayPainter(painter);


        /** Interactions */
        MouseInputListener mia = new PanMouseInputListener(OurMap);
        OurMap.addMouseListener(mia);
        OurMap.addMouseMotionListener(mia);

        OurMap.addMouseListener(new CenterMapListener(OurMap));

        OurMap.addMouseWheelListener(new ZoomMouseWheelListenerCursor(OurMap));

        OurMap.addKeyListener(new PanKeyListener(OurMap));

        /** Affichage de la carte */
        JFrame mapFrame = new JFrame("Welcome to our map");
        mapFrame.getContentPane().add(OurMap);
        mapFrame.setSize(800, 600);
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setVisible(true);
        OurMap.setAddressLocation(almostAnHotel.getCoord());
        OurMap.setZoom(7);

        System.out.println(almostAnHotel.toString());

    }
}
