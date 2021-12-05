package matrix;

public class RotationMatrix {
    public static final int SIZE = 4;
    private double[][] values;


    public RotationMatrix() {
        this.values = new double[SIZE][SIZE];
    }

    public RotationMatrix(double[]... rows) {
        this.values = new double[SIZE][];
        System.arraycopy(rows, 0, this.values, 0, SIZE);
    }

    public double[][] getValues() {
        return values;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }
}
