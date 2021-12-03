package org.jolmkbL2B;

import org.jolmkbL2B.controllers.*;
//import org.jolmkbL2B.controllers.UserContentController;

import java.sql.*;


public class AppControllers {
    public static Connection con;
    public final MarqueurController marqueurController;
    public final ListeController listeController;
    public final CommentaireController commentaireController;
    public final ConnexionController connexionController;
    public final UtilisateurController utilisateurController;
    public final MemoController memoController;
//    public static UserContentController userContentController;

    public AppControllers()    {
        this.marqueurController = new MarqueurController();
        this.listeController = new ListeController();
        this.commentaireController = new CommentaireController();
        this.memoController = new MemoController();
        this.connexionController = new ConnexionController();
        this.utilisateurController = new UtilisateurController();
    }

}
