package org.jolmkbL2B.locations;

import org.jxmapviewer.viewer.GeoPosition;

public class EtabEnseignement extends Lieu  {
    private final String schoolType;
    private final String PubliqueOuPrive;
    private final String city; //NOTE DEV : Me semble imporant ici car certaines ecole dans des les petites villes n'ont pas de nom


    public EtabEnseignement(double latitude, double longitude, String lieuID, String marqueurID, String name, String zipCode,
                        SchoolType schoolType, String PubliqueOrPrivate, String city)
    {
        super(PlaceType.ENSEIGNEMENT, latitude, longitude, lieuID, marqueurID, name, zipCode);
        this.schoolType = schoolType.toString();
        this.PubliqueOuPrive = PubliqueOrPrivate;
        this.city = city;
    }

    public EtabEnseignement(GeoPosition coord, String name, SchoolType schoolType, SchoolStatus status)    {
        super(PlaceType.ENSEIGNEMENT, coord, name);


    }
    public String fetchSchoolStatus()   {

    }
    /** Regarde dans la base de donnee si l'etablissment est publique ou privé pour set le parametre PubliqueOuPrive correctement */
    public String fetchSchoolStatus()   {

    }

    public String getSchoolType() {return schoolType;}
    public String getPubliqueOuPrive() {
        return PubliqueOuPrive;
    }
    public String getCity() {
        return city;
    }
}
