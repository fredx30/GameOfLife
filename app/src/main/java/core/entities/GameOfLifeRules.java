package core.entities;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class GameOfLifeRules {

    /**
     * See class GameOfLifeRules for rules. This method takes a simulated grid and updates the status 1 tick in accordance to the game rules.
     * @param grid A game of life grid.
     * @param iteration a tracker for checking the age of the simulation.
     * @return The simulated game of life grid one game tick forward.
     */
    public boolean[][] applyRulesToGrid(boolean[][] grid, AtomicInteger iteration) {
        /*
         * To ensure all square updates are applied at the same time we clone the simulated grid.
         * This allows for applying the updates in bulk by replacing the simulated grid with the altered grid
         * on an event of our choosing.
         * Alternatively this could be created as a queue of changes (more work needed). This would allow for
         * changes to be streamed and would avoid the need for cloning the original array. This way even a large
         * grid could remain responsive. Streaming changes as nodes would also allow for backtracking.
         */
        boolean[][] outputGrid = EntityUtility.deepCopy(grid);
        for (int rowIndex = 0; rowIndex < outputGrid.length; rowIndex++) {
            for (int colIndex = 0; colIndex < outputGrid[0].length; colIndex++) {
                boolean squareActiveStatus = grid[rowIndex][colIndex];
                int neighborCount = countActiveNeighbors(grid,colIndex,rowIndex);
                if (squareActiveStatus) {
                    // Square is active
                    switch (neighborCount) {
                        case 0:
                        case 1:
                            // Death by solitude
                            outputGrid[rowIndex][colIndex] = applyDeathBySolitude(iteration, colIndex,rowIndex);
                            continue;
                        case 2:
                        case 3:
                            // Cell survives
                            continue;
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            // Death by overpopulation
                            outputGrid[rowIndex][colIndex] = applyDeathByOverPopulation(iteration, colIndex,rowIndex);
                    }
                } else {
                    // Square is not active
                    if (neighborCount == 3) {
                        // Cell becomes active
                        outputGrid[rowIndex][colIndex] = applyGrowPopulation(iteration, colIndex,rowIndex);
                    }
                }
            }
        }
        iteration.incrementAndGet();
        return outputGrid;
    }

    private boolean applyDeathBySolitude(AtomicInteger iteration, int x, int y) {
        reportToConsole("Cell killed due to solitude at (" + x + ", " + y + ") during iteration " + iteration.get());
        return false;
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
    }

    private boolean applyDeathByOverPopulation(AtomicInteger iteration, int x, int y) {
        reportToConsole("Cell killed due to overpopulation at (" + x + ", " + y + ") during iteration " + iteration.get());
        return false;
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
    }

    private boolean applyGrowPopulation(AtomicInteger iteration, int x, int y) {
        reportToConsole("Cell grown at (" + x + ", " + y + ") during iteration " + iteration.get());
        return true;
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
    }

    /**
     * Counts the number of active cells within a 3x3 grid centered on x,y.
     * @param grid the simulation grid.
     * @param x the center x index
     * @param y the center y index
     * @return count of active cells within a 3x3 grid centered on x,y
     */
    private int countActiveNeighbors(boolean[][] grid, int x, int y) {
        int neighborIterator = 0;
        for (int rowIndex = y-1; rowIndex <= y+1; rowIndex++) {
            // Indexes outside the scope of the simulation will be treated as dead.
            if (rowIndex < 0 || rowIndex >= grid.length){
                continue;
            }
            for (int colIndex = x-1; colIndex <= x+1; colIndex++) {
                // Indexes outside the scope of the simulation will be treated as dead.
                // FIXME in certain cases this could result in spaceships that turn on the simulation edge. This shouldn't happen.
                if (colIndex < 0 || colIndex >= grid[rowIndex].length){
                    continue;
                }
                // Only count neighbor cells so we skip the center of the 3x3
                if (rowIndex == y && colIndex == x){
                    continue;
                }
                if (grid[rowIndex][colIndex]) {
                    neighborIterator++;
                }
            }
        }
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
        return neighborIterator;
    }

    private void reportToConsole(String s) {
        System.out.println(s);
    }
}
