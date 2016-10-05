package edu.drexel.cs430.renderengine.shapes;

/**
 * Created by Angel on 9/25/2016.
 */
public class Point {
    private float x;
    private float y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
