package org.jolmkbL2B;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Classe de test pour AppControllers
 * @author Mathys Gagner
 */
public class AppControllersTest
{
    @Test
    public void testAppControllersNotFull() {
        boolean assertion = true;
        AppControllers appControllers = new AppControllers();
        if (appControllers.idUtilisateurConnecte == 0) {
            assertion = false;
        }
        assertFalse(assertion);
    }

    @Test
    public void testAppControllersFull() {
        boolean assertion = true;
        AppControllers appControllers = new AppControllers();
        appControllers.idUtilisateurConnecte = 2;
        if (appControllers.idUtilisateurConnecte == 0) {
            assertion = false;
        }
        assertTrue(assertion);
    }
}
