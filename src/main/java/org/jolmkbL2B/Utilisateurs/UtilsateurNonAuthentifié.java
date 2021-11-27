package org.jolmkbL2B.Utilisateurs;
/**
 *
 * @author Oualid siraji
 */
 // Attribut de La classe UtilisateurNonAuthentifié

public class UtilsateurNonAuthentifié {
    String nom;
    //Constructeur()
    public UtilsateurNonAuthentifié(String nom) {
        nom = nom;
    }

    //Getters Et Setters
    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }
}
