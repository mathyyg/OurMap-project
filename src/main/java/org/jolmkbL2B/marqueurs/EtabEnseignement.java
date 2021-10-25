package org.jolmkbL2B.marqueurs;

import org.jolmkbL2B.locations.Lieu;

public class EtabEnseignement extends Marqueur {
    private final String schoolType;
    private final String PubliqueOuPrive;
    private final String city; //NOTE DEV : Me semble imporant ici car certaines ecole dans des les petites villes n'ont pas de nom


    public EtabEnseignement(double latitude, double longitude, String lieuID, String marqueurID, String name, String zipCode,
                            SchoolType schoolType, String PubliqueOrPrivate, String city)
    {
        super(PlaceType.ENSEIGNEMENT, latitude, longitude, lieuID, name);
        this.schoolType = schoolType.toString();
        this.PubliqueOuPrive = PubliqueOrPrivate;
        this.city = city;
    }

   /* public EtabEnseignement(GeoPosition coord, String name, SchoolType schoolType, SchoolStatus status)    {
        super(PlaceType.ENSEIGNEMENT, coord, name);


    }*/
    public String getSchoolType() {return schoolType;}
    public String getPubliqueOuPrive() {
        return PubliqueOuPrive;
    }
    public String getCity() {
        return city;
    }
}
