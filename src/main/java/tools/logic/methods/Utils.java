package tools.logic.methods;

import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.Pair;

import java.util.function.Function;

import static com.sun.tools.javac.util.Pair.of;
import static java.lang.Math.pow;
import static tools.Constants.MATRIX_SIZE;

public class Utils {

    public static double[] plus(double[] x, double[] y) {
        checkVector(y);
        return operation(x, pair -> pair.snd + y[pair.fst]);
    }

    public static double[] minus(double[] x, double[] y) {
        checkVector(y);
        return operation(x, pair -> pair.snd - y[pair.fst]);
    }

    public static double[] multiply(double k, double[] x) {
        return operation(x, pair -> k * pair.snd);
    }

    private static double[] operation(double[] x, @NotNull Function<Pair<Integer, Double>, Double> operation) {
        checkVector(x);

        double[] result = new double[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result[i] = operation.apply(of(i, x[i]));
        }

        return result;
    }

    public static double[] multiply(double[][] matrix, double[] vector) {
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

    public static double[] multiply(double[] vector, double[][] matrix) {
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

    public static double norm(double[] x) {
        checkVector(x);

        //noinspection SuspiciousNameCombination
        return Math.sqrt(scalar(x, x));
    }

    public static double matrixNorm(double[][] matrix) {
        double max = Double.MIN_VALUE;

        for (int i = 0; i < MATRIX_SIZE; i++) {
            double cur = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                cur = matrix[i][j];
            }
            max = Math.max(max, cur);
        }
        System.out.println("norm " + max);
        return max;
    }

    public static double scalar(double[] x, double[] y) {
        checkVector(x);
        checkVector(y);

        double result = 0.;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result += x[i] * y[i];
        }

        return result;
    }

    public static double getDeterminant(double[][] matrix, int n) {
        double temp = 0;
        int k = 1;

        if (n == 1)
            temp = matrix[0][0];
        else if (n == 2)
            temp = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        else {
            for (int i = 0; i < n; i++) {
                int m = n - 1;
                double tempMatrix[][] = new double[m][m];
                getChangedMatrix(matrix, n, tempMatrix, 0, i);
                temp = temp + k * matrix[0][i] * getDeterminant(tempMatrix, m);
                k = -k;
            }
        }
        return temp;
    }

    public static double matrixCond(double[][] matrix) throws IllegalArgumentException {
        double det = getDeterminant(matrix, MATRIX_SIZE);
        System.out.println(det);
        double[][] obrMatrix = new double[MATRIX_SIZE][MATRIX_SIZE];
        if (det == 0) {
            throw new IllegalArgumentException("Determinant is 0!");
        }
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                int m = MATRIX_SIZE - 1;
                double tempMatrix[][] = new double[m][m];
                getChangedMatrix(matrix, MATRIX_SIZE, tempMatrix, i, j);
                obrMatrix[i][j] = pow(-1.0, i + j + 2) * getDeterminant(tempMatrix, m) / det;
            }
        }

        double[][] transp = new double[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++)
            for (int j = 0; j < MATRIX_SIZE; j++)
                transp[j][i] = obrMatrix[i][j];
        return matrixNorm(transp) * matrixNorm(matrix);
    }

    public static void checkVector(double[] vector) {
        assert vector.length == MATRIX_SIZE;
    }

    public static void checkMatrix(double[][] matrix) {
        assert matrix.length == MATRIX_SIZE;
        for (int i = 0; i < matrix.length; i++) {
            checkVector(matrix[i]);
        }
    }

    private static void getChangedMatrix(double[][] matrix, int n, double[][] tempMatrix, int indRow, int indCol) {
        int ki = 0;
        for (int i = 0; i < n; i++) {
            if (i != indRow) {
                for (int j = 0, kj = 0; j < n; j++) {
                    if (j != indCol) {
                        tempMatrix[ki][kj] = matrix[i][j];
                        kj++;
                    }
                }
                ki++;
            }
        }
    }
}
