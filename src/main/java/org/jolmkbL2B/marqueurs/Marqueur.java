package org.jolmkbL2B.marqueurs;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Les methodes sont des getters et setters, l'objet en lui meme ne realsie pas vraiment d'action autre que le partage
 * des informations qu'il contient. Idem pour les classes étendues */
public class Marqueur extends DefaultWaypoint {
    private PlaceType placeType; //type d'endroit. Redondant avec le instanceof ?
    private long lieuID;        //identifiant dans la base de données
    private String name;        // nom de l'endroit
    private String city;        //commmune ou l'endroit se situe
    private String description; //description publique du lieu

    private final JButton button;

    public Marqueur(PlaceType placeType, double latitude, double longitude, long lieuID, String name,
                    String city, String description)
    {
        super(latitude, longitude);
        this.placeType = placeType;
        this.lieuID = lieuID;
        this.name = name;
        this.city= city;
        this.description = description;

        button = new JButton(placeType.toString().substring(0, 1));
        button.setSize(24, 24);
        button.setPreferredSize(new Dimension(24, 24));
        button.addMouseListener(new SwingMarqueurMouseListener());
        button.setVisible(true);
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public long getLieuID() {
        return lieuID;
    }

    public void setLieuID(long lieuID) {
        this.lieuID = lieuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JButton getButton() {
        return button;
    }



    private class SwingMarqueurMouseListener implements MouseListener {

        /** Pour les interactions avec les marqueurs sur la carte */
        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(button, "You clicked on " + name);
            System.out.println("You clicked on " + name);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
