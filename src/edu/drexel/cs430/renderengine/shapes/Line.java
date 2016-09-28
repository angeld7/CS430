package edu.drexel.cs430.renderengine.shapes;

/**
 * Created by Angel on 9/25/2016.
 */
public class Line {
    public static String ID = "Line";
    private Point startPoint;
    private Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
