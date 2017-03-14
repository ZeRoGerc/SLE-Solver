package tools.ui;

import com.sun.istack.internal.NotNull;
import tools.logic.Equation;
import tools.logic.Result;
import tools.logic.methods.YakobiMethod;

public class MainScreenDelegate {

    private static final double DEFAULT_EPS = 1e-3;

    @NotNull
    public Result solveWithYakobi(@NotNull Equation equation) {
        YakobiMethod method = new YakobiMethod();
        return method.solve(equation, DEFAULT_EPS);
    }

}
