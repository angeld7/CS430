package edu.drexel.cs430.renderengine.geometry;

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

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Point getPointFromT(float t) {
        return new Point(
                startPoint.x + t * (endPoint.x - startPoint.x),
                startPoint.y + t * (endPoint.y - startPoint.y)
        );
    }
}
