package org.jolmkbL2B;

import org.jolmkbL2B.controllers.*;
//import org.jolmkbL2B.controllers.UserContentController;

import java.sql.*;

/**
 * @author Mathys Gagner
 * Classe qui permet d'avoir TOUS les controllers (intéragissant avec la base de données) dans un seul objet
 * Cette objet est amené à être passé en paramètre de (presque) toutes les classes "graphiques" (Panel, Frame, ...)
 * On peut noter la présence du champ idUtilisateurConnecte, qui permet d'avoir l'id de l'utilisateur actuellement connecté
 * accessible n'importe quand et de n'importe où (à condition que l'objet AppControllers soit passé en paramètre)
 */
public class AppControllers {
    public static Connection con;
    public final MarqueurController marqueurController;
    public final ListeController listeController;
    public final CommentaireController commentaireController;
    public final ConnexionController connexionController;
    public final UtilisateurController utilisateurController;
    public final MemoController memoController;
    public final SuggestionController suggestionController;
    public long idUtilisateurConnecte;
//    public static UserContentController userContentController;

    public AppControllers()    {
        this.marqueurController = new MarqueurController();
        this.listeController = new ListeController();
        this.commentaireController = new CommentaireController();
        this.memoController = new MemoController();
        this.connexionController = new ConnexionController();
        this.utilisateurController = new UtilisateurController();
        this.suggestionController = new SuggestionController();
    }

}
