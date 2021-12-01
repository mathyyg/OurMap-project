package org.jolmkbL2B.marqueurs;

import java.util.Arrays;
/** Dans cette version, l'objet n'a pas spécialement de méthodes autre que les getters, setters et toString
 * @author Bastien
 * @version 3 */
public class Hotel extends Marqueur
{

    private final String city;        //commmune ou l'endroit se situe
    private String description; //description publique du lieu

    private String address;
    private final boolean hasRestaurant;
    private String numTelephone;
    private int categorieEtoiles;
    private String siteWeb;
    private String tripAdvisor;
    private boolean[] labelHandicap; /* Pour chaque type de handicap (moteur, mental, auditif, visuel, true si l'Hotel
    est adapté pour acceuillir ce type de handicap. Par défaut : 0 si l'information n'est pas disponible */
    private boolean animauxAcceptes; // 0 si non ou pas sûr, 1 si oui



    public Hotel( long lieuID, double latitude, double longitude,
                 String name, String city, String description, String address, boolean hasRestaurant, String numTele, int categorieEtoiles,
                 String siteWeb, String tripAdvisor, boolean[] labelHandicap, boolean animauxAcceptes)
    {
        super(PlaceType.HOTEL, latitude, longitude, lieuID, name);
        this.city= city;
        this.description = description;
        this.address = address;
        this.hasRestaurant = hasRestaurant;
        this.numTelephone = numTele;
        this.categorieEtoiles = categorieEtoiles;
        this.siteWeb = siteWeb;
        this.tripAdvisor = tripAdvisor;
        this.labelHandicap = labelHandicap;
        this.animauxAcceptes = animauxAcceptes;
    }

    public String getAddress() {
        return address;
    }

    public boolean isHasRestaurant() {
        return hasRestaurant;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public int getCategorieEtoiles() {
        return categorieEtoiles;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public String getTripAdvisor() {
        return tripAdvisor;
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

    /** Getter pour animauxAcceptes. Renvoie un entier car sert principalement lors d'interaction avec la base de
     * donnees, où les booleens sont codés sous forme de 0 et de 1
     * @author Bastien*/
    public int isAnimauxAcceptes() {
        if (animauxAcceptes) return 1;
        else return 0;
    }

    /** Getter pour labelhandicap. Renvoie des entiers car sert principalement lors d'interaction avec la base de
     * donnees, où les booleens sont codés sous forme de 0 et de 1
     * @author Bastien*/
    public int[] getLabelHandicap() {
        int[] labelInt = {0, 0, 0, 0};
        for(int i = 0; i<4 ; i++)   {
            if (labelHandicap[i] == true) labelInt[i] = 1;
        }
        return labelInt;
    }

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
