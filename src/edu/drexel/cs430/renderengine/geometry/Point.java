package edu.drexel.cs430.renderengine.geometry;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Created by Angel on 9/25/2016.
 */
public class Point {
    private float x;
    private float y;
    private float z;
    private boolean is2d;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        z = 1;
        is2d = true;
    }

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        is2d = false;
    }

    public Point(Point p) {
        x = p.x;
        y = p.y;
        z = p.z;
        is2d = p.is2d;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float z() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Float.compare(point.x, x) != 0) return false;
        if (Float.compare(point.y, y) != 0) return false;
        return Float.compare(point.z, z) == 0;

    }

    protected boolean is2d() {
        return is2d;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }

    public Point transform2D(RealMatrix transform) {
        RealMatrix newPoint = transform.multiply(new Array2DRowRealMatrix(new double[]{x, y, 1}));
        double[] arr = newPoint.getColumn(0);
        return new Point((float) arr[0], (float) arr[1], z());
    }

    public Point transform3D(RealMatrix transform) {
        RealMatrix newPoint = transform.multiply(new Array2DRowRealMatrix(new double[]{x, y, z, 1}));
        double[] arr = newPoint.getColumn(0);
        return new Point((float) arr[0], (float) arr[1], (float) arr[2]);
    }
}
