package util;

import model.Square;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SquareUtil {

    public static void paint(Square square, Graphics graphics){
        List<Line2D> lines = square.getLines();
        for(Line2D line : lines){
            ((Graphics2D) graphics).draw(line);
        }
    }


    public static void parallelMove(Square square, double dx, double dy){
        List<Line2D> lines = square.getLines();
        List<Line2D> newLines = new ArrayList<>();

        for (Line2D line : lines) {
            Point2D start = line.getP1();
            Point2D end = line.getP2();
            double startX = start.getX() + dx;
            double startY = start.getY() + dy;
            double endX = end.getX() + dx;
            double endY = end.getY() + dy;
            newLines.add(new Line2D.Double(startX, startY, endX, endY));
        }

        square.setLines(newLines);
    }


    public static void rotate(Square square, Point2D point, double angle){
        List<Line2D> lines = square.getLines();
        List<Line2D> newLines = new ArrayList<>();

        for (Line2D line : lines) {
            Point2D start = line.getP1();
            Point2D end = line.getP2();
            double newX = point.getX() + (start.getX() - point.getX()) * Math.cos(angle) - (start.getY() - point.getY()) * Math.sin(angle);
            double newY = point.getY() + (start.getY() - point.getY()) * Math.cos(angle) + (start.getX() - point.getX()) * Math.sin(angle);
            double newX2 = point.getX() + (end.getX() - point.getX()) * Math.cos(angle) - (end.getY() - point.getY()) * Math.sin(angle);
            double newY2 = point.getY() + (end.getY() - point.getY()) * Math.cos(angle) + (end.getX() - point.getX()) * Math.sin(angle);
            newLines.add(new Line2D.Double(newX, newY, newX2, newY2));
        }

        square.setLines(newLines);
    }

}
