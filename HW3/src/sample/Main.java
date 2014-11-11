package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {

        Canvas canvas = new Canvas(400, 400);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        graphicsContext.beginPath();
                        graphicsContext.moveTo(event.getX(), event.getY());
                        graphicsContext.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        graphicsContext.lineTo(event.getX(), event.getY());
                        graphicsContext.stroke();
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {

                    }
                });

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        Menu menuView = new Menu("View");
        MenuItem menuFileSave = new MenuItem("Save");
        menuBar.getMenus().add(menuFile);
        menuBar.getMenus().add(menuView);
        menuFile.getItems().add(menuFileSave);

        BorderPane root = new BorderPane();

        root.getChildren().add(canvas);
        root.setTop(menuBar);
        final Scene scene = new Scene(root, 400, 400);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>(){
                    @Override
                    public void handle(KeyEvent t) {
                        if(t.getCode()== KeyCode.ESCAPE){
                            Stage st = (Stage)primaryStage.getScene().getWindow();
                            st.close();
                        }
                    }
                });
        primaryStage.setTitle("iapb134301");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.fill();
        gc.strokeRect(
                0,              //x == upper left corner
                0,              //y == upper left corner
                canvasWidth,    //width
                canvasHeight);  //height

        gc.setFill(Color.RED);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);

    }

}