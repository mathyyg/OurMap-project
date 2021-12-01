package org.jolmkbL2B;

import org.jolmkbL2B.controllers.*;
//import org.jolmkbL2B.controllers.UserContentController;

import java.sql.*;


public class AppControllers {
    public static Connection con;
    public static MarqueurController marqueurController;
    private final ListeController listeController;
    private final CommentaireController commentaireController;
//    public static UserContentController userContentController;

    public AppControllers()    {
        this.marqueurController = new MarqueurController();
        this.listeController = new ListeController();
        this.commentaireController = new CommentaireController();
    }

}
