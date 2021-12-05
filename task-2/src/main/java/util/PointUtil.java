package util;

import point.Point;

public class PointUtil {

    public static Point shift(Point point, Point vector) {

        return new Point(
                point.getX() + vector.getX(),
                point.getY() + vector.getY(),
                point.getZ() + vector.getZ()
        );
    }


}
