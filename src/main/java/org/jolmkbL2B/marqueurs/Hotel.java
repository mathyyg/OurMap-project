package org.jolmkbL2B.marqueurs;
import org.jolmkbL2B.locations.Lieu;
import org.jolmkbL2B.marqueurs.PlaceType;

import java.util.Arrays;

public class Hotel extends Marqueur
{
    private final boolean hasRestaurant;
    private int categorieEtoiles;
    private boolean[] labelHandicap; /* Pour chaque type de handicap (moteur, mental, auditif, visuel, true si l'Hotel
    est adapté pour acceuillir ce type de handicap. Par défaut : 0 si l'information n'est pas disponible */
    private boolean animauxAcceptes; // 0 si non ou pas sûr, 1 si oui



    /** Constructeur pour import depuis BD des lieux repertoriés
     * @author Bastien */
    public Hotel(double latitude, double longitude, String lieuID,
                 String name, boolean hasRestaurant,
                                    int categorieEtoiles, boolean[] labelHandicap, boolean animauxAcceptes)
    {
        super(PlaceType.HOTEL, latitude, longitude, lieuID, name);
        this.hasRestaurant = hasRestaurant;
        this.categorieEtoiles = categorieEtoiles;
        this.labelHandicap = labelHandicap;
        this.animauxAcceptes = animauxAcceptes;
    }



    /** Constructeur utilisé lorsqu'un lieu est ajouté depuis le carte
     * @author Bastien */
    /* public Hotel(GeoPosition coord, String name, boolean hasRestaurant, int stars)
    {
        super(PlaceType.HOTEL, coord, name);
        this.hasRestaurant = hasRestaurant;
        this.categorieEtoiles = stars;
        this.labelHandicap = new boolean[]  {false, false, false, false};
        this.animauxAcceptes = false ;
    }*/

    public boolean isHasRestaurant()    {return hasRestaurant;}
    public int getcategorieEtoiles()    {return categorieEtoiles;}

    public String getLabelHandi()   {return("Handicap moteur : " + labelHandicap[0] + ".\nHandicap mental : " +
                labelHandicap[1] + "\nHandicap auditif : " + labelHandicap[2] + "\nHandicap visuel : " +
            labelHandicap[3]);}

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
