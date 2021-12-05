package form;

import cube.Cube;
import cube.Projection;
import util.CubeCreator;
import util.CubeUtil;
import point.Point;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    private final JPanel panel = new JPanel();

    public Form() {
        getContentPane().add(panel);
        setSize(800,500);

    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        Cube cube = CubeCreator.createCube();

        Cube rotatedCube = CubeUtil.rotateAboutArbitraryLine(
                cube,
                new Point(0, 0, 0),
                new Point(25, 30, 35),
                Math.toRadians(45)
        );

        Point shiftPoint =  new Point(340, 280, 0);
        CubeUtil.shift(rotatedCube, shiftPoint);

        Projection rotatedCubeProjection = new Projection(CubeUtil.project(cube));
        rotatedCubeProjection.paint(graphics);

        Projection ShiftedRotatedCubeProjection = new Projection(CubeUtil.project(rotatedCube));
        ShiftedRotatedCubeProjection.paint(graphics);
    }
}
