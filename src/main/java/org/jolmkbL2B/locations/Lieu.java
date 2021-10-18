package org.jolmkbL2B.locations;

import org.jxmapviewer.viewer.*;
import org.jolmkbL2B.ourUtil.Generator;

public abstract class Lieu     {
    private GeoPosition coord;

    private String placeType;
    private String id;
    private String name;

    private String city;
    private String zipCode;
    private String address;

    private String description;
    //private Set<String> commentaires; pour le prochain Sprint.
    private String userMemo;


/** Ce constructeur doit être utilisé pour importer un marqueur depuis les bases de donnees importées (y compris la ville, zipCode et addresse*/
    public Lieu(String placeType, double latitude, double longitude, String id, String name, String city, String zipCode,String address)
    {   // TO-DO : requetes SQL, les appeler dans le constructeur ou avant ?
        // IDEE : Methode loadant les marqueurs de la BD et appelant ce contructeur au lancement
        this.coord = new GeoPosition(latitude, longitude);
        this.placeType = placeType;
        this.id = id;
        this.name = name;
        this.city=city;
        this.zipCode=zipCode;
        this.address = address;
        this.description = "No description set yet";
        this.userMemo = "no user memo set yet";
    }

    /** Ce constructeur est utilisé pour créer un marqueur depuis la carte. Il fait appel a une methode de generation de String por generer l'id */
    public Lieu(GeoPosition coord, String placeType, String name)
    {
        this.coord=coord;
        this.placeType = placeType;
        this.id = "test"; //generateId();
        this.name = name;
        this.city =  "We don't have that information yet. You can help by providing it !";
        // Proposer a l'utilisateur de la rentrer ou la chercher dans la BD BANO grace aux coordonnées ??
        this.address = "We don't have that information yet. You can help by providing it !";
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
    public String getId() {return id;}
    public String getName() {return name;}
    public String getCity() {return city;}
    public String getZipCode() {return zipCode;}
    public String getAddress() {return address;}
    public String getDescription() {return description;}
    public String getUserMemo() {return userMemo;}


    public void setName(String name) {this.name = name;}
    public void setCity(String city) {this.city = city;}
    public void setZipCode(String zipCode) {this.zipCode = zipCode;}
    public void setAddress(String address) {this.address = address;}
    public void setDescription(String description) {this.description = description;}
    public void setUserMemo(String userMemo) {this.userMemo = userMemo;}
}
