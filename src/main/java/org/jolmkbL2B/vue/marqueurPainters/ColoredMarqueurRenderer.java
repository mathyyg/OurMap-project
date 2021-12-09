package org.jolmkbL2B.vue.marqueurPainters;


import org.jolmkbL2B.marqueurs.MarqueurLoader;
import  org.jolmkbL2B.vue.panel.MapPanel;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.ServerError;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.sql.*;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import org.jolmkbL2B.marqueurs.ArretBus;
import org.jolmkbL2B.marqueurs.Marqueur;
import org.jolmkbL2B.marqueurs.*;
import org.jolmkbL2B.marqueurs.MarqueurLoader.*;
import org.jolmkbL2B.marqueurs.PlaceType;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointRenderer;


import static org.jolmkbL2B.marqueurs.Marqueur.*;
/**
 * MarqueurRenderer modifié qui sert à remplacer le DefaultwaypointRenderer classique trouvé dans la
 * classe parDéfaut DefaultWaypointRenderer cette méthode sert à differencier les Differents Type de Marqueur
 * affiché sur notre carte, la méthode DefaultWaypointRenderer faisait en sorte de superposer une image d'un
 * marqueur non modifiable sur la carte , cette méthode s'en inspire associant chaque image d'un marqueur à son type
 * d'enum , CUSTOM,SCHOOL,HOTEL,ARRETBUS à l'aide d'un IF qui executé cette vérification avant chaque instanciation
 *
 * @author KHALED MAHDI
 * */

public class ColoredMarqueurRenderer implements WaypointRenderer<Marqueur> {
    /**
     * un BufferedImage qui importe l'image que nous avons choisi depuis un chemin relatif que nous spécifions
     *  dans ce cas la c'est le src/main/java/org/jolmkbL2B/vue/marqueurPainters/
     * dans ce repertoire nous avons 3 types d'images spécifique pour chaque type de marqueur
     * la méthode BufferedImage requiert un try/catch si jamais elle n'arrive pas à trouver une image
     * dans ce cas la le catch le chemin erroné que nous avons spécifié
     **/
    BufferedImage BLUEPIN;

    {
        try {
            BLUEPIN = ImageIO.read(new File("src/main/java/org/jolmkbL2B/vue/marqueurPainters/BLUE PIN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage REDPIN;

    {
        try {


            REDPIN =ImageIO.read(new File("src/main/java/org/jolmkbL2B/vue/marqueurPainters/RED PIN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage ORANGEPIN;

    {
        try {


            ORANGEPIN =ImageIO.read(new File("src/main/java/org/jolmkbL2B/vue/marqueurPainters/ORANGE PIN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    BufferedImage GREENPIN;

    {
        try {


            GREENPIN =ImageIO.read(new File("src/main/java/org/jolmkbL2B/vue/marqueurPainters/GREEN PIN.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * la méthode paintWaypoint disponible dans WaypointRenderer fourni avec Jxmapviewer
     * qui à était modifié afin de vérifier le type d'enum avant chaque affichage et d'afficher
     * l'image correspondante
     * elle prends en paramètre un objet graphique 2D , la carte sur laquelle elle superposera l'image, et
     * cette fois ci notre marqueur avec tout ses attributs ce qui permettra de vérifier son enum (PLACETYPE)
     * à l'aide d'un IF else
     *
     * @param g,map,waypoint,REDPIN,GREENPIN,BLUEPIN,ORANGEPIN
     *
     * */
    @Override
    public void paintWaypoint(Graphics2D g, JXMapViewer map, Marqueur waypoint)
    {

        if (waypoint.getPlaceType()==PlaceType.HOTEL)
        {
            Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());

            int x = (int) point.getX() - REDPIN.getWidth() / 2;
            int y = (int) point.getY() - REDPIN.getHeight();

            g.drawImage(REDPIN, x, y, null);
        }
        else if (waypoint.getPlaceType()==PlaceType.ARRETBUS)
        {
            Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());

            int x = (int) point.getX() - BLUEPIN.getWidth() / 2;
            int y = (int) point.getY() - BLUEPIN.getHeight();

            g.drawImage(BLUEPIN, x, y, null);
        }

        else if (waypoint.getPlaceType()==PlaceType.CUSTOM)
        {
            Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());

            int x = (int) point.getX() - GREENPIN.getWidth() / 2;
            int y = (int) point.getY() - GREENPIN.getHeight();

            g.drawImage(GREENPIN, x, y, null);
        }

        else if (waypoint.getPlaceType()==PlaceType.SCHOOL)
        {
            Point2D point = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());

            int x = (int) point.getX() - ORANGEPIN.getWidth() / 2;
            int y = (int) point.getY() - ORANGEPIN.getHeight();

            g.drawImage(ORANGEPIN, x, y, null);
        }


    }



}