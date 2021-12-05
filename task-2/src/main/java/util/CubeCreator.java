package util;

import cube.Cube;
import point.Point;

public class CubeCreator {

    public static Cube createCube(){
        return new Cube(
                new Point(0,0,0),
                new Point(100, 0, 0),
                new Point(100, 100, 0),
                new Point(0, 100,0),

                new Point(100, 0, 50),
                new Point(0,0,100),
                new Point(50, 0, 100),
                new Point(100, 50, 100),

                new Point(100, 100, 100),
                new Point(0, 100, 100)
        );
    }
}
