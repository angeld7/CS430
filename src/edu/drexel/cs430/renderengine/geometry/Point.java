package edu.drexel.cs430.renderengine.geometry;

/**
 * Created by Angel on 9/25/2016.
 */
public class Point {
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        x = p.x;
        y = p.y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Float.compare(point.x, x) != 0) return false;
        return Float.compare(point.y, y) == 0;

    }

    public boolean isEqualRounded(Point p) {
        if (this == p) return true;
        if (p == null) return false;
        if (Float.compare(Math.round(p.x), Math.round(x)) != 0) return false;
        return Float.compare(Math.round(p.y), Math.round(y)) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }
}
