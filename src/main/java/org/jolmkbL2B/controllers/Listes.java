package org.jolmkbL2B.controllers;

import java.sql.ResultSet;

public interface Listes {
    ResultSet fetchList(long idliste, long requestingUser);

    long createList(long idutilisateur, String listName);

    boolean addToList(long idmarqueur, long idliste, long requestingUser);

    boolean addCollaborateurListe(long idliste, String newCollaborateurID, long requestingUser);

    boolean checkListAccessRight(long idliste, long idutilisateur);
}
