package edu.drexel.cs430.renderengine.util;

import edu.drexel.cs430.renderengine.geometry.Point;

import java.io.*;
import java.util.List;

/**
 * Created by Angel on 11/14/16.
 */
public class SMFReader implements Closeable{
    private BufferedReader br;
    private List<Point> points;
    String line;

    public SMFReader(File file) throws IOException {
        br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null && line.charAt(0) == 'v') {

        }
        this.line = br.readLine();
    }
    @Override
    public void close() throws IOException {

    }
}
