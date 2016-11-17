package edu.drexel.cs430.renderengine.util;

import edu.drexel.cs430.renderengine.geometry.Point;
import edu.drexel.cs430.renderengine.geometry.Polygon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 11/14/16.
 */
public class SMFReader implements Closeable {
    private BufferedReader br;
    private List<Point> points = new ArrayList<>();
    String line;

    public SMFReader(File file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = getLineArr(line);
            if (arr.length > 3 && "v".equals(arr[0])) {
                points.add(new Point(
                        Float.valueOf(arr[1]),
                        Float.valueOf(arr[2]),
                        Float.valueOf(arr[3]))
                );
            } else if ("f".equals(arr[0])) {
                break;
            }
        }
        this.line = line;
    }

    private String[] getLineArr(String line) {
        return line.trim().replaceAll("\\s+", " ").split(" ");
    }

    public Polygon nextPolygon() throws IOException {
        Polygon polygon = null;
        if (line != null) {
            String[] arr = getLineArr(line);
            if (arr.length > 3 && "f".equals(arr[0])) {
                polygon = new Polygon();
                for (int i = 1; i < arr.length; i++) {
                    polygon.addVertex(points.get(Integer.valueOf(arr[i]) - 1));
                }
            }
        }
        line = br.readLine();
        return polygon;
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}
