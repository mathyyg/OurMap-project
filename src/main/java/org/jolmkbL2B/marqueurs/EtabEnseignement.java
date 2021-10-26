package org.jolmkbL2B.marqueurs;
/** Dans cette version, l'objet n'a pas spécialement de méthodes autre que les getters, setters et toString
 * @author Bastien
 * @version 1 */
public class EtabEnseignement extends Marqueur {
    private final SchoolType schoolType; //Si l'etablissement est une cole maternelle, elmentaire, college, lycee etc. Voir enum
    private final SchoolStatus publicOuPrive;
    private final String address;

    public EtabEnseignement(double latitude, double longitude, long lieuID, String name,  String city, String address,
                            SchoolType schoolType, SchoolStatus publicOuPrive)
    {
        super(PlaceType.ENSEIGNEMENT, latitude, longitude, lieuID, name, city);
        this.address = address;
        this.schoolType = schoolType;
        this.publicOuPrive = publicOuPrive;
    }

    public SchoolType getSchoolType() {return schoolType;}
    public SchoolStatus PublicOuPrive() {
        return publicOuPrive;
    }
    public String getAddress()  {return address;}
}
