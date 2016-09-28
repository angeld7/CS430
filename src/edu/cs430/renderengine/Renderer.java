package edu.cs430.renderengine;

import com.sun.org.apache.regexp.internal.RE;
import edu.cs430.renderengine.shapes.Line;
import edu.cs430.renderengine.shapes.Point;

/**
 * Created by Angel on 9/25/2016.
 */
public class Renderer {

    private boolean[][] pixelMatrix;

    public Renderer(int canvasX, int canvasY) {
        pixelMatrix = new boolean[canvasY][canvasX];
    }

    public void drawLine(Line line) {
        Point start = line.getStartPoint();
        Point end = line.getEndPoint();
        if (start.getX() == end.getX()) {
            //Horizontal line
            int x = start.getX();
            int startY = Math.min(start.getY(), end.getY());
            int endY = Math.max(start.getY(), end.getY());
            for (int y = startY; y <= endY; y++) {
                pixelMatrix[y][x] = true;
            }
        } else if (start.getY() == end.getY()) {
            //Vertical line
            int y = start.getY();
            int startX = Math.min(start.getX(), end.getX());
            int endX = Math.max(start.getX(), end.getX());
            for (int x = startX; x <= endX; x++) {
                pixelMatrix[y][x] = true;
            }
        } else {
            float slope = (end.getY() - start.getY()) / (float) (end.getX() - start.getX());
            int dx = Math.abs(end.getX() - start.getX());
            int dy = Math.abs(end.getY() - start.getY());
            int D = 2 * dy - dx;
            if (slope > 0 && slope <= 1) {
                if (start.getX() > end.getX()) swap(start, end);
                int y = start.getY();
                for (int x = start.getX(); x <= end.getX(); x++) {
                    pixelMatrix[y][x] = true;
                    if ((D = nextD(D, dx, dy)) > 0) y++;
                }
            } else if (slope > 1) {
                if (start.getY() > end.getY()) swap(start, end);
                int x = start.getX();
                for (int y = start.getY(); y <= end.getY(); y++) {
                    pixelMatrix[y][x] = true;
                    if ((D = nextD(D, dy, dx)) > 0) x++;
                }
            } else if (slope < 0 && slope >= -1) {
                if (start.getX() > end.getX()) swap(start, end);
                int y = start.getY();
                for (int x = start.getX(); x <= end.getX(); x++) {
                    pixelMatrix[y][x] = true;
                    if ((D = nextD(D, dx, dy)) > 0) y--;
                }
            } else {
                if (start.getY() > end.getY()) swap(start, end);
                int x = start.getX();
                for (int y = start.getY(); y <= end.getY(); y++) {
                    pixelMatrix[y][x] = true;
                    if ((D = nextD(D, dy, dx)) > 0) x--;
                }
            }
        }
    }

    private int nextD(int D, int n1, int n2) {
        D += D <= 0 ? 2 * n2 : 2 * (n2 - n1);
        return D;
    }

    private void swap(Point s, Point e) {
        int tX = s.getX(), tY = s.getY();
        s.setX(e.getX());
        s.setY(e.getY());
        e.setX(tX);
        e.setY(tY);
    }

    public boolean[][] getPixelMatrix() {
        return pixelMatrix;
    }
}
