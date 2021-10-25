package org.jolmkbL2B.marqueurs;

import org.jolmkbL2B.locations.Lieu;
import org.jolmkbL2B.marqueurs.PlaceType;

public class ArretBus extends Marqueur {
    private boolean accesHandicap;

    public ArretBus(double latitude, double longitude, String lieuID,
                    String name, boolean accesHandicap) {
        super(PlaceType.ARRETBUS, latitude, longitude, lieuID, name);
        this.accesHandicap = accesHandicap;
    }
    public boolean isAccesHandicap() {return accesHandicap;}

    public void setAccesHandicap(boolean accesHandicap) {this.accesHandicap = accesHandicap;}
}
