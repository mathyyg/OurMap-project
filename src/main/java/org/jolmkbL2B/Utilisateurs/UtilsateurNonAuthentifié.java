package org.jolmkbL2B.Utilisateurs;
/**
 *
 * @author Oualid siraji
 */
 // Attribut de La classe UtilisateurNonAuthentifi√©

public class UtilsateurNonAuthentifi√© extends Utilisateurs {
    String nom;
    //Constructeur()
    public UtilsateurNonAuthentifi√©(String nom)
    {
        nom = nom;
    }
    public UtilsateurNonAuthentifi√©()
    {
    }

    //Getters Et Setters
    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }
}
