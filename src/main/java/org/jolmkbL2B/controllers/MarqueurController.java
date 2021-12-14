package org.jolmkbL2B.controllers;
import org.jolmkbL2B.controllers.databaseTablesAndColumns.*;
import org.jolmkbL2B.marqueurs.*;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;

/** Cette classe contient toutes les méthodes pour les interactions avec la base de données concernant les marqueurs et
 * objets dérivés.
 * @author Bastien*/
public class MarqueurController  {
    public Connection con;


    /** Constructeur. Etablit la connection à la base de données sur un serveur distant
     * @throws SQLException en cas de soucis de connection.
     */
    public MarqueurController() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://play.kidl.fr:3306/?user=mathys",
                    "mathys", "PROJET2022poogl");
            this.con = con;
            Statement stmt = con.createStatement();

            /** Requête sur le serveur pour utiliser la base de données de
             dédiée à l'application */
            stmt.execute("USE ourmapdb;");


            /** Les updates et insertion sur la base de données ne sont pas enregistrées
             * automatiquement. Il est nécessaire d'executer la commande "con.commit(); pour enregistrer les changements.
             * Ceci permet d'éviter que des transactions incompletes soient enregistrés en cas de crash.*/
            con.setAutoCommit(false);
        }
        catch(SQLException sqlException) {
            this.con = null;
            System.out.println(sqlException.getMessage());
            System.out.println("Error establishing connection with database in class MarqueurController" +
                    ". Initialization cannot continue.");
            // Soucis : si pas internet, on peut pas utiliser l'appli
            sqlException.printStackTrace();
        }
    }



    /** Recupere tous les marqueurs de la base de données.
     * @since 2.0 ~
     * @version 1
     * @retyrn ResultSet aux colonnes suivantes : (`placeType`, `latitude`, `longitude`, `marqueurName`)
     * @author Bastien
     */
    public ResultSet fetchAll() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * " +
                    " FROM marqueurs WHERE placeType NOT LIKE \"CUSTOM\";");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet fetchUserCustomMarqueur(long idutilisateur)    {
        try {
            Statement stmt = con.createStatement();

            /* doublon dans la clause where pour eviter d'eventuelles erreur, par précaution */
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, placeType, latitude, longitude, marqueurName" +
                    " FROM marqueurs NATURAL JOIN customMarqueurs WHERE marqueurs.placeType = \"CUSTOM\" AND idowner = " + idutilisateur + ";");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /** Recupere tous les marqueurs d'un certain placeType (seulement les hotels, les arrets de bus etc...)
     * @since 2.4 ~
     * @version 1
     * @retyrn ResultSet aux colonnes suivantes : (`placeType`, `latitude`, `longitude`, `marqueurName`)
     * @author Bastien
     */
    public ResultSet fetchAllByType(PlaceType placeType)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT idmarqueur, placeType, latitude, longitude, marqueurName" +
                    " FROM marqueurs WHERE placeType = \"" + placeType.toString() + "\";");
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Recupère tous les marqueurs de la base de données.
     * @since 2.0 ~
     * @param id identifiant d'un marqueur
     * @version 1
     * @author Bastien
     */
    public ResultSet fetchMarqueurBasicInfo(long id)    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM marqueurs WHERE idmarqueur = " + id + ";");
            checkResultSetSize(rs);
            return rs;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Recupere toutes les infos d'un unique marqueur
     * @version 1
     * @retyrn ResultSet aux colonnes suivantes : (`placeType`, `latitude`, `longitude`, `marqueurName`) + colonnes specifiques aux tables
     * @author Bastien*/
    public ResultSet fetchAllInfo(long id, PlaceType placeType) {
        String table = selectTable(placeType);

        try {
            if (table.equals("none"))    {
                System.err.println("Erreur lors de la selection de la table de BD.");
                return null;
            }
            else {
                Statement stmt = con.createStatement();
                ResultSet infoMarqueur = stmt.executeQuery("SELECT * FROM marqueurs NATURAL JOIN " + table + " WHERE " +
                        "idmarqueur = " + id + ";");
                checkResultSetSize(infoMarqueur);
                return infoMarqueur;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** @author Mathys */
    public String fetchMarqueurNom(long id, PlaceType placeType) {
        String table = selectTable(placeType);

        try {
            if (table.equals("none"))    {
                System.err.println("Erreur lors de la selection de la table de BD.");
                return null;
            }
            else {
                Statement stmt = con.createStatement();
                ResultSet infoMarqueur = stmt.executeQuery("SELECT marqueurName FROM marqueurs NATURAL JOIN " + table + " WHERE " +
                        "idmarqueur = " + id + ";");
                checkResultSetSize(infoMarqueur);
                while(infoMarqueur.next()) {
                    return infoMarqueur.getString(1);
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**@author Mathys */
    public String fetchMarqueurDescription(long id, PlaceType placeType) {
        String table = selectTable(placeType);

        try {
            if (table.equals("none"))    {
                System.err.println("Erreur lors de la selection de la table de BD.");
                return null;
            }
            else {
                Statement stmt = con.createStatement();
                ResultSet infoMarqueur = stmt.executeQuery("SELECT marqueurDesc FROM " + table + " NATURAL JOIN marqueurs WHERE " +
                        "idmarqueur = " + id + ";");
                checkResultSetSize(infoMarqueur);
                while(infoMarqueur.next()) {
                    return infoMarqueur.getString(1);
                }
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /** Verifie qu'un ResultSet a au plus 1 resultat. Affiche un message d'avertissement si ce n'est pas le cas,
     * mais ne change pas le contenu du resultSet, sert seulement d'avertissement.
     * @author Bastien*/
    private void checkResultSetSize(ResultSet rs)   {
        try {
            int rsSize = rs.getFetchSize();
            if (rsSize > 1)
            {
                System.out.println(" Warning : Unexpected ResultSet size. " +
                        "Exepected : 1. Actual size : " + rsSize + ".\nStill returning all results.");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /** Selection d'une table de base de donnees en fonction d'un parametre Enum PlaceType donné
     * @param placeType d'un objet Marqueur
     * @return une string correspondant au nom de la table de la BD qui doit être utilisée
     * @author Bastien */
    protected String selectTable(PlaceType placeType) {
        switch(placeType)   {
            case SCHOOL:
                return "schools";
            case HOTEL:
                return "hotels";
            case ARRETBUS:
                return "arretsbus";
            case CUSTOM :
                return "customMarqueurs";
            default:
                System.err.println("Argument PlaceType non reconnu;");
                return "none";
        }
    }

    /** Insertion des données de base d'un nouveau marqueur dans la table marqueurs.
     * Cette méthode est uniquement appelée par els méthodes InsertMarqueur(Hotel), (ArretBus), (School) et (Custom)
     * @author Bastien
     * @since 2.5
     * @param marqueur : un objet Marqueur
     * @return identifiant de la nouvelle entree, -1 en cas d'erreur
     * @version 1*/

    private int insertMarqueur(Marqueur marqueur)    {
        boolean success = false;
        int lastID = -1;

        try {
            Statement stmt = con.createStatement();
            //Requete reprenant tous les attribtus de l'objet pour les traduire en SQL, sous le format requis pour la BD
            String sql1 = "INSERT INTO `ourmapdb`.`marqueurs`\n" +
                    "(`placeType`,\n" +
                    "`latitude`,\n" +
                    "`longitude`,\n" +
                    "`marqueurName`)\n VALUES (\"" + marqueur.getPlaceType().toString() + "\", " +
                    "\"" + marqueur.getPosition().getLatitude() + "\", \"" + marqueur.getPosition().getLongitude() + "\", \"" + marqueur.getName() + "\");";
            stmt.executeUpdate(sql1);

            String getLastIDQuerry = "SELECT idmarqueur FROM marqueurs ORDER BY idmarqueur DESC LIMIT 1;"; //Recuperation de l'identifiant de la nouvelle entree
            ResultSet rs = stmt.executeQuery(getLastIDQuerry);
            rs.next();
            lastID = rs.getInt(1);
            con.commit();
            stmt.close();
        }
        catch(SQLException e)
            {e.printStackTrace();}

        return lastID;

    }
    /** Insertion des données de base d'un nouvel Hotel dans la tables marqueurs et hotels
     * @author Bastien
     * @since 2.5
     * @param hotel : un objet Hotel (classe étendue de marqueur)
     * @return identifiant de la nouvelle entree
     * @version 2*/
    public boolean insertMarqueur(Hotel hotel) {
        boolean success = false;
        try {
            int id = insertMarqueur((Marqueur) hotel); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(id> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`hotels`\n" +
                        "(`idmarqueur`,\n" +
                        "`city`,\n" +
                        "`marqueurDescription`,\n" +
                        "`adresse`,\n" +
                        "`hasRestaurant`,\n" +
                        "`numTel`,\n" +
                        "`etoiles`,\n" +
                        "`siteWeb`,\n" +
                        "`tripadvisor`,\n" +
                        "`handi_moteur`,\n" +
                        "`handi_mental`,\n" +
                        "`handi_auditif`,\n" +
                        "`handi_visuel`,\n" +
                        "`accepteAnimaux`)\n" +
                        "VALUES ( " + id + ", \"" + hotel.getCity() + "\" , \"" + hotel.getDescription() + "\", \"" + hotel.getAddress() + "\", " + hotel.isHasRestaurant() + ", \"" + hotel.getNumTelephone() +
                        "\", " + hotel.getCategorieEtoiles() + ", \"" + hotel.getSiteWeb() + "\", \"" + hotel.getTripAdvisor() +
                        "\", " + hotel.getLabelHandicap()[0] + ", " + hotel.getLabelHandicap()[1] + ", " + hotel.getLabelHandicap()[1] +
                        ", " + hotel.getLabelHandicap()[2] + ", " + hotel.getLabelHandicap()[3] + ", " + hotel.isAnimauxAcceptes() + ");";

                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return success;
    }

    /** Insertion des données de base d'un nouvel Arret de Bus dans les tables marqueurs et arretsbus
     * @author Bastien
     * @since 2.5
     * @param arret : un objet ArretBus (classe étendue de marqueur)
     * @return identifiant de la nouvelle entree
     * @version 2*/
    public boolean insertMarqueur(ArretBus arret)   {
        boolean success = false;
        try {
            int id = insertMarqueur((Marqueur) arret); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(id> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`arretsbus`\n" +
                        "(`idmarqueur`,\n" +
                        "`city`,\n" +
                        "`marqueurDescription`,\n" +
                        "`accesHandi`)\n" +
                        "VALUES\n" +
                        "( " + id + ", \"" + arret.getCity() + "\" , \"" + arret.getDescription() + "\", " + arret.isAccesHandicap() + ");\n";
                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return success;
    }

    /** Insertion des données de base d'un nouvel Arret de Bus dans les tables marqueurs et schools
     * @author Bastien
     * @since 2.5
     * @param school : un objet School (classe étendue de marqueur)
     * @return identifiant de la nouvelle entree
     * @version 2*/
    public boolean insertMarqueur(School school)   {
        boolean success = false;
        try {
            int id = insertMarqueur((Marqueur) school); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(id> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`schools`\n" +
                        "(`idschools`,\n" +
                        "`city`,\n" +
                        "`marqueurDescription`,\n" +
                        "`schoolType`,\n" +
                        "`statut`,\n" +
                        "`adresse`)\n" +
                        "VALUES\n" +
                        "(" + id + ", \"" + school.getCity() + "\" , \"" + school.getDescription() + "\", \"" +
                        school.getSchoolType().toString() + "\", \"" +
                        school.PublicOuPrive().toString() + "\", \"" +
                        school.getAddress() + ");\n";
                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return success;
    }

    /** Insertion des données de base d'un nouvel Arret de Bus dans les tables marqueurs et customMarqueurs
     * @author Bastien
     * @since 2.6
     * @param custom : un objet CustomMarqueur (classe étendue de marqueur)
     * @return identifiant de la nouvelle entree
     * @version 2*/
    public int insertMarqueur(CustomMarqueur custom)   {
        int resid =-1; //variable contenant l'identifiant du marqueur dans la base de données
        boolean success = false;
        try {
            resid = insertMarqueur((Marqueur) custom); //Les informations de classes mere Marqueur sont envoyés vers la table marqueurs
            if(resid> 0) { //si id < 0, c'est qu'il y a une erreur, les exceptions sont deja lancee dans la methode insertMarqueur(Marqueur)
                Statement stmt = con.createStatement();

                String sql = "INSERT INTO `ourmapdb`.`customMarqueurs`\n" +
                        "(`idmarqueur`,\n" +
                        "`marqueurDesc`,\n" +
                        "`idOwner`)\n" +
                        "VALUES\n" +
                        "( " + resid + ", \"" + custom.getDescription() + "\", " + custom.getOwnerID() + ");\n";
                int tmp = stmt.executeUpdate(sql); //executeUpdate renvoie le nombre de lignes modifiees / inserees
                con.commit();
                stmt.close();
                if (tmp > 0) success = true; //si plus de 0 lignes sont ;odifee, l'operation est un succès
            }
        }
        catch(SQLException e)  {e.printStackTrace();}
        return resid;
    }



    /** Cette méthode permet de modifier les informations d'un Hotel dans la base de donnée.
     *
     * @author Bastien
     * @param changes Hashmap la clé est le nom de la colonne a modifier, la valeur est le changement à réaliser.
     * @param idmarqueur identifiant du marqueur concerné
     *
     * @return true en cas de succès, false en cas d'echec
     * @version 3*/
    public boolean updateHotel(long idmarqueur, HashMap<ModifiableHotelsColumns, String> changes) {
        try {
            Statement stmt = con.createStatement();
            String formattedValue = ""; //la valeur à rentrer, avec guillemets en cas de Varchar, sans en cas d'entier


            for(Map.Entry e : changes.entrySet()) {
                ModifiableHotelsColumns colonne = (ModifiableHotelsColumns) e.getKey();
                switch(colonne) {
                    case marqueurName:
                        stmt.executeUpdate("\"UPDATE `ourmapdb`.`marqueurs` (SET marqueurName = " + e.getValue().toString()
                                + "WHERE idmarqueur = " + idmarqueur + "\";"); //marqueurName est le seul élément modifiable dans la table Marqueurs
                        break;
                        case etoiles: //étoiles est un entier, il est donc formatté sans guillemets
                            formattedValue = e.getValue().toString();
                            break;
                        case adresse, numTel, siteWeb, tripadvisor, marqueurDescription: // formatés avec des guillemets car ce sont des VARCHAR
                            formattedValue = "\"" + e.getValue().toString() + "\"";
                            break;
                        case hasRestaurant, handi_moteur, handi_mental, handi_auditif, handi_visuel, accepteAnimaux: //Sous forme de 1 ou 0 (booléens)
                            String bool = e.getValue().toString();
                            if(bool.equals("true") || bool.equals("True") || bool.equals("TRUE") || bool.equals("1"))   {
                                formattedValue = "1";
                            }
                            else formattedValue = "0";
                            break;
                        default :
                            throw new RuntimeException("Vous n'avez pas la permission de modifier cette" +
                                    "information (" + colonne + ")");
                    }
                if(colonne != ModifiableHotelsColumns.marqueurName) { //le statement est dofférent pour marqueurName
                    String sqlUpdate = "UPDATE `ourmapdb`.`hotels` (SET " + colonne + " = " + formattedValue + "WHERE  idmarqueur = " + idmarqueur + ";";
                    stmt.executeUpdate(sqlUpdate);
                }
            }
            stmt.close();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            }
            catch (SQLException ex)    {
                ex.printStackTrace();
            }
        }
        catch (RuntimeException e)    {
            System.err.println(e);
        }
        return false;
    }

    /** Cette méthode permet de modifier les informations d'un Arret de bus dans la base de donnée.
     *
     * @author Bastien
     * @param changes Hashmap la clé est le nom de la colonne a modifier, la valeur est le changement à réaliser.
     * @param idmarqueur identifiant du marqueur concerné
     *
     * @return true en cas de succès, false en cas d'echec
     * @version 3*/
    public boolean updateArretBus(long idmarqueur, HashMap<TableArretsBus, String> changes) {
        try {
            Statement stmt = con.createStatement();
            String formattedValue = "";


            for (Map.Entry e : changes.entrySet()) {
                ModifiableArretsBusColumns colonne = (ModifiableArretsBusColumns) e.getKey();
                switch(colonne) {
                    case marqueurName: //marqueurName est le seul élément modifiable dans la table Marqueurs
                        stmt.executeUpdate("\"UPDATE `ourmapdb`.`marqueurs` (SET marqueurName = " + e.getValue().toString() + "WHERE idmarqueur = " + idmarqueur + "\";");
                        break;
                    case marqueurDescription: // formatés avec des guillemets car c'est un VARCHAR
                        formattedValue = "\"" + e.getValue().toString() + "\"";
                        break;
                    case accesHandi: // sous forme de 0 ou 1, san guillemets (booleen)
                        String bool = e.getValue().toString();
                        if(bool.equals("true") || bool.equals("True") || bool.equals("TRUE") || bool.equals("1"))   {
                            formattedValue = "1";
                        }
                        else formattedValue = "0";
                        break;
                    default :
                        throw new RuntimeException("Vous n'avez pas la permission de modifier cette" +
                                "information (" + colonne + ")");
                }
                if(colonne != ModifiableArretsBusColumns.marqueurName) { //le statement est dofférent pour marqueurName
                    String sqlUpdate = "UPDATE `ourmapdb`.`arretsbus` (SET " + colonne + " = " + formattedValue + "WHERE  idmarqueur = " + idmarqueur + ";";
                    stmt.executeUpdate(sqlUpdate);
                }
            }
            stmt.close();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (RuntimeException e)    {
            System.err.println(e);
        }
        return false;
    }


    /** Cette méthode permet de modifier les informations d'une école dans la base de donnée.
     *
     * @author Bastien
     * @param changes Hashmap la clé est le nom de la colonne à modifier, la valeur est le changement à réaliser.
     * @param idmarqueur identifiant du marqueur concerné
     *
     * @return true en cas de succès, false en cas d'échec
     * @version 3*/
    public boolean updateSchool(long idmarqueur, HashMap<ModifiableSchoolsColumns, String> changes) {
        try {
            Statement stmt = con.createStatement();


            for (Map.Entry e : changes.entrySet()) {
                ModifiableSchoolsColumns colonne = (ModifiableSchoolsColumns) e.getKey();
                switch(colonne) {
                    case marqueurName:
                        stmt.executeUpdate("\"UPDATE `ourmapdb`.`marqueurs` (SET marqueurName = " + e.getValue().toString() + "WHERE idmarqueur = " + idmarqueur + "\";");
                        break;
                    case marqueurDescription:
                        stmt.executeUpdate("UPDATE `ourmapdb`.`customMarqueurs` SET marqueurDesc = \"" + e.getValue().toString() + "\" WHERE idmarqueur = " + idmarqueur + ";");
                        break;
                    default :
                        throw new RuntimeException("Vous n'avez pas la permission de modifier cette" +
                                "information (" + colonne + ")");
                }
            }
            stmt.close();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (RuntimeException e)    {
            System.err.println(e);
        }
        return false;
    }


    /** Cette méthode permet de modifier les informations d'un marqueur custom dans la base de donnée.
     *
     * @author Bastien
     * @param changes Hashmap la clé est le nom de la colonne à modifier, la valeur est le changement à réaliser.
     * @param idmarqueur identifiant du marqueur concerné
     *
     * @return true en cas de succès, false en cas d'échec
     * @version 3*/
    public boolean updateCustom(long idmarqueur, HashMap<ModifiableCustomsColumns, String> changes, long idRequestingUser) {

        try {
            Statement stmt = con.createStatement();

            ResultSet ownerPrivilegeCheck = stmt.executeQuery("SELECT idowner FROM customMarqueurs WHERE idmarqueur = " + idmarqueur + ";");
            ownerPrivilegeCheck.next(); //Verification que la personne qui demande la modification est bien proproétaire du marqueur
            if(ownerPrivilegeCheck.getLong("idowner") != idRequestingUser ) {
                throw new RuntimeException("Vous n'êtes pas propriétaire de ce marqueur et n'avez pas le droit de le modifier.");
            }

            for (Map.Entry e : changes.entrySet()) {
                ModifiableCustomsColumns colonne = (ModifiableCustomsColumns) e.getKey();
                switch(colonne) {
                    case marqueurName:
                        stmt.executeUpdate("UPDATE `ourmapdb`.`marqueurs` SET marqueurName = \"" + e.getValue().toString() + "\" WHERE idmarqueur = " + idmarqueur + ";");
                        break;
                    case marqueurDescription:
                        stmt.executeUpdate("UPDATE `ourmapdb`.`customMarqueurs` SET marqueurDesc = \"" + e.getValue().toString() + "\" WHERE idmarqueur = " + idmarqueur + ";");
                        break;
                    default :
                        throw new RuntimeException("Vous n'avez pas la permission de modifier cette" +
                                "information (" + colonne + ")");
                }
            }

            stmt.close();
            con.commit();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        catch (RuntimeException e)    {
            System.err.println(e);
        }
        return false;
    }

}