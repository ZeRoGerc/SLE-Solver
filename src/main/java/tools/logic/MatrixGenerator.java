package tools.logic;

import com.sun.istack.internal.NotNull;

import java.util.Random;

import static tools.Constants.MATRIX_SIZE;

public class MatrixGenerator {

    @NotNull
    private final Random random = new Random();

    @NotNull
    public double[][] generateRandomMatrix() {
        double[][] matrix = new double[MATRIX_SIZE][MATRIX_SIZE + 1];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE + 1; j++) {
                matrix[i][j] = random.nextGaussian() * 100;
            }
        }

        return matrix;
    }

    @NotNull
    public double[][] generateHilbertMatrix() {
        double[][] matrix = new double[MATRIX_SIZE][MATRIX_SIZE + 1];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = 1d / (i + j - 1);
            }
        }
        for (int j = 0; j < MATRIX_SIZE; j++) {
            matrix[j][MATRIX_SIZE] = random.nextGaussian() * 100;
        }

        return matrix;
    }

    @NotNull
    public double[][] generateDiagonalPrevailingMatrix() {
        double[][] matrix = new double[MATRIX_SIZE][MATRIX_SIZE + 1];
        int n = 10;

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE+1; j++) {
                matrix[i][j] = random.nextGaussian() * n;
            }
        }
        for (int j = 0; j < MATRIX_SIZE; j++) {
            matrix[j][j] = MATRIX_SIZE * MATRIX_SIZE * n + Math.abs(random.nextGaussian() * n * 5);
        }

        return matrix;
    }
}
