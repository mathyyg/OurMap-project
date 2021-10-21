package org.jolmkbL2B.locations;

import org.jxmapviewer.viewer.GeoPosition;

public class ArretBus extends Lieu  {
    private boolean accesHandicap;

    public ArretBus(PlaceType placeType, double latitude, double longitude, String lieuID, String marqueurID,
                    String name, String zipCode, boolean accesHandicap) {
        super(placeType, latitude, longitude, lieuID, marqueurID, name, zipCode);
        this.accesHandicap = accesHandicap;
    }

    public ArretBus(PlaceType placeType, GeoPosition coord, String name, boolean accesHandicap) {
        super(placeType, coord, name);
        this.accesHandicap = accesHandicap;
    }

    public boolean isAccesHandicap() {return accesHandicap;}

    public void setAccesHandicap(boolean accesHandicap) {this.accesHandicap = accesHandicap;}
}
