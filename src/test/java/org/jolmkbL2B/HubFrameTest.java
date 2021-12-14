package org.jolmkbL2B;

import org.jolmkbL2B.vue.frame.HubFrame;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;


/**
 * Classe de test pour HubFrame
 * @author Mathys Gagner
 */
public class HubFrameTest {
    HubFrame hubFrame;

    @BeforeEach
    void init() throws SQLException {
        hubFrame = new HubFrame(new AppControllers());
    }

    @Test
    void testThemesUp() throws SQLException {
        assertFalse(hubFrame.themes.isEmpty());
    }

    @Test
    void testMapPanelUp() {
        assertFalse(hubFrame.mapPanel1 == null);
    }

    @Test
    void testWaypointsNotEmpty() {
        assertFalse(hubFrame.mapPanel1.waypoints.isEmpty());
    }

    @Test
    void testMapPanelMouseListenersUp() {
        assertTrue(hubFrame.mapPanel1.OurMap.getMouseListeners().length > 0);
    }
}
