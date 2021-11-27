package org.jolmkbL2B.controllers;

public class UtilisateurAuthentifié {

//Attribut de la classe Utilisateur nom authentifié
    public int idutilisateur;
    public String displayName;
    public String password;
    public int idFavList;
    public boolean isAdmin;
//Constructeur avec parametres
    public UtilisateurAuthentifié(int idutilisateur, String displayName, String password, int idFavList, boolean isAdmin) {
        this.idutilisateur = idutilisateur;
        this.displayName = displayName;
        this.password = password;
        this.idFavList = idFavList;
        this.isAdmin = isAdmin;
    }
    //Constructeur sans parametres
    public UtilisateurAuthentifié()
    {

    }
    //Accesseurs et mutateurs
    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdFavList() {
        return idFavList;
    }

    public void setIdFavList(int idFavList) {
        this.idFavList = idFavList;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    @Override
    public String toString() {
        return "UtilisateurAuthentifié{" +
                "idutilisateur=" + idutilisateur +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                ", idFavList=" + idFavList +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
