package org.jolmkbL2B.markers;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jolmkbL2B.locations.*;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;


public class HotelMarker extends DefaultWaypoint {
    private Hotel markedHotel;
    private String name;
    private final JButton button;

    public HotelMarker(Hotel _hotel)
    {
        this.markedHotel=_hotel;
        this.name=markedHotel.getName();
        button = new JButton(name.substring(0, 1));
        button.setVisible(true);
        button.setSize(30,30);
    }

    public JButton getButton()  {return button;}

}