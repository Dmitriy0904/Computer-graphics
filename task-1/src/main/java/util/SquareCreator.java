package util;

import model.Square;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class SquareCreator {

    public static Square newSquare(){
        List<Line2D> lines = new ArrayList<>();

        lines.add(new Line2D.Float(400, 150, 450, 200));
        lines.add(new Line2D.Float(450, 200,450, 250));
        lines.add(new Line2D.Float(450, 250, 350, 250));
        lines.add(new Line2D.Float(350, 150, 350, 250));
        lines.add(new Line2D.Float(350, 150, 400, 150));

        return new Square(lines);
    }
}
