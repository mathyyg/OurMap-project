package org.jolmkbL2B.locations;
import org.jxmapviewer.viewer.GeoPosition;

public class Hotel extends Lieu
{
    public final boolean hasRestaurant;
    public String numTelephone;
    public String siteWeb;
    public String urlTripadvisor;
    public int categorieEtoiles;
    public boolean[] labelHandicap; /** Pour chaque type de handicap, true si l'Hotel est ok pour acceuillir ce type de handicap, false sinon. 0 si info inconnue */
    public boolean animauxAcceptes; /** 0 si non ou pas sûr, 1 si oui*/

    /** Constructeur pour import depuis BD des lieux repertoriés */
    public Hotel(double latitude, double longitude, String id, String name, String city,
                 String zipCode, String address, boolean hasRestaurant, String numTelephone, String siteWeb, String urlTripadvisor,
                                    int categorieEtoiles, boolean[] labelHandicap, boolean animauxAcceptes)
    {
        super("Hotel", latitude, longitude,id, name, city, zipCode, address);
        this.hasRestaurant = hasRestaurant;
        this.numTelephone = numTelephone;
        this.siteWeb = siteWeb;
        this.urlTripadvisor = urlTripadvisor;
        this.categorieEtoiles = categorieEtoiles;
        this.labelHandicap = labelHandicap;
        this.animauxAcceptes = animauxAcceptes;
    }

    public Hotel(GeoPosition coord, String name, boolean hasRestaurant, int stars)
    {
        super(coord, "Hotel", name);
        this.hasRestaurant = hasRestaurant;
        this.numTelephone = "We don't have that information yet. You can help by providing it !";
        this.siteWeb = "We don't have that information yet. You can help by providing it !";
        this.urlTripadvisor = "We don't have that information yet. You can help by providing it !";
        this.categorieEtoiles = stars;
        this.labelHandicap = new boolean[]  {false, false, false, false};
        this.animauxAcceptes = false ;
    }

    public boolean isHasRestaurant()    {return hasRestaurant;}
    public String getSiteWeb()    {return numTelephone;}
    public String getUrlTripadvisor()    {return urlTripadvisor;}
    public int getcategorieEtoiles()    {return categorieEtoiles;}

    public String getLabelHandi()   {return("Handicap moteur : " + labelHandicap[0] + ".\nHandicap mental : " +
                labelHandicap[1] + "\nHandicap auditif : " + labelHandicap[2] + "\nHandicap visuel : " +
            labelHandicap[3]);}

    public boolean isAnimauxAcceptes(){return animauxAcceptes;}

    @Override
    public String toString() {
        return "Hotel{" +
                "hasRestaurant=" + hasRestaurant +
                ", numTelephone='" + numTelephone + '\'' +
                ", siteWeb='" + siteWeb + '\'' +
                ", urlTripadvisor='" + urlTripadvisor + '\'' +
                ", categorieEtoiles=" + categorieEtoiles +
                ", labelHandicap= " + this.getLabelHandi() +
                ", animauxAcceptes=" + animauxAcceptes +
                '}';
    }
}
