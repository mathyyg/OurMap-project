package org.jolmkbL2B.vue.panel;

import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import org.jolmkbL2B.controllers.MarqueurController;
import org.jolmkbL2B.marqueurs.Marqueur;
import org.jolmkbL2B.marqueurs.MarqueurLoader;
import org.jolmkbL2B.marqueurs.PlaceType;
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
    public static JXMapViewer OurMap; //L'objet "carte"
    private final GeoPosition Blois_Focus; //Le point central de la carte au chargement
    private HashSet<Marqueur> waypoints; //Un hashset contenant tous les waypoints affichés
    private MarqueurLoader loader; // Un objet qui gere l'affichage des marqueurs

    public MapPanel() {

        this.setLayout(new BorderLayout());
//        try {
//            UIManager.setLookAndFeel( new FlatDarkPurpleIJTheme() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }

        this.OurMap = new JXMapViewer();
        this.Blois_Focus = new GeoPosition(47.58507056469526, 1.337506934455753);
        this.loader = new MarqueurLoader();

        /** Creer une TileFactoryInfo pour importer OpenStreetMap La Tile factory genere grosso modo topus les points de la carte */
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        OurMap.setTileFactory(tileFactory);


        /** AFFICHARGE MARQUEUR */
        this.waypoints = loader.loadAllMarkers();
        //this.waypoints = loader.loadAllMarkersByType(PlaceType.HOTEL);
        //this.waypoints = loader.loadListToNewSet(1,1);
        //this.waypoints = loader.loadListInCurrentSet(waypoints, 1, 1);
        OurMap.setOverlayPainter(loader.updateDisplay(waypoints));


        /** mouse clicks to waypoints method  */
        OurMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {

                /** création d'un marqueur en cliquant  */
                JXMapViewer me_src = (JXMapViewer) me.getSource();

                Marqueur clickwaypoint = new Marqueur(PlaceType.ARRETBUS,me_src.convertPointToGeoPosition(me.getPoint()).getLatitude(),
                        me_src.convertPointToGeoPosition(me.getPoint()).getLongitude(), 0, "click", "click", "click");

                waypoints.add(clickwaypoint);
                OurMap.setOverlayPainter(loader.updateDisplay(waypoints));

                System.out.println(" mouse  x coordinates =" + me_src.getMousePosition().getX() + "/ mouse y coordinates =" + me_src.getMousePosition().getY());
                System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());

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
        OurMap.setAddressLocation(Blois_Focus);
        OurMap.setZoom(7);
        this.add(OurMap);
    }

    /** Cette methode permet d'ajouter un Waypoint à l'affichage de la carte */
    //TODO pouvoir ajouter les infos du marqueurs pour affichage dans une frame
    public void updateMapOverlay(Marqueur w)    {
        waypoints.add(w);
        OurMap.setOverlayPainter(loader.updateDisplay(waypoints));
    }

    // METHODE MAIN DESTINEE U N I Q U E M E N T AU DEBUG
//    public static void main(String[] args) {
//        JFrame test = new JFrame("test");
//        test.add(new MapPanel());
//        test.pack();
//        test.setVisible(true);
//    }

}
