package org.jolmkbL2B.controllers;

import java.sql.ResultSet;

public interface ListesEtCommentaires {

    public ResultSet fetchAllCommentairesByMarqueur(long idmarqueur);

    public ResultSet fetchAllCommentairesByUser(long idmarqueur);

    boolean setCommentaireVisible(long idutilisateur, long idmarqueur, boolean visible);

    boolean postCommentaire(long idutilisateur, long idmarqueur, String text);

    ResultSet fetchList(long idliste);

    long createList(long idutilisateur, String listName);

    boolean addToList(long idmarqueur, long idliste, long requestingUser);

    boolean addCollaborateurListe(long idliste, String newCollaborateurID, long requestingUser);

    boolean checkListAccessRight(long idliste, long idutilisateur);
}
