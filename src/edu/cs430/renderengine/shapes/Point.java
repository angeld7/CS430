package edu.cs430.renderengine.shapes;

/**
 * Created by Angel on 9/25/2016.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Point translateY(Point point, int ySize) {
        return new Point(point.x, ySize - point.y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
