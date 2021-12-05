package util;

import cube.Cube;
import matrix.RotationMatrix;
import point.Point;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CubeUtil {

    public static void shift(Cube cube, Point vector) {
        List<Point> cubePoints = cube.getPoints();
        List<Point> shiftedPoints = new ArrayList<>();
        for (Point point : cubePoints) {
            shiftedPoints.add(PointUtil.shift(point, vector));
        }
        cube.setPoints(shiftedPoints);
    }

    public static Cube rotateAboutArbitraryLine(Cube cube, Point p, Point pDash, double phi) {
        cube.setRotationMatrix(rotateAboutArbitraryAxis(p, pDash, phi));
        List<Point> cubePoints = cube.getPoints();
        LinkedList<Point> updatedList = new LinkedList<>();

        for (Point point : cubePoints) {
            updatedList.add(RotationMatrixUtil.multiplyByVertex(cube.getRotationMatrix(), point));
        }

        return new Cube(updatedList);
    }


    private static RotationMatrix rotateAboutArbitraryAxis(Point p, Point pDash, double phi) {
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();

        double a = pDash.getX();
        double b = pDash.getY();
        double c = pDash.getZ();

        double l = Math.sqrt(a * a + b * b + c * c);
        double v = Math.sqrt(b * b + c * c);

        RotationMatrix T = new RotationMatrix(
                new double[] {1, 0, 0, 0},
                new double[] {0, 1, 0, 0},
                new double[] {0, 0, 1, 0},
                new double[] {-x, -y, -z, 1}
        );
        RotationMatrix Rx = new RotationMatrix(
                new double[] {1, 0, 0, 0},
                new double[] {0, c / v, b / v, 0},
                new double[] {0, -(b / v), c / v, 0},
                new double[] {0, 0, 0, 1}
        );
        RotationMatrix Ry = new RotationMatrix(
                new double[] {v / l, 0, -(a / l), 0},
                new double[] {0, 1, 0, 0},
                new double[] {a / l, 0, v / l, 0},
                new double[] {0, 0, 0, 1}
        );
        RotationMatrix Rz = new RotationMatrix(
                new double[] {Math.cos(phi), Math.sin(phi), -(a / l), 0},
                new double[] {-Math.sin(phi), Math.cos(phi), 0, 0},
                new double[] {0, 0, 1, 0},
                new double[] {0, 0, 0, 1}
        );
        RotationMatrix RyMinus = new RotationMatrix(
                new double[] {v / l, 0, a / l, 0},
                new double[] {0, 1, 0, 0},
                new double[] {-(a / l), 0, v / l, 0},
                new double[] {0, 0, 0, 1}
        );
        RotationMatrix RxMinus = new RotationMatrix(
                new double[] {1, 0, 0, 0},
                new double[] {0, c / v, -(b / v), 0},
                new double[] {0, b / v, c / v, 0},
                new double[] {0, 0, 0, 1}
        );
        RotationMatrix TMinus = new RotationMatrix(
                new double[] {1, 0, 0, 0},
                new double[] {0, 1, 0, 0},
                new double[] {0, 0, 1, 0},
                new double[] {x, y, z, 1}
        );

        RotationMatrixUtil.multiply(T, Rx);
        RotationMatrixUtil.multiply(T, Ry);
        RotationMatrixUtil.multiply(T, Rz);
        RotationMatrixUtil.multiply(T, RyMinus);
        RotationMatrixUtil.multiply(T, RxMinus);
        RotationMatrixUtil.multiply(T, TMinus);

        return T;
    }

    public static LinkedList<Point2D.Double> project(Cube cube) {
        List<Point> cubePoints = cube.getPoints();
        LinkedList<Point2D.Double> projectionPoints = new LinkedList<>();
        for (Point point3D : cubePoints) {
            projectionPoints.add(projectPoint(point3D));
        }
        return projectionPoints;
    }

    private static Point2D.Double projectPoint(Point point3D) {
        double x = point3D.getX();
        double y = point3D.getY();
        double z = point3D.getZ();

        return new Point2D.Double(x, y);
    }
}
