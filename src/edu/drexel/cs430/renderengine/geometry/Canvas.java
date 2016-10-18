package edu.drexel.cs430.renderengine.geometry;

import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Angel on 10/17/2016.
 */
public class Canvas {
    public boolean[][] pixelMatrix;
    public int height, width;
    public Line left, right, top, bottom;
    public List<Line> sides;

    public Canvas(int canvasX, int canvasY) {
        pixelMatrix = new boolean[canvasY][canvasX];
        height = canvasY;
        width = canvasX;
        left = new Line(
                new Point(0, 0),
                new Point(0, height - 1)
        );
        right = new Line(
                new Point(width - 1, height - 1),
                new Point(width - 1, 0)
        );
        top = new Line(
                new Point(0, height - 1),
                new Point(width - 1, height - 1)
        );
        bottom = new Line(
                new Point(width - 1, 0),
                new Point(0, 0)
        );
        sides = Arrays.asList(left, top, right, bottom);
    }
}
