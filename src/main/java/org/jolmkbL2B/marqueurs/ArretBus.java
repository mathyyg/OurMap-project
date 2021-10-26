package org.jolmkbL2B.marqueurs;
/** Dans cette version, l'objet n'a pas spécialement de méthodes autre que les getters, setters et toString
 * @author Bastien
 * @version 1 */
public class ArretBus extends Marqueur {
    private boolean accesHandicap;

    public ArretBus(double latitude, double longitude, long lieuID,
                    String name, String city, boolean accesHandicap) {
        super(PlaceType.ARRETBUS, latitude, longitude, lieuID, name, city);
        this.accesHandicap = accesHandicap;
    }
    public boolean isAccesHandicap() {return accesHandicap;}

    public void setAccesHandicap(boolean accesHandicap) {this.accesHandicap = accesHandicap;}

    @Override
    public String toString() {
        return "ArretBus{" +
                "accesHandicap=" + accesHandicap +
                '}';
    }
}
