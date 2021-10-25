package org.jolmkbL2B.marqueurs;

import org.jolmkbL2B.ourUtil.Generator;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public abstract class Marqueur extends DefaultWaypoint {
    private PlaceType placeType;
    private String lieuID;
    private String name;
    private String description;
    //private Set<String> commentaires; pour le prochain Sprint.
    private String userMemo;

    public Marqueur(PlaceType placeType, double latitude, double longitude, String lieuID, String name)
    {
        super(latitude, longitude);
        this.placeType = placeType;
        this.lieuID = lieuID;
        this.name = name;
        this.description = "No description set yet";
        this.userMemo = "no user memo set yet";
    }

    public GeoPosition getCoord()    {return getPosition();}
    public String getPlaceType() {return placeType;}
    public String getLieuID() {return lieuID;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getUserMemo() {return userMemo;}


    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setUserMemo(String userMemo) {this.userMemo = userMemo;}

}
