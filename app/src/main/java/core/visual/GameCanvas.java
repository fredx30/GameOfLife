package core.visual;

import core.entities.EntityGrid;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {

    // This defines the size of the window
    public final static int CANVAS_PIXEL_SIZE = 800;

    // This defines the scale of the game field in the view
    public final static int GRID_PIXEL_SIZE = 20;

    /**
     * @param canvasGridStart defines where the view of the gamefield is on the abstract 2d plane
     * Starting at gridPixelSize 20 and canvasPixelSize 800 will allow a total of 40x40 squares to be shown.
     * A canvasGridStart of 30 would then show squares of index 30 all the way to index 70 (col & row) this
     * allows limitation of the view to part of the simulation.
     */
    public final int canvasGridStart = 30;

    public GameLoopTimer gameLoopTimer;


    public GameCanvas() {
        super(CANVAS_PIXEL_SIZE, CANVAS_PIXEL_SIZE);

        gameLoopTimer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                clearCanvas();
                drawVisibleSquares();
            }
        };
        gameLoopTimer.start();
    }

    /**
     * Gets the width of the canvas measured in gameplay entity squares (default: 40)
     * @return the amount of squares of size gridPixelSize required to fill up the total canvasPixelSize
     */
    public int getCanvasIndexCoverage(){
        return CANVAS_PIXEL_SIZE / GRID_PIXEL_SIZE;
    }

    public void drawVisibleSquares() {
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        graphicsContext.save();
        graphicsContext.setFill(Color.GREEN);
        for (int rowIndex = canvasGridStart; rowIndex < (canvasGridStart + this.getCanvasIndexCoverage()); rowIndex++) {
            for (int colIndex = canvasGridStart; colIndex < (canvasGridStart + this.getCanvasIndexCoverage()); colIndex++) {
                if (EntityGrid.getEntityGrid().isSquareActive(rowIndex,colIndex)) {
                    drawSquare(rowIndex, colIndex);
                }
            }
        }
        graphicsContext.restore();
    }

    public void drawSquare(int rowIndex, int colIndex) {
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        int xStart = (rowIndex - this.canvasGridStart) * this.GRID_PIXEL_SIZE;
        int yStart = (colIndex - this.canvasGridStart) * this.GRID_PIXEL_SIZE;
        graphicsContext.fillRoundRect(xStart,yStart,this.GRID_PIXEL_SIZE, this.GRID_PIXEL_SIZE,10, 10);
    }

    public void clearCanvas() {
        GraphicsContext graphicsContext = this.getGraphicsContext2D();
        graphicsContext.setFill(Color.GRAY);
        graphicsContext.clearRect(0,0,CANVAS_PIXEL_SIZE, CANVAS_PIXEL_SIZE);
    }
}
