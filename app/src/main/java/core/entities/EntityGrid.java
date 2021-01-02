package core.entities;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class will represent the abstract 2d plane on which the game of life takes place. For the first implementation
 * this will be limited to a static grid size outside of which the entities will no longer be simulated. Increasing the
 * size of the space will allow the simulation to continue further outside of the visible screen space.
 */
public class EntityGrid {

    /** @param SIMULATED_GRID_SIZE Increase this number to make the system keep track of items further outside the screen */
    private final int SIMULATED_GRID_SIZE = 100;
    /** @param gameRunning indicated if game ticks should be applied at the rate of gameSpeed. */
    private volatile boolean gameRunning = true;
    /** @param gameSpeed designated in ms between gameTicks */
    private volatile int gameSpeed = 1000;
    private final GameOfLifeRules ruleSet;


    private static EntityGrid entityGridSingletonInstance;

    private boolean[][] entityGrid;

    private EntityGrid() {
        // Start all squares empty.
        entityGrid = new boolean[SIMULATED_GRID_SIZE][SIMULATED_GRID_SIZE];
        ruleSet = new GameOfLifeRules();
    }

    public static EntityGrid getEntityGrid() {
        if (EntityGrid.entityGridSingletonInstance == null) {
            EntityGrid.entityGridSingletonInstance = new EntityGrid();
        }
        return EntityGrid.entityGridSingletonInstance;
    }

    public boolean isSquareActive(int rowIndex, int colIndex) {
        return entityGrid[rowIndex][colIndex];
    }

    /**
     * Adds a start configuration to the grid.
     * @param startXIndex Where on the grids X axis to put the entity
     * @param startYIndex Where on the grids Y axis to put the entity
     * @param entity a boolean[][] dictating which square of the entity are active (true) and which are dead (false).
     * @throws IndexOutOfBoundsException
     */
    public void addEntityToGrid(int startXIndex, int startYIndex, boolean[][] entity) throws IndexOutOfBoundsException {
        // FIXME: Assume all entity arrays are equal length.
        if (entity.length + startYIndex > this.SIMULATED_GRID_SIZE || entity[0].length + startXIndex > this.SIMULATED_GRID_SIZE) {
            throw new IndexOutOfBoundsException("The added entity is outside of the simulated grid!");
        }
        for (int rowIndex = startYIndex; rowIndex < (startYIndex + entity.length); rowIndex++) {
            for (int colIndex = startXIndex; colIndex < (startXIndex + entity[0].length); colIndex++) {
                entityGrid[rowIndex][colIndex] = entity[rowIndex-startYIndex][colIndex-startXIndex];
            }
        }
    }

    public int getGridSize() {
        return SIMULATED_GRID_SIZE;
    }

    /**
     * Creates a thread to run the simulation behind the scenes. This will apply the ruleset in the gamerules once
     * every `gameSpeed` milliseconds.
     */
    public void startSimulation() {
        Thread gameSimulationRunner = new Thread(() -> {
            try {
                Thread.sleep(1000);
                AtomicInteger iteratation = new AtomicInteger();
                iteratation.set(0);
                while (gameRunning) {
                    Thread.sleep(gameSpeed);
                    EntityGrid.getEntityGrid().executeSimulationTick(iteratation);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        gameSimulationRunner.start();
    }

    /**
     * Stops the game simulation.
     */
    public void stopSimulation() {
        gameRunning = false;
    }

    /**
     * Sets the simulation speed.
     * @param speed time in ms between ticks.
     */
    public void setSimulationSpeed(int speed) {
        EntityGrid.getEntityGrid().gameSpeed = speed;
    }

    private void executeSimulationTick(AtomicInteger iteration) {
        this.entityGrid = ruleSet.applyRulesToGrid(this.entityGrid, iteration);
    }


}
