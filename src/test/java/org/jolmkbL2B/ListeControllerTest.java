package org.jolmkbL2B;

import org.jolmkbL2B.controllers.ListeController;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Classe de test pour ListeController
 * @author Mathys Gagner
 */
public class ListeControllerTest {
    @Test
    void testAddToListNonClosed() throws SQLException {
        ListeController listeController = new ListeController();
        assertFalse(listeController.con.isClosed());
    }

    @Test
    void testAddToListNull() {
        ListeController listeController = new ListeController();
        listeController.con = null;
        assertThrows(NullPointerException.class, () -> {
            listeController.con.close();
        });
    }

    @Test
    void testAddToListIsClosed() throws SQLException {
        ListeController listeController = new ListeController();
        listeController.con.close();
        assertTrue(listeController.con.isClosed());
    }

    @Test
    void testAddToListSucceed() {
        ListeController listeController = new ListeController();
        assertTrue(listeController.addToList(9999, 18, 2));
    }

    @Test
    void testAddToListMarqueurInList() throws SQLException {
        ListeController listeController = new ListeController();
        listeController.addToList(888, 18, 2);
        ResultSet res = listeController.fetchList(18, 2);
        boolean assertion = false;
        while(res.next()) {
            if(res.getInt(1) == 888) {
                assertion = true;
            }
        }
        assertTrue(assertion);
    }

    @Test
    void testAddToListMarqueurNotInList() throws SQLException {
        ListeController listeController = new ListeController();
        ResultSet res = listeController.fetchList(17, 2);
        boolean assertion = false;
        while(res.next()) {
            if(res.getInt(1) == 9998) {
                assertion = true;
            }
        }
        assertFalse(assertion);
    }

    @AfterAll
    public static void reset() {
        ListeController listeController = new ListeController();
        listeController.removeFromList(9999, 18, 2);
        listeController.removeFromList(888, 18, 2);
    }

}