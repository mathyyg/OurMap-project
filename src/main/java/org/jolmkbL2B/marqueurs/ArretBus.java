package org.jolmkbL2B.marqueurs;
/** Dans cette version, l'objet n'a pas spécialement de méthodes autre que les getters, setters et toString
 * @author Bastien
 * @version 1 */
public class ArretBus extends Marqueur {

    private final String city;        //commmune ou l'endroit se situe
    private String description; //description publique du lieu

    private boolean accesHandicap;

    public ArretBus( long lieuID, double latitude, double longitude,
                    String name, String city, String description, boolean accesHandicap) {
        super(PlaceType.ARRETBUS, latitude, longitude, lieuID, name);
        this.city= city;
        this.description = description;
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

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
