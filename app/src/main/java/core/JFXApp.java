package core;

import core.entities.EntityGrid;
import core.entities.staticConfigurations.StillLife;
import core.visual.GameCanvas;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class JFXApp extends Application{



    public static void main(String[] args) {
        int len = args.length;
        EntityGrid.getEntityGrid().addEntityToGrid(30,30, StillLife.RING);
        JFXApp.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle(getGreeting());
        Group root = new Group();
        GameCanvas canvas = new GameCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.drawVisibleSquares(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public String getGreeting() {
        return "Hello world!";
    }




}
