package tools.logic.methods;

import tools.logic.Equation;
import tools.logic.Result;

import java.util.Arrays;

public class GaussMethod implements IMethod {
    @Override
    public Result solve(Equation equation, double eps) {
        int n = equation.getMatrix().length;
        double[][] matrix = new double[n][n+1];
        for (int i = 0; i < n; i++) {
            System.arraycopy(equation.getMatrix()[i], 0, matrix[i], 0, n);
        }
        for (int i = 0; i < n; i++) {
            matrix[i][n] = equation.getVector()[i];
        }
        double temp[] = new double[n];
        Arrays.fill(temp, 0);
//        for (int i = 0; i < n; i++) {
//            x[i] = equation.getVector()[i]/matrix[i][i];
//        }

        long iter = 0;
        long startTime = System.currentTimeMillis();
        double tmp;
        int k;

        for (int i = 0; i < n; i++) {
            iter++;
            tmp = matrix[i][i];
            for (int j = n; j >= i; j--) {
                matrix[i][j] /= tmp;
            }
            for (int j = i + 1; j < n; j++) {
                tmp = matrix[j][i];
                for (k = n; k >= i; k--) {
                    matrix[j][k] -= tmp * matrix[i][k];
                }
            }
        }

        temp[n - 1] = matrix[n - 1][n];
        for (int i = n - 2; i >= 0; i--) {
            iter++;
            temp[i] = matrix[i][n];
            for (int j = i + 1; j < n; j++) {
                temp[i] -= matrix[i][j] * temp[j];
            }
        }

        return new Result(true, temp, iter, System.currentTimeMillis() - startTime);
    }
}
