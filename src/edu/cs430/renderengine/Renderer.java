package edu.cs430.renderengine;

import com.sun.org.apache.regexp.internal.RE;
import edu.cs430.renderengine.shapes.Line;
import edu.cs430.renderengine.shapes.Point;

/**
 * Created by Angel on 9/25/2016.
 */
public class Renderer {

    boolean[][] pixelMatrix;
    int width;
    int height;

    public Renderer(int canvasX, int canvasY) {
        pixelMatrix = new boolean[canvasY][canvasX];
        width = canvasX;
        height = canvasY;
    }

    public void drawLine(Line line) {
        Point start = Point.translateY(line.getStartPoint(), height);
        Point end = Point.translateY(line.getEndPoint(), height);
        if(start.getX() == end.getX()) {
            //Horizontal line
            int x = start.getX();
            int startY = Math.min(start.getY(), end.getY());
            int endY = Math.max(start.getY(), end.getY());
            for(int y = startY; y <= endY; y++) {
                pixelMatrix[y][x] = true;
            }
        } else if(start.getY() == end.getY()) {
            //Vertical line
            int y = start.getY();
            int startX = Math.min(start.getX(), end.getX());
            int endX = Math.max(start.getX(), end.getX());
            for(int x = startX; x <= endX; x++) {
                pixelMatrix[y][x] = true;
            }
        } else {
            float slope =  (end.getY() - start.getY()) / (float)(end.getX() - start.getX());
            if(slope < 0 && slope >= -1) {
                int dx = end.getX() - start.getX();
                int dy = end.getY() - start.getY();
                int D = 2 * dy - dx;
                int y = start.getY();
                for(int x = start.getX(); x <= end.getX(); x++) {
                    pixelMatrix[y][x] = true;
                    if(D <= 0) D += 2*dy;
                    else {
                        D += 2*(dy - dx);
                        y++;
                    }
                }
            }
        }
    }

    public boolean[][] getPixelMatrix() {
        return pixelMatrix;
    }
}
