package tools.logic.methods;

import com.sun.istack.internal.NotNull;
import tools.logic.Equation;
import tools.logic.Result;

import java.util.Arrays;

import static java.util.Arrays.copyOf;
import static tools.Constants.MATRIX_SIZE;
import static tools.logic.methods.Utils.*;

public class DescentMethod implements IMethod {

    @NotNull
    private Equation equation;

    private double[] x;

    private double[] r;

    private double[] z;

    @Override
    public Result solve(@NotNull Equation equation, double eps) {
        this.equation = equation;
        long startTime = System.currentTimeMillis();
        long iterations = 0;

        x = pickInitial();
        r = minus(equation.getVector(), multiply(equation.getMatrix(), x));
        z = copyOf(r, MATRIX_SIZE);


        while (iterations < 1e6) {
            iterations++;
            iterate();

            if (resultIsOK(eps)) {
                break;
            }
        }

        long time = System.currentTimeMillis() - startTime;

        if (resultIsOK(eps)) {
            return new Result(equation.getMatrix(), true, x, iterations, time, "Descent method");
        } else {
            return new Result(equation.getMatrix(), false, x, iterations, time,  "Descent method");
        }
    }

    private void iterate() {
        double a = scalar(r, r) / scalar(multiply(equation.getMatrix(), z), z);

        double[] newX = plus(x, multiply(a, z));
        double[] newR = minus(r, multiply(a, multiply(equation.getMatrix(), z)));

        double b = scalar(newR, newR) / scalar(r, r);

        double[] newZ = plus(newR, multiply(b, z));

        x = newX;
        r = newR;
        z = newZ;
    }

    private boolean resultIsOK(double eps) {
        double k = Utils.norm(r) / Utils.norm(equation.getVector());
        return k < eps;
    }

    private double[] pickInitial() {
        double[] result = new double[MATRIX_SIZE];
        Arrays.fill(result, 0.);
        return result;
    }
}
