package tools.logic;

public class Equation {

    private double matrix[][];

    private double vector[];

    public Equation(double[][] matrix, double[] vector) {
        this.matrix = matrix;
        this.vector = vector;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getVector() {
        return vector;
    }
}