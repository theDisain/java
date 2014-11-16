package sample;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {

        final Canvas canvas = new Canvas(400,400);
        canvas.setStyle("-fx-background: rgba(0, 30, 0, 0);");
        final GraphicsContext g = canvas.getGraphicsContext2D();
        initDraw(g);

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(event.isSecondaryButtonDown()){
                            double x = event.getSceneX();
                            double y = event.getSceneY();
                            moveCanvas(x,y, g);
                            System.out.println("RIGHT MOUSE BTN DOWN");
                        }
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
        Menu menuFunct = new Menu("Functions");
        MenuItem Rect = new MenuItem("Rectangle");
        Rect.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                final GraphicsContext g = canvas.getGraphicsContext2D();
                Rect(g);
            }
        });
        MenuItem Line = new MenuItem("Line");
        Line.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                final GraphicsContext g = canvas.getGraphicsContext2D();
                Line(g);
            }
        });
        MenuItem menuFileSave = new MenuItem("Save");
        menuBar.getMenus().add(menuFile);
        menuFile.getItems().add(menuFileSave);
        menuFileSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File("res/maps"));
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG","*.png"));
                fc.setTitle("Save image");
                File file = fc.showSaveDialog(primaryStage);
                if(file != null){
                    Double CanvasDoubleValueWidth = canvas.getWidth();
                    Double CanvasDoubleValueHeight = canvas.getHeight();
                    Integer CanvasIntValueWidth = CanvasDoubleValueWidth.intValue();
                    Integer CanvasIntValueHeight = CanvasDoubleValueHeight.intValue();
                    WritableImage wi = new WritableImage(CanvasIntValueWidth, CanvasIntValueHeight);
                    try {
                        SnapshotParameters sp = new SnapshotParameters();
                        sp.setFill(Color.TRANSPARENT);
                        ImageIO.write(SwingFXUtils.fromFXImage(canvas.snapshot(sp, wi), null), "png", file);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        menuBar.getMenus().add(menuFunct);
        menuFunct.getItems().add(Rect);
        menuFunct.getItems().add(Line);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Action response = Dialogs.create()
                        .owner(primaryStage)
                        .title("Exit Dialog")
                        .masthead("You are about to exit the program.")
                        .message("Are you sure?")
                        .showConfirm()
                        ;


                if (response == Dialog.ACTION_YES) {
                    // ... user chose YES
                    Stage st = (Stage)primaryStage.getScene().getWindow();
                    st.close();
                } else response.isDisabled();
            }
        });


        BorderPane root = new BorderPane();

        root.getChildren().add(canvas);
        root.setTop(menuBar);
        final Scene scene = new Scene(root, 400, 400);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if(event.getCode() == KeyCode.BACK_SPACE){
                            canvasReset(g);
                            System.out.println("BACKSPACE PRESSED");
                        }
                    }
                });
        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>(){
                    @Override
                    public void handle(KeyEvent t) {
                        if(t.getCode()== KeyCode.ESCAPE){
                            Action response = Dialogs.create()
                                    .owner(primaryStage)
                                    .title("Exit Dialog")
                                    .masthead("You are about to exit the program.")
                                    .message("Are you sure?")
                                    .showConfirm()
                                    ;


                            if (response == Dialog.ACTION_YES) {
                                // ... user chose YES
                                Stage st = (Stage)primaryStage.getScene().getWindow();
                                st.close();
                            } else response.isDisabled();


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
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);


    }
    private void canvasReset(GraphicsContext gc){
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
    private void moveCanvas(double x, double y, GraphicsContext gc){
        gc.getCanvas().setTranslateX(x);
        gc.getCanvas().setTranslateY(y);

    }
    public void Rect(final GraphicsContext gc){

        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
                    gc.strokeRect(event.getX(),event.getY(),event.getX(), event.getY());
                }
                else  if(event.getEventType() == MouseEvent.MOUSE_DRAGGED){
                    gc.strokeRect(event.getX(),event.getY(),event.getX(),event.getY());
                }

            }
        };
    }
    public void Line(final GraphicsContext gc){
        gc.getCanvas().addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        gc.beginPath();
                        gc.moveTo(event.getX(), event.getY());
                        gc.stroke();
                    }
                });
        gc.getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.isPrimaryButtonDown()) {
                            gc.setLineWidth(1);
                            gc.lineTo(event.getX(), event.getY());
                            gc.stroke();
                            System.out.println(event.getX() + event.getY());
                        }

                    }
                });
        gc.getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        gc.setLineWidth(5);
                        gc.moveTo(event.getX(), event.getY());
                        gc.lineTo(event.getX(), event.getY());
                        gc.stroke();
                        System.out.println("MOUSECLICK;" + "X:" + event.getX() + " " + "Y:" + event.getY());
                    }
                });
    }

}