package core.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityGridTest {

    @Test
    public void testGetInstance() {
        assertNotNull(EntityGrid.getEntityGrid());
    }

    @Test
    public void testInitFalseSquareStatus() {
        // Should initialize to false
        assertFalse(EntityGrid.getEntityGrid().isSquareActive(0,0));
    }

    @Test
    public void testCanSwitchSquareStatus() {
        boolean[][] entityToAdd = {{true}};
        // Add an active square to grid id 1 should cause it to become active.
        EntityGrid.getEntityGrid().addEntityToGrid(0,0,entityToAdd);
        assertTrue(EntityGrid.getEntityGrid().isSquareActive(0,0));
    }
}
