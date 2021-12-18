package form;

import cube.Cube;
import cube.Projection;
import util.CubeCreator;
import util.CubeUtil;
import point.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class Form extends JFrame {
    private final JPanel panel = new JPanel();

    public Form() {
        getContentPane().add(panel);
        setSize(800,500);

    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        //1
        Cube cube = CubeCreator.createCube();
//        Projection first = new Projection(CubeUtil.project(cube));
//        first.paint(graphics);


        //2
//        Point shiftPoint =  new Point(340, 280, 0);
//        CubeUtil.shift(cube, shiftPoint);
//        Projection second = new Projection(CubeUtil.project(cube));
//        second.paint(graphics);


        //3
        Cube rotatedCube = CubeUtil.rotateAboutArbitraryLine(
                cube,
                new Point(0, 0, 0),
                new Point(25, 25, 25),
                Math.toRadians(45)
        );

        Point shiftPoint =  new Point(340, 280, 0);
        CubeUtil.shift(rotatedCube, shiftPoint);
        Projection third = new Projection(CubeUtil.project(rotatedCube));
        third.paint(graphics);
    }
}
