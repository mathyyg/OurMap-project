package org.jolmkbL2B;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.*;
import org.jxmapviewer.viewer.*;
import org.jolmkbL2B.marqueurs.*;


public class Main
{
    public static void main( String[] args ) {
        System.out.println("Hello World!");
        App app = new App();


        //Generator generator = new Generator();
        JXMapViewer OurMap = new JXMapViewer();
        MarqueurLoader loader = new MarqueurLoader();
        Set<Waypoint> markers = new HashSet<Waypoint>();


        /** Creer une TileFactoryInfo pour importer OpenStreetMap La Tile factory genere grosso modo topus les points de la carte */
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        OurMap.setTileFactory(tileFactory);


        /** AFFICHARGE MARQUEUR */
        markers.add(sncf);
        markers.add(papin);

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(markers);

        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
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

                List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
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
        JFrame mapFrame = new JFrame("Welcome to our map");
        mapFrame.getContentPane().add(OurMap);
        mapFrame.setSize(800, 600);
        mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapFrame.setVisible(true);
        OurMap.setAddressLocation(gare.getCoord());
        OurMap.setZoom(7);
    }
}
