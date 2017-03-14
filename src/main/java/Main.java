import com.sun.istack.internal.NotNull;
import tools.logic.LogicComponentsProvider;
import tools.ui.MainScreen;

public class Main {

    public static void main(@NotNull String[] args) {
        LogicComponentsProvider logicComponentsProvider = new LogicComponentsProvider();
        MainScreen mainScreen = new MainScreen(logicComponentsProvider);

        mainScreen.show();
    }
}
