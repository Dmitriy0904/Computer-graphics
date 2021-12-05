package util;

import matrix.RotationMatrix;
import point.Point;

import static matrix.RotationMatrix.SIZE;

public class RotationMatrixUtil {

    public static void multiply(RotationMatrix left, RotationMatrix right) {
        for (int row = 0; row < left.getValues().length; row++) {
            double[] resultRow = new double[SIZE];

            for (int i = 0; i < left.getValues().length; i++) {
                double[] b = new double[SIZE];

                for (int j = 0; j < left.getValues()[i].length; j++) {
                    b[j] = right.getValues()[j][i];
                }
                resultRow[i] = getMultipliedValue(left.getValues()[row], b);
            }

            left.getValues()[row] = resultRow;
        }
    }


    public static Point multiplyByVertex(RotationMatrix matrix, Point point) {
        double[] rotatedPoint = new double[SIZE];
        for (int i = 0; i < matrix.getValues().length; i++) {
            rotatedPoint[i] = getMultipliedValue(
                    matrix.getValues()[i],
                    new double[] { point.getX(), point.getY(), point.getZ(), point.getF() }
            );
        }
        return new Point(rotatedPoint[0], rotatedPoint[1], rotatedPoint[2]);
    }



    private static double getMultipliedValue(double[] a, double[] b) {
        double result = 0.0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }
}
