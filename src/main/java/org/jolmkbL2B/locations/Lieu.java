package org.jolmkbL2B.locations;

import org.jxmapviewer.viewer.*;
import org.jolmkbL2B.ourUtil.Generator;

public abstract class Lieu     {
    private GeoPosition coord;

    private String placeType;
    private String lieuID;
    private String marqueurID;
    private String name;
    private String zipCode;
    private String description;
    //private Set<String> commentaires; pour le prochain Sprint.
    private String userMemo;


    /** Ce constructeur doit être utilisé pour importer un lieu depuis les bases de
     * donnees (y compris zipCode).  */
    public Lieu(PlaceType placeType, double latitude, double longitude, String lieuID, String marqueurID, String name, String zipCode)
    {
        this.coord = new GeoPosition(latitude, longitude);
        this.placeType = placeType.toString();
        this.lieuID = lieuID;
        this.marqueurID =  marqueurID;
        this.name = name;
        this.zipCode=zipCode;
        this.description = "No description set yet";
        this.userMemo = "no user memo set yet";
    }

    /** Ce constructeur est utilisé pour créer un marqueur depuis la carte.
     * Il fait appel a une methode de generation de String por generer les ID lieu et marqueurs qui n'existent
     * pas encore dans la BD    */
    public Lieu(PlaceType placeType, GeoPosition coord, String name)
    {
        this.placeType = placeType.toString();
        this.coord=coord;
        this.lieuID = generateId();
        this.marqueurID = marqueurID; //generateId
        this.name = name;
        this.zipCode = "We don't have that information yet. You can help by providing it !";
        this.description = "No description set yet";
        this.userMemo = "no user memo set yet";
    }

    public String generateId() { /* algo recursif : tant creer un id, verifie qu'il n'existe pas deja.
                                                      Si il n'existe pas, on peut l'utiliser, sinon on recommence.*/
        String newId = (this.zipCode + Generator.generateString());
        /* TODO : if newId !exists DANS LA BASE DE DONNEES DES MARQUEURS */
        return newId;
		//else return generateId();
    }
    public GeoPosition getCoord()    {return coord;}
    public String getPlaceType() {return placeType;}
    public String getLieuID() {return lieuID;}
    public String getMarqueurID()   {return marqueurID;}
    public String getName() {return name;}
    public String getZipCode() {return zipCode;}
    public String getDescription() {return description;}
    public String getUserMemo() {return userMemo;}


    public void setName(String name) {this.name = name;}
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}
    public void setDescription(String description) {this.description = description;}
    public void setUserMemo(String userMemo) {this.userMemo = userMemo;}
}
