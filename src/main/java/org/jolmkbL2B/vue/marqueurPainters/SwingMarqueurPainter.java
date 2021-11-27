package org.jolmkbL2B.vue.marqueurPainters;

import org.jolmkbL2B.marqueurs.Marqueur;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;


/** Cette classe permet de localiser correctement les boutons des marqueurs sur la carte.
 * Recopie / adaptation d'une classe la librairie JXMapViewer2 a l'objet Marqueur.
 *
 * @author Daniel Stahr, contributeur au projet de la librairie JXMapViewer2 utilisee pour ce projet
 */
public class SwingMarqueurPainter extends WaypointPainter<Marqueur> {

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {
        for (Marqueur Marqueur : getWaypoints()) {
            Point2D point = jxMapViewer.getTileFactory().geoToPixel( Marqueur.getPosition(), jxMapViewer.getZoom());
            Rectangle rectangle = jxMapViewer.getViewportBounds();
            int buttonX = (int)(point.getX() - rectangle.getX());
            int buttonY = (int)(point.getY() - rectangle.getY());
            JButton button = Marqueur.getButton();
            button.setLocation(buttonX - button.getWidth() / 2, buttonY - button.getHeight() / 2);
        }
    }
}
