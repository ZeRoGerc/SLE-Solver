package tools.logic;

import com.sun.istack.internal.NotNull;

import java.util.Random;

import static tools.ui.UIConstants.MATRIX_SIZE;

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
}
