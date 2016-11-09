package edu.drexel.cs430.renderengine.geometry;

import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Angel on 10/17/2016.
 */
public class Canvas {
    public final boolean[][] pixelMatrix;
    public final int height, width;
    public final Line left, right, top, bottom;
    public final List<Line> sides;
    public final int xMax, yMax, xMin, yMin;

    public Canvas(int canvasX, int canvasY, int xMax, int yMax, int xMin, int yMin) {
        pixelMatrix = new boolean[canvasY][canvasX];
        height = canvasY;
        width = canvasX;
        this.xMax = xMax;
        this.yMax = yMax;
        this.xMin = xMin;
        this.yMin = yMin;

        left = new Line(
                new Point(xMin, yMin),
                new Point(xMin, yMax)
        );
        top = new Line(
                new Point(xMin, yMax),
                new Point(xMax, yMax)
        );
        right = new Line(
                new Point(xMax, yMax),
                new Point(xMax, yMin)
        );
        bottom = new Line(
                new Point(xMax, yMin),
                new Point(xMin, yMin)
        );
        sides = Arrays.asList(left, top, right, bottom);
    }
}
