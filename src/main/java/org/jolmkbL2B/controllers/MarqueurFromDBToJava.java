package org.jolmkbL2B.controllers;

import org.jolmkbL2B.marqueurs.PlaceType;

import java.sql.ResultSet;

public interface MarqueurFromDBToJava
{
    /** Retourne un ResultSet contenant les champs (idmarqueur, type, latitude, longitude, name) de toutes les entrées
     * de la table marqueurs.
     *  */
    ResultSet fetchAll();

    /** Retourne tous les champs de la table marqueurs d'une ligne dont l'identifiant correspond au long fourni.
     * (idmarqueur, type, latitude, longitude, name, city, description).
     * */
    ResultSet fetchMarqueurBasicInfo(long id);

    /** Retourne toutes les infos (de la table marqueurs ET de la table spécifique au Type de marqueur
     *
     * Pour un hotel : (idmarqueur, type, latitude, longitude, name, adresse, hasRestaurant, numTel, etoiles, siteWeb,
     * tripadvisor, handi_moteur, handi_mental, handi_auditif, handi_visuel, accepteAnimaux)
     *
     * Pour un arret de bus : (idmarqueur, type, latitude, longitude, name, accesHandi).
     *
     * Pour une école : (idmarqueur, type, latitude, longitude, name, type, statut, adresse).
     *
     *  */
    ResultSet fetchAllInfo(long id, PlaceType placeType);
}
