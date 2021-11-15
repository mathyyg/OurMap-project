package org.jolmkbL2B.marqueurs;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
/** Les methodes sont des getters et setters, l'objet en lui meme ne realsie pas vraiment d'action autre que le partage
 * des informations qu'il contient. Idem pour les classes étendues */
public class Marqueur extends DefaultWaypoint {
    private PlaceType placeType; //type d'endroit. Redondant avec le instanceof ?
    private long lieuID;        //identifiant dans la base de données
    private String name;        // nom de l'endroit
    private String city;        //commmune ou l'endroit se situe
    private String description; //description publique du lieu


    public Marqueur(PlaceType placeType, double latitude, double longitude, long lieuID, String name,
                    String city, String description)
    {
        super(latitude, longitude);
        this.placeType = placeType;
        this.lieuID = lieuID;
        this.name = name;
        this.city= city;
        this.description = description;
    }

    public GeoPosition getCoord()    {return getPosition();}
    public PlaceType getPlaceType() {return placeType;}
    public long getLieuID() {return lieuID;}
    public String getName() {return name;}
    public String getCity() {return city;}

    public String getDescription() {return description;}


    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}

}
