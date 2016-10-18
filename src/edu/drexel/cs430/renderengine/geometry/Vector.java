package edu.drexel.cs430.renderengine.geometry;

/**
 * Created by Angel on 10/17/16.
 */
public class Vector extends Point {

    public Vector(Point p) {
        super(p.x(), p.y());
    }

    public Vector(float x, float y) {
        super(x, y);
    }

    public static Vector subtract(Point p1, Point p2) {
        return new Vector(p1.x() - p2.x(), p1.y() - p2.y());
    }

    public Vector getNormal() {
        return new Vector(y(), x() == 0 ? 0 : -x());
    }

    public float magnitude() {
        return (float) Math.sqrt(Math.pow(x(), 2) + Math.pow(y(), 2));
    }

    public static float dotProduct(Point v1, Point v2) {
        return (v1.x() * v2.x()) + (v1.y() * v2.y());
    }


    public Vector normalize() {
        float mag = magnitude();
        return new Vector(x() / mag, y() / mag);
    }
}
