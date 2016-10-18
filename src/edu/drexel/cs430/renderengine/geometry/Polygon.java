package edu.drexel.cs430.renderengine.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Angel on 10/17/2016.
 */
public class Polygon implements Iterable<Line> {
    public List<Point> vertices;

    public Polygon() {
        vertices = new ArrayList<>();
    }

    public Polygon(Point... vertices) {
        this.vertices = new ArrayList<>(Arrays.asList(vertices));
    }

    public Polygon(List<Point> vertices) {
        this.vertices = vertices;
    }

    public void addVertex(Point point) {
        vertices.add(point);
    }

    public Point getVertex(int vertexNum) {
        return vertices.get(vertexNum);
    }

    public int getNumSides() {
        return vertices.size() - 1;
    }

    public Line getEdge(int edgeNum) {
        return new Line(getVertex(edgeNum), getVertex(edgeNum + 1));
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
            return side < getNumSides();
        }

        @Override
        public Line next() {
            return getEdge(side++);
        }
    }
}
