package model;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Square {
    private List<Line2D> lines;

    public Square() {
        this.lines = new ArrayList<>();
    }

    public Square(List<Line2D> lines) {
        this.lines = lines;
    }

    public List<Line2D> getLines() {
        return lines;
    }

    public void setLines(List<Line2D> lines) {
        this.lines = lines;
    }

}