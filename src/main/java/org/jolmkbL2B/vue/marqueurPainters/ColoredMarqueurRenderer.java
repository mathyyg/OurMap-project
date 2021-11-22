package org.jolmkbL2B.vue.marqueurPainters;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.rmi.ServerError;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jolmkbL2B.marqueurs.Marqueur;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointRenderer;

public class ColoredMarqueurRenderer implements WaypointRenderer<Marqueur> {

    private final Map<Color, BufferedImage> map = new HashMap<Color, BufferedImage>();
    private BufferedImage origImage;

    public ColoredMarqueurRenderer() {
            URL resource = getClass().getResource("waypoint_white.png");

            try
            {
                origImage = ImageIO.read(resource);
            }
            catch (Exception ex)
            {
                System.err.println("couldn't read waypoint_white.png");
            }
        }
        paintWaypoint(Graphics2D g, JXMapViewer OurMap, Marqueur marqueur)  {


        }
    }
}
