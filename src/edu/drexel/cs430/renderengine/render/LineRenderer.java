package edu.drexel.cs430.renderengine.render;

import edu.drexel.cs430.renderengine.geometry.Line;
import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.render.util.RenderUtils;

/**
 * Created by Angel on 9/25/2016.
 */
public class LineRenderer {

    private boolean[][] pixelMatrix;
    private int height, width;
    private Line left, right, top, bottom;

    public LineRenderer(int canvasX, int canvasY) {
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
    }

    public void drawLine(Line line) {
        if (!clipLine(line)) return;
        Point start = line.getStartPoint();
        Point end = line.getEndPoint();
        if (start.x == end.x) {
            //Horizontal line
            float x = start.x;
            float startY = Math.min(start.y, end.y);
            float endY = Math.max(start.y, end.y);
            for (float y = startY; y <= endY; y++) {
                pixelMatrix[Math.round(y)][Math.round(x)] = true;
            }
        } else if (start.y == end.y) {
            //Vertical line
            float y = start.y;
            float startX = Math.min(start.x, end.x);
            float endX = Math.max(start.x, end.x);
            for (float x = startX; x <= endX; x++) {
                pixelMatrix[Math.round(y)][Math.round(x)] = true;
            }
        } else {
            float slope = (end.y - start.y) / (end.x - start.x);
            float dx = Math.abs(end.x - start.x);
            float dy = Math.abs(end.y - start.y);
            float D = 2 * dy - dx;
            if (slope > 0 && slope <= 1) {
                if (start.x > end.x) swap(start, end);
                float y = start.y;
                for (float x = start.x; x <= end.x; x++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dx, dy)) > 0) y++;
                }
            } else if (slope > 1) {
                if (start.y > end.y) swap(start, end);
                float x = start.x;
                for (float y = start.y; y <= end.y; y++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dy, dx)) > 0) x++;
                }
            } else if (slope < 0 && slope >= -1) {
                if (start.x > end.x) swap(start, end);
                float y = start.y;
                for (float x = start.x; x <= end.x; x++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dx, dy)) > 0) y--;
                }
            } else {
                if (start.y > end.y) swap(start, end);
                float x = start.x;
                for (float y = start.y; y <= end.y; y++) {
                    pixelMatrix[Math.round(y)][Math.round(x)] = true;
                    if ((D = nextD(D, dy, dx)) > 0) x--;
                }
            }
        }
    }

    private float nextD(float D, float n1, float n2) {
        D += D <= 0 ? 2 * n2 : 2 * (n2 - n1);
        return D;
    }

    private void swap(Point s, Point e) {
        float tX = s.x, tY = s.y;
        s.x = e.x;
        s.y = e.y;
        e.x = tX;
        e.y = tY;
    }

    private boolean clipLine(Line line) {
        Point s = line.getStartPoint(), e = line.getEndPoint();
        int b1 = getBitCode(s), b2 = getBitCode(e);
        if ((b1 & b2) != 0) return false;
        for (int b = 1; b <= 8; b *= 2) {
            if (((b1 | b2) & b) == b) clip(line, b1, b);
        }
        return true;
    }

    private void clip(Line line, int b1, int bit) {
        Point p1, p2;
        if ((b1 & bit) == bit) {
            p1 = line.getStartPoint();
            p2 = line.getEndPoint();
        } else {
            p2 = line.getStartPoint();
            p1 = line.getEndPoint();
        }
        Line l = new Line(p1, p2);
        Point i;
        switch (bit) {
            case 1:
                i = RenderUtils.findIntersection(l, left);
                break;
            case 2:
                i = RenderUtils.findIntersection(l, right);
                break;
            case 4:
                i = RenderUtils.findIntersection(l, top);
                break;
            default:
                i = RenderUtils.findIntersection(l, bottom);
                break;
        }
        p1.x = i.x;
        p1.y = i.y;
    }

    private int getBitCode(Point point) {
        int bitCode = 0;
        if (point.x < 0) {
            bitCode |= 1;
        } else if (point.x >= width) {
            bitCode |= 2;
        }
        if (point.y < 0) {
            bitCode |= 8;
        } else if (point.y >= height) {
            bitCode |= 4;
        }
        return bitCode;
    }

    public boolean[][] getPixelMatrix() {
        return pixelMatrix;
    }
}
