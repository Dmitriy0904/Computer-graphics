package form;

import model.Square;
import util.SquareCreator;
import util.SquareUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;


public class Form extends JFrame {
    private JPanel panel = new JPanel();

    public Form() {
        getContentPane().add(panel);
        setSize(800,500);
    }

    @Override
    public void paint(Graphics graphics) {

        Square square = SquareCreator.newSquare();
        SquareUtil.paint(square, graphics);

        SquareUtil.parallelMove(square, 100, 100);
        SquareUtil.paint(square, graphics);

        SquareUtil.rotate(square, new Point2D.Double(570, 300), 60);
        SquareUtil.paint(square, graphics);

    }
}
