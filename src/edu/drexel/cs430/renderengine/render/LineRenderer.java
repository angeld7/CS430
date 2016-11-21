package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.geometry.Canvas;
import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;

import java.awt.*;

/**
 * Created by Angel on 9/25/2016.
 */
class LineRenderer {

    Canvas canvas;

    LineRenderer(Canvas canvas) {
        this.canvas = canvas;
    }

    void render(Line line) {
        Point start = line.start();
        Point end = line.end();
        if (start.x() == end.x()) {
            //Horizontal line
            float x = start.x();
            float startY = Math.min(start.y(), end.y());
            float endY = Math.max(start.y(), end.y());
            for (float y = startY; y <= endY; y++) {
                setPixel(start, end, x, y, line.getColor());
            }
        } else if (start.y() == end.y()) {
            //Vertical line
            float y = start.y();
            float startX = Math.min(start.x(), end.x());
            float endX = Math.max(start.x(), end.x());
            for (float x = startX; x <= endX; x++) {
                setPixel(start, end, x, y, line.getColor());
            }
        } else {
            float slope = (end.y() - start.y()) / (end.x() - start.x());
            float dx = Math.abs(end.x() - start.x());
            float dy = Math.abs(end.y() - start.y());
            float D = 2 * dy - dx;
            if (slope > 0 && slope <= 1) {
                if (start.x() > end.x()) {
                    start = line.end();
                    end = line.start();
                }
                float y = start.y();
                for (float x = start.x(); x <= end.x(); x++) {
                    setPixel(start, end, x, y, line.getColor());
                    if ((D = nextD(D, dx, dy)) > 0) y = Math.min(y + 1, end.y());
                }
            } else if (slope > 1) {
                if (start.y() > end.y()) {
                    start = line.end();
                    end = line.start();
                }
                float x = start.x();
                for (float y = start.y(); y <= end.y(); y++) {
                    setPixel(start, end, x, y, line.getColor());
                    if ((D = nextD(D, dy, dx)) > 0) x = Math.min(x + 1, end.x());
                }
            } else if (slope < 0 && slope >= -1) {
                if (start.x() > end.x()) {
                    start = line.end();
                    end = line.start();
                }
                float y = start.y();
                for (float x = start.x(); x <= end.x(); x++) {
                    setPixel(start, end, x, y, line.getColor());
                    if ((D = nextD(D, dx, dy)) > 0) y = Math.max(y - 1, end.y());
                }
            } else {
                if (start.y() > end.y()) {
                    start = line.end();
                    end = line.start();
                }
                float x = start.x();
                for (float y = start.y(); y <= end.y(); y++) {
                    setPixel(start, end, x, y, line.getColor());
                    if ((D = nextD(D, dy, dx)) > 0) x = Math.max(x - 1, end.x());
                }
            }
        }
    }

    public void setPixel(Point start, Point end, float xf, float yf, Color color) {
        int x = Math.round(xf);
        int y = Math.round(yf);
        float z = findZ(start, end, xf, yf);
        if (!canvas.pixelMatrix[y][x] || canvas.pixelZ[x][y] < z) {
            canvas.pixelMatrix[y][x] = true;
            canvas.pixelColors[y][x] = color;
        }
    }

    private float findZ(Point start, Point end, float xf, float yf) {
        float a1, a2, s;
//        if (start.y() == end.y()) {
//            a1 = start.x();
//            a2 = end.x();
//            s = xf;
//
//        } else {
            a1 = start.y();
            a2 = end.y();
            s = yf;
//        }
        return start.z() - (start.z() - end.z()) * ((a1 - s) / (a1 - a2));
    }

    private float nextD(float D, float n1, float n2) {
        D += D <= 0 ? 2 * n2 : 2 * (n2 - n1);
        return D;
    }
}
