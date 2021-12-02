package org.jolmkbL2B.marqueurs;

public class CustomMarqueur extends Marqueur    {
    private final String city;
    private String description;

    private final long ownerID;

    public CustomMarqueur(PlaceType placeType, double latitude, double longitude, long lieuID, String name, String city,
                          String description, long ownerID) {
        super(placeType, latitude, longitude, lieuID, name);
        this.city = city;
        this.description = description;

        this.ownerID = ownerID;
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

    public long getOwnerID() {
        return ownerID;
    }
}
