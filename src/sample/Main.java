import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    class MyHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    private double oldX = 0, oldY = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem menuFileSave = new MenuItem("Save");
        menuBar.getMenus().add(menuFile);
        menuFile.getItems().add(menuFileSave);

        root.setTop(menuBar);

        Pane main = new Pane();
        root.setCenter(main);

        Rectangle r1 = new Rectangle(10, 10, 100, 100);
        Rectangle r2 = new Rectangle(100, 100, 50, 20);
        main.getChildren().add(r1);
        main.getChildren().add(r2);


        //EventHandler<MouseEvent> mouseHandler = new MyHandler();
        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        Polyline line = new Polyline();
                        line.getPoints().add(event.getX());
                        line.getPoints().add(event.getY());
                        main.getChildren().add(line);
                    }
                    oldX = event.getX();
                } else {
                    // drag
                    if (event.getButton() == MouseButton.PRIMARY) {
                        for (Node s : main.getChildren()) {
                            if (s instanceof Rectangle) {
                                Rectangle r = (Rectangle)s;
                                r.setX(r.getX() + (event.getX() - oldX));
                            }
                        }
                    } else {
                        //secondary
                        for (Node s : main.getChildren()) {
                            if (s instanceof Polyline) {
                                Polyline p = (Polyline)s;
                                if (p.getPoints().size() < 4) {
                                    p.getPoints().add(event.getX());
                                    p.getPoints().add(event.getY());
                                } else {
                                    p.getPoints().set(2, event.getX());
                                    p.getPoints().set(3, event.getY());
                                }
                            }
                        }
                    }
                }
                oldX = event.getX();

            }
        };

        main.setOnMousePressed(mouseHandler);
        main.setOnMouseDragged(mouseHandler);