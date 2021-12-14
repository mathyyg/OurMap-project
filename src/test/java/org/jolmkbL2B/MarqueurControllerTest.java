package org.jolmkbL2B;

import org.jolmkbL2B.controllers.ListeController;
import org.jolmkbL2B.controllers.MarqueurController;
import org.jolmkbL2B.marqueurs.PlaceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Classe de test pour MarqueurController
 * @author Mathys Gagner
 */
//@RunWith(MockitoJUnitRunner.class)
public class MarqueurControllerTest {
//    @Mock
//    private ResultSet attendu;

    @Test
    public void testFetchAllInfoNonClosed() throws SQLException {
        MarqueurController marqueurController = new MarqueurController();
        assertFalse(marqueurController.con.isClosed());
    }

    @Test
    public void testFetchAllInfoIsClosed() throws SQLException {
        MarqueurController marqueurController = new MarqueurController();
        marqueurController.con.close();
        assertTrue(marqueurController.con.isClosed());
    }

    @Test
    public void testFetchAllInfoNull() {
        MarqueurController marqueurController = new MarqueurController();
        marqueurController.con = null;
        assertThrows(NullPointerException.class, () -> {
            marqueurController.con.close();
        });
    }

    @Test
    public void testFetchAllInfoCorrect() throws SQLException {
        boolean assertion = false;
        MarqueurController marqueurController = new MarqueurController();
        ResultSet res = marqueurController.fetchAllInfo(1, PlaceType.HOTEL);

        while(res.next()) {
            if(res.getString(5).equals("Novotel Blois - Centre Val de Loire")
            && res.getInt(1) == 1 && res.getString(2).equals("HOTEL")
            && res.getDouble(3) == (47.5916773) && res.getDouble(4) == 1.3361703) {
                assertion = true;
            }
        }
        assertTrue(assertion);
    }
}
