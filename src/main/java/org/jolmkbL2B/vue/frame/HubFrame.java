/*
 * Created by JFormDesigner on Wed Dec 01 13:08:35 CET 2021
 */

package org.jolmkbL2B.vue.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.*;

import org.jolmkbL2B.AppControllers;
import org.jolmkbL2B.marqueurs.Marqueur;
import org.jolmkbL2B.marqueurs.PlaceType;
import org.jolmkbL2B.vue.panel.*;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author Mathys Gagner
 */
public class HubFrame extends JFrame {

    private AppControllers app;
    public HubFrame(AppControllers app) {
        super("OurMap : Hub");
        this.app = app;
        initComponents();
    }

    MouseListener addMarqueur = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            enableMarqueurSelection(false);

            /** création d'un marqueur en cliquant  */
            JXMapViewer me_src = (JXMapViewer) me.getSource();

            Marqueur clickwaypoint = new Marqueur(PlaceType.CUSTOM,me_src.convertPointToGeoPosition(me.getPoint()).getLatitude(),
                    me_src.convertPointToGeoPosition(me.getPoint()).getLongitude(), 0, "click");

            mapPanel1.waypoints.add(clickwaypoint);
            mapPanel1.OurMap.setOverlayPainter(mapPanel1.loader.updateDisplay(mapPanel1.waypoints));

            System.out.println(" mouse  x coordinates =" + me_src.getMousePosition().getX() + "/ mouse y coordinates =" + me_src.getMousePosition().getY());
            System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());
            enableMarqueurCreation(false);
            listesMarqueursPanel1.createMarqueurButton.setEnabled(true);
            enableMarqueurSelection(true);
            listesMarqueursPanel1.createMarqueurButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listesMarqueursPanel1.createMarqueurButton.setEnabled(false);
                    enableMarqueurCreation(true);
                }
            });
        }

        public void mousePressed(MouseEvent e) {} public void mouseReleased(MouseEvent e) {} public void mouseEntered(MouseEvent e) {} public void mouseExited(MouseEvent e) {}
    };

    MouseListener selectMarqueurMap = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent me) {
            JXMapViewer me_src = (JXMapViewer) me.getSource();
            for(Marqueur wp : mapPanel1.waypoints) {
                if (Math.abs(wp.getPosition().getLatitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLatitude())  <= 0.00001
                && Math.abs(wp.getPosition().getLongitude() - me_src.convertPointToGeoPosition(me.getPoint()).getLongitude()) <= 0.00001 ) {
                    System.out.println(wp.getName() + " | " + wp.getPlaceType().toString());
                    marqueurInfosPanel1.tfNom.setText(wp.getName() + " (" + wp.getPlaceType().toString() + ")");
//                    marqueurInfosPanel1.taDesc.setText(wp.);
                    break;
                }
                else {
                    marqueurInfosPanel1.tfNom.setText("aucun marqueur");
                    marqueurInfosPanel1.taDesc.setText("");
                }
//                System.out.println(wp.getPosition().toString());
            }
            System.out.println("CONVERTING MOUSE COORDINATES TO geoposition ones =====> latitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLatitude() + "  longitude :" + me_src.convertPointToGeoPosition(me.getPoint()).getLongitude());

        }
        public void mousePressed(MouseEvent e) {} public void mouseReleased(MouseEvent e) {} public void mouseEntered(MouseEvent e) {} public void mouseExited(MouseEvent e) {}

    };



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label2 = new JLabel();
        label3 = new JLabel();
        listesMarqueursPanel1 = new ListesMarqueursPanel();
        mapPanel1 = new MapPanel();
        marqueurInfosPanel1 = new MarqueurInfosPanel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label2 ----
        label2.setText("text");
        contentPane.add(label2, BorderLayout.WEST);

        //---- label3 ----
        label3.setText("text");
        contentPane.add(label3, BorderLayout.NORTH);
        contentPane.add(listesMarqueursPanel1, BorderLayout.EAST);
        contentPane.add(mapPanel1, BorderLayout.CENTER);
        contentPane.add(marqueurInfosPanel1, BorderLayout.SOUTH);
        setSize(845, 520);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        listesMarqueursPanel1.createMarqueurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listesMarqueursPanel1.createMarqueurButton.setEnabled(false);
                enableMarqueurCreation(true);
            }
        });

        enableMarqueurSelection(true);


    }

    public void enableMarqueurCreation(boolean etat) {
        /** mouse clicks to waypoints method  */
        if(etat == true) {
            mapPanel1.OurMap.addMouseListener(addMarqueur);
            enableMarqueurSelection(false);
        }
        else {
            mapPanel1.OurMap.removeMouseListener(addMarqueur);
        }
    }

    public void enableMarqueurSelection(boolean etat) {
        if(etat == true) {
            mapPanel1.OurMap.addMouseListener(selectMarqueurMap);
        }
        else {
            mapPanel1.OurMap.removeMouseListener(selectMarqueurMap);
        }
    }

    public void enablePlusDinfos(boolean etat, Marqueur mq) {
        ResultSet infos = this.app.marqueurController.fetchAllInfo(mq.getLieuID(), mq.getPlaceType());
        switch(mq.getPlaceType()) {
            case CUSTOM:
                break;
            case ARRETBUS:
                break;
            case HOTEL:
                break;
            case SCHOOL:
                break;
            default:
                throw new IllegalArgumentException("PlaceType invalide");
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    public JLabel label2;
    public JLabel label3;
    public ListesMarqueursPanel listesMarqueursPanel1;
    public MapPanel mapPanel1;
    public MarqueurInfosPanel marqueurInfosPanel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
