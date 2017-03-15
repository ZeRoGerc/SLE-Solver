package tools.logic.methods;

import com.sun.istack.internal.NotNull;
import com.sun.tools.javac.util.Pair;

import java.util.function.Function;

import static com.sun.tools.javac.util.Pair.of;
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

    public static double scalar(double[] x, double[] y) {
        checkVector(x);
        checkVector(y);

        double result = 0.;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            result += x[i] * y[i];
        }

        return result;
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
}
