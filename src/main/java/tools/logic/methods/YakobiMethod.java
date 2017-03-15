package tools.logic.methods;

import com.sun.istack.internal.NotNull;
import tools.logic.Equation;
import tools.logic.Result;

import java.util.Arrays;

public class YakobiMethod implements IMethod {

    @Override
    @NotNull
    public Result solve(@NotNull Equation equation, double eps) {
        int n = equation.getMatrix().length;
        double[][] matrix = equation.getMatrix();
        double[] temp = new double[n];
        double norm;
        double[] x = new double[n];
        Arrays.fill(x, 0);

        long iter = 0;
        long startTime = System.currentTimeMillis();
        do {
            for (int i = 0; i < n; i++) {
                temp[i] = equation.getVector()[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        temp[i] -= matrix[i][j] * x[j];
                    }
                }
                temp[i] /= matrix[i][i];
            }
            norm = Math.abs(x[0] - temp[0]);
            for (int h = 0; h < n; h++) {
                if (Math.abs(x[h] - temp[h]) > norm) {
                    norm = Math.abs(x[h] - temp[h]);
                }
                x[h] = temp[h];
            }
            iter++;
            if (iter > 1_000_000) {
                return new Result(matrix, false, x, iter, System.currentTimeMillis() - startTime, "Метод Якоби");
            }
        } while (norm > eps);

        return new Result(matrix, true, x, iter, System.currentTimeMillis() - startTime, "Метод Якоби");
    }
}
