package org.jolmkbL2B.controllers;

import java.sql.ResultSet;

public interface Commentaires {
    public ResultSet fetchAllCommentairesByMarqueur(long idmarqueur);

    public ResultSet fetchAllCommentairesByUser(long idmarqueur);

    boolean setCommentairesVisible(long idutilisateur, long idmarqueur, boolean visible);

    boolean setCertainCommVisible(long idutilisateur, long idmarqueur, String text, boolean visible);

    boolean postCommentaire(long idutilisateur, long idmarqueur, String text);
}
