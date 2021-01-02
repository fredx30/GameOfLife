package core;

import core.entities.EntityGrid;
import core.entities.staticConfigurations.SpaceShip;
import core.visual.GameCanvas;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class JFXApp extends Application {

    public static void main(String[] args) {
        EntityGrid.getEntityGrid().addEntityToGrid(32, 32, SpaceShip.GLIDER);

        Thread frontendApp = new Thread(()-> {
            JFXApp.launch(args);
        });
        frontendApp.start();

        EntityGrid.getEntityGrid().startSimulation();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(getTitle());
        Group root = new Group();
        GameCanvas canvas = new GameCanvas();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));

        // Handle graceful closing of app.
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                canvas.gameLoopTimer.stop();
                EntityGrid.getEntityGrid().stopSimulation();
            }
        });

        primaryStage.show();
    }

    public String getTitle() {
        return "Hello world!";
    }

}
