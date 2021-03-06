package org.jolmkbL2B.marqueurs;

import org.jolmkbL2B.controllers.ListeController;
import org.jolmkbL2B.controllers.MarqueurController;
import org.jolmkbL2B.vue.marqueurPainters.SwingMarqueurPainter;
import org.jxmapviewer.viewer.Waypoint;
import org.jolmkbL2B.vue.marqueurPainters.ColoredMarqueurRenderer;
import org.jxmapviewer.viewer.WaypointPainter;

import java.util.HashSet;
import java.sql.*;

/** Cette classe transforme les ResultSet obtenu dans les classes Controllers en objet Marqueurs, regroupés en HashSet
 * Elle permet en general de charger des marqueurs dans un nouveau set (donc de filtrer par listes), ou de charger dans un meme set
 * les marqueurs d'une liste déjà affichée.
 *
 * Les méthodes publics contiennent les appels aux méthodes des controllers pour obtenir le resultSet et un appel a une
 * méthode privée (ajouter à un Set de Marqueur pré-existant ou creer un nouveau Set).
 *
 * La classe contient aussi la méthode updateDisplay, permettant de passer d'un HashSet de marqueurs à des objets
 * visualisables sur l'écran. Celle-ci est surtout une séquence d'appels aux méthodes nécéssaires à l'affichage du set de marqueur.
 *
 * @author Bastien*/

public class MarqueurLoader {
    private MarqueurController marqueurController;
    private WaypointPainter<Marqueur> marqueurPainter;
    private ListeController listeController;
    private final String info = "Classe qui charge ls marqueurs.";


    public MarqueurLoader() {
        //this.marqueurPainter = new SwingMarqueurPainter(); A UTILISER QUAND LE RENDERER SERA PRET
        this.marqueurPainter = new WaypointPainter<Marqueur>();
        this.marqueurController = new MarqueurController();
        this.listeController = new ListeController();
    }

    /** Rend visible sur la carte les marqueurs d'un HashSet
     * @author Bastien
     * @version 2
     * @param marqueurs (HashSet de marqueurs
     * @return les marqueurs visibles, generqlement appelé dqns un OurMap.setOverlay
     */
    public WaypointPainter<Marqueur> updateDisplay(HashSet<Marqueur> marqueurs)  {
        marqueurPainter.setWaypoints(marqueurs);
       marqueurPainter.setRenderer(new ColoredMarqueurRenderer());

        return marqueurPainter;
    }

    /** Méthode executee a l'initialisation pour creer le set initial de marqueur, charge tous les marqueurs publics et
     * ajoute les marqueurs customs de l'utilisateur.
     * @author Bastien
     * @return HashSet de tous les marqueurs publiques de la base de données + les marqueurs custom de l'utilisateur
     * @version 3*/
    public HashSet<Marqueur> loadAllMarkers(long idutilisateur) {
        HashSet<Marqueur> marqueurSet = loadNewSet(marqueurController.fetchAll()); // Chargement des marqueurs publics
        return updateMarqueurSet(marqueurSet, marqueurController.fetchUserCustomMarqueur(idutilisateur));
        //Ajout des marqueurs customisés de l'utilisateur au HashSet

    }
    /** Méthode chargeant tous les marqueurs publiques pour un utilisateur non connecté
     * @author Bastien
     * @return HashSet de marqueurs à afficher */
    public HashSet<Marqueur> loadAllMarkers() {
        HashSet<Marqueur> marqueurSet = loadNewSet(marqueurController.fetchAll()); // Chargement des marqueurs publics
        return marqueurSet;
    }

    /** Méthode chargeant seulement les marqueurs d'un certain type.
     * @author Bastien
     * @param idutilisateur Au cas où l'utilisateur veuille charger ses marqueurs custom
     * @param placeType le type de marqueur recherché
     * @return HashSet de marqueurs à afficher */
    public HashSet<Marqueur> loadAllMarkersByType(PlaceType placeType, long idutilisateur) {
        if(placeType == placeType.CUSTOM)   return loadNewSet(marqueurController.fetchUserCustomMarqueur(idutilisateur));
        else return loadNewSet(marqueurController.fetchAllByType(placeType));
    }

    /** Méthode chargeant seulement les marqueurs d'un certain type.
     * @author Bastien
     * @param placeType le type de marqueur recherché
     * @return HashSet de marqueurs à afficher*/
    public HashSet<Marqueur> loadAllMarkersByType(PlaceType placeType) {
        return loadNewSet(marqueurController.fetchAllByType(placeType));
    }


    /** Méthode chargeant uniquement les marqueurs d'une liste dans un nouveau set
     * @author Bastien
     * @version 1
     * @since 2.4.2 */
    public HashSet<Marqueur> loadListToNewSet(long idliste, long idutilisateur) {
        return loadNewSet(listeController.fetchList(idliste, idutilisateur));
    }

    /** Méthode chargeant une liste de marqueurs dans un nouveau set
     * @author Bastien
     * @version 1
     * @since 2.4.2 */
    public HashSet<Marqueur> loadListInCurrentSet(HashSet<Marqueur> currentSet, long idliste, long idutilisateur) {
        return updateMarqueurSet(currentSet, listeController.fetchList(idliste, idutilisateur));
    }

    /** Cette methode privée est utilisée pour ajouter des marqueurs importés de la base de données (typiquement issus d'une liste)
     * a un set de marqueurs deja exitant / affiché
     * @author Bastien
     * @version 1
     * @since 2.4.2
     * @param initialSet le Set de marqueur pré-existant
     * @param rs le resultSet de la requete SQL efectuée precedemment
     * @return le Set de marqueurs mis à jour
     */
    private HashSet<Marqueur> updateMarqueurSet(HashSet initialSet, ResultSet rs) {
        try {
            while (rs.next()) {
                String type = rs.getString("placeType"); // Selection du placeType de Marqueur (ce champs est une Enum)
                PlaceType placeType = null;

                switch (type) {
                    case "HOTEL":
                        placeType = placeType.HOTEL;
                        break;
                    case "ARRETBUS":
                        placeType = placeType.ARRETBUS;
                        break;
                    case "SCHOOL":
                        placeType = placeType.SCHOOL;
                        break;
                    case "CUSTOM" :
                        placeType = placeType.CUSTOM;
                        break;
                    default:
                        throw new SQLDataException("Donnée " + type + " ne correspond pas aux valeurs attendues dans ENUM (PlaceType). ");
                }
                //Instanciation du marqueur avec tous les champs du contructeur et de la base de donnée
                Marqueur wp = new Marqueur(placeType, rs.getDouble("latitude"),
                        rs.getDouble("longitude"), rs.getLong("idmarqueur"),
                        rs.getString("marqueurName"));

                initialSet.add(wp); //ajout du marqueur au Set
            }
        }
        catch (SQLDataException dataException) {
            System.err.println(dataException);
        }
        catch(SQLException sqlException)   {
            System.out.println(sqlException.getMessage());
            // Soucis : si pas internet, on peut pas utiliser l'appli
        }
        return initialSet;
    }

    /** Cette méthode (quasi-identique à updateMarqueurSet), renvoie un nouveau Set de Marqueur (au lieu d'en mettre un à jour)
     * @author Bastien
     * @version 1
     * @since 2.4.2
     * @param rs Le ResultSet de la requete effectuee
     * @return le Nouveau set de Marqueur
     */
    private HashSet<Marqueur> loadNewSet(ResultSet rs) {
        HashSet<Marqueur> marqueurs = new HashSet<Marqueur>();
        try {
            while (rs.next()) {
                String type = rs.getString("placeType"); // Selection du placeType de Marqueur (ce champs est une Enum)
                PlaceType placeType = null;
                switch (type) {
                    case "HOTEL":
                        placeType = placeType.HOTEL;
                        break;
                    case "ARRETBUS":
                        placeType = placeType.ARRETBUS;
                        break;
                    case "SCHOOL":
                        placeType = placeType.SCHOOL;
                        break;
                    case "CUSTOM" :
                        placeType = placeType.CUSTOM;
                        break;
                    default:
                        throw new SQLDataException("Donnée " + type + " ne correspond pas aux valeurs attendues dans ENUM (PlaceType). ");
                }
                //Creation du nouveau Marqueur
                Marqueur wp = new Marqueur(placeType, rs.getDouble("latitude"),
                        rs.getDouble("longitude"), rs.getLong("idmarqueur"),
                        rs.getString("marqueurName"));

                //System.out.println(wp.getPlaceType()); TEST PURPOSE

                marqueurs.add(wp); //Ajout au Set
            }
        }
        catch (SQLDataException dataException) {
            System.err.println(dataException);
        }
        catch(SQLException sqlException)   {
            System.out.println(sqlException.getMessage());
            // Soucis : si pas internet, on peut pas utiliser l'appli
        }
        return marqueurs;
    }
}
