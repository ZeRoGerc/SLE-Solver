package tools.logic.methods;

import static tools.Constants.MATRIX_SIZE;

public class Utils {

    private double[] multply(double[][] matrix, double[] vector) {
        checkMatrix(matrix);
        checkVector(vector);

        double[] result = new double[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result[i] = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    private double[] multply(double[] vector, double[][] matrix) {
        checkVector(vector);
        checkMatrix(matrix);

        double[] result = new double[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result[i] = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                result[i] += matrix[j][i] * vector[j];
            }
        }

        return result;
    }

    private double scalar(double[] x, double[] y) {
        checkVector(x);
        checkVector(y);

        double result = 0.;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result += x[i] * y[i];
        }

        return result;
    }

    private void checkVector(double[] vector) {
        assert vector.length == MATRIX_SIZE;
    }

    private void checkMatrix(double[][] matrix) {
        assert matrix.length == MATRIX_SIZE;
        for (int i = 0; i < matrix.length; i++) {
            checkVector(matrix[i]);
        }
    }
}
