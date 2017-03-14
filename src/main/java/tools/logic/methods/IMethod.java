package tools.logic.methods;

import com.sun.istack.internal.NotNull;
import tools.logic.Equation;
import tools.logic.Result;

public interface IMethod {

    @NotNull
    Result solve(@NotNull Equation equation, double eps);

}


