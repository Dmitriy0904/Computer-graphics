package cube;

import matrix.RotationMatrix;
import point.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cube {

    private List<Point> points;
    private RotationMatrix rotationMatrix;

    public Cube(List<Point> list) {
        this.points = list;
        this.rotationMatrix = new RotationMatrix();
    }

    public Cube(Point... points) {
        this.points = new ArrayList<>();
        this.points.addAll(Arrays.asList(points));
    }

    public List<Point> getPoints() {
        return points;
    }

    public RotationMatrix getRotationMatrix() {
        return rotationMatrix;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void setRotationMatrix(RotationMatrix rotationMatrix) {
        this.rotationMatrix = rotationMatrix;
    }
}