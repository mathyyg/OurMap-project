package org.jolmkbL2B.vue.panel;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import org.jolmkbL2B.marqueurs.MarqueurLoader;
import org.jolmkbL2B.marqueurs.School;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapPanel extends JPanel {

    public MapPanel() {
        this.setLayout(new BorderLayout());
//        try {
//            UIManager.setLookAndFeel( new FlatDarkPurpleIJTheme() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }

        //Generator generator/ = new Generator();
        JXMapViewer OurMap = new JXMapViewer();
        MarqueurLoader loader = new MarqueurLoader();
        Set<Waypoint> markers = new HashSet<Waypoint>();


        /** Creer une TileFactoryInfo pour importer OpenStreetMap La Tile factory genere grosso modo topus les points de la carte */
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        OurMap.setTileFactory(tileFactory);


        /** AFFICHARGE MARQUEUR */
        School fac = new School(47.590044038859595, 1.3366520404815674, 1, "Fac", "", "","",null, null);
        markers.add(fac);
//        markers.add(sncf);
//        markers.add(papin);

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(markers);

        java.util.List<org.jxmapviewer.painter.Painter<JXMapViewer>> painters = new ArrayList<org.jxmapviewer.painter.Painter<JXMapViewer>>();
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        OurMap.setOverlayPainter(painter);


        /** mouse clicks to waypoints method  */
        OurMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                /** création d'un marqueur en cliquant  */
                JXMapViewer me_src = (JXMapViewer) me.getSource();

                DefaultWaypoint clickwaypoint = new DefaultWaypoint((me_src.convertPointToGeoPosition(me.getPoint())));

                markers.add(clickwaypoint);
                WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
                waypointPainter.setWaypoints(markers);

                List<org.jxmapviewer.painter.Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
                painters.add(waypointPainter);

                CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
                OurMap.setOverlayPainter(painter);


                System.out.println(" mouse  x coordinates =" + me_src.getMousePosition().getX() + "/ mouse y coordinates =" + me_src.getMousePosition().getY());
                System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());

                /**address location or center  position */


            }
        });

        /** Interactions  avec le bouton de la souris*/
        MouseInputListener mia = new PanMouseInputListener(OurMap);
        OurMap.addMouseListener(mia);
        OurMap.addMouseMotionListener(mia);
        OurMap.addMouseListener(new CenterMapListener(OurMap));
        OurMap.addMouseWheelListener(new ZoomMouseWheelListenerCursor(OurMap));
        OurMap.addKeyListener(new PanKeyListener(OurMap));

        /** Affichage de la carte */
        OurMap.setAddressLocation(fac.getCoord());
        OurMap.setZoom(7);

        this.add(OurMap);
    }

    // METHODE MAIN DESTINEE U N I Q U E M E N T AU DEBUG
//    public static void main(String[] args) {
//        JFrame test = new JFrame("test");
//        test.add(new MapPanel());
//        test.pack();
//        test.setVisible(true);
//    }

}
