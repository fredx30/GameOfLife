package core.entities;

public class GameOfLifeRules {

    /**
     * See class GameOfLifeRules for rules. This method takes a simulated grid and updates the status 1 tick in accordance to the game rules.
     * @param grid A game of life grid.
     * @return The simulated game of life grid one game tick forward.
     */
    public boolean[][] applyRulesToGrid(boolean[][] grid) {
        /*
         * To ensure all square updates are applied at the same time we clone the simulated grid.
         * This allows for applying the updates in bulk by replacing the simulated grid with the altered grid
         * on an event of our choosing.
         */
        boolean[][] outputGrid = grid.clone();
        for (int rowIndex = 0; rowIndex < outputGrid.length; rowIndex++) {
            for (int colIndex = 0; colIndex < outputGrid[0].length; colIndex++) {
                boolean squareActiveStatus = outputGrid[rowIndex][colIndex];
                int neighborCount = countActiveNeighbors(grid,colIndex,rowIndex);
                if (squareActiveStatus) {
                    // Square is active
                    switch (neighborCount) {
                        case 0:
                        case 1:
                            // Death by solitude
                            outputGrid[rowIndex][colIndex] = applyDeathBySolitude();
                            break;
                        case 2:
                        case 3:
                            // Cell survives
                            break;
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            // Death by overpopulation
                            outputGrid[rowIndex][colIndex] = applyDeathByOverPopulation();
                            break;
                    }
                } else {
                    // Square is not active
                    if (neighborCount == 3) {
                        // Cell becomes active
                        outputGrid[rowIndex][colIndex] = applyGrowPopulation();
                        break;
                    }
                }
            }
        }
        return outputGrid;
    }

    private boolean applyDeathBySolitude() {
        return false;
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
    }

    private boolean applyDeathByOverPopulation() {
        return false;
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
    }

    private boolean applyGrowPopulation() {
        return true;
        // FIXME: Amend with logic to handle cases around the edges of the boundaries of SIMULATED_GRID.
    }

    private int countActiveNeighbors(boolean[][] grid, int x, int y) {
        int neighborIterator = 0;
        for (int rowIndex = x-1; rowIndex <= x+3; rowIndex++) {
            // Indexes outside the scope of the simulation will be treated as dead.
            if (rowIndex < 0 || rowIndex >= EntityGrid.getEntityGrid().getGridSize()){
                continue;
            }
            for (int colIndex = y-1; colIndex <= y+3; colIndex++) {
                // Indexes outside the scope of the simulation will be treated as dead.
                if (colIndex < 0 || colIndex >= EntityGrid.getEntityGrid().getGridSize()){
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
}
