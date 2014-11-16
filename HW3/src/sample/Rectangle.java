package sample;

import javafx.scene.shape.Rectangle;

/**
 * Created by Ain-Joonas on 16.11.2014.
 */
public class MyRect extends Rectangle {
    public MyRect(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
    }

    public void setWidthHeight(double endX, double endY) {
        // setArcHeight(Math.abs(endX-startX));
        setX(Math.min(endX, startX));
        setY(Math.min(endY, startY));
        setWidth(Math.abs(endX - startX));
        setHeight(Math.abs(endY - startY));
    }
}