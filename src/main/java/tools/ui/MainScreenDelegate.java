package tools.ui;

import com.sun.istack.internal.NotNull;
import tools.logic.LogicComponentsProvider;

public class MainScreenDelegate {

    @NotNull
    private final LogicComponentsProvider logicComponentsProvider;

    public MainScreenDelegate(@NotNull LogicComponentsProvider logicComponentsProvider) {
        this.logicComponentsProvider = logicComponentsProvider;
    }
}
