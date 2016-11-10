package edu.drexel.cs430.renderengine.geometry;

import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Angel on 10/17/2016.
 */
public class Polygon implements Iterable<Line> {
    private List<Point> vertices;
    private boolean fill = true;

    private float xMax = Float.MIN_VALUE, xMin = Float.MAX_VALUE, yMax = Float.MIN_VALUE, yMin = Float.MAX_VALUE;

    public Polygon() {
        vertices = new ArrayList<>();
    }

    public Polygon(Point... vertices) {
        this.vertices = new ArrayList<>(Arrays.asList(vertices));
        findLimits();
    }

    public Polygon(List<Point> vertices) {
        this.vertices = vertices;
        findLimits();
    }

    public void addVertex(Point point) {
        vertices.add(point);
        checkPointForLimit(point);
    }

    public int getNamOfVertices() {
        return vertices.size();
    }

    public Point getVertex(int vertexNum) {
        return vertices.get(vertexNum);
    }

    public Line getEdge(int edgeNum) {
        return new Line(getVertex((edgeNum - 1) < 0 ? vertices.size() - 1 : edgeNum - 1), getVertex(edgeNum));
    }

    private void findLimits() {
        vertices.forEach(this::checkPointForLimit);
    }

    private void checkPointForLimit(Point p) {
        if (p.x() > xMax) xMax = p.x();
        if (p.x() < xMin) xMin = p.x();
        if (p.y() > yMax) yMax = p.y();
        if (p.y() < yMin) yMin = p.y();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polygon polygon = (Polygon) o;

        return vertices != null ? vertices.equals(polygon.vertices) : polygon.vertices == null;

    }

    @Override
    public int hashCode() {
        return vertices != null ? vertices.hashCode() : 0;
    }

    @Override
    public SideIterator iterator() {
        return new SideIterator();
    }

    public class SideIterator implements Iterator<Line> {

        int side = 0;

        @Override
        public boolean hasNext() {
            return side < vertices.size();
        }

        @Override
        public Line next() {
            return getEdge(side++);
        }
    }

    public float xMax() {
        return xMax;
    }

    public float xMin() {
        return xMin;
    }

    public float yMax() {
        return yMax;
    }

    public float yMin() {
        return yMin;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void transform(RealMatrix transformation) {
        vertices.forEach(point -> point.transform(transformation));
        findLimits();
    }
}
