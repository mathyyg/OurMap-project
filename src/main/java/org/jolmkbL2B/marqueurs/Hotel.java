package org.jolmkbL2B.marqueurs;

import java.util.Arrays;
/** Dans cette version, l'objet n'a pas spécialement de méthodes autre que les getters, setters et toString
 * @author Bastien
 * @version 3 */
public class Hotel extends Marqueur
{
    private String address;
    private final boolean hasRestaurant;
    private int categorieEtoiles;
    private boolean[] labelHandicap; /* Pour chaque type de handicap (moteur, mental, auditif, visuel, true si l'Hotel
    est adapté pour acceuillir ce type de handicap. Par défaut : 0 si l'information n'est pas disponible */
    private boolean animauxAcceptes; // 0 si non ou pas sûr, 1 si oui



    public Hotel(double latitude, double longitude, long lieuID,
                 String name, String city, String address, boolean hasRestaurant,
                                    int categorieEtoiles, boolean[] labelHandicap, boolean animauxAcceptes)
    {
        super(PlaceType.HOTEL, latitude, longitude, lieuID, name, city);
        this.address = address;
        this.hasRestaurant = hasRestaurant;
        this.categorieEtoiles = categorieEtoiles;
        this.labelHandicap = labelHandicap;
        this.animauxAcceptes = animauxAcceptes;
    }

    public boolean isHasRestaurant()    {return hasRestaurant;}
    public int getcategorieEtoiles()    {return categorieEtoiles;}
    public boolean isAnimauxAcceptes()  {return animauxAcceptes;}

    @Override
    public String toString() {
        return "Hotel{" +
                "hasRestaurant=" + hasRestaurant +
                ", categorieEtoiles=" + categorieEtoiles +
                ", labelHandicap=" + Arrays.toString(labelHandicap) +
                ", animauxAcceptes=" + animauxAcceptes +
                '}';
    }
}
