package edu.drexel.cs430.renderengine.geometry;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.Iterator;

/**
 * Created by Angel on 9/25/2016.
 */
public class Line implements Iterable<Point> {
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

    public Line transform2D(RealMatrix transformation) {
        return new Line(startPoint.transform2D(transformation),
                endPoint.transform2D(transformation));
    }

    public Line transform3D(RealMatrix transformation) {
        return new Line(startPoint.transform3D(transformation),
                endPoint.transform3D(transformation));
    }

    @Override
    public Iterator<Point> iterator() {
        return new PointIterator();
    }

    public class PointIterator implements Iterator<Point> {

        boolean start = true;

        @Override
        public boolean hasNext() {
            return start;
        }

        @Override
        public Point next() {
            if (start) {
                start = false;
                return startPoint;
            } else {
                return endPoint;
            }
        }
    }
}
