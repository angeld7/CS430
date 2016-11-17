package edu.drexel.cs430.renderengine.geometry;

import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Angel on 9/25/2016.
 */
public class Line {
    private Point startPoint;
    private Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(Line line) {
        startPoint = new Point(line.start());
        endPoint = new Point(line.end());
    }

    public Point start() {
        return startPoint;
    }

    public Point end() {
        return endPoint;
    }

    public Point getPointFromT(float t) {
        return new Point(
                startPoint.x() + t * (endPoint.x() - startPoint.x()),
                startPoint.y() + t * (endPoint.y() - startPoint.y())
        );
    }

    public void transform2D(RealMatrix transformation) {
        startPoint.transform2D(transformation);
        endPoint.transform2D(transformation);
    }

    public void transform3D(RealMatrix transformation) {
        startPoint.transform3D(transformation);
        endPoint.transform3D(transformation);
    }
}
