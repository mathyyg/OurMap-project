package org.jolmkbL2B.marqueurs;
/** Dans cette version, l'objet n'a pas spécialement de méthodes autre que les getters, setters et toString
 * @author Bastien
 * @version 1 */
public class ArretBus extends Marqueur {
    private boolean accesHandicap;

    public ArretBus( long lieuID, double latitude, double longitude,
                    String name, String city, String description, boolean accesHandicap) {
        super(PlaceType.ARRETBUS, latitude, longitude, lieuID, name, city, description);
        this.accesHandicap = accesHandicap;
    }

    /** Getter pour accesHandicap. Renvoie des entiers car sert principalement lors d'interaction avec la base de
     * donnees, où les booleens sont codés sous forme de 0 et de 1
     * @author Bastien*/
    public int isAccesHandicap() {
        if(accesHandicap) return 1;
        else return  0;}

    public void setAccesHandicap(boolean accesHandicap) {this.accesHandicap = accesHandicap;}

    @Override
    public String toString() {
        return "ArretBus{" +
                "accesHandicap=" + accesHandicap +
                '}';
    }
}
