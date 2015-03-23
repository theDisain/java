package sample;


import java.util.ArrayList;
import java.util.ListIterator;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.*;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ColorPicker;

/**
 * Main class of the program.
 * @author ROG
 *
 */
@SuppressWarnings("restriction")
public class Main extends Application {

    ArrayList<Action> actions = new ArrayList<Action>();
    boolean currentShape = false;
    double SceneX, SceneY;
    double TranslateX, TranslateY;

    /**
     * Main method.
     * @param args used
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage s) throws Exception {
        VBox topContainer = new VBox();
        MenuBar mainMenu = new MenuBar();

        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);

        MenuItem line = new MenuItem("Line");
        MenuItem rectangle = new MenuItem("Rectangle");

        MenuItem undo = new MenuItem("undo");
        MenuItem redo = new MenuItem("redo");
        MenuItem exit = new MenuItem("exit");


        MenuItem pick = new MenuItem("Pick a color");

        Menu file = new Menu("File");
        Menu shape = new Menu("Shape");
        Menu clr = new Menu("Color");

        shape.getItems().addAll(line,rectangle);
        file.getItems().addAll(undo, redo, exit);
        clr.getItems().add(pick);
        topContainer.getChildren().addAll(colorPicker, mainMenu);
        mainMenu.getMenus().addAll(file, shape, clr);

        s.setTitle("KimpApplication");
        final Pane dp = new Pane();
        BorderPane bp = new BorderPane();
        bp.setTop(topContainer);
        Scene scene = new Scene(bp, 800, 600);
        bp.setCenter(dp);

        rectangle.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                currentShape = true;
            }
        });

        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                boolean hasvalid = false;
                for (Action a : actions) {
                    if (a.isValid) {
                        hasvalid = true;
                        break;
                    }
                }
                if (hasvalid)
                    for (ListIterator<Action> iterator = actions.listIterator(actions.size()); iterator.hasPrevious();) {
                        Object a  = iterator.previous();
                        if (((Action)a).isValid) {
                            ((Action)a).undo();
                            break;
                        }
                    }
            }
        });

        redo.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Action last = null;
                if (actions.size() > 0)
                    last = actions.get(0);

                for (ListIterator<Action> iterator = actions.listIterator(actions.size()); iterator.hasPrevious();) {
                    Object a  = iterator.previous();
                    if (((Action)a).isValid) {
                        last = ((Action)a);
                        break;
                    }
                }
                if (last != null)
                    for (int i = actions.indexOf(last); i < actions.size(); i++) {
                        if (!actions.get(i).isValid) {
                            actions.get(i).redo();
                            break;
                        }
                    }
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                exitDiag(dp);
            }
        });

        //line mouse events
        final EventHandler<MouseEvent> lineOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.isControlDown() && e.getEventType() == MouseEvent.MOUSE_PRESSED
                        && e.isPrimaryButtonDown()) {
                    SceneX = e.getSceneX();
                    SceneY = e.getSceneY();
                    TranslateX = ((Polyline)(e.getSource())).getTranslateX();
                    TranslateY = ((Polyline)(e.getSource())).getTranslateY();

                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setRadius(5.0);
                    dropShadow.setOffsetX(3.0);
                    dropShadow.setOffsetY(3.0);
                    dropShadow.setColor(Color.BLACK);
                    Polyline line = (Polyline) e.getSource();
                    line.setEffect(dropShadow);
                } else if (e.isControlDown() && e.getEventType() == MouseEvent.MOUSE_PRESSED
                        && e.isSecondaryButtonDown()) {
                    Polyline line = (Polyline) e.getSource();
                    line.setVisible(false);
                    actions.add(new Action(line, Action.ACTION_DELETE));
                    //dp.getChildren().remove(line);
                }
            }
        };

        final EventHandler<MouseEvent> lineOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.isControlDown() && t.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double offsetX = t.getSceneX() - SceneX;
                    double offsetY = t.getSceneY() - SceneY;
                    double newTranslateX = TranslateX + offsetX;
                    double newTranslateY = TranslateY + offsetY;
                    if (((actions.get(actions.size()-1).s) != (Polyline) (t.getSource())) ^ ((actions.get(actions.size()-1).action != Action.ACTION_MOVE)))
                        actions.add(new Action((Polyline) (t.getSource()), Action.ACTION_MOVE).setActionParameters(t.getSceneX()- SceneX, t.getSceneY()- SceneY, newTranslateX, newTranslateY));
                    else {
                        actions.get(actions.size()-1).modifyPath(newTranslateX, newTranslateY);
                    }
                    ((Polyline) (t.getSource())).setTranslateX(newTranslateX);
                    ((Polyline) (t.getSource())).setTranslateY(newTranslateY);

                }
            }
        };

        final EventHandler<MouseEvent> lineOnMouseReleasedEventHandler =
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        ((Polyline)(t.getSource())).setEffect(null);
                    }
                };

        //rectangle mouse events
        final EventHandler<MouseEvent> rectangleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.isControlDown() && t.getEventType() == MouseEvent.MOUSE_PRESSED
                        && t.isPrimaryButtonDown()) {
                    SceneX = t.getSceneX();
                    SceneY = t.getSceneY();
                    TranslateX = ((Rectangle)(t.getSource())).getTranslateX();
                    TranslateY = ((Rectangle)(t.getSource())).getTranslateY();

                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setRadius(5.0);
                    dropShadow.setOffsetX(3.0);
                    dropShadow.setOffsetY(3.0);
                    dropShadow.setColor(Color.BLACK);
                    Rectangle rect = (Rectangle) t.getSource();
                    rect.setEffect(dropShadow);
                }  else if (t.isControlDown() && t.getEventType() == MouseEvent.MOUSE_PRESSED
                        && t.isSecondaryButtonDown()) {
                    Rectangle rect = (Rectangle) t.getSource();
                    //dp.getChildren().remove(rect);
                    rect.setVisible(false);
                    actions.add(new Action(rect, Action.ACTION_DELETE));
                }
            }
        };


        final EventHandler<MouseEvent> rectangleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.isControlDown() && t.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double offsetX = t.getSceneX() - SceneX;
                    double offsetY = t.getSceneY() - SceneY;
                    double newTranslateX = TranslateX + offsetX;
                    double newTranslateY = TranslateY + offsetY;
                    if (((actions.get(actions.size()-1).s) != (Rectangle) (t.getSource())) ^ ((actions.get(actions.size()-1).action != Action.ACTION_MOVE)))
                        actions.add(new Action((Rectangle) (t.getSource()), Action.ACTION_MOVE).setActionParameters(t.getSceneX()- SceneX, t.getSceneY()- SceneY, newTranslateX, newTranslateY));
                    else {
                        actions.get(actions.size()-1).modifyPath(newTranslateX, newTranslateY);
                    }
                    ((Rectangle) (t.getSource())).setTranslateX(newTranslateX);
                    ((Rectangle) (t.getSource())).setTranslateY(newTranslateY);
                }
            }
        };

        final EventHandler<MouseEvent> rectangleOnMouseReleasedEventHandler =
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        ((Rectangle)(t.getSource())).setEffect(null);
                    }
                };

        //drawing lines & rectangles
        EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>() {
            double startX, startY;
            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED &&
                        !event.isControlDown()) {
                    if (!currentShape) {
                        if (event.getY() < 0) return;
                        Polyline line = new Polyline();
                        line.setOnMousePressed(lineOnMousePressedEventHandler);
                        line.setOnMouseDragged(lineOnMouseDraggedEventHandler);
                        line.setOnMouseReleased(lineOnMouseReleasedEventHandler);
                        dp.getChildren().add(line);
                        actions.add(new Action(line, Action.ACTION_CREATE));
                        line.getPoints().add(event.getX()); // x
                        line.getPoints().add(event.getY()); // y
                        line.getPoints().add(event.getX());
                        line.getPoints().add(event.getY());
                    } else if (currentShape) {
                        if (event.getY() < 0) return;
                        Rectangle rect = new Rectangle();
                        rect.setOnMousePressed(rectangleOnMousePressedEventHandler);
                        rect.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
                        rect.setOnMouseReleased(rectangleOnMouseReleasedEventHandler);
                        startX = event.getX();
                        startY = event.getY();
                        rect.setX(startX);
                        rect.setY(startY);
                        rect.setFill(Color.TRANSPARENT);
                        rect.setStroke(colorPicker.getValue());
                        dp.getChildren().add(rect);
                        actions.add(new Action(rect, Action.ACTION_CREATE));
                    }
                } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED &&
                        !event.isControlDown()) {
                    if (event.getY() < 0) {
                        return;
                    }
                    if (!currentShape) {
                        ObservableList<Node> list = dp.getChildren();
                        if (list.size() < 1) return;
                        int linesListSize = dp.getChildren().size();
                        Polyline p = (Polyline) list.get(linesListSize - 1); //last line
                        p.setStroke(colorPicker.getValue());
                        p.getPoints().add(event.getX());
                        p.getPoints().add(event.getY());
                    } else if (currentShape) {
                        ObservableList<Node> list = dp.getChildren();
                        int linesListSize = dp.getChildren().size();
                        Rectangle rect = (Rectangle) list.get(linesListSize - 1); //last rectangle
                        if (event.getX() < startX) {
                            rect.setX(event.getX());
                            rect.setWidth(startX - event.getX());
                        } else {
                            rect.setWidth(event.getX() - startX);
                        }
                        if (event.getY() < startY) {
                            rect.setY(event.getY());
                            rect.setHeight(event.getY() - startY);
                        }
                        rect.setHeight(Math.abs(startY - event.getY()));
                    }
                }
            }
        };


        //exiting with esc
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
		        	  
		        	 /* if (!actions.get(0).isValid) {
		        		  exitDiag(dp);
		        	  }*/
                    boolean hasvalid = false;
                    for (Action a : actions) {
                        if (a.isValid) {
                            hasvalid = true;
                            break;
                        }
                    }
                    if (!hasvalid)
                        exitDiag(dp);

                    for (ListIterator<Action> iterator = actions.listIterator(actions.size()); iterator.hasPrevious();) {
                        Object a  = iterator.previous();
                        if (((Action)a).isValid) {
                            ((Action)a).undo();
                            break;
                        }
                    }
                }
                Action last = null;
                if (actions.size() > 0)
                    last = actions.get(0);

                for (ListIterator<Action> iterator = actions.listIterator(actions.size()); iterator.hasPrevious();) {
                    Object a  = iterator.previous();
                    if (((Action)a).isValid) {
                        last = ((Action)a);
                        break;
                    }
                }
                if (last != null)
                    if (t.getCode() == KeyCode.SPACE) {
                        for (int i = actions.indexOf(last); i < actions.size(); i++) {
                            if (!actions.get(i).isValid) {
                                actions.get(i).redo();
                                break;
                            }
                        }
                    }
            }
        });
        dp.addEventHandler(MouseEvent.MOUSE_PRESSED, myHandler);
        dp.addEventHandler(MouseEvent.MOUSE_DRAGGED, myHandler);

        s.setScene(scene);
        s.show();
    }

    public class Action {
        public static final int ACTION_CREATE = 0;
        public static final int ACTION_DELETE = 1;
        public static final int ACTION_MOVE = 2;

        public Shape s = null;
        public int action = -1;
        public boolean isValid = true;

        double fromX, fromY, toX, toY;

        public Action(Shape s, int action) {
            this.s = s;
            this.action = action;
        }

        public Action setActionParameters(double x, double y, double x2, double y2) {
            fromX = x;
            fromY = y;
            toX = x2;
            toY = y2;
            return this;
        }

        public Action modifyPath (double x, double y) {
            this.toX = x;
            this.toY = y;
            return this;
        }

        public void undo() {
            if (action == ACTION_DELETE) {
                s.setVisible(true);
                isValid = false;
            }
            if (action == ACTION_CREATE) {
                s.setVisible(false);
                isValid = false;
            }
            if (action == ACTION_MOVE) {
                s.setTranslateX(fromX);
                s.setTranslateY(fromY);
                isValid = false;
            }

        }

        public void redo() {
            if (action == ACTION_DELETE) {
                s.setVisible(false);
                isValid = true;
            }
            if (action == ACTION_CREATE) {
                s.setVisible(true);
                isValid = true;
            }
            if (action == ACTION_MOVE) {
                s.setTranslateX(toX);
                s.setTranslateY(toY);
                isValid = true;
            }
        }
    }

    public void exitDiag(final Pane dp) {

        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Label exitLabel = new Label("Are you sure you want to exit?");
        exitLabel.setAlignment(Pos.BASELINE_CENTER);

        Button yesBtn = new Button("Yes");
        yesBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                dialogStage.close();
                Stage sb = (Stage) dp.getScene().getWindow();
                sb.close();
            }
        });
        Button noBtn = new Button("No");

        noBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                dialogStage.close();

            }
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.setSpacing(40.0);
        hBox.getChildren().addAll(yesBtn, noBtn);

        VBox vBox = new VBox();
        vBox.setSpacing(40.0);
        vBox.getChildren().addAll(exitLabel, hBox);

        dialogStage.setScene(new Scene(vBox));
        dialogStage.show();
    }
}