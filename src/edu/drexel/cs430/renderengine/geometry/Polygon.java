package edu.drexel.cs430.renderengine.geometry;

import org.apache.commons.math3.linear.RealMatrix;

import java.awt.*;
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

    private static final float MAX = Float.MAX_VALUE;
    private float xMax, xMin, yMax, yMin, zMax, zMin;
    private Color color;

    public Polygon() {
        vertices = new ArrayList<>();
        resetLimits();
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
        Line line = new Line(getVertex((edgeNum - 1) < 0 ? vertices.size() - 1 : edgeNum - 1), getVertex(edgeNum));
        line.setColor(color);
        return line;
    }

    private void findLimits() {
        resetLimits();
        vertices.forEach(this::checkPointForLimit);
    }

    public void resetLimits() {
        xMax = -MAX;
        xMin = MAX;
        yMax = -MAX;
        yMin = MAX;
        zMax = -MAX;
        zMin = MAX;
    }

    private void checkPointForLimit(Point p) {
        if (p.x() > xMax) xMax = p.x();
        if (p.x() < xMin) xMin = p.x();
        if (p.y() > yMax) yMax = p.y();
        if (p.y() < yMin) yMin = p.y();
        if (p.z() > zMax) zMax = p.z();
        if (p.z() < zMin) zMin = p.z();

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

    public List<Point> getVerticies() {
        return vertices;
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

    public float zMax() {
        return zMax;
    }

    public float zMin() {
        return zMin;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Polygon transform3D(RealMatrix transformation) {
        List<Point> newPoints = new ArrayList<>();
        vertices.forEach(point ->
                newPoints.add(point.transform3D(transformation))
        );
        return new Polygon(newPoints);
    }

    public Polygon transform2D(RealMatrix transformation) {
        List<Point> newPoints = new ArrayList<>();
        vertices.forEach(point ->
                newPoints.add(point.transform2D(transformation))
        );
        return new Polygon(newPoints);
    }

    public Polygon perspectiveScale() {
        List<Point> newPoints = new ArrayList<>();
        vertices.forEach(p ->
                newPoints.add(new Point(p.x() / -p.z(), p.y() / -p.z(), p.z()))
        );
        return new Polygon(newPoints);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
