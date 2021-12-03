package org.jolmkbL2B.Utilisateurs;
/**
 *
 * @author Oualid siraji
 */
  //Attributs de La classe Admin
public class Admin extends UtilisateurAuthentifié {


    // Constructeur avec parametre()
    public Admin(int idutilisateur, String displayName, String password, int idFavList) {
        super(idutilisateur, displayName, password, idFavList, true);
        this.isAdmin = true;
    }
    //Constructeur sans parametre
    public Admin()
    {
    }

    // Getters et Setters
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
}
