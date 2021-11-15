package org.jolmkbL2B.controllers;

import java.sql.ResultSet;

public interface ListesEtCommentaires {

    ResultSet fetchAllCommentaires(long idmarqueur);

    ResultSet fetchAllCommentaires(String idUtilisateur);

    boolean setCommentaireVisible(String idutilisateur, long idmarqueur, boolean visible);

    boolean postCommentaire(String idutilisateur, long idmarqueur, String text);

    ResultSet fetchList(long idliste);

    long createList(String idutilisateur, String listName);

    boolean addToList(long idmarqueur, long idliste, String requestingUser);

    boolean addCollaborateurListe(long idliste, String newCollaborateurID, String requestingUser);

    boolean checkListAccessRight(long idliste, String idutilisateur);
}
