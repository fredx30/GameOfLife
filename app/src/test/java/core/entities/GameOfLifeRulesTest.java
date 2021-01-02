package core.entities;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class GameOfLifeRulesTest {
    private final static String[] STILL_LIFE_STRING_ARRAY_CONFIGURATION = {
            "-+-",
            "+-+",
            "+-+",
            "-+-",
    };
    private final static String[] OSCILLATOR_STRING_ARRAY_CONFIGURATION = {
            "-+-",
            "-+-",
            "-+-",
    };
    private final static String[] EMPTY_STRING_ARRAY_CONFIGURATION = {
            "---",
            "---",
            "---",
    };

    @Test
    public void testEmptyGrid() {
        GameOfLifeRules gameOfLifeRules = new GameOfLifeRules();
        AtomicInteger simulationAge = new AtomicInteger(0);
        boolean[][] emptyGrid = gameOfLifeRules.applyRulesToGrid(EntityUtility.loadEntityFromString(EMPTY_STRING_ARRAY_CONFIGURATION), simulationAge);
        assertEquals(1, simulationAge.get());
        assertArrayEquals( new boolean[3][3], emptyGrid);
    }

    @Test
    public void testStillLife() {
        GameOfLifeRules gameOfLifeRules = new GameOfLifeRules();
        AtomicInteger simulationAge = new AtomicInteger(0);
        boolean[][] stillLifeGrid = gameOfLifeRules.applyRulesToGrid(EntityUtility.loadEntityFromString(STILL_LIFE_STRING_ARRAY_CONFIGURATION), simulationAge);
        boolean[][] expectedGrid = {
                {false,true,false,},
                {true,false,true},
                {true,false,true,},
                {false,true,false,},
        };
        assertEquals(1, simulationAge.get());
        assertArrayEquals(expectedGrid, stillLifeGrid);
    }

    @Test
    public void testOscillator() {
        GameOfLifeRules gameOfLifeRules = new GameOfLifeRules();
        AtomicInteger simulationAge = new AtomicInteger(0);
        boolean[][] oscillatorGrid = gameOfLifeRules.applyRulesToGrid(EntityUtility.loadEntityFromString(OSCILLATOR_STRING_ARRAY_CONFIGURATION), simulationAge);
        boolean[][] expectedOddIteration = {
                {false,false,false,},
                {true,true,true},
                {false,false,false,},
        };
        boolean[][] expectedEvenIteration = {
                {false,true,false,},
                {false,true,false},
                {false,true,false,},
        };
        assertEquals(1, simulationAge.get());
        assertArrayEquals(expectedOddIteration, oscillatorGrid);
        oscillatorGrid = gameOfLifeRules.applyRulesToGrid(oscillatorGrid, simulationAge);
        assertEquals(2, simulationAge.get());
        assertArrayEquals(expectedEvenIteration, oscillatorGrid);
    }
}
